package com.allegiant.customer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;

import com.allegiant.search.PredicateType;
import com.allegiant.search.SearchException;
import com.allegiant.search.SearchPredicate;
import com.allegiant.search.SearchRequest;
import com.allegiant.search.SearchResponse;


@Service
public class CustomerService {

	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("allegiant");

	public List<Customer> readAll(){
		EntityManager em = emf.createEntityManager();
		Query query = em.createNamedQuery("Customer.findAll");		
		@SuppressWarnings("unchecked")
		List<Customer> results = query.getResultList();
		return results;
	}
	public Customer read(Integer id) {	
		EntityManager em = emf.createEntityManager();		
		Customer customer = em.find(Customer.class, id);		
		return customer;
	}
	

	public Customer getByLongitude(String lng){
		BigDecimal bd = new BigDecimal(lng);
		
		EntityManager em = emf.createEntityManager();
		CriteriaBuilder queryBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Customer> cq = queryBuilder.createQuery(Customer.class);
		Root<Customer> root = cq.from(Customer.class);
		
		Expression<Number> lngExpr = root.get("longitude");
		Expression<BigDecimal> lngExpr2 = queryBuilder.toBigDecimal(lngExpr);
		Predicate lngPred = queryBuilder.equal(lngExpr2, bd);
		cq.where(queryBuilder.and(lngPred));
		TypedQuery<Customer> compoundQuery = em.createQuery(cq);
		List<Customer> results = compoundQuery.getResultList();		
		if (results.size() > 0){
			return results.get(0);
		}else{
			return null;
		}

	}
	/**
	 * Transforms the SearchRequest POJO from the user/browser into a series of database predicates, which are then sent to the database to retrieve the search results. 
	 * 
	 */
	public SearchResponse<Customer> search (SearchRequest request) throws Exception{
		EntityManager em = emf.createEntityManager();
		Query query = em.createNamedQuery("Customer.findAll");
		List<Customer> allCustomers = query.getResultList();
		int totalCustomers = allCustomers.size();
		Map<String,String> parameterMap = new Hashtable<String,String>();
		CriteriaBuilder queryBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Customer> cq = queryBuilder.createQuery(Customer.class);
		
		Root<Customer> root = cq.from(Customer.class);
		//Predicate compoundPredicate = new CompoundPredicate(queryBuilder, BooleanOperator.AND);
		List<Predicate> criteria = new ArrayList<Predicate>(request.getPredicates().size());
		for (int index=0; index < request.getPredicates().size(); index++){
			SearchPredicate uiPredicate = request.getPredicates().get(index);
			Predicate dbPredicate = null;
			switch (uiPredicate.getOperator()){
				case BEGINSWITH: 
					if (uiPredicate.getType() == PredicateType.STRING){						
						dbPredicate = queryBuilder.like(root.<String>get(uiPredicate.getName()),  uiPredicate.getValue() + "%");
						/*
						Expression<String> columnExpression = root.<String>get(uiPredicate.getName());
						Expression<String> inputExpression = queryBuilder.parameter(String.class, "val" + index);
						//store the name of the parameter and it's actual query-time value, becasue we can't set parameters now, have to wait until all the predicates are built.
						parameterMap.put("val" + index, uiPredicate.getValue());
						Expression<Integer> containsExpression = queryBuilder.function("INSTR", Integer.class, columnExpression, inputExpression);						
						dbPredicate = queryBuilder.equal(containsExpression, 1);
						*/						
						
					}else{
						throw new SearchException("Only String types can have a begins-with operator");
					}
					break;
				case CONTAINS: 
					if (uiPredicate.getType() == PredicateType.STRING){
						dbPredicate = queryBuilder.like(root.<String>get(uiPredicate.getName()), "%" + uiPredicate.getValue() + "%");
						/*
						Expression<String> columnExpression = root.<String>get(uiPredicate.getName());
						Expression<String> inputExpression = queryBuilder.parameter(String.class, "val" + index);
						//store the name of the parameter and it's actual query-time value, becasue we can't set parameters now, have to wait until all the predicates are built.
						parameterMap.put("val" + index, uiPredicate.getValue());
						Expression<Integer> containsExpression = queryBuilder.function("INSTR", Integer.class, columnExpression, inputExpression);						
						dbPredicate = queryBuilder.greaterThan(containsExpression, 0);						
						*/
					}else{
						throw new SearchException("Only String types can have a contains operator");
					}
					break;
				case EQUAL: 
					if (uiPredicate.getType() == PredicateType.DATE){
						try{
							Date predicateDate = new Date(Long.parseLong(uiPredicate.getValue()));
							dbPredicate = queryBuilder.equal(root.<Date>get(uiPredicate.getName()), predicateDate);
						}catch (NumberFormatException nfe){
							throw new SearchException("Date Field for " + uiPredicate.getName() + " is in the wrong format: " + uiPredicate.getValue());
						}
					}else if (uiPredicate.getType() == PredicateType.STRING){
						dbPredicate = queryBuilder.equal(root.<String>get(uiPredicate.getName()), uiPredicate.getValue());						
					}else{
						BigDecimal bd = new BigDecimal(uiPredicate.getValue());
						Expression<Number> expr1 = root.get(uiPredicate.getName());
						Expression<BigDecimal> expr2 = queryBuilder.toBigDecimal(expr1);
						dbPredicate = queryBuilder.equal(expr2, bd); //TODO: are there missing scenarios here?						
					}
					break;
				case GREATERTHAN:
					if (uiPredicate.getType() == PredicateType.DATE){
						try{
							Date predicateDate = new Date(Long.parseLong(uiPredicate.getValue()));
							dbPredicate = queryBuilder.greaterThanOrEqualTo(root.<Date>get(uiPredicate.getName()), predicateDate);
						}catch (NumberFormatException nfe){
							throw new SearchException("Date Field for " + uiPredicate.getName() + " is in the wrong format: " + uiPredicate.getValue());
						}						
					}else if (uiPredicate.getType() == PredicateType.STRING){
						dbPredicate = queryBuilder.greaterThanOrEqualTo(root.<String>get(uiPredicate.getName()), uiPredicate.getValue());						
					}else{
						//TODO: This parameterization means that we can't use floating point numbers, we may need to add another type or something
						dbPredicate = queryBuilder.greaterThanOrEqualTo(root.<Float>get(uiPredicate.getName()), Float.parseFloat(uiPredicate.getValue()));						
					}
					break;
				case LESSTHAN:			
					if (uiPredicate.getType() == PredicateType.DATE){
						try{
							Date predicateDate = new Date(Long.parseLong(uiPredicate.getValue()));
							dbPredicate = queryBuilder.lessThanOrEqualTo(root.<Date>get(uiPredicate.getName()), predicateDate);
						}catch (NumberFormatException nfe){
							throw new SearchException("Date Field for " + uiPredicate.getName() + " is in the wrong format: " + uiPredicate.getValue());
						}
					}else if (uiPredicate.getType() == PredicateType.STRING){
						dbPredicate = queryBuilder.lessThanOrEqualTo(root.<String>get(uiPredicate.getName()), uiPredicate.getValue());
					}else{
						dbPredicate = queryBuilder.lessThanOrEqualTo(root.<Float>get(uiPredicate.getName()), Float.parseFloat(uiPredicate.getValue()));
						
					}
					break;
					
				default:
					throw new SearchException("Unknown operator type: "  + uiPredicate.getOperator());
			}
			criteria.add(dbPredicate);			
		}	
		
		
		cq.where(criteria.toArray(new Predicate[criteria.size()]));
		
				
		TypedQuery<Customer> compoundQuery = em.createQuery(cq);
		System.out.println("----");
		
		for (String key : parameterMap.keySet()){			
			String val = parameterMap.get(key);
			System.out.println(key + "-->" + val);
			compoundQuery.setParameter(key, val);
			
		}
		List<Customer> results = compoundQuery.getResultList();		
		SearchResponse<Customer> response = new SearchResponse<Customer>(results,5); //TODO: pagination
		return response;
	}
	
	public Customer create(Customer customer) {
		EntityManager em = emf.createEntityManager();
		if (customer.getCreatedAt() == null){
			Date now = new Date();
			customer.setCreatedAt(now);
		}
		em.getTransaction().begin();		
		em.persist(customer);
		em.flush();
		em.getTransaction().commit();		
		return customer;
	}
	
	public Customer update(Customer customer) {
		EntityManager em = emf.createEntityManager();
		Customer base = em.find(Customer.class, customer.getId());
		if (base == null) {
			//TODO: 404
			throw new IllegalArgumentException("Can't find customer with id " + customer.getId());
		}else {			
			em.getTransaction().begin();
			base.setEmail(customer.getEmail());
			base.setFirstName(customer.getFirstName());
			base.setIp(customer.getIp());
			base.setLastName(customer.getLastName());
			base.setLatitude(customer.getLatitude());
			base.setLongitude(customer.getLongitude());
			Date now = new Date();
			base.setUpdatedAt(now);
			
			
			em.persist(base);			
			em.flush();
			Customer result = em.find(Customer.class, base.getId()); 
			em.getTransaction().commit();
			return result;
		}
	}	
	
	
	public boolean delete(Integer id) {
		EntityManager em = emf.createEntityManager();
		Customer deleteMe = em.find(Customer.class, id);
		if (deleteMe == null) {
			throw new IllegalArgumentException("Can't find customer with id " + id);			
		}else {
			em.getTransaction().begin();
			em.remove(deleteMe);
			em.flush();
			em.getTransaction().commit();
		}
		return true;
	}

}
package com.allegiant.search;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.allegiant.customer.Customer;
import com.allegiant.customer.CustomerFileReader;
import com.allegiant.customer.CustomerService;

import junit.framework.TestCase;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations={"classpath:root-context.xml"})
public class SearchTest extends TestCase{

	@Autowired
	CustomerService service;
	
	@Test
	public void testGetByLongitude() throws Exception{		
		Customer result = service.getByLongitude("176.849182");
		assertNotNull(result);
		
	}
	//these tests are intended to be run after loading both data1.csv and data2.csv in the customerFileReaderTest test
	@Test
	public void testGreaterThanNumber() throws Exception{
		SearchRequest request = new SearchRequest();
		List<SearchPredicate> sps = new ArrayList<SearchPredicate>();
		request.setPredicates(sps);
		SearchPredicate greaterSp = new SearchPredicate();
		greaterSp.setName("longitude");
		greaterSp.setOperator(PredicateOperator.GREATERTHAN);
		greaterSp.setType(PredicateType.NUMBER);
		greaterSp.setValue("174");
		sps.add(greaterSp);
		
		SearchResponse<Customer> response = service.search(request);
		assertNotNull(response);
		assertEquals(3, response.getResults().size());
		
	}
	

	
	@Test
	public void testEqualsNumber() throws Exception {
		SearchRequest request = new SearchRequest();
		List<SearchPredicate> sps = new ArrayList<SearchPredicate>();
		request.setPredicates(sps);
		request.setPageNumber(1);
		SearchPredicate equalSp = new SearchPredicate();
		equalSp.setName("longitude");
		equalSp.setOperator(PredicateOperator.EQUAL);
		equalSp.setType(PredicateType.NUMBER);
		equalSp.setValue("176.849182");
		sps.add(equalSp);
		
		SearchResponse<Customer> response = service.search(request);
		assertEquals(1, response.getResults().size());
		Customer result = response.getResults().get(0);
		assertEquals("Martha", result.getFirstName());
		assertEquals("mhowellpv@eepurl.com", result.getEmail());		
	}

	private String  translateToUnixTimeStamp(String dateStr) throws ParseException{
		Date date = CustomerFileReader.parseDate(dateStr);
		long timeVal =  date.getTime();
		return String.valueOf(timeVal);
	}
	
	@Test
	public void testGreaterThanDate() throws Exception{
		SearchRequest request = new SearchRequest();
		List<SearchPredicate> sps = new ArrayList<SearchPredicate>();
		request.setPredicates(sps);
		SearchPredicate greaterSp = new SearchPredicate();
		greaterSp.setName("createdAt");
		greaterSp.setOperator(PredicateOperator.GREATERTHAN);
		greaterSp.setType(PredicateType.DATE);
		greaterSp.setValue(translateToUnixTimeStamp("2015-01-30 23:24:56"));
		sps.add(greaterSp);
		
		SearchResponse<Customer> response = service.search(request);
		assertEquals(3, response.getResults().size());
		
	}
	@Test
	public void testEqualsDate() throws Exception {
		SearchRequest request = new SearchRequest();
		List<SearchPredicate> sps = new ArrayList<SearchPredicate>();
		request.setPredicates(sps);
		SearchPredicate equalSp = new SearchPredicate();
		equalSp.setName("createdAt");
		equalSp.setOperator(PredicateOperator.EQUAL);
		equalSp.setType(PredicateType.DATE);
		equalSp.setValue(translateToUnixTimeStamp("2015-01-13 18:46:40"));
		sps.add(equalSp);
		
		SearchResponse<Customer> response = service.search(request);
		assertEquals(1, response.getResults().size());
		Customer result = response.getResults().get(0);
		assertEquals("Martha", result.getFirstName());
		assertEquals("mhowellpv@eepurl.com", result.getEmail());
	}

	@Test
	public void testEqualsString() throws Exception{
		SearchRequest request = new SearchRequest();
		List<SearchPredicate> sps = new ArrayList<SearchPredicate>();
		request.setPredicates(sps);
		SearchPredicate equalSp = new SearchPredicate();
		equalSp.setName("firstName");
		equalSp.setOperator(PredicateOperator.EQUAL);
		equalSp.setType(PredicateType.STRING);
		equalSp.setValue("Martha");
		sps.add(equalSp);
		
		SearchResponse<Customer> response = service.search(request);
		assertEquals(10, response.getResults().size());
		for (Customer checkCustomer : response.getResults()){
			assertEquals("Martha", checkCustomer.getFirstName());
		}
	}
	
	@Test
	public void testContains() throws Exception{
		SearchRequest request = new SearchRequest();
		List<SearchPredicate> sps = new ArrayList<SearchPredicate>();
		request.setPredicates(sps);
		request.setItemsPerPage(30);
		SearchPredicate containsSp = new SearchPredicate();
		containsSp.setName("lastName");
		containsSp.setType(PredicateType.STRING);
		containsSp.setOperator(PredicateOperator.CONTAINS);
		containsSp.setValue("owel");
		sps.add(containsSp);
		
		SearchResponse<Customer> response = service.search(request);
		assertEquals(28, response.getResults().size());
		for (Customer checkCustomer : response.getResults()){
			assertTrue(checkCustomer.getLastName().contains("owel"));
		}		
	}
	
	
	@Test
	public void testBeginsWith() throws Exception{
		SearchRequest request = new SearchRequest();
		List<SearchPredicate> sps = new ArrayList<SearchPredicate>();
		request.setPredicates(sps);
		request.setItemsPerPage(20);
		SearchPredicate prefixSps = new SearchPredicate();
		prefixSps.setName("lastName");
		prefixSps.setType(PredicateType.STRING);
		prefixSps.setOperator(PredicateOperator.BEGINSWITH);
		prefixSps.setValue("Mc");
		sps.add(prefixSps);
		
		SearchResponse<Customer> response = service.search(request);
		assertEquals(14, response.getResults().size());
		for (Customer checkCustomer : response.getResults()){
			String prefix = checkCustomer.getLastName().substring(0, 2);
			assertEquals("Mc", prefix);
		}	
	}
	
	@Test
	public void testSort() throws Exception{
		SearchRequest request = new SearchRequest();
		List<SearchPredicate> sps = new ArrayList<SearchPredicate>();
		request.setPredicates(sps);
		request.setItemsPerPage(30);
		SearchPredicate containsSp = new SearchPredicate();
		containsSp.setName("lastName");
		containsSp.setType(PredicateType.STRING);
		containsSp.setOperator(PredicateOperator.CONTAINS);
		containsSp.setValue("owel");
		sps.add(containsSp);
		
		Sort sort = new Sort();
		sort.setColumnName("longitude");
		sort.setSortType(SortType.ASC);
		List<Sort> sorts = new ArrayList<Sort>();
		sorts.add(sort);
		request.setSorts(sorts);
		
		SearchResponse<Customer> response = service.search(request);
		assertEquals(28, response.getResults().size());
		for (int index=0; index < response.getResults().size(); index++){
			if (index < response.getResults().size()-1){
				Customer current = response.getResults().get(index);
				Customer next = response.getResults().get(index+1);
				assertTrue(current.getLongitude() < next.getLongitude());
			}			
		}		
	
	}
	
	@Test
	public void testMiscStuff(){
		for (Object obj : PredicateOperator.values()){
			System.out.println(obj);
		}		
	}
}

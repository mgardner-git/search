package com.allegiant.search;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.allegiant.customer.Customer;
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
	public void testGreaterThanDate() throws Exception{
		SearchRequest request = new SearchRequest();
		List<SearchPredicate> sps = new ArrayList<SearchPredicate>();
		request.setPredicates(sps);
		SearchPredicate greaterSp = new SearchPredicate();
		greaterSp.setName("createdAt");
		greaterSp.setOperator(PredicateOperator.GREATERTHAN);
		greaterSp.setType(PredicateType.DATE);
		greaterSp.setValue("2015-01-30 23:24:56");
		sps.add(greaterSp);
		
		SearchResponse<Customer> response = service.search(request);
		assertEquals(3, response.getResults().size());
		
	}
	
	@Test
	public void testEqualsNumber() throws Exception {
		SearchRequest request = new SearchRequest();
		List<SearchPredicate> sps = new ArrayList<SearchPredicate>();
		request.setPredicates(sps);
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
	
	@Test
	public void testEqualsDate() throws Exception {
		SearchRequest request = new SearchRequest();
		List<SearchPredicate> sps = new ArrayList<SearchPredicate>();
		request.setPredicates(sps);
		SearchPredicate equalSp = new SearchPredicate();
		equalSp.setName("createdAt");
		equalSp.setOperator(PredicateOperator.EQUAL);
		equalSp.setType(PredicateType.DATE);
		equalSp.setValue("2015-01-13 18:46:40");
		sps.add(equalSp);
		
		SearchResponse<Customer> response = service.search(request);
		assertEquals(1, response.getResults().size());
		Customer result = response.getResults().get(0);
		assertEquals("Martha", result.getFirstName());
		assertEquals("mhowellpv@eepurl.com", result.getEmail());
	}

	@Test
	public void testEqualsString() throws Exception{
		
	}
	
	@Test
	public void testContains() throws Exception{
		
	}
	
	@Test
	public void testBeginsWith() throws Exception{
		
	}
}

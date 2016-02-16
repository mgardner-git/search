package com.allegiant.customer;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.allegiant.search.PredicateOperator;
import com.allegiant.search.PredicateType;
import com.allegiant.search.SearchPredicate;
import com.allegiant.search.SearchRequest;
import com.allegiant.search.SearchResponse;

import junit.framework.TestCase;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations={"classpath:root-context.xml"})

public class CustomerServiceTest extends TestCase{

	@Autowired
	CustomerService service;
	
	/*
	@Test
	public void testRead(){
		Customer result = service.read(1);
		assertNotNull(result);
	}
	*/
	
}

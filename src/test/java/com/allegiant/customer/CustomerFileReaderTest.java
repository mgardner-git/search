package com.allegiant.customer;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.TestCase;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations={"classpath:root-context.xml"})

public class CustomerFileReaderTest extends TestCase{

	@Autowired
	CustomerService service;
	@Autowired
	CustomerFileReader reader;
	
	@Test
	public void testRead1() throws Exception{
		int initialSize = service.readAll().size();
		String path = "C:\\Users\\Owner\\workspace\\search\\doc\\etl\\data1.csv";
		reader.readFile(path, CustomerFileFormat.FORMAT1);
		
		List<Customer> results = service.readAll();
		assertTrue(results.size() - initialSize == 1000);		
	}
	
	@Test
	public void testRead2() throws Exception{
		int initialSize = service.readAll().size();
		String path = "C:\\Users\\Owner\\workspace\\search\\doc\\etl\\data2.csv";
		reader.readFile(path, CustomerFileFormat.FORMAT2);
		List<Customer> results = service.readAll();
		assertTrue(results.size() - initialSize == 1000);
		
	}
	
	@Test
	public void testSplit(){
		String testStr = "2015-01-19 01:35:10,183.225.57.32,,,Barbara,Mills,bmills2q@unc.edu";
		String[] tokens = testStr.split(",");
		assertEquals(7, tokens.length);
		for (String token : tokens){
			System.out.println(token);
		}
	}
	
	
}

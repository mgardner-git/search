package com.allegiant.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allegiant.search.SearchRequest;
import com.allegiant.search.SearchResponse;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/rest/customer")
public class CustomerController {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	@Autowired
	CustomerService customerService;

	
	@RequestMapping(value="" , method = RequestMethod.POST)
	public  @ResponseBody Customer create(@RequestBody Customer customer){
		Customer result = customerService.create(customer);
		return result;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces="application/json")
	public  @ResponseBody Customer read(@PathVariable Integer id){
		Customer result = customerService.read(id);
		return result;
	}
	
	@RequestMapping(value="/search", method=RequestMethod.POST, produces="application/json"   )
	public @ResponseBody SearchResponse<Customer> search (@RequestBody SearchRequest request) throws Exception{
		SearchResponse<Customer> response = customerService.search(request);
		return response;
		
 	}
	
	@RequestMapping(value="", method=RequestMethod.PUT)
	public  @ResponseBody Customer update(@RequestBody Customer customer){
							
		Customer result = customerService.update(customer);
		return result;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public @ResponseBody boolean delete(@PathVariable Integer id){
		customerService.delete(id);
		return true;
	}

}

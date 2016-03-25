package com.example.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Customer;
import com.example.repositories.CustomerRepository;

@RestController
public class CustomerController {

	@Autowired
	CustomerRepository customerRepository;

	@RequestMapping("/customers")
	public List<Customer> getCustomers() {
		return customerRepository.findAll();
	}

	@RequestMapping("/customer/{customerId}")
	public Customer getCustomers(@PathVariable("customerId") int customerId) {
		return customerRepository.findOne(customerId);
	}

	@RequestMapping(value="/admin/savecustomer", method=RequestMethod.POST)
	public HashMap<String, Object> saveCustomer(@RequestBody Customer customer) {
		HashMap<String, Object> returnParams = new HashMap<String, Object>();
		
		try {
			customerRepository.save(customer);
			returnParams.put("status", true);
		} catch (Exception e) {
			returnParams.put("status", false);
			returnParams.put("msg", "Customer Addition Failed");
		}

		return returnParams;
	}
	
	@RequestMapping("/savecustomeralt")
	public HashMap<String, Object> saveCustomer(@RequestBody HashMap<String, Object> params) {
		HashMap<String, Object> returnParams = new HashMap<String, Object>();
		
		try {
			
			returnParams.put("status", true);
		} catch (Exception e) {
			returnParams.put("status", false);
			returnParams.put("msg", "Customer Addition Failed");
		}

		return returnParams;
	}
	
}

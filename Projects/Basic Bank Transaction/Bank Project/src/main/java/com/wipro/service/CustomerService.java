package com.wipro.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wipro.model.Customer;

@Service
public interface CustomerService {

	public Customer addCustomer(Customer e);

	public List<Customer> getAllCustomer();

	public Customer getCustomerById(int eid);

	public Customer updateCustomer(int eid, Customer emp);

	public String deleteCustomerById(int eid);

	public String deleteAllCustomer();

	public Customer getCustomerByEmail(String email);
}

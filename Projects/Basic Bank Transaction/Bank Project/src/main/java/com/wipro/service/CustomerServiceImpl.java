package com.wipro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.model.Account;
import com.wipro.model.Customer;
import com.wipro.repo.AddressRepository;
import com.wipro.repo.CustomerRepository;
import com.wipro.utils.AccountNumGenerator;

@Service
public class CustomerServiceImpl implements CustomerService {

	// @Autowired
	// EmployeeDAO repository;

	@Autowired
	CustomerRepository repository;

	@Autowired
	AddressRepository repo;

	@Override
	public Customer addCustomer(Customer e) {
		Account ac = e.getAcc();
		ac.setAccountNumber(AccountNumGenerator.generate());
		e.setAcc(ac);
		return repository.save(e);
	}

	@Override
	public List<Customer> getAllCustomer() {
		return repository.findAll();
	}

	@Override
	public Customer getCustomerById(int eid) {
		Optional op = repository.findById(eid);
		if (op.isPresent()) {
			return (Customer) op.get();
		} else
			return null;
	}

	@Override
	public Customer getCustomerByEmail(String email) {
		return repository.findByEmail(email);
	}

	@Override
	public Customer updateCustomer(int eid, Customer emp) {
		Optional op = repository.findById(eid);
		Customer e;
		if (op.isPresent()) {
			e = (Customer) op.get();
			// e.setEid(emp.getEid());
			e.setEname(emp.getEname());
			e.setEmail(emp.getEmail());
			emp.getAddr().setAid(e.getAddr().getAid());
			e.setAddr(emp.getAddr());
			emp.getAcc().setAccountNumber(e.getAcc().getAccountNumber());
			emp.getAcc().setAccountId(e.getAcc().getAccountId());
			e.setAcc(emp.getAcc());
			repository.save(e);
			return e;
		} else
			return null;
	}

	@Override
	public String deleteCustomerById(int eid) {
		Optional op = repository.findById(eid);
		Customer e;
		if (op.isPresent()) {
			e = (Customer) op.get();

			repository.deleteById(eid);

			return "Deleted Successfully";
		} else
			return "Object not found with given ID";
	}

	@Override
	public String deleteAllCustomer() {
		List<Customer> list = repository.findAll();
		if (!list.isEmpty()) {
			repository.deleteAll();
			return "All Data Deleted";
		} else
			return "No data present to delete";
	}

}

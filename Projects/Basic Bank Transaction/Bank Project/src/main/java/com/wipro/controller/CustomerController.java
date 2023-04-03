package com.wipro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.exceptions.CustomerNotFoundException;
import com.wipro.exceptions.NoDataFoundException;
import com.wipro.exceptions.NullDataException;
import com.wipro.model.Customer;
import com.wipro.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService service;

	@PostMapping("/add")
	public ResponseEntity<?> addCustomer(@RequestBody Customer emp) throws NullDataException {
		if (emp != null) {
			Customer e = service.addCustomer(emp);
			return new ResponseEntity<>(e, HttpStatus.CREATED);
		} else {
			throw new NullDataException("Null Data cannot be added.");
		}

	}

	@GetMapping("/getall")
	public ResponseEntity<?> getAllCustomer() throws NoDataFoundException {
		List<Customer> list = service.getAllCustomer();
		if (list.isEmpty()) {
			// return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			throw new NoDataFoundException("Customer contains no data, Please add data");
		} else
			return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/get/{eid}")
	public ResponseEntity<?> getCustomerById(@PathVariable("eid") int eid) throws CustomerNotFoundException {
		Customer emp = service.getCustomerById(eid);
		if (emp != null)
			return new ResponseEntity<>(emp, HttpStatus.OK);
		else
			// return new ResponseEntity<>("Object not found with given id",
			// HttpStatus.NOT_FOUND);
			throw new CustomerNotFoundException("Customer Details are not found with given ID - " + eid);
	}

	@GetMapping("/getbyemail")
	public ResponseEntity<?> getCustomerByEmail(@RequestParam("email") String email) throws CustomerNotFoundException {
		Customer emp = service.getCustomerByEmail(email);
		if (emp != null)
			return new ResponseEntity<>(emp, HttpStatus.OK);
		else
			// return new ResponseEntity<>("Object not found with given Email",
			// HttpStatus.NOT_FOUND);
			throw new CustomerNotFoundException("Customer Details are not found with given Email ID -" + email);
	}

	@PutMapping("/update/{eid}")
	public ResponseEntity<?> updateCustomer(@PathVariable("eid") int eid, @RequestBody Customer cm)
			throws CustomerNotFoundException, NullDataException {
		int id;
		try {
			id = service.getCustomerById(eid).getEid();
		} catch (Exception e) {
			throw new CustomerNotFoundException(
					"Customer Details not found with given ID - " + eid + ". You cannot perform this operation.");
		}

		if (cm != null && id == eid) {
			Customer e = service.updateCustomer(eid, cm);
			return new ResponseEntity<>(e, HttpStatus.OK);
		} else {
			// return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
			throw new NullDataException(
					"Something went wrong !\nEither null data is updating or Customer with given ID - " + eid
							+ "is not found.");
		}
	}

	@DeleteMapping("/delete/{eid}")
	public ResponseEntity<?> deleteCustomerById(@PathVariable("eid") int eid) throws CustomerNotFoundException {
		int id;
		try {
			id = service.getCustomerById(eid).getEid();
		} catch (Exception e) {
			throw new CustomerNotFoundException(
					"Customer Details not found with given ID - " + eid + ". You cannot perform this operation.");
		}
		if (id == eid) {
			String msg = service.deleteCustomerById(eid);
			return new ResponseEntity<>(msg, HttpStatus.OK);
		} else {
			throw new CustomerNotFoundException("Customer details with given ID = " + eid + "is not found.");
		}

	}

	@DeleteMapping("/deleteall")
	public ResponseEntity<?> deleteAllCustomer() {
		String msg = service.deleteAllCustomer();
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

}

package com.wipro.unitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.wipro.model.Account;
import com.wipro.model.Address;
import com.wipro.model.Customer;
import com.wipro.repo.CustomerRepository;
import com.wipro.service.CustomerServiceImpl;

@ExtendWith(SpringExtension.class)
public class CustomerServiceTest {

	@Mock
	private CustomerRepository repo;

	@InjectMocks
	private CustomerServiceImpl service;

	@Test
	public void testAddCustomer() {
		Customer expected = new Customer(1, "Ram", "ram@gmail.com", new Address(1, "Sdk", "Pune", "MH", "India"),
				new Account(1, "Saving", "Pune", 1250));
		when(repo.save(expected)).thenReturn(expected);
		Customer actual = service.addCustomer(expected);
		assertEquals(expected, actual);
	}

	@Test
	public void testGetAllCustomers() {
		Customer e1 = new Customer(1, "Ram", "ram@gmail.com", new Address(1, "Sdk", "Pune", "MH", "India"),
				new Account(1, "Saving", "Pune", 1250));
		Customer e2 = new Customer(2, "Raja", "raja@gmail.com", new Address(1, "HDK", "Bangalore", "KA", "India"),
				new Account(2, "Saving", "Bangalore", 1250));
		List<Customer> expected = Arrays.asList(e1, e2);
		when(repo.findAll()).thenReturn(expected);
		List<Customer> actual = service.getAllCustomer();
		assertEquals(expected, actual);
	}

	@Test
	public void testGetCustomerById() {
		Customer e1 = new Customer(1, "Ram", "ram@gmail.com", new Address(1, "Sdk", "Pune", "MH", "India"),
				new Account(1, "Saving", "Pune", 1250));
		Optional<Customer> expected = Optional.of(e1);
		when(repo.findById(1)).thenReturn(expected);
		Customer actual = service.getCustomerById(1);
		assertEquals(expected.get().getEid(), actual.getEid());
	}

	@Test
	public void testGetCustomerByEmail() {
		Customer expected = new Customer(1, "Ram", "ram@gmail.com", new Address(1, "Sdk", "Pune", "MH", "India"),
				new Account(1, "Saving", "Pune", 1250));
		when(repo.findByEmail("ram@gmail.com")).thenReturn(expected);
		Customer actual = service.getCustomerByEmail("ram@gmail.com");
		assertEquals(expected, actual);
	}

	@Test
	public void testUpdateCustomer() {
		Customer e1 = new Customer(1, "Ram", "ram@gmail.com", new Address(1, "Sdk", "Pune", "MH", "India"),
				new Account(1, "Saving", "Pune", 1250));
		Customer e2 = new Customer(2, "Raja", "raja@gmail.com", new Address(1, "HDK", "Bangalore", "KA", "India"),
				new Account(2, "Saving", "Bangalore", 1250));
		Optional<Customer> ex1 = Optional.of(e1);
		when(repo.findById(1)).thenReturn(ex1);
		Customer actual = service.updateCustomer(1, e2);
		assertEquals(e2.getEname(), actual.getEname());
	}

	@Test
	public void testDeleteCustomerById() {
		Customer e1 = new Customer(1, "Ram", "ram@gmail.com", new Address(1, "Sdk", "Pune", "MH", "India"),
				new Account(1, "Saving", "Pune", 1250));
		Optional<Customer> ex1 = Optional.of(e1);
		when(repo.findById(1)).thenReturn(ex1);
		String actual = service.deleteCustomerById(1);
		assertEquals("Deleted Successfully", actual);
	}

	@Test
	public void testDeleteAllCustomer() {
		Customer e1 = new Customer(1, "Ram", "ram@gmail.com", new Address(1, "Sdk", "Pune", "MH", "India"),
				new Account(1, "Saving", "Pune", 1250));
		Customer e2 = new Customer(2, "Raja", "raja@gmail.com", new Address(1, "HDK", "Bangalore", "KA", "India"),
				new Account(2, "Saving", "Bangalore", 1250));
		List<Customer> expected = Arrays.asList(e1, e2);
		when(repo.findAll()).thenReturn(expected);
		String actual = service.deleteAllCustomer();
		assertEquals("All Data Deleted", actual);
	}

}

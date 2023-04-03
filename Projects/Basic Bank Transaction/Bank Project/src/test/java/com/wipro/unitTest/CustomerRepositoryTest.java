package com.wipro.unitTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.wipro.model.Account;
import com.wipro.model.Address;
import com.wipro.model.Customer;
import com.wipro.repo.CustomerRepository;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class CustomerRepositoryTest {

	@Autowired
	private TestEntityManager em;

	@Autowired
	private CustomerRepository repo;

	@Test
	public void testIsCustomerEmpty() throws Exception {
		Iterable customers = repo.findAll();
		List<Customer> expected = new ArrayList<>();
		Assertions.assertEquals(customers, expected);
	}

	@Test
	public void testAddNullCustomer() throws Exception {
		Customer expected = new Customer();
		em.persist(expected);
		Customer actual = repo.save(expected);
		Assertions.assertEquals(actual, expected);
	}

	@Test
	public void testAddCustomer() throws Exception {
		Customer expected = new Customer("Ram", "ram@gmail.com", new Address(1, "SKD", "Pune", "MH", "India"),
				new Account(123, "PUNE", "saving", 1250));
		em.persist(expected);
		Customer actual = repo.findAll().get(0);
		Assertions.assertEquals(actual.getEmail(), expected.getEmail());
	}

	@Test
	public void testGetAllCustomers() {
		em.persist(new Customer("rama", "rama@gmail.com", new Address(1, "SKD", "Pune", "MH", "India"),
				new Account(123, "PUNE", "saving", 1250)));
		em.persist(new Customer("raja", "raja@gmail.com", new Address(1, "SKD", "Pune", "MH", "India"),
				new Account(123, "PUNE", "saving", 1250)));
		em.persist(new Customer("sam", "sam@gmail.com", new Address(1, "SKD", "Pune", "MH", "India"),
				new Account(123, "PUNE", "saving", 1250)));
		int actual = repo.findAll().size();
		Assertions.assertEquals(actual, 3);
	}

	@Test
	public void testGetCustomerById() {
		em.persist(new Customer("rama", "rama@gmail.com", new Address(1, "SKD", "Pune", "MH", "India"),
				new Account(123, "PUNE", "saving", 1250)));
		Customer actual = (Customer) repo.findById(2).get();
		Assertions.assertEquals(actual.getEid(), 2);
	}

	@Test
	public void testGetCustomerByEmail() {
		Customer expected = new Customer("Ram", "ram@gmail.com", new Address(1, "SKD", "Pune", "MH", "India"),
				new Account(123, "PUNE", "saving", 1250));
		String expect = expected.getEmail();
		em.persist(expected);
		Customer actual = repo.findByEmail(expect);
		Assertions.assertEquals(actual.getEmail(), expect);
	}

	@Test
	public void testDeleteCustomerById() {
		Customer expected = new Customer("Ram", "ram@gmail.com", new Address(1, "SKD", "Pune", "MH", "India"),
				new Account(123, "PUNE", "saving", 1250));
		em.persist(expected);
		int size = repo.findAll().size();
		repo.deleteById(1);
		Assertions.assertEquals(size - 1, repo.findAll().size());
	}

	@Test
	public void testDeleteAllCustomers() {
		Customer expected = new Customer("Ram", "ram@gmail.com", new Address(1, "SKD", "Pune", "MH", "India"),
				new Account(123, "PUNE", "saving", 1250));
		String expect = expected.getEmail();
		em.persist(expected);
		Customer actual = repo.findByEmail(expect);
		Assertions.assertEquals(actual.getEmail(), expect);
	}

}

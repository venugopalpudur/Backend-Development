package com.wipro.integrationTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.wipro.SpringBootCaseStudy;
import com.wipro.model.Account;
import com.wipro.model.Address;
import com.wipro.model.Customer;
import com.wipro.repo.CustomerRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = SpringBootCaseStudy.class)
public class CustomerIntegrationTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private CustomerRepository repo;

	@Test
	// @Sql(statements = "DELETE FROM CUSTOMER", executionPhase = =
	// Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testAddCustomer() throws Exception {
		Customer cs = new Customer("Ram", "ram@gmail.com", new Address(1, "SKD", "Pune", "MH", "India"),
				new Account(123, "PUNE", "saving", 1250));
		// Customer expect = repo.save(cs);
		Customer response = testRestTemplate.postForObject("http://localhost:" + port + "/customer/add", cs,
				Customer.class);

		assertEquals("Ram", response.getEname());
		// assertEquals(1, repo.findAll().size());
	}

	@Test
	public void testAllCustomers() {
		Customer cs = new Customer("Ganesh", "ganesh@gmail.com", new Address(1, "SKD", "Pune", "MH", "India"),
				new Account(123, "PUNE", "saving", 1250));
		testRestTemplate.postForObject("http://localhost:" + port + "/customer/add", cs, Customer.class);
		List<Customer> response = testRestTemplate.getForObject("http://localhost:" + port + "/customer/getall",
				List.class);
		assertEquals(2, response.size());
		// assertEquals(1, repo.findAll().size());

	}

	@Test
	public void testGetById() {
		Customer cs = new Customer("Ravi", "ravi@gmail.com", new Address(1, "SKD", "Pune", "MH", "India"),
				new Account(123, "PUNE", "saving", 1250));
		testRestTemplate.postForObject("http://localhost:" + port + "/customer/add", cs, Customer.class);
		Customer response = testRestTemplate.getForObject("http://localhost:" + port + "/customer/get/{eid}",
				Customer.class, 1);
		assertEquals(1, response.getEid());
	}

	@Test
	public void testUpdateCustomer() {
		Customer cs = new Customer("Vijay", "vijay@gmail.com", new Address(1, "SKD", "Pune", "MH", "India"),
				new Account(123, "PUNE", "saving", 1250));
		testRestTemplate.postForObject("http://localhost:" + port + "/customer/add", cs, Customer.class);
		Customer update = new Customer("Raja", "raja@gmail.com", new Address(1, "Panvel", "Mumbai", "MH", "India"),
				new Account(123, "Mumbai", "saving", 8974));
		testRestTemplate.put("http://localhost:" + port + "/customer/update/{eid}", update, 1);
		Customer updatedCopy = repo.findById(1).get();
		assertAll(() -> assertNotNull(updatedCopy), () -> assertEquals("raja@gmail.com", updatedCopy.getEmail()));
	}

	@Test
	public void testDeleteById() {
		Customer cs = new Customer("Sam", "sam@gmail.com", new Address(1, "SKD", "Pune", "MH", "India"),
				new Account(123, "PUNE", "saving", 1250));
		testRestTemplate.postForObject("http://localhost:" + port + "/customer/add", cs, Customer.class);
		List<Customer> list = repo.findAll();
		System.out.println(list);
		testRestTemplate.delete("http://localhost:" + port + "/customer/delete/{eid}",
				repo.findByEmail("sam@gmail.com").getEid());
		List<Customer> deletedList = repo.findAll();
		assertEquals(list.size() - 1, deletedList.size());
	}

	@Test
	public void testDeleteAll() {
		Customer cs = new Customer("John", "john@gmail.com", new Address(1, "SKD", "Pune", "MH", "India"),
				new Account(123, "PUNE", "saving", 1250));
		testRestTemplate.postForObject("http://localhost:" + port + "/customer/add", cs, Customer.class);
		testRestTemplate.delete("http://localhost:" + port + "/customer/deleteall");
		List<Customer> deletedList = repo.findAll();
		assertEquals(0, deletedList.size());
	}

}

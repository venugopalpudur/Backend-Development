package com.wipro.unitTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.controller.CustomerController;
import com.wipro.model.Account;
import com.wipro.model.Address;
import com.wipro.model.Customer;
import com.wipro.repo.AccountRepository;
import com.wipro.repo.CustomerRepository;
import com.wipro.repo.TransactionRepository;
import com.wipro.repo.TransferRepository;
import com.wipro.service.CustomerService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CustomerController.class)
class CustomerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerService service;

	@MockBean
	private Customer cust;

	@MockBean
	private CustomerRepository repo;

	@MockBean
	private TransactionRepository tr;

	@MockBean
	private TransferRepository trs;

	@MockBean
	private AccountRepository ac;

	private static ObjectMapper mapper = new ObjectMapper();

	@Test
	public void addCustomerDetailsTest() throws Exception {

		Customer actual = new Customer(1, "sam", "sam@gmail.com", new Address(1, "midc", "solapur", "MH", "india"),
				new Account(1, 123456789, "saving", "pune", 1000.0));

		Customer expect = new Customer(1, "sam", "sam@gmail.com", new Address(1, "midc", "solapur", "MH", "india"),
				new Account(1, 123456789, "saving", "pune", 1000.0));

		when(service.addCustomer(any(Customer.class))).thenReturn(actual);

		String json = mapper.writeValueAsString(expect);

		mockMvc.perform(MockMvcRequestBuilders.post("/customer/add").content(json).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is(201))
				.andExpect(MockMvcResultMatchers.content().string(json));
	}

	@Test
	public void getAllCustomerDetailsTest() throws Exception {

		Customer c1 = new Customer(1, "sam", "sam@gmail.com", new Address(1, "midc", "solapur", "MH", "india"),
				new Account(1, 123456789, "saving", "pune", 1000.0));
		List<Customer> actual = Arrays.asList(c1);

		Customer e1 = new Customer(1, "sam", "sam@gmail.com", new Address(1, "midc", "solapur", "MH", "india"),
				new Account(1, 123456789, "saving", "pune", 1000.0));
		List<Customer> expected = Arrays.asList(e1);

		when(service.getAllCustomer()).thenReturn(actual);

		String json = mapper.writeValueAsString(expected);

		mockMvc.perform(MockMvcRequestBuilders.get("/customer/getall").content(json).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().string(json));
	}

	@Test
	public void getCustomerByIdTest() throws Exception {

		Customer c1 = new Customer(1, "sam", "sam@gmail.com", new Address(1, "midc", "solapur", "MH", "india"),
				new Account(1, 123456789, "saving", "pune", 1000.0));
		List<Customer> actual = Arrays.asList(c1);

		Customer e1 = new Customer(1, "sam", "sam@gmail.com", new Address(1, "midc", "solapur", "MH", "india"),
				new Account(1, 123456789, "saving", "pune", 1000.0));
		List<Customer> expected = Arrays.asList(e1);

		int id = 1;
		when(service.getCustomerById(id)).thenReturn(c1);

		String json = mapper.writeValueAsString(e1);

		mockMvc.perform(MockMvcRequestBuilders.get("/customer/get/{eid}", id).content(json)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().string(json));
	}

	@Test
	public void getCustomerByEmailTest() throws Exception {

		Customer c1 = new Customer(1, "sam", "sam@gmail.com", new Address(1, "midc", "solapur", "MH", "india"),
				new Account(1, 123456789, "saving", "pune", 1000.0));
		List<Customer> actual = Arrays.asList(c1);

		Customer e1 = new Customer(1, "sam", "sam@gmail.com", new Address(1, "midc", "solapur", "MH", "india"),
				new Account(1, 123456789, "saving", "pune", 1000.0));
		List<Customer> expected = Arrays.asList(e1);

		String email = "getbyemail";
		when(service.getCustomerByEmail(email)).thenReturn(c1);

		String json = mapper.writeValueAsString(e1);

		mockMvc.perform(MockMvcRequestBuilders.get("/customer/getbyemail").param("email", email).content(json)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().string(json));
	}

	@Test
	public void deleteCustomerById() throws Exception {

		Customer c1 = new Customer(1, "sam", "sam@gmail.com", new Address(1, "midc", "solapur", "MH", "india"),
				new Account(1, 123456789, "saving", "pune", 1000.0));
		List<Customer> actual = Arrays.asList(c1);

		Customer e1 = new Customer(1, "sam", "sam@gmail.com", new Address(1, "midc", "solapur", "MH", "india"),
				new Account(1, 123456789, "saving", "pune", 1000.0));
		List<Customer> expected = Arrays.asList(e1);

		int id = 1;

		when(service.getCustomerById(id)).thenReturn(cust);
		when(cust.getEid()).thenReturn(id);
		when(service.deleteCustomerById(id)).thenReturn("Deleted Successfully");

		String json = "Deleted Successfully";

		mockMvc.perform(MockMvcRequestBuilders.delete("/customer/delete/{eid}", id).content(json)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().string(json));
	}

	@Test
	public void deleteCustomer() throws Exception {

		Customer c1 = new Customer(1, "sam", "sam@gmail.com", new Address(1, "midc", "solapur", "MH", "india"),
				new Account(1, 123456789, "saving", "pune", 1000.0));
		List<Customer> actual = Arrays.asList(c1);

		Customer e1 = new Customer(1, "sam", "sam@gmail.com", new Address(1, "midc", "solapur", "MH", "india"),
				new Account(1, 123456789, "saving", "pune", 1000.0));
		List<Customer> expected = Arrays.asList(e1);

		when(service.deleteAllCustomer()).thenReturn("All Data Deleted");

		String json = "All Data Deleted";

		mockMvc.perform(MockMvcRequestBuilders.delete("/customer/deleteall").content(json)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().string(json));
	}

}

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
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.wipro.controller.AccountController;
import com.wipro.model.Account;
import com.wipro.model.Transaction;
import com.wipro.model.Transfer;
import com.wipro.repo.AccountRepository;
import com.wipro.repo.AddressRepository;
import com.wipro.repo.CustomerRepository;
import com.wipro.repo.TransactionRepository;
import com.wipro.repo.TransferRepository;
import com.wipro.service.AccountService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AccountController.class)
class AccountControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AccountService service;

	@MockBean
	private Account acc;

	@MockBean
	private CustomerRepository repo;

	@MockBean
	private AddressRepository add;

	@MockBean
	private TransactionRepository tr;

	@MockBean
	private TransferRepository trs;

	@MockBean
	private AccountRepository ac;

	private static ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

	@Test
	public void addAccountDetailsTest() throws Exception {

		Account actual = new Account(1, 587964512, "Saving", "Pune", 1583.0);
		Account expect = new Account(1, 587964512, "Saving", "Pune", 1583.0);

		when(service.addAccount(any(Account.class))).thenReturn(actual);

		String json = mapper.writeValueAsString(expect);

		mockMvc.perform(MockMvcRequestBuilders.post("/account/add").content(json).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is(201))
				.andExpect(MockMvcResultMatchers.content().string(json));
	}

	@Test
	public void getAllAccountDetailsTest() throws Exception {

		Account acc = new Account(1, 587964512, "Saving", "Pune", 1583.0);
		List<Account> act = Arrays.asList(acc);
		List<Account> exp = Arrays.asList(acc);

		when(service.getAllAccounts()).thenReturn(act);

		String json = mapper.writeValueAsString(exp);

		mockMvc.perform(MockMvcRequestBuilders.get("/account/getall").content(json).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().string(json));
	}

	@Test
	public void getAccountByIdTest() throws Exception {

		Account actual = new Account(1, 587964512, "Saving", "Pune", 1583.0);
		Account expect = new Account(1, 587964512, "Saving", "Pune", 1583.0);
		int id = 1;
		when(service.getAccountById(id)).thenReturn(actual);
		String json = mapper.writeValueAsString(expect);
		mockMvc.perform(MockMvcRequestBuilders.get("/account/getid/{accountId}", id).content(json)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().string(json));
	}

	@Test
	public void getAccountByNum() throws Exception {

		Account actual = new Account(1, 587964512, "Saving", "Pune", 1583.0);
		Account expect = new Account(1, 587964512, "Saving", "Pune", 1583.0);

		long Num = 587964512;
		when(service.getAccountByNum(Num)).thenReturn(actual);
		String json = mapper.writeValueAsString(expect);
		mockMvc.perform(MockMvcRequestBuilders.get("/account/getnum/{accountNum}", Num).content(json)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().string(json));
	}

	@Test
	public void deleteAccountByNum() throws Exception {

		Account actual = new Account(1, 587964512, "Saving", "Pune", 1583.0);
		Account expect = new Account(1, 587964512, "Saving", "Pune", 1583.0);

		when(service.getAccountByNum(actual.getAccountNumber())).thenReturn(acc);
		when(acc.getAccountNumber()).thenReturn(actual.getAccountNumber());
		when(service.deleteAccount(actual.getAccountNumber())).thenReturn("Deleted Successfully");

		String json = "Deleted Successfully";

		mockMvc.perform(MockMvcRequestBuilders.delete("/account/delete/{accountNum}", actual.getAccountNumber())
				.content(json).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().string(json));
	}

	@Test
	public void deleteAccount() throws Exception {

		Account actual = new Account(1, 587964512, "Saving", "Pune", 1583.0);
		Account expect = new Account(1, 587964512, "Saving", "Pune", 1583.0);

		when(service.deleteAll()).thenReturn("All Data Deleted");

		String json = "All Data Deleted";

		mockMvc.perform(MockMvcRequestBuilders.delete("/account/deleteall").content(json)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().string(json));
	}

	// -----------------------Transfers & Transactions --------------------------

	@Test
	public void getBalanceOfTest() throws Exception {

		Account actual = new Account(1, 587964512, "Saving", "Pune", 1583.0);
		Account expect = new Account(1, 587964512, "Saving", "Pune", 1583.0);

		when(service.getAccountByNum(actual.getAccountNumber())).thenReturn(acc);
		when(acc.getAccountNumber()).thenReturn(actual.getAccountNumber());
		when(service.getBalanceOf(actual.getAccountNumber())).thenReturn(actual);

		String json = mapper.writeValueAsString(expect);

		mockMvc.perform(MockMvcRequestBuilders.get("/account/balance/{accountNum}", actual.getAccountNumber())
				.content(json).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().string(json));
	}

	@Test
	public void getAllTransactionsTest() throws Exception {

		Transaction e1 = new Transaction(1, null, 123456789, 1500, 157963345, "SUCCESS");
		List<Transaction> list = Arrays.asList(e1);
		// List<Account> exp = Arrays.asList(acc);

		when(service.getAllTransactions()).thenReturn(list);

		String json = mapper.writeValueAsString(list);

		mockMvc.perform(MockMvcRequestBuilders.get("/account/transactions").content(json)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().string(json));
	}

	@Test
	public void getAllTransfersTest() throws Exception {

		Transfer e1 = new Transfer(1, null, 598715236, 785645978, 485652354, "SUCCESS");
		List<Transfer> list = Arrays.asList(e1);

		when(service.getAllTransfers()).thenReturn(list);

		String json = mapper.writeValueAsString(list);

		mockMvc.perform(MockMvcRequestBuilders.get("/account/transfers").content(json)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().string(json));
	}

}

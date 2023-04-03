package com.wipro.integrationTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
import org.springframework.transaction.annotation.Transactional;

import com.wipro.SpringBootCaseStudy;
import com.wipro.model.Account;
import com.wipro.repo.AccountRepository;
import com.wipro.repo.TransactionRepository;
import com.wipro.repo.TransferRepository;
import com.wipro.utils.AccountTransfer;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = SpringBootCaseStudy.class)
@Transactional
public class AccountIntegrationTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private AccountRepository repo;

	@Autowired
	private TransactionRepository transrepo;

	@Autowired
	private TransferRepository repotf;

	@Test
	public void testAddAccount() throws Exception {
		Account acc = new Account(2, 587964512, "Saving", "Pune", 1583.0);
		Account response = testRestTemplate.postForObject("http://localhost:" + port + "/account/add", acc,
				Account.class);
		assertNotEquals(acc.getAccountNumber(), response.getAccountNumber());
	}

	@Test
	public void testAllAccount() {
		Account acc = new Account(2, 587964512, "Saving", "Pune", 1583.0);
		testRestTemplate.postForObject("http://localhost:" + port + "/account/add", acc, Account.class);
		List<Account> response = testRestTemplate.getForObject("http://localhost:" + port + "/account/getall",
				List.class);
		// System.out.println(response.size());
		assertEquals(2, response.size());
	}

	@Test
	public void testGetById() {
		Account acc = new Account(1, 587964512, "Saving", "Pune", 1583.0);
		testRestTemplate.postForObject("http://localhost:" + port + "/account/add", acc, Account.class);
		Account response = testRestTemplate.getForObject("http://localhost:" + port + "/account/getid/{accountId}",
				Account.class, 1);
		assertEquals(1, response.getAccountId());
	}

	@Test
	public void testUpdateAccount() {
		Account acc = new Account(1, 587964512, "Saving", "Pune", 1583.0);

		testRestTemplate.postForObject("http://localhost:" + port + "/account/add", acc, Account.class);
		Account update = new Account(2, 897546123, "Saving", "Mumbai", 9845.0);

		testRestTemplate.put("http://localhost:" + port + "/account/update/{accountNum}", update, 1);
		Account updatedCopy = repo.findById(1).get();
		assertAll(() -> assertNotNull(updatedCopy),
				() -> assertEquals(update.getAccountType(), updatedCopy.getAccountType()));
	}

	@Test
	public void testDeleteById() {
		Account acc = new Account(1, 587964512, "Saving", "Pune", 1583.0);

		testRestTemplate.postForObject("http://localhost:" + port + "/account/add", acc, Account.class);
		List<Account> list = repo.findAll();
		long accountNum = repo.findAll().get(0).getAccountNumber();

		System.out.println(list);
		System.out.println(accountNum);

		testRestTemplate.delete("http://localhost:" + port + "/account/delete/{accountNum}", accountNum);
		List<Account> deletedList = repo.findAll();
		System.out.println(deletedList);
		assertEquals(list.size(), deletedList.size());
	}

	@Test
	public void testDeleteAll() {
		Account acc = new Account(1, 587964512, "Saving", "Pune", 1583.0);

		testRestTemplate.postForObject("http://localhost:" + port + "/account/add", acc, Account.class);
		testRestTemplate.delete("http://localhost:" + port + "/account/deleteall");
		List<Account> deletedList = repo.findAll();
		assertEquals(0, deletedList.size());
	}

	@Test
	public void testTransfer() {
		AccountTransfer tf = new AccountTransfer(789351786, 631589963, 1000);
		// Transfer e1 = new Transfer(1, null, tf.getSourceAccountNum(),
		// tf.getTargetAccountNum(), 485652354, "SUCCESS");
		String actual = testRestTemplate.postForObject("http://localhost:" + port + "/account/transfer", tf,
				String.class);
		assertEquals(
				"Either source or target account number is not exists in bank's database. Please enter valid account number.",
				actual);
	}

	@Test
	public void testGetBalanceOf() {
		long accountNumber = 587964512;
		String actual = testRestTemplate.getForObject("http://localhost:" + port + "/account/balance/{accNum}",
				String.class, 587964512);

		assertEquals("Customer Details not found with given number - " + accountNumber
				+ ". You cannot perform this operation.", actual);
	}

	@Test
	public void testWithdraw() {
		Account acc = new Account(1, 587964512, "Saving", "Pune", 1583.0);
		String actual = testRestTemplate.postForObject("http://localhost:" + port + "/account/withdraw", acc,
				String.class);

		assertEquals("Acccount number is not exists in bank's database. Please enter valid account number.", actual);
	}

	@Test
	public void testDeposite() {
		Account acc = new Account(1, 587964512, "Saving", "Pune", 1583.0);
		String actual = testRestTemplate.postForObject("http://localhost:" + port + "/account/deposite", acc,
				String.class);

		assertEquals("Acccount number is not exists in bank's database. Please enter valid account number.", actual);
	}
}

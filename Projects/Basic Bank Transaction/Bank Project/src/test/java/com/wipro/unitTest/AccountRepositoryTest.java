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
import com.wipro.repo.AccountRepository;

@DataJpaTest
@ExtendWith(SpringExtension.class) // ------------------------------------
public class AccountRepositoryTest {

	@Autowired
	private TestEntityManager em;

	@Autowired
	private AccountRepository repo;

	@Test
	public void testIsAcountEmpty() throws Exception {
		Iterable account = repo.findAll();
		List<Account> expected = new ArrayList<>();
		Assertions.assertEquals(account, expected);
	}

	@Test
	public void testAddNullAccount() throws Exception {
		Account expected = new Account();
		em.persist(expected);
		Account actual = repo.save(expected);
		Assertions.assertEquals(actual, expected);
	}

	@Test
	public void testAddAccount() throws Exception {
		Account expected = new Account(123456789, "Saving", "Pune", 1587);
		em.persist(expected);
		Account actual = repo.findAll().get(0);
		Assertions.assertEquals(actual.getAccountNumber(), expected.getAccountNumber());
	}

	@Test
	public void testGetAllAccounts() {
		em.persist(new Account(123456789, "Saving", "Pune", 1587));
		em.persist(new Account(755894654, "Saving", "Pune", 5681));
		em.persist(new Account(254978563, "Saving", "Pune", 4782));
		int actual = repo.findAll().size();
		Assertions.assertEquals(actual, 3);
	}

	@Test
	public void testGetAccountById() {
		em.persist(new Account(123456789, "Saving", "Pune", 1587));
		Account actual = (Account) repo.findById(repo.findAll().get(0).getAccountId()).get(); // 4
		int size = repo.findAll().size();
		Assertions.assertEquals(actual.getAccountId(), repo.findAll().get(0).getAccountId());
	}

	@Test
	public void testGetAccountByNum() {
		em.persist(new Account(123456789, "Saving", "Pune", 1587));
		Account actual = (Account) repo.findByAccountNumber(123456789);
		Assertions.assertEquals(actual.getAccountNumber(), 123456789);
	}

	@Test
	public void testDeleteAccountById() {
		Account expected = new Account(123456789, "Saving", "Pune", 1587);
		em.persist(expected);
		int size = repo.findAll().size();
		repo.deleteById(repo.findAll().get(0).getAccountId()); // 3
		Assertions.assertEquals(size - 1, repo.findAll().size());
	}

	@Test
	public void testDeleteAccountByNum() {
		Account expected = new Account(123456789, "Saving", "Pune", 1587);
		em.persist(expected);
		int size = repo.findAll().size();
		repo.deleteByAccountNumber(123456789);
		Assertions.assertEquals(size - 1, repo.findAll().size());
	}

	@Test
	public void testDeleteAllAccounts() {
		Account expected = new Account(123456789, "Saving", "Pune", 1587);
		em.persist(expected);
		repo.deleteAll();
		Assertions.assertEquals(0, repo.findAll().size());
	}

}

package com.wipro.unitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.wipro.model.Account;
import com.wipro.model.Transaction;
import com.wipro.model.Transfer;
import com.wipro.repo.AccountRepository;
import com.wipro.repo.TransactionRepository;
import com.wipro.repo.TransferRepository;
import com.wipro.service.AccountServiceImpl;
import com.wipro.utils.AccountTransfer;

@ExtendWith(SpringExtension.class)
public class AccountServiceTest {

	@Mock
	private AccountRepository repo;

	@Mock
	TransactionRepository repoTs;

	@Mock
	TransferRepository repoTf;

	@Mock
	Transaction ts;

	@Mock
	Transfer tf;

	@InjectMocks
	private AccountServiceImpl service;

	@Test
	public void testAddAccount() {
		Account expected = new Account(1, 123456789, "Saving", "Pune", 1578.0);
		when(repo.save(expected)).thenReturn(expected);
		Account actual = service.addAccount(expected);
		assertEquals(expected, actual);
	}

	@Test
	public void testGetAllAccounts() {
		Account e1 = new Account(1, 123456789, "Saving", "Pune", 1578.0);
		Account e2 = new Account(2, 257894523, "Saving", "Mumbai", 5894.0);
		List<Account> expected = Arrays.asList(e1, e2);
		when(repo.findAll()).thenReturn(expected);
		List<Account> actual = service.getAllAccounts();
		assertEquals(expected, actual);
	}

	@Test
	public void testGetAccountById() {
		Account e1 = new Account(1, 123456789, "Saving", "Pune", 1578.0);
		Optional<Account> expected = Optional.of(e1);
		when(repo.findById(1)).thenReturn(expected);
		Account actual = service.getAccountById(1);
		assertEquals(expected.get().getAccountId(), actual.getAccountId());
	}

	@Test
	public void testGetAccountByNum() {
		Account expected = new Account(1, 123456789, "Saving", "Pune", 1578.0);
		when(repo.findByAccountNumber(123456789)).thenReturn(expected);
		Account actual = service.getAccountByNum(123456789);
		assertEquals(expected, actual);
	}

	@Test
	public void testUpdateAccount() {
		Account e1 = new Account(1, 123456789, "Saving", "Pune", 1578.0);
		Account e2 = new Account(2, 257894523, "Saving", "Mumbai", 5894.0);
		when(repo.findByAccountNumber(123456789)).thenReturn(e1);
		Account actual = service.updateAccount(123456789, e2);
		assertEquals(e2.getAccountNumber(), actual.getAccountNumber());
	}

	@Test
	public void testDeleteAccountById() {
		Account e1 = new Account(1, 123456789, "Saving", "Pune", 1578.0);
		when(repo.findByAccountNumber(123456789)).thenReturn(e1);
		String actual = service.deleteAccount(123456789);
		assertEquals("Deleted Successfully", actual);
	}

	@Test
	public void testDeleteAllAccount() {
		Account e1 = new Account(1, 123456789, "Saving", "Pune", 1578.0);
		Account e2 = new Account(2, 257894523, "Saving", "Mumbai", 5894.0);
		List<Account> expected = Arrays.asList(e1, e2);
		when(repo.findAll()).thenReturn(expected);
		String actual = service.deleteAll();
		assertEquals("All Data Deleted", actual);
	}

	// --------------- Transaction Tests--------------------------------

	@Test
	public void testGetAllTransactions() {
		Transaction e1 = new Transaction(1, LocalDateTime.now(), 123456789, 1578, 157963345, "SUCCESS");
		Transaction e2 = new Transaction(2, LocalDateTime.now(), 257894523, 1578, 789345934, "SUCCESS");
		List<Transaction> expected = Arrays.asList(e1, e2);
		when(repoTs.findAll()).thenReturn(expected);
		List<Transaction> actual = service.getAllTransactions();
		assertEquals(expected, actual);
	}

	@Test
	public void testGetBalanceOf() {
		Account e1 = new Account(1, 123456789, "Saving", "Pune", 1578.0);
		when(repo.findByAccountNumber(e1.getAccountNumber())).thenReturn(e1);
		Account actual = service.getBalanceOf(123456789);
		assertEquals(e1.getAccountBalance(), actual.getAccountBalance());
	}

	@Test
	public void testGetWithdraw() {
		Account a1 = new Account(1, 123456789, "Saving", "Pune", 1578.0);
		Account a2 = new Account(1, 123456789, "Saving", "Pune", 578.0);
		Transaction e1 = new Transaction(1, LocalDateTime.now(), a1.getAccountNumber(), 578.0, a1.hashCode(),
				"DEBITED");
		when(repo.save(any())).thenReturn(a2);
		when(repo.findByAccountNumber(a1.getAccountNumber())).thenReturn(a1);
		Transaction actual = service.withdraw(a1.getAccountNumber(), 1000);
		assertEquals(a2.getAccountBalance(), actual.getBalance());
	}

	@Test
	public void testGetDeposite() {
		Account a1 = new Account(1, 123456789, "Saving", "Pune", 1578.0);
		Account a2 = new Account(1, 123456789, "Saving", "Pune", 578.0);
		Transaction e1 = new Transaction(1, LocalDateTime.now(), a1.getAccountNumber(), 2578, a1.hashCode(),
				"CREDITED");
		when(repo.findByAccountNumber(a1.getAccountNumber())).thenReturn(a1);
		when(repo.save(any())).thenReturn(a2);
		Transaction actual = service.deposite(a1.getAccountNumber(), 1000);
		assertEquals(e1.getBalance(), actual.getBalance());
	}

	// ----------------- Transfer Tests ---------------------------------

	@Test
	public void testTransferFunds() {
		Account a1 = new Account(1, 123456789, "Saving", "Pune", 1578.0);
		Account a2 = new Account(2, 257894523, "Saving", "Mumbai", 5894.0);
		Transfer e1 = new Transfer(1, LocalDateTime.now(), a1.getAccountNumber(), a2.getAccountNumber(), 485652354,
				"SUCCESS");
		AccountTransfer transfer = new AccountTransfer(a1.getAccountNumber(), a2.getAccountNumber(), 1000);
		when(repo.findByAccountNumber(transfer.getSourceAccountNum())).thenReturn(a1);
		when(repo.findByAccountNumber(transfer.getTargetAccountNum())).thenReturn(a2);
		when(repoTf.save(any(Transfer.class))).thenReturn(e1);
		Transfer actual = service.transferFunds(transfer);
		assertEquals(e1, actual);
	}

	@Test
	public void testGetAllTransfers() {
		Transfer e1 = new Transfer(1, LocalDateTime.now(), 123456789, 157963345, 485652354, "CREDITED");
		Transfer e2 = new Transfer(1, LocalDateTime.now(), 257894523, 789345934, 482369753, "DEBITED");
		List<Transfer> expected = Arrays.asList(e1, e2);
		when(repoTf.findAll()).thenReturn(expected);
		List<Transfer> actual = service.getAllTransfers();
		assertEquals(expected, actual);
	}

}

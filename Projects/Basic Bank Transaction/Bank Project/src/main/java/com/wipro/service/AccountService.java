package com.wipro.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wipro.model.Account;
import com.wipro.model.Transaction;
import com.wipro.model.Transfer;
import com.wipro.utils.AccountTransfer;

@Service
public interface AccountService {

	public Account addAccount(Account acc);

	public List<Account> getAllAccounts();

	public List<Transaction> getAllTransactions();

	public List<Transfer> getAllTransfers();

	// public List<Customer> getAllCustomers();
	public Account getAccountById(int accountId);

	public Account getAccountByNum(long accountNum);

	public Account updateAccount(long accountNum, Account acc);

	public String deleteAccount(long accountNum);

	public String deleteAll();

	public Transfer transferFunds(AccountTransfer transfer);

	public Account getBalanceOf(long accountNumber);

	public Transaction withdraw(long accountNumber, double amount);

	public Transaction deposite(long accountNumber, double amount);

}

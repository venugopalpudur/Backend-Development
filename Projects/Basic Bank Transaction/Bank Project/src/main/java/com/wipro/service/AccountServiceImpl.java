package com.wipro.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.model.Account;
import com.wipro.model.Transaction;
import com.wipro.model.Transfer;
import com.wipro.repo.AccountRepository;
import com.wipro.repo.TransactionRepository;
import com.wipro.repo.TransferRepository;
import com.wipro.utils.AccountNumGenerator;
import com.wipro.utils.AccountTransfer;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	TransactionRepository repo;

	@Autowired
	TransferRepository repotrans;

	Account acc;

	@Autowired
	AccountRepository repository;

	@Override
	public Account addAccount(Account e) {
		e.setAccountNumber(AccountNumGenerator.generate());
		return repository.save(e);
	}

	@Override
	public List<Account> getAllAccounts() {
		return repository.findAll();
	}

	@Override
	public List<Transaction> getAllTransactions() {
		return repo.findAll();
	}

	@Override
	public List<Transfer> getAllTransfers() {
		return repotrans.findAll();
	}

	@Override
	public Account getAccountById(int accountId) {
		Optional op = repository.findById(accountId);
		if (op.isPresent()) {
			return (Account) op.get();
		} else
			return null;
	}

	public Account getAccountByNum(long accountNum) {
		Account ac = repository.findByAccountNumber(accountNum);
		if (ac != null) {
			return ac;
		} else
			return null;
	}

	@Override
	public Account updateAccount(long accountNum, Account acc) {
		Account ac = repository.findByAccountNumber(accountNum);

		if (ac != null) {
			ac.setAccountNumber(acc.getAccountNumber());
			ac.setAccountId(acc.getAccountId());
			ac.setAccountType(acc.getAccountType());
			ac.setAccountBranch(acc.getAccountBranch());
			ac.setAccountBalance(acc.getAccountBalance());
			repository.save(ac);
			return ac;
		} else
			return null;
	}

	@Override
	public String deleteAccount(long accountNum) {
		Account ac = repository.findByAccountNumber(accountNum);

		if (ac != null) {

			repository.deleteByAccountNumber(accountNum);

			return "Deleted Successfully";
		} else
			return "Object not found with given ID";
	}

	@Override
	public String deleteAll() {
		List<Account> list = repository.findAll();
		if (!list.isEmpty()) {
			repository.deleteAll();
			return "All Data Deleted";
		} else
			return "No data present to delete";
	}

	@Override
	public Transfer transferFunds(AccountTransfer transfer) {

		Account targetAccount = repository.findByAccountNumber(transfer.getTargetAccountNum());
		Account sourceAccount = repository.findByAccountNumber(transfer.getSourceAccountNum());
		Transfer ts = new Transfer();
		if (transfer.getAmount() > 0) {
			if (sourceAccount.getAccountBalance() >= transfer.getAmount()) {

				sourceAccount.setAccountBalance(sourceAccount.getAccountBalance() - transfer.getAmount());
				targetAccount.setAccountBalance(targetAccount.getAccountBalance() + transfer.getAmount());
				repository.save(sourceAccount);
				repository.save(targetAccount);

				ts.setDateTime(LocalDateTime.now());
				ts.setSourceAccountNum(sourceAccount.getAccountNumber());
				ts.setTargetAccountNum(targetAccount.getAccountNumber());
				ts.setTransferId(sourceAccount.hashCode());
				ts.setStatus("SUCCESS");

				return repotrans.save(ts); // returning transaction ID
			} else {
				ts.setDateTime(LocalDateTime.now());
				ts.setSourceAccountNum(sourceAccount.getAccountNumber());
				ts.setTargetAccountNum(targetAccount.getAccountNumber());
				ts.setTransferId(0);
				ts.setStatus("No sufficient funds in source account to transfer.");
				return ts;
			}
		} else {
			ts.setDateTime(LocalDateTime.now());
			ts.setSourceAccountNum(sourceAccount.getAccountNumber());
			ts.setTargetAccountNum(targetAccount.getAccountNumber());
			ts.setTransferId(0);
			ts.setStatus("Transaction amount cannot be Negative");
			return ts;
		}

	}

	@Override
	public Account getBalanceOf(long accountNumber) {
		return repository.findByAccountNumber(accountNumber);
	}

	@Override
	public Transaction withdraw(long accountNumber, double amount) {
		Account acc = repository.findByAccountNumber(accountNumber);

		// add condition for minimum balance
		acc.setAccountBalance(acc.getAccountBalance() - amount);

		Transaction ts = new Transaction();
		Account ac = repository.save(acc);
		if (ac != null) {
			ts.setDateTime(LocalDateTime.now());
			ts.setAccountNum(acc.getAccountNumber());
			ts.setBalance(acc.getAccountBalance());
			ts.setTransactionId(acc.hashCode());
			ts.setStatus("DEBITED");
		}
		repo.save(ts);
		return ts;
	}

	@Override
	public Transaction deposite(long accountNumber, double amount) {
		Account acc = repository.findByAccountNumber(accountNumber);
		acc.setAccountBalance(acc.getAccountBalance() + amount);

		Transaction ts = new Transaction();
		Account ac = repository.save(acc);
		if (ac != null) {
			ts.setDateTime(LocalDateTime.now());
			ts.setAccountNum(acc.getAccountNumber());
			ts.setBalance(acc.getAccountBalance());
			ts.setTransactionId(acc.hashCode());
			ts.setStatus("CREDITED");
		}
		repo.save(ts);
		return ts;
	}

}

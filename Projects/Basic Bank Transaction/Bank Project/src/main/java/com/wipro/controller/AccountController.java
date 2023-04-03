package com.wipro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.exceptions.AccountNotFoundException;
import com.wipro.exceptions.NoDataFoundException;
import com.wipro.exceptions.NullDataException;
import com.wipro.model.Account;
import com.wipro.model.Transaction;
import com.wipro.model.Transfer;
import com.wipro.service.AccountService;
import com.wipro.utils.AccountTransfer;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	AccountService service;

	@PostMapping("/add")
	public ResponseEntity<?> addAccount(@RequestBody Account acc) throws NullDataException {
		if (acc != null) {
			Account ac = service.addAccount(acc);
			return new ResponseEntity<>(ac, HttpStatus.CREATED);
		} else
			throw new NullDataException("Null Data cannot be added");
	}

	@GetMapping("/getall")
	public ResponseEntity<?> getAllAccounts() throws NoDataFoundException {
		List<Account> list = service.getAllAccounts();
		if (list.isEmpty())
			throw new NoDataFoundException("Account details contains null data, Please add data");
		else
			return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/getid/{accountId}") // added extra --> :\\d+
	public ResponseEntity<?> getAccountById(@PathVariable("accountId") int accountId) throws AccountNotFoundException {
		Account ac = service.getAccountById(accountId);
		if (ac != null)
			return new ResponseEntity<>(ac, HttpStatus.OK);
		else
			throw new AccountNotFoundException("No Account present with Account ID -" + accountId);
	}

	@GetMapping("/getnum/{accountNum}")
	public ResponseEntity<?> getAccountByNum(@PathVariable("accountNum") int accountNum)
			throws AccountNotFoundException {
		Account ac = service.getAccountByNum(accountNum);
		if (ac != null)
			return new ResponseEntity<>(ac, HttpStatus.OK);
		else
			throw new AccountNotFoundException("No Account present with Account Number -" + accountNum);
	}

	@PutMapping("/update/{accountNum}")
	public ResponseEntity<?> updateAccount(@PathVariable("accountNum") int accountNum, @RequestBody Account acc)
			throws AccountNotFoundException, NullDataException {
		long id;
		try {
			id = service.getAccountByNum(accountNum).getAccountNumber();
		} catch (Exception e) {
			throw new AccountNotFoundException("Account Details not found with given Number - " + accountNum
					+ ". You cannot perform this operation.");
		}

		if (acc != null && id == accountNum) {
			Account accnt = service.updateAccount(accountNum, acc);
			return new ResponseEntity<>(accnt, HttpStatus.OK);
		} else {
			// return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
			throw new NullDataException(
					"Something went wrong !\nEither null data is updating or Account with given Number - " + accountNum
							+ "is not found.");
		}
	}

	@DeleteMapping("/delete/{accountNum}")
	public ResponseEntity<?> deleteAccount(@PathVariable("accountNum") int accountNum) throws AccountNotFoundException {

		long id;
		try {
			id = service.getAccountByNum(accountNum).getAccountNumber();
		} catch (Exception e) {
			throw new AccountNotFoundException("Account Details not found with given Number - " + accountNum
					+ ". You cannot perform this operation.");
		}
		if (accountNum == id) {
			String msg = service.deleteAccount(accountNum);
			return new ResponseEntity<>(msg, HttpStatus.OK);
		} else {
			throw new AccountNotFoundException("Account details with given Number = " + accountNum + "is not found.");
		}
	}

	@DeleteMapping("deleteall")
	public ResponseEntity<?> deleteAll() {
		String msg = service.deleteAll();
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	/*
	 * @GetMapping("/transfer") public String transferFunds(@RequestParam("from")
	 * long from, @RequestParam("to") long to,
	 * 
	 * @RequestParam("amount") double amount) {
	 * 
	 * return service.transferFunds(from, to, amount); }
	 */

	@PostMapping("/transfer")
	public ResponseEntity<?> transferFunds(@RequestBody AccountTransfer transfer) throws AccountNotFoundException {
		if (service.getAccountByNum(transfer.getSourceAccountNum()) != null
				&& service.getAccountByNum(transfer.getTargetAccountNum()) != null) {
			return new ResponseEntity<>(service.transferFunds(transfer), HttpStatus.OK);
		} else {
			throw new AccountNotFoundException(
					"Either source or target account number is not exists in bank's database. Please enter valid account number.");
		}

	}

	@GetMapping("/balance/{accNum}")
	public ResponseEntity<?> getBalanceOf(@PathVariable("accNum") long accountNumber) throws AccountNotFoundException {

		long accNum;
		try {
			accNum = service.getAccountByNum(accountNumber).getAccountNumber();
		} catch (Exception e) {
			throw new AccountNotFoundException("Customer Details not found with given number - " + accountNumber
					+ ". You cannot perform this operation.");
		}

		if (accNum == accountNumber) {
			Account balance = service.getBalanceOf(accountNumber);
			return new ResponseEntity<>(balance, HttpStatus.OK); // "Account Number : " + accountNumber + "\nAccount
																	// Balance : " + balance
		} else
			throw new AccountNotFoundException("NO Account present with Account Number -" + accountNumber);

	}

	@PostMapping("/withdraw")
	public ResponseEntity<?> withdraw(@RequestBody Account ac) throws AccountNotFoundException {
		if (service.getAccountByNum(ac.getAccountNumber()) != null) {
			return new ResponseEntity<>(service.withdraw(ac.getAccountNumber(), ac.getAccountBalance()), HttpStatus.OK);
		} else {
			throw new AccountNotFoundException(
					"Acccount number is not exists in bank's database. Please enter valid account number.");
		}
	}

	@PostMapping("/deposite")
	public ResponseEntity<?> deposite(@RequestBody Account ac) throws AccountNotFoundException {
		Account check = service.getAccountByNum(ac.getAccountNumber());
		if (check != null) {
			return new ResponseEntity<>(service.deposite(ac.getAccountNumber(), ac.getAccountBalance()), HttpStatus.OK);
		} else {
			throw new AccountNotFoundException(
					"Acccount number is not exists in bank's database. Please enter valid account number.");
		}
	}

	@GetMapping("/transactions")
	public ResponseEntity<?> getAllTransactions() throws NoDataFoundException {
		List<Transaction> list = service.getAllTransactions();
		if (list.isEmpty())
			throw new NoDataFoundException("Account have no transactions yet.");
		else
			return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/transfers")
	public ResponseEntity<?> getAllTransfers() throws NoDataFoundException {
		List<Transfer> list = service.getAllTransfers();
		if (list.isEmpty())
			throw new NoDataFoundException("Account have no transfers yet. ");
		else
			return new ResponseEntity<>(list, HttpStatus.OK);
	}
}

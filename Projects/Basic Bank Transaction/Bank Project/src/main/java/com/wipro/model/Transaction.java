package com.wipro.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private LocalDateTime dateTime;
	private long accountNum;
	private double balance;
	private long transactionId;
	private String status;

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transaction(int id, LocalDateTime dateTime, long accountNum, double balance, long transactionId,
			String status) {
		super();
		this.id = id;
		this.dateTime = dateTime;
		this.accountNum = accountNum;
		this.balance = balance;
		this.transactionId = transactionId;
		this.status = status;
	}

	public Transaction(int id, long accountNum, double balance, long transactionId, String status) {
		super();
		this.id = id;
		// this.dateTime = dateTime;
		this.accountNum = accountNum;
		this.balance = balance;
		this.transactionId = transactionId;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public long getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(long accountNum) {
		this.accountNum = accountNum;
	}

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", dateTime=" + dateTime + ", accountNum=" + accountNum + ", balance="
				+ balance + ", transactionId=" + transactionId + ", status=" + status + "]";
	}

}

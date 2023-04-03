package com.wipro.utils;

import javax.validation.constraints.Positive;

public class AccountTransfer {

	private long sourceAccountNum;
	private long targetAccountNum;

	@Positive(message = "Transfer amount must be positive")
	private double amount;

	public AccountTransfer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccountTransfer(long sourceAccountNum, long targetAccountNum,
			@Positive(message = "Transfer amount must be positive") double amount) {
		super();
		this.sourceAccountNum = sourceAccountNum;
		this.targetAccountNum = targetAccountNum;
		this.amount = amount;
	}

	public long getSourceAccountNum() {
		return sourceAccountNum;
	}

	public void setSourceAccountNum(long sourceAccountNum) {
		this.sourceAccountNum = sourceAccountNum;
	}

	public long getTargetAccountNum() {
		return targetAccountNum;
	}

	public void setTargetAccountNum(long targetAccountNum) {
		this.targetAccountNum = targetAccountNum;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "AccountTransfer [sourceAccountNum=" + sourceAccountNum + ", targetAccountNum=" + targetAccountNum
				+ ", amount=" + amount + "]";
	}

}

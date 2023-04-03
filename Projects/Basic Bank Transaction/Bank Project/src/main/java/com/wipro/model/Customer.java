package com.wipro.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) // IDENTITY
	int eid;

	String ename;

	String email;

	@OneToOne(cascade = CascadeType.ALL) // EXTRA ADDED --- fetch = FetchType.EAGER
	@JoinColumn(name = "addr_id", referencedColumnName = "aid")
	Address addr;

	@OneToOne(cascade = CascadeType.ALL) // EXTRA ADDED --- fetch = FetchType.EAGER
	@JoinColumn(name = "acc_id", referencedColumnName = "accountId")
	Account acc;

	public Customer() {
		super();
	}

	public Customer(int eid, String ename, String email, Address addr, Account acc) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.email = email;
		this.addr = addr;
		this.acc = acc;
	}

	public Customer(String ename, String email, Address addr, Account acc) {
		super();
		this.ename = ename;
		this.email = email;
		this.addr = addr;
		this.acc = acc;
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddr() {
		return addr;
	}

	public void setAddr(Address addr) {
		this.addr = addr;
	}

	public Account getAcc() {
		return acc;
	}

	public void setAcc(Account acc) {
		this.acc = acc;
	}

	@Override
	public String toString() {
		return "Customer [eid=" + eid + ", ename=" + ename + ", email=" + email + ", addr=" + addr + ", acc=" + acc
				+ "]";
	}

}

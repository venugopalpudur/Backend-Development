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
@Table(name="empaug29")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int eid;
	
	String ename;
	
	String email;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="addr_id", referencedColumnName="aid")
	Address addr;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Employee() {
		super();
	}

	public Employee(int eid, String ename, Address addr) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.addr=addr;
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
	
	

	public Address getAddr() {
		return addr;
	}

	public void setAddr(Address addr) {
		this.addr = addr;
	}

	@Override
	public String toString() {
		return "Employee [eid=" + eid + ", ename=" + ename + "]";
	}
	
	
}

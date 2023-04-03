package com.wipro;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Employee {
	
	private int eid;
	private String ename;
	
	@Autowired (required=false)
	@Qualifier(value="address")
	private Address address;
	
	public Employee(int eid, String ename, Address address) {
		System.out.println("Employee arg constructor is called");
		this.eid = eid;
		this.ename = ename;
		this.address = address;
	}
	
	//@Autowired
	public Employee( Address address) { // autowire="constructor"
		System.out.println("Employee Address type parameterized constructor is called");
		this.address = address;
	}
	
	public Employee() {
		System.out.println("Employee No arg constructor is called");
	}

	public Address getAddress() {
		return address;
	}

	//@Autowired
	public void setAddress(Address address) {
		System.out.println("Employee setAddress() is called");
		this.address = address;
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		System.out.println("Employee setEid( ) is called");
		this.eid = eid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		System.out.println("Employee setEname( ) is called");
		this.ename = ename;
	}
	
	@Override
	public String toString() {
		return "Employee [eid=" + eid + ", ename=" + ename + ", address=" + address + "]";
	}
	
	@PreDestroy
	public void destroy() {
		System.out.println("Employee destroy() is called");		
		}

		@PostConstruct
		public void init() {
			System.out.println("Employee init() is called during initialization -----");
		}
		
}

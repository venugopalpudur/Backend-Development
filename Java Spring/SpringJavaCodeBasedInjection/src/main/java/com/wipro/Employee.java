package com.wipro;

public class Employee {
	
	private int eid;
	private String ename;
	private Address address;
	
	public Employee(int eid, String ename, Address address) {
		System.out.println("Employee arg constructor is called");
		this.eid = eid;
		this.ename = ename;
		this.address = address;
	}
	
	public Employee() {
		System.out.println("Employee No arg constructor is called");
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
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
}

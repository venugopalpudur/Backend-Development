package com;

public class Employee {
	
	private int eid;
	private String ename;
	
	public Employee() {
		System.out.println("Employee No arg constructor is called");
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
		return "Employee [eid=" + eid + ", ename=" + ename + "]";
	}
}

package com.wipro;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Employee { // implements InitializingBean, DisposableBean{
	
	private int eid;
	private String ename;
	private List<Address> addresses;
	
	public Employee(int eid, String ename, List<Address> addresses) {
		System.out.println("Employee arg constructor is called");
		this.eid = eid;
		this.ename = ename;
		this.addresses = addresses;
	}
	
	public Employee() {
		System.out.println("Employee No arg constructor is called");
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
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
		return "Employee [eid=" + eid + ", ename=" + ename + ", addresses=" + addresses + "]";
	}

	/*@Override
	public void afterPropertiesSet() throws Exception {
	System.out.println("Employee afterPropertiesSet() is called during initialization");		
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("Employee destroy() is called during initialization");
	}*/
	
	@PreDestroy
	public void destroy() {
		System.out.println("Employee destroy() is called");		
		}

		@PostConstruct
		public void init() {
			System.out.println("Employee init() is called during initialization -----");
		}
		
}

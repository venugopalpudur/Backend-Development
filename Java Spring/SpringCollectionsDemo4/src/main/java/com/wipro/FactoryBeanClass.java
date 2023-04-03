package com.wipro;

public class FactoryBeanClass {
	// factory method
	public Employee getEmployee() {
		System.out.println("Factory method is called --------");
		return new Employee();
	}
}

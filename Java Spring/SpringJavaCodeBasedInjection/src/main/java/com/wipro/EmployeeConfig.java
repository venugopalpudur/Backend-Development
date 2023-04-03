package com.wipro;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeConfig {
	
	@Bean
	public Employee getEmployee() {
		Employee e = new Employee();
		e.setEid(101);
		e.setEname("Geetha");
		e.setAddress(getAddr());
		return e;
	}
	
	@Bean
	public Address getAddr() {
		return new Address(461, "Bangalore", "KA", "India");
	}
}

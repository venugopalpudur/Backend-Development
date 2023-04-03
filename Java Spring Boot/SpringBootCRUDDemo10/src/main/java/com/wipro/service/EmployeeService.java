package com.wipro.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wipro.model.Employee;

@Service
public interface EmployeeService {
	
	public Employee addEmployee(Employee e);
	public List<Employee> getAllEmployees();
	public Employee getEmployeeById(int eid);
	public Employee updateEmployee(int eid, Employee emp);
	public String deleteEmployee(int eid);
	public Employee getEmployeeByEmail(String email);
}

package com.wipro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDAO repository;
	
	@Override
	public Employee addEmployee(Employee e) {
		// TODO Auto-generated method stub
		return repository.addEmployee(e);
	}

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return repository.getAllEmployees();
	}

	@Override
	public Employee getEmployeeById(int eid) {
		// TODO Auto-generated method stub
		return repository.getEmployeeById(eid);
	}

	@Override
	public Employee updateEmployee(int eid, Employee emp) {
		// TODO Auto-generated method stub
		return repository.updateEmployee(eid, emp);
	}

	@Override
	public String deleteEmployee(int eid) {
		// TODO Auto-generated method stub
		return repository.deleteEmployee(eid);
	}

}

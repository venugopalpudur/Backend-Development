package com.wipro;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	//@Autowired
	//EmployeeDAO repository;
	
	@Autowired
	EmployeeRepository repository;
	
	@Override
	public Employee addEmployee(Employee e) {
		return repository.save(e);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return repository.findAll();
	}

	@Override
	public Employee getEmployeeById(int eid) {
		Optional op=repository.findById(eid);
		if(op.isPresent()) {
			return (Employee) op.get();
		}
		else
			return null;
	}

	@Override
	public Employee updateEmployee(int eid, Employee emp) {
		Optional op=repository.findById(eid);
		Employee e;
		if(op.isPresent()) {
			e=(Employee) op.get();
			e.setEid(emp.getEid());
			e.setEname(emp.getEname());
			repository.save(e);
			return e;
		}else
			return null;
	}

	@Override
	public String deleteEmployee(int eid) {
		Optional op=repository.findById(eid);
		Employee e;
		if(op.isPresent()) {
			e=(Employee) op.get();

			repository.deleteById(eid);;
			return "Deleted Successfully";
		}else
			return "Object not found with given ID";	}

}

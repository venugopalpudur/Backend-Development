package com.wipro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class EmployeeDAO {

	static List<Employee> emplist = new ArrayList<Employee>();

	public Employee addEmployee(Employee e) {
		emplist.add(e);
		return e;
	}

	public List<Employee> getAllEmployees() {
		return emplist;
	}

	public Employee getEmployeeById(int eid) {

		Employee e = null;
		for (Employee emp : emplist) {
			if (emp.getEid() == eid) {
				e = emp;
				break;
			}
		}
		return e;
	}

	public Employee updateEmployee(int eid, Employee e) {

		Employee emp = getEmployeeById(eid);

		if (emp != null) {
			emp.setEname(e.getEname());
		}
		return emp;
	}

	public String deleteEmployee(int eid) {

		Employee emp = getEmployeeById(eid);
		if (emp != null) {
			emplist.remove(emp);
			return "Deleted Successfully";
		} else {
			return "Object not found with given ID";
		}
	}
}

package com.wipro.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	public Employee findByEmail(String email);
	
	//@Query()
	//public List<Employee> findEmployeeByEmailEndingWithCom(String email);

	//@Query()
	//public List<Employee> findEmployeeBySalRange(long start, long end);
}

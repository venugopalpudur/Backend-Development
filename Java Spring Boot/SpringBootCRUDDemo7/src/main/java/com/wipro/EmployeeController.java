package com.wipro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService service;
	
	@PostMapping("/add")
	public ResponseEntity<?> addEmployee(@RequestBody Employee emp) {
		Employee e=service.addEmployee(emp);
		return new ResponseEntity<>(e, HttpStatus.CREATED);
	}
	
	@GetMapping("/getall")
	public ResponseEntity<?> getAllEmployees(){
		List<Employee> list=service.getAllEmployees();
		if(list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else
			return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	
	@GetMapping("/get/{eid}")
	public ResponseEntity<?> getEmployeesById(@PathVariable("eid") int eid){
		Employee emp=service.getEmployeeById(eid);
		if(emp!=null)
			return new ResponseEntity<>(emp, HttpStatus.OK);
		else
			return new ResponseEntity<>("Object not found with given id", HttpStatus.NOT_FOUND);
	}
	
	
	@PutMapping("/update/{eid}")
	public ResponseEntity<?> updateEmployeesById(@PathVariable("eid") int eid, @RequestBody Employee emp){
		Employee e=service.updateEmployee(eid, emp);
		if(emp!=null)
			return new ResponseEntity<>(emp, HttpStatus.OK);
		else
			return new ResponseEntity<>("Object not found with given id", HttpStatus.NOT_FOUND);

	}
	
	
	@DeleteMapping("/delete/{eid}")
	public ResponseEntity<?> deleteEmployeesById(@PathVariable("eid") int eid){
		String msg=service.deleteEmployee(eid);
		return new ResponseEntity<>(msg, HttpStatus.OK);

	}
	
}

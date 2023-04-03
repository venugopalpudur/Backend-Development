package com.wipro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeDAO dao;

	@Autowired
	RestTemplate templat;

	@RequestMapping("/create")
	public String createTable() {
		return dao.createTable();
	}

	@PostMapping("/add")
	public String addEmployee(@RequestBody Employee emp) {
		return dao.insertData(emp);
	}

	@GetMapping("/getall")
	public List<Employee> getAllEmployees() {
		return dao.getEmployees();
	}

	@GetMapping("/get/{eid}")
	public Employee getEmployeesById(@PathVariable("eid") int eid) {
		return dao.getEmployeesById(eid);
	}

	@PutMapping("/update/{eid}")
	public String updateEmployee(@PathVariable("eid") int eid, @RequestBody Employee emp) {
		return dao.updateEmployee(eid, emp);
	}

	@DeleteMapping("/delete/{eid}")
	public String deleteEmployee(@PathVariable("eid") int eid) {
		return dao.deleteEmployee(eid);
	}

	@GetMapping("/getfakeservicedata")
	public List<?> getData() {
		return templat.getForObject("https://jsonplaceholder.typicode.com/posts", List.class);
	}
}

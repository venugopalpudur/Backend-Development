package com.wipro;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
			
		Employee employee = context.getBean(Employee.class);
		
		employee.setEid(1009);
		employee.setEname("Krish");
		
		EmployeeDAO dao= context.getBean(EmployeeDAO.class);
		
		//dao.createTable();
		
		dao.insertData(employee);
		
		List<Employee> list = dao.getEmployees();
		
		list.forEach(System.out::println);
		
		//System.out.println("----------"+dao.getEmployeeById(1001));
		
		//System.out.println("Employee Details =" +employee);

	}

}

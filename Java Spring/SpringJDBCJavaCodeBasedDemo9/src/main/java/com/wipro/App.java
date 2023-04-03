package com.wipro;

import java.util.List;  

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		
		//ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		
		ApplicationContext context = new AnnotationConfigApplicationContext(EmployeeConfig.class);
		Employee employee = context.getBean(Employee.class);
		
		employee.setEid(1010);
		employee.setEname("Ram");
		
		EmployeeDAO dao= context.getBean(EmployeeDAO.class);
		
		//dao.createTable();
		
		dao.insertData(employee);
		
		List<Employee> list = dao.getEmployees();
		
		list.forEach(System.out::println);
		
		//System.out.println("----------"+dao.getEmployeeById(1001));
		
		//System.out.println("Employee Details =" +employee);

	}

}

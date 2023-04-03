package com.wipro;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		
		Employee employee = context.getBean(Employee.class);
		
		EmployeeDAO dao= context.getBean(EmployeeDAO.class);
		
		dao.insertData(employee);
		List<Employee> list = dao.getAllEmployees();
		list.forEach(System.out::println);
		
		System.out.println(dao.getEmployeeById(1));
		
		Employee emp = new Employee();
		emp.setEname("NewName");
		emp.setEid(2);
		
		System.out.println(dao.updateEmployee(2, emp));
		
		System.out.println(dao.deleteEmployee(4));
		
		list = dao.getAllEmployees();
		list.forEach(System.out::println);
		
    }
}

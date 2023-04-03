package com.wipro;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
			
		Employee employee = context.getBean(Employee.class);
		
		EmployeeDAO dao= context.getBean(EmployeeDAO.class);
		
		//dao.createTable();
		
		
		Scanner sc = new Scanner(System.in);
		String check;
		
		do {
			System.out.println("1. Insert Data\n 2. Retrieve Data\n 3. Retrieve Data By ID\n 4. Update Data By ID\n 5. Delete Data By ID");
			int choice=sc.nextInt();
			switch(choice) {
			case 1: dao.insertData(employee);
				break;
				
			case 2: List<Employee> list = dao.getEmployees();
					list.forEach(System.out::println); 
				break;
				
			case 3: System.out.println(dao.getEmployeeById(1001));
				break;
			
			case 4: dao.setEmployeeById(1001, "Damodar");
				break;
				
			case 5: dao.deleteEmployeeById(1001);
				break;
			}
			System.out.println("Do you want to continue ? y/n");
			check=sc.next();
		}while(check.equals("y"));
	}

}

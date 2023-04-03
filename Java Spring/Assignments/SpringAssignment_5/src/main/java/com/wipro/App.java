package com.wipro;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) {

		ApplicationContext context = new AnnotationConfigApplicationContext(SutdentConfig.class);
		Student st = context.getBean(Student.class);

		StudentDAO dao = context.getBean(StudentDAO.class);

		// 

		Scanner sc = new Scanner(System.in);
		String check;

		do {
			System.out.println(
					" 1. Create Table\n 2. Preload List of 10 Students Data\n 3. Insert Data\n 4. Retrieve Data\n 5. Retrieve Data By ID\n 6. Update Data By ID\n 7. Delete Data By ID\n 8. Delete All");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:dao.createTable();
				break;
				
			case 2: List<Student> list = new ArrayList<Student>();
					list.add(new Student(1, "Ajay", new Address(25, "Pune", "MH", "India")));
					list.add(new Student(2, "Vijay", new Address(125, "Mumbai", "MH", "India")));
					list.add(new Student(3, "Ganesh", new Address(50, "Thane", "MH", "India")));
					list.add(new Student(4, "Ram", new Address(68, "Hyderabad", "TG", "India")));
					list.add(new Student(5, "Ravi", new Address(155, "Chennai", "TN", "India")));
					list.add(new Student(6, "John", new Address(855, "Texas", "Texas", "USA")));
					list.add(new Student(7, "Raj", new Address(145, "Delhi", "Delhi", "India")));
					list.add(new Student(8, "Dharma", new Address(964, "Kolkata", "WB", "India")));
					list.add(new Student(9, "Navin", new Address(111, "Bangalore", "KA", "India")));
					list.add(new Student(10, "Steve", new Address(236, "Los Angelas", "California", "USA")));
					dao.preload(list);
				break;
				
			case 3:
				System.out.println("Enter Student ID: ");
				st.setStudentId(sc.nextInt());
				System.out.println("Enter Student Name: ");
				st.setStudentName(sc.next());
				System.out.println("Enter the H.No.: ");
				int hnn = sc.nextInt();
				System.out.println("Enter the City: ");
				String ct = sc.next();
				System.out.println("Enter the State: ");
				String stt = sc.next();
				System.out.println("Enter the Country: ");
				String ctry = sc.next();
				st.setAddress(new Address(hnn, ct, stt, ctry));
				dao.addStudent(st);
				break;

			case 4:
				List<Student> lis = dao.getAllDetails();
				lis.forEach(x-> System.out.printf(" %-10d %-10s %-10d %-15s %-13s %-10s\n", x.getStudentId(),x.getStudentName(), x.getAddress().getHno(), x.getAddress().getCity(), x.getAddress().getState(), x.getAddress().getCountry() ));
				System.out.println("");
				break;

			case 5:
				System.out.println("Enter Student ID to be Retrieved: ");
				Student xx= dao.getDetails(sc.nextInt());
				System.out.printf(" %-10d %-10s %-10d %-15s %-13s %-10s\n", xx.getStudentId(),xx.getStudentName(), xx.getAddress().getHno(), xx.getAddress().getCity(), xx.getAddress().getState(), xx.getAddress().getCountry() );
				System.out.println();
				break;

			case 6:
				System.out.println("Student ID to be Updated: ");
				int id=sc.nextInt();
				System.out.println("Enter the Student Name: ");
				String name = sc.next();
				System.out.println("Enter the H.No.: ");
				int hn = sc.nextInt();
				System.out.println("Enter the City: ");
				String city = sc.next();
				System.out.println("Enter the State: ");
				String state = sc.next();
				System.out.println("Enter the Country: ");
				String country = sc.next();
				dao.setStudentsById(id, name, hn, city, state, country);
				break;

			case 7:
				System.out.println("Student ID to be deleted: ");
				int sid=sc.nextInt();
				dao.deleteStudentsById(sid);
				break;
			case 8:
				dao.deleteStudents();
				break;
			}
			System.out.println("Do you want to continue ? y/n");
			check = sc.next();
		} while (check.equals("y"));
	}

}

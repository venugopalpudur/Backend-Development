package com.wipro;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		Student st = context.getBean("st1", Student.class);
		System.out.println("Student Details = "+st);
		
		Student st1 = context.getBean("st2", Student.class);
		System.out.println("Student Details = "+st1);
		
	}
}

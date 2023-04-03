package com.wipro;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

		Movie movie = context.getBean("mv", Movie.class);

		System.out.println("Employee Details =" + movie);

	}

}

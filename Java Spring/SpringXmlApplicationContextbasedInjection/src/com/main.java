package com;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class main {

	public static void main(String[] args) {

		// Resource resource = new ClassPathResource("com/beans.xml");

		// BeanFactory factory = new XmlBeanFactory(resource);

		ApplicationContext context = new ClassPathXmlApplicationContext("com/beans.xml");

		Employee employee = context.getBean("emp", Employee.class);

		System.out.println("Employee Details Injected using Setter injection =" + employee);

		employee = context.getBean("emp1", Employee.class);

		System.out.println("Employee Details Injected using Constructor injection =" + employee);

	}

}

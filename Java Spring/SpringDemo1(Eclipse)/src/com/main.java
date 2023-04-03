package com;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class main {

	public static void main(String[] args) {
		
		Resource resource = new ClassPathResource("com/beans.xml");
		
		BeanFactory factory = new XmlBeanFactory(resource);
		
		Employee employee = factory.getBean("emp", Employee.class);
		
		System.out.println("Employee Details =" +employee);

	}

}

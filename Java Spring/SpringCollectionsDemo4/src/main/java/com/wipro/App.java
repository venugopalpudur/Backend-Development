package com.wipro;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Employee emp = context.getBean("emp", Employee.class);
        System.out.println("Employee Details = "+ emp);
        System.out.println("Employee Object1 = "+ emp.hashCode());
        
       /* context=null;
        Employee emp1 = context.getBean("emp", Employee.class);
        System.out.println("Employee Object1 = "+ emp1.hashCode());
*/
        /*Employee emp1 = context.getBean("emp", Employee.class);
        System.out.println("Employee Details = "+ emp);
        System.out.println("Employee Object1 = "+ emp1.hashCode());
        */
    }
}

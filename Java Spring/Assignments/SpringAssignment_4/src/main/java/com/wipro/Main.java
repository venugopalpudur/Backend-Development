package com.wipro;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(PlayerConfig.class);

		for(int i=1; i<6; i++) {
			Player pl = context.getBean("player"+i, Player.class);
			System.out.println("Player Details ="+pl);
		}
		
		System.out.println("\nType Country Name :");
		Scanner sc = new Scanner(System.in);
		String Country = sc.nextLine();
		for(int i=1; i<6; i++) {
			Player p = context.getBean("player"+i, Player.class);
			if(p.getCountry().getCountryName().equalsIgnoreCase(Country)) {
				System.out.println(p);
			}
		}
		sc.close();
	}
}

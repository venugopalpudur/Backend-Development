package com.wipro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication // == @Configuration + @ComponentScan + @EnableAutoConfiguration
@ComponentScan(basePackages = { "com.wipro.reo", "com.wipro.controller", "com.wipro.model", "com.wipro.service",
		"com.wipro.exceptions" })
public class SpringBootCaseStudy {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCaseStudy.class, args);
	}

	/*
	 * @Bean public RestTemplate getTemplate() { return new RestTemplate(); }
	 */
}

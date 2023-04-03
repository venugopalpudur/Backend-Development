package com.wipro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication // == @Configuration + @ComponentScan + @EnableAutoConfiguration
@ComponentScan(basePackages={"com.wipro.reo", "com.wipro.controller", "com.wipro.model", "com.wipro.service", "com.wipro.exceptions"})
public class SpringBootCrudDemo9Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCrudDemo9Application.class, args);
	}

	/*@Bean
	public RestTemplate getTemplate() {
		return new RestTemplate();
	}*/
}

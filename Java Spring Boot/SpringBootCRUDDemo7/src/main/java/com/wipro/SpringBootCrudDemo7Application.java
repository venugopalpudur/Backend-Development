package com.wipro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication // == @Configuration + @ComponentScan + @EnableAutoConfiguration
public class SpringBootCrudDemo7Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCrudDemo7Application.class, args);
	}

	/*@Bean
	public RestTemplate getTemplate() {
		return new RestTemplate();
	}*/
}

package com.wipro;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/")
	public String sayHello() {
		return "Hello, World";
	}
	

	@GetMapping("/{name}")
	public String sayHelloWithName(@PathVariable("name") String name) {
		return "Hello,"+name;
	}
}

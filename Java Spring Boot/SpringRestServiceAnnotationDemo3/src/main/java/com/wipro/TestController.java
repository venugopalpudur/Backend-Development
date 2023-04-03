package com.wipro;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Controller
//@ResponseBody
public class TestController {

	@RequestMapping(value="/test", produces="text/xml")
	public String testMessage() {
		return "Test message from Spring rest controller class";
	}

	@RequestMapping("/time")
	public String getTo() {
		return new Date().toString();
	}

	@RequestMapping("/date")
	public Date getToday() {
		return new Date();
	}

	@RequestMapping(value="/emp", produces="application/json")
	//@RequestMapping("/emp")
	public Employee getEmployee() {
		return new Employee(1, "Raja");
	}
	
	@RequestMapping(value="/testhtml", produces="text/html")
	public String getmsg() {
		return "<body bgcolor='cyan'>Your name is what</body>";
	}
	
}

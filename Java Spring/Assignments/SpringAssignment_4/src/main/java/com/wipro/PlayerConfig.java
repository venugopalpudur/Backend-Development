package com.wipro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class PlayerConfig {

	@Bean("player1")
	public Player getPlayer1() {
		return new Player("P001", "Sachin", getCountry1());
	}
	
	@Bean("player2")
	public Player getPlayer2() {
		return new Player("P002", "arjun", getCountry2());
	}
	

	@Bean("player3")
	public Player getPlayer3() {
		return new Player("P003", "Sam", getCountry1());
	}
	
	@Bean("player4")
	public Player getPlayer4() {
		return new Player("P004", "Ganesh", getCountry2());
	}
	
	@Bean("player5")
	public Player getPlayer5() {
		return new Player("P005", "Raghu", getCountry1());
	}
	
	@Bean
	public Country getCountry1(){
		return new Country("C001", "India");
 	}
	
	@Bean
	public Country getCountry2(){
		return new Country("C002", "USA");
 	}
}

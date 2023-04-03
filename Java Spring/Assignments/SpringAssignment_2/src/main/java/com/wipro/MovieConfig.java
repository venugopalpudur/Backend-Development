package com.wipro;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MovieConfig {
	
	@Bean
	public Movie getMovie() {
		Movie e = new Movie();
		e.setMovieId("M001");
		e.setMovieName("The Firm");
		e.setMovieActor("Tom Cruise");
		return e;
	}
}

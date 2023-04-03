package com.wipro;

public class Movie {
	
	private String movieId;
	private String movieName;
	private String movieActor;	
	
	public Movie(String movieId, String movieName, String movieActor) {
		super();
		this.movieId = movieId;
		this.movieName = movieName;
		this.movieActor = movieActor;
	}

	public Movie() {
		System.out.println("Movie No arg constructor is called");
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		System.out.println("Movie setMovieId( ) is called");
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		System.out.println("Movie setMovieName( ) is called");
		this.movieName = movieName;
	}

	public String getMovieActor() {
		return movieActor;
	}

	public void setMovieActor(String movieActor) {
		System.out.println("Movie setMovieActor( ) is called");
		this.movieActor = movieActor;
	}

	@Override
	public String toString() {
		return "Movie [movieId=" + movieId + ", movieName=" + movieName + ", movieActor=" + movieActor + "]";
	}

}

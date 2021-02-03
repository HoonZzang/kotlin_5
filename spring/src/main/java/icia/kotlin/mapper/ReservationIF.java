package icia.kotlin.mapper;

import java.util.ArrayList;

import icia.kotlin.bean.Movie;

public interface ReservationIF {
	public ArrayList<Movie> getMovieList();
	public ArrayList<Movie> getMovieDate(Movie movie);
}

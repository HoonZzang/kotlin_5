package icia.kotlin.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import icia.kotlin.beans.Member;
import icia.kotlin.beans.Movie;
import icia.kotlin.mapper.MapperInterface;
import icia.kotlin.mapper.ReservationIf;

@Service
public class Reservation {
	@Autowired
	private ReservationIf mapper;
	@Autowired
	private PlatformTransactionManager tran;
	@Autowired
	private Gson gson;
	
	public ModelAndView entrance(Movie movie) {
		
		ModelAndView mav = null;
		
		if(movie.getMvCode() == null) {
			mav = this.movieListCtl(movie);
		}else {
			switch(movie.getSCode()){
			case "Step2":
				mav = this.screeningDate(movie);
				break;
			case "Step3":
	            mav = this.screeningTime(movie);
	            break;
			case "Step4":
	            mav = this.screenseat(movie);
	            break;
			}
		}
		return mav;
	}
	
	private ModelAndView screenseat(Movie movie) {
	      ModelAndView mav = new ModelAndView();
	      System.out.println(movie);
	      mav.setViewName("step4");
	      mav.addObject("Access", this.getCurrentDate('f'));
	      
	      return mav;
	   }

	/* Screening Time */
	private ModelAndView screeningTime(Movie movie) {
	      ModelAndView mav = new ModelAndView();
	      /* Start Date */
	      mav.addObject("Access", this.getCurrentDate('d'));
	      
	      
	      /* Movie Info & Convert to JSON */
	      String jsonData = gson.toJson(this.getScreening(movie));
	      System.out.println("jsonData=" + jsonData); 
	      mav.addObject("ScreeningData", jsonData);   
	      
	      return mav;
	   }
	   
	   
	private ArrayList<Movie> getScreening(Movie movie) {
		return mapper.getScreening(movie);
	}

	/* Screening Date */
	private ModelAndView screeningDate(Movie movie) {
		ModelAndView mav = new ModelAndView();
		/* Start Date */
		mav.addObject("Access", this.getCurrentDate('d'));
		
		/* Movie Info & Convert to JSON */
		String jsonData = gson.toJson(this.getMovieList(movie));
		mav.addObject("movieData", jsonData);	
		
		/* View */
		mav.setViewName("step2");
		return mav;
	}
	
	/* Movie List */
	private ModelAndView movieListCtl(Movie movie) {
		ModelAndView mav = new ModelAndView();
				
		/* AccessTime */
		
		mav.addObject("Access", this.getCurrentDate('f'));
		
		/* Convert to JSON*/
		String jsonData = gson.toJson(this.getMovieList(movie));
		mav.addObject("jsonData", jsonData);
		
		/* View */
		mav.setViewName("home");
		return mav;
	}
	
	private ArrayList<Movie> getMovieList(Movie movie){	
		return mapper.getMovieList(movie);
	}
	
	/* Current DataTime */
	private String getCurrentDate(char dateType) {
		Date date = new Date();
		
		SimpleDateFormat sdf = (dateType=='f')? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E요일") :
			(dateType=='d')? new SimpleDateFormat("yyyy-MM-dd"): 
				(dateType=='t')? new SimpleDateFormat("HH:mm E요일") : null;
				
		return sdf.format(date);
	}
}

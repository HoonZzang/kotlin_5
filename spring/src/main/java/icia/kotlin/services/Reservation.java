package icia.kotlin.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.ModelAndView;

import icia.kotlin.bean.Movie;
import icia.kotlin.mapper.ReservationIF;

@Service
public class Reservation {
	@Autowired
	private ReservationIF mapper;
	@Autowired
	private PlatformTransactionManager tran;
	
	public ModelAndView entrance(Movie movie) {
		ModelAndView mav = null;
		System.out.println(movie.getMvCode());
		if(movie.getMvCode() == null) {
			mav = this.movieListCtl();
		}else {
			System.out.println("else 진입");
				mav = this.movieDateCtl(movie);
		}
		
		return mav;
	}

	private ModelAndView movieListCtl() {
		ModelAndView mav = new ModelAndView();
		
		/* AccessTIme */
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E요일");
		mav.addObject("Access", sdf.format(date));
		
		System.out.println(this.getMovieList().size());
		//select?MVCODE= + movie.getMvCode()
		mav.addObject("makeSelect", makeSelectMovie(this.getMovieList()));
		mav.setViewName("home");
		return mav;
	}
	
	private ModelAndView movieDateCtl(Movie movie) {
		ModelAndView mav = new ModelAndView();
		
		/* AccessTIme */
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E요일");
		mav.addObject("Access", sdf.format(date));
		mav.addObject("makeDate", makeDateMovie(this.getMovieDate(movie)));
		mav.setViewName("movieDate");
		return mav;
	}
	
	private ArrayList<Movie> getMovieList(){
		return mapper.getMovieList();
	}
	
	private ArrayList<Movie> getMovieDate(Movie movie) {
		return mapper.getMovieDate(movie);
	}
	
	//SelectMovie MakeHTML
	private String makeSelectMovie(ArrayList<Movie> sMovie) {
		StringBuffer sb = new StringBuffer();
	      int index = 0;
	      for (Movie movie : sMovie) {
	         
	         index++;
	         
	         // 3으로 나눠서 1이 나오면 이미를 하나씩 3개까지 출력
	         if (index % 3 == 1) {
	            sb.append("<div style=\"display: flex\">");
	         }
	         sb.append("<div onClick=\"movieDate(\'" + movie.getMvCode() + "\')\">");
	         sb.append("<div><img src=\"./resources/img/" + movie.getMvImage() + "\" /></div>");
	         sb.append("<div>" + movie.getMvName() +" / "+ movie.getMvGrade() + "등급</div>");
	         sb.append("</div><br><br>");

	         // 3번째 상품씩 넘기기
	         if (index % 3 == 0) {
	            sb.append("</div>");
	         }
	      }
	      if (index % 3 != 0) {
	         sb.append("</div>");
	      }
	      return sb.toString();
	   }
	
	//DateMovie MakeHTML
	private String makeDateMovie(ArrayList<Movie> sMovie) {
		StringBuffer sb = new StringBuffer();
	      int index = 0;
	      for (Movie movie : sMovie) {
	         
	         index++;
	         
	         // 3으로 나눠서 1이 나오면 이미를 하나씩 3개까지 출력
	         if (index % 3 == 1) {
	            sb.append("<div style=\"display: flex\">");
	         }
	         sb.append("<div onClick=\"movieDate(\'" + movie.getMvCode() + "\')\">");
	         sb.append("<div><img src=\"./resources/img/" + movie.getMvImage() + "\" /></div>");
	         sb.append("<div>" + movie.getMvName() +" / "+ movie.getMvGrade() + "등급</div>");
	         sb.append("<div>소개글 <br>" + movie.getMvComments() + "</div>");
	         sb.append("</div><br><br>");

	      }
	      return sb.toString();
	   }
}
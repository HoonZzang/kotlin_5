package icia.kotlin.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import icia.kotlin.bean.Member;
import icia.kotlin.bean.Movie;
import icia.kotlin.services.Authentication;
import icia.kotlin.services.Reservation;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	ModelAndView mav;
	@Autowired
	Authentication auth;
	@Autowired
	Reservation res;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(@ModelAttribute Movie movie) {
		mav = new ModelAndView();
		mav = res.entrance(movie);
		
		return mav;
	}
	@RequestMapping(value = "/movieDate", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView movieDate(@ModelAttribute Movie movie) {
		System.out.println(movie.getMvCode());
		return res.entrance(movie);

	}
	
	
	@RequestMapping(value = "/LoginForm", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView loginForm() {
		mav = new ModelAndView();
		mav.setViewName("loginForm");
		
		return mav;
	}
	
	@RequestMapping(value = "/Login", method = {RequestMethod.POST})
	public ModelAndView login(@ModelAttribute Member m) {
		
		return auth.entrance(m);
	}
	
}

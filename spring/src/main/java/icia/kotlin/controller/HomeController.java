package icia.kotlin.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import icia.kotlin.beans.Member;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
   
   private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
   
   /**
    * Simply selects the home view to render by returning its name.
    */
   @RequestMapping(value = "/", method = RequestMethod.GET)
   public ModelAndView home(Locale locale, ModelAndView mv) {
      logger.info("Welcome home! The client locale is {}.", locale);
      
      Date date = new Date();
      DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
      
      String formattedDate = dateFormat.format(date);
      
      mv.addObject("serverTime", formattedDate );
      mv.addObject("welcome", "어서오세요~ 환영합니다");
      
      mv.setViewName("home"); //jsp

//      try {
//         Class.forName("oracle.jdbc.driver.OracleDriver");
//         Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@106.243.194.230:7006:xe","JUN123","1234");
//         System.out.println("success");
//      } catch (Exception e) {
//         // TODO Auto-generated catch block
//         e.printStackTrace();
//      }
      
      return mv;
   }
   
   
   @RequestMapping(value = "LoginForm" , method = {RequestMethod.GET,RequestMethod.POST})
 public ModelAndView logInForm() {
	   ModelAndView mav = new ModelAndView();
	   mav.setViewName("loginForm");  //jsp
	   return mav;
   }
//   
//   @RequestMapping(value = "Login" , method = {RequestMethod.POST})
// public ModelAndView logIn(@RequestParam ("mId") String id ,@RequestParam ("mPwd") String pwd) {
//	   ModelAndView mav = new ModelAndView();
//	   mav.addObject("mId", id);
//	   mav.addObject("mPwd",pwd);
//	   
//	   mav.setViewName("loginForm");  //jsp
//	   
//	   return mav;
//   }
   @RequestMapping(value ="Login" , method = {RequestMethod.POST})
  public ModelAndView logIn(@ModelAttribute Member m, 
		  @RequestParam("memberInfo") String[] memberInfo) { //String[] member 저장된값을 @ModelAttribute에서 사용가능
	   //memberInfo 배열로 받을 수 있다 
	   ModelAndView mav = new ModelAndView();
	   mav.addObject("mId",m.getMId());
	   mav.addObject("mPwd",m.getMPwd());
	   mav.addObject("memberId" , memberInfo[0]);
	   mav.addObject("memberPwd" , memberInfo[1]);
	   mav.addObject("memberInfo",m.getMemberInfo()[0]);
	   
	   mav.setViewName("loginForm");  //jsp
	   
	   return mav;
  
   }
   
   
}
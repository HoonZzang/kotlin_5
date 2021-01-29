package icia.kotlin.services;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import icia.kotlin.beans.Member;

@Service
public class Authentication {

	public Authentication() {}
	
	public ModelAndView entrance(Member m) {
		ModelAndView mav = null;
		switch(m.getServiceCode()){
		case "Login":
			mav = this.logInCtl(m);
			break;
		}
		return mav;
	}

	public ModelAndView logInCtl(Member m) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("mId", m.getMId());
		mav.addObject("mPwd", m.getMPwd());
		mav.setViewName("loginForm");

		return mav;
	}

}

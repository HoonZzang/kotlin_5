package icia.kotlin.services;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import icia.kotlin.beans.Member;
import icia.kotlin.mapper.MapperInterface;
import lombok.Setter;

@Service
public class Authentication {

	public Authentication() {}
	
	@Autowired
	private MapperInterface mapper;
	
	public ModelAndView entrance(Member m) {
		ModelAndView mav = null;
		switch(m.getSCode()){
		case "Login":
			mav = this.logInCtl(m);
			break;
		}
		return mav;
	}
	
	

	public ModelAndView logInCtl(Member m) {
		ModelAndView mav = new ModelAndView();
		
		if(this.isMember(m)) {
			if(this.isAccess(m)) {
				mav.addObject("member", this.getMemberInfo(m));
			}
			
		}
		mav.setViewName("loginForm");

		return mav;
	}
	
	private boolean convertToBoolean(int value) {
		return value == 1? true : false;
	}
	
	// Member 여부 확인
	private boolean isMember(Member member) {
		
		return convertToBoolean(mapper.isMember(member));
	}
	// 패스워드 일치 확인
	private boolean isAccess(Member member) {
		
		return convertToBoolean(mapper.isAccess(member));
	}
	
	private Member getMemberInfo(Member member) {
		
		return mapper.getMemberInfo(member);
	}

}

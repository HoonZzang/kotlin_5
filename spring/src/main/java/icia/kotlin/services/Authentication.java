package icia.kotlin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import icia.kotlin.beans.Member;
import icia.kotlin.mapper.MapperInterface;


@Service
public class Authentication {
@Autowired
 private MapperInterface mapper;

public Authentication() {}
	
	public ModelAndView entrance(Member m) {
		ModelAndView mav = null;
		
		switch(m.getSCode()) {
		case "LogIn" :
			mav = logInCtl(m);
		break;
		}
	
		
		return mav;
	}

	private boolean convertToBoolean(int value) {
		return value==1? true:false;
	}
	
	//Member 여부확인
	private boolean isMember(Member m) {
		return convertToBoolean(mapper.isMember(m));
	}
	
	
	//Access 확인
	private boolean isAccess(Member m) {
		return convertToBoolean(mapper.isAccess(m));
	}
	private ModelAndView logInCtl(Member m) {
		ModelAndView mav;
		mav = new ModelAndView();
		//아이디 유무 확인
		if(this.isMember(m)) {
			//아이디 비밀번호 일치 확인
			if(this.isAccess(m)) {
				System.out.println("로그인 성공");
				//mav.addObject("memberInfo" , this.isMemberInfo(m));
				mav.addObject("mId" , this.isMemberInfo(m).getMId());
				mav.addObject("mName",this.isMemberInfo(m).getMName());
				mav.addObject("mPhone", this.isMemberInfo(m).getMPhone());
			}
		}
		mav.setViewName("loginForm");
		return mav;
	}
		//memberInfo 회원정보 
	private Member isMemberInfo(Member m) {
		
		return mapper.memberInfo(m);
	}
}

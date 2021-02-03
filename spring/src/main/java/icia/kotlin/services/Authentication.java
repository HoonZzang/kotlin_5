package icia.kotlin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.servlet.ModelAndView;

import icia.kotlin.bean.Member;
import icia.kotlin.mapper.MapperInterface;

@Service
public class Authentication {
	
	@Autowired
	private MapperInterface mapper;
	@Autowired
	private PlatformTransactionManager tran;
	public Authentication() {
		
	}
	
	public ModelAndView entrance(Member m) {
		ModelAndView mav = null;
		switch(m.getSCode()) {
		
		case "A":
			mav = this.logInCtl(m);
			break;
			
		}
		
		return mav;
	}
	
	public ModelAndView logInCtl(Member m) {
		ModelAndView mav = new ModelAndView();
		
		//현재 나의 트렌젝션을 기억한다.
		TransactionStatus status = tran.getTransaction(new DefaultTransactionDefinition());
		
		try {
			if(isMember(m)) {
				System.out.println("isMember");
				if(isAccess(m)) {
					System.out.println("isAccess");
					getMemberInfo(m);
					mav.addObject("mId", getMemberInfo(m).getMId());
					mav.addObject("mName", getMemberInfo(m).getMName());
					mav.addObject("mPhone", getMemberInfo(m).getMPhone());

					m.setMId("hoho2");
					m.setMName("hoy");
					m.setMPwd("4321");
					m.setMPhone("01012340988");


					this.insCustomer(m);
					this.insMovie(m);
					System.out.println("insert성공");
					tran.commit(status);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			tran.rollback(status);
			System.out.println("rollback 완료");
		}
		mav.setViewName("loginForm");
		return mav;
	}

	private boolean isAccess(Member member) {
		return convertToBoolean(mapper.isAccess(member));
	}

	private boolean isMember(Member member) {
		return convertToBoolean(mapper.isMember(member));
	}
	
	private Member getMemberInfo(Member member) {
		return mapper.getMemberInfo(member);
	}
	
	private boolean convertToBoolean(int value) {
		return value == 1 ? true : false;
	}
	
	/* Transaction 처리를 위한 메서드  1 */
	private int insCustomer(Member member) {
		return mapper.insCustomer(member);
	}
	
	/* Transaction 처리를 위한 메서드  2 */
	private int insMovie(Member member) {
		return mapper.insMovie(member);
	}
}

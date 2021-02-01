package icia.kotlin.services;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.servlet.ModelAndView;

import icia.kotlin.beans.Member;
import icia.kotlin.mapper.MapperInterface;
import lombok.Setter;

@Service
public class Authentication {

	public Authentication() {}
	
	@Autowired
	private MapperInterface mapper;
	@Autowired
	private PlatformTransactionManager tran;
	
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
		
		TransactionStatus status = tran.getTransaction(new DefaultTransactionDefinition());
		
		try {
			if(this.isMember(m)) {
				if(this.isAccess(m)) {
					mav.addObject("member", this.getMemberInfo(m));
//					/* TRANSACTION 처리를 위한 메서드 1: ST INSERT  */
//					m.setMId("noh2");
//					m.setMName("놉");
//					m.setMPwd("1577");
//					m.setMPhone("15771579");
//					this.insCustomer(m);
//					
//					/* TRANSACTION 처리를 위한 메서드 2: MV INSERT  */
//					m.setMvCode("77777777");
//					m.setMvName("도라에몽");
//					m.setMvGrade("A");
//					m.setMvStatus("I");
//					m.setMvImage("00000000.jpg");
//					m.setMvComments("대나무헬리콥터");
//					this.insMovie(m);
					
					tran.commit(status);
				}

			}
		}catch(Exception e) {
			e.printStackTrace();
			tran.rollback(status);
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
	
	// 회원정보 가져오기
	private Member getMemberInfo(Member member) {
		
		return mapper.getMemberInfo(member);
	}
	
//	/* SPRING FRAMEWORK에서의 TRANSACTION
//	*  1. @Transactional을 이용한 Transaction
//	*  2. AOP이용한 Transaction
//	*  3. Programmatic Transaction
//	*/
//	
//	/* TRANSACTION 처리를 위한 메서드 1 */
//	private int insCustomer(Member member) {
//		
//		return mapper.insCustomer(member);
//	}
//
//	/* TRANSACTION 처리를 위한 메서드 2 */
//	private int insMovie(Member member) {
//		
//		return mapper.insMovie(member);
//	}
}

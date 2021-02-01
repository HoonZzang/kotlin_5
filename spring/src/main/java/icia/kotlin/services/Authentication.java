package icia.kotlin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.servlet.ModelAndView;

import icia.kotlin.beans.Member;
import icia.kotlin.beans.Movie;
import icia.kotlin.mapper.MapperInterface;


@Service
public class Authentication {
@Autowired
 private MapperInterface mapper; //데이터타입이 완벽하게 일치해서 이름자유

@Autowired
private PlatformTransactionManager tran; // 부모클래스이다 자식을 포함할순있어도 트랜잭션  tran 데이터타입이 완벽하게 일치하지는 않아서 이름을 같게 해줘야함  
// root-context 참고 


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


	
	private ModelAndView logInCtl(Member m) {
		ModelAndView mav;
		mav = new ModelAndView();
		
		TransactionStatus status = tran.getTransaction(new DefaultTransactionDefinition()); //트랜잭션 시작
		//TransactionStatus : tran.getTransaction(new DefaultTransactionDefinition());
		//힙에있으니까 상태값으로 호출  그래서 TransactionStatus 커밋과 롤백 처리한다    
		
		try {
		//아이디 유무 확인
		if(this.isMember(m)) {
			//아이디 비밀번호 일치 확인
			if(this.isAccess(m)) {
				System.out.println("로그인 성공");
			  //mav.addObject("memberInfo" , this.isMemberInfo(m));
				
//				mav.addObject("mpwd",this.isMemberInfo(m).getMPwd());
//				mav.addObject("mId" , this.isMemberInfo(m).getMId());
//				mav.addObject("mName",this.isMemberInfo(m).getMName());
//				mav.addObject("mPhone", this.isMemberInfo(m).getMPhone());
				
				//Transaction 처리를 위한 메서드 1. : ST INSERT    1,2 연관성없지만 두개 동시에 인서트가능하는지 테스트 
				
				m.setMId("test72");
				m.setMPwd("1234");
				m.setMName("테스트이름8");
				m.setMPhone("01077774441");
				this.insCustomer(m);
				
				
				
				//Transaction 처리를 위한 메서드 2 : MV INSERT
				
				
				
				Movie movie = new Movie();
				movie.setMvCode("00008023");
				movie.setMvName("test영화");
				movie.setMvGrade("S");
				movie.setMvStatus("A");
				movie.setMvImage("00008023.jpg");
				movie.setMvComments("테스트 코멘트입니다");
				
				
				this.insMovie(movie);
				tran.commit(status);
				System.out.println("커밋성공");
				
				
				
			}
		}
		
	}catch(Exception e) {
		e.printStackTrace();
		tran.rollback(status);
		System.out.println("롤백");
	}
		
		
		mav.setViewName("loginForm");
		return mav;
	}
	
	private boolean convertToBoolean(int value) {
		return value==1? true:false;
	}
	
	//Member 여부확인
	private boolean isMember(Member m) {
		return convertToBoolean(mapper.isMember(m));
	}
	
	
	//Access 확인  액세스 가능 여부 : 패스워드 일치 여부
	private boolean isAccess(Member m) {
		return convertToBoolean(mapper.isAccess(m));
	}
		//memberInfo 회원정보 가져오기 
	private Member isMemberInfo(Member m) {
		
		
		return mapper.memberInfo(m);
	}
	
	
	private int insCustomer(Member member) {
		
		return mapper.insCustomer(member);
	}
	
	private int insMovie(Movie movie) {
		return mapper.insMovie(movie);
	}
	
	//spring framework에서 transaction
	//1.@Transactional 을 이용한 Transaction 메서드 단위
	//2.AOP를 이용한 Transaction 클래스단위  (관심지향적프로그램)
	//3.programmatic Transaction   원할떄 트랜잭션하는거
	
 
	
	
}

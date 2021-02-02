package icia.kotlin.mapper;


import icia.kotlin.bean.Member;

public interface MapperInterface {
	
	public int isMember(Member member);
	
	public int isAccess(Member member);
	
	public Member getMemberInfo(Member member);
	
	public int insCustomer(Member member);
	
	public int insMovie(Member member);
}

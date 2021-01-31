package icia.kotlin.mapper;

import org.apache.ibatis.annotations.Select;

import icia.kotlin.beans.Member;

public interface MapperInterface {
	@Select("SELECT SYSDATE FROM DUAL")
	public String getDate();
	public String getDate2();
	public int isMember(Member member);
	public int isAccess(Member member);
	public Member getMemberInfo(Member member);
}

package icia.kotlin.mapper;

import org.apache.ibatis.annotations.Select;

import icia.kotlin.beans.Member;

public interface MapperInterface {
	public int isMember(Member member);
	public int isAccess(Member member);
	public Member memberInfo(Member member);
}

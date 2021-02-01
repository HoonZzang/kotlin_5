package icia.kotlin.beans;

import lombok.Data;

@Data
public class Member {
	private String mId;
	private String mPwd;
	private String mName;
	private String mPhone;
	private String SCode;
	
	private String MvCode;
	private String MvName;
	private String MvGrade;
	private String MvStatus;
	private String MvImage;
	private String MvComments;
}

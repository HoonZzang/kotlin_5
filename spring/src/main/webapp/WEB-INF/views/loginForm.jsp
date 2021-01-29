<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LogInForm</title>
</head>
<body>
<!--    <input type="text" name="mId" placeholder="아이디를 입력해주세요"/> -->
<!--    <input type="password" name="mPwd" placeholder="패스워드를 입력해주세요"/> -->
<!--    <input type="button" value="서버 요청" onClick="moveLoginForm()"/> -->
   
   <input type="text" name="mId" placeholder="아이디를 입력해주세요"/>
   <input type="password" name="mPwd" placeholder="패스워드를 입력해주세요"/>
   <input type="button" value="서버 요청" onClick="moveLoginForm()"/>
   <br/>
   ${mId }
   ${mName }
   ${mPhone }
<%-- 	${memberInfo.getMId() } --%>
<%-- 	${memberInfo.getMName() } --%>
<%-- 	${memberInfo.getMPhone() } --%>
<%--    ${memberId }<br/> --%>
<%--    ${memberPwd }<br/> --%>
</body>

<script>
 function moveLoginForm(){
	 var mId = document.getElementsByName("mId")[0];
	 var mPwd = document.getElementsByName("mPwd")[0];
	 
	 
	 var form = document.createElement("form");
	 
	 form.action = "LogIn?sCode=LogIn";
	 form.method= "Post";
	 
	 form.appendChild(mId);
	 form.appendChild(mPwd);
	 
	 document.body.appendChild(form);
	 
	 form.submit();
 }
 
</script>
</html>
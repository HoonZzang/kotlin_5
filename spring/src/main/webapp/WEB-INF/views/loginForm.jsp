<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LogInForm</title>
</head>
<body>
	<input type="text" name="mId" placeholder="아이디를 입력해주세요" />
	<input type="password" name="mPwd" placeholder="패스워드를 입력해주세요" />
	<input type="button" value="서버 요청" onClick="moveLoginForm()"/>
	<br />
	${mId }<br />
	${mPwd }
	${memberId }<br />
	${memberPwd }<br />
	${memberPwd2 }<br />
</body>
<script>
function moveLoginForm(){
	var mId = document.getElementsByName("mId")[0];
	var mPwd = document.getElementsByName("mPwd")[0];
	
	var form = document.createElement("form");
	form.action = "Login?memberInfo="+mId.value+"&memberInfo="+mPwd.value;
	form.method = "POST";
	
	form.appendChild(mId);
	form.appendChild(mPwd);
	document.body.appendChild(form);
	
	form.submit();
}
</script>
</html>
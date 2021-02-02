<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>loginForm</title>
</head>
<body>
	아이디<input type="text" name="mId" placeholder="아이디를 입력해주세요"> </br>
	비밀번호<input type="password" name="mPwd" placeholder="비밀번호를 입력해주세요"></br>
	<input type="button" value="서버요청" onClick="moveLoginForm()" />
	${mId }
	${mName }
	${mPhone }
</body>
	<script>
	function moveLoginForm() {
		var mId = document.getElementsByName("mId")[0];
		var mPwd = document.getElementsByName("mPwd")[0];
		
		var form = document.createElement("form");
		form.action = "Login?SCode=A";
		form.method = "POST";
		
		form.appendChild(mId);
		form.appendChild(mPwd);
		document.body.appendChild(form);
		
		form.submit();
		
	}
	</script>
</html>
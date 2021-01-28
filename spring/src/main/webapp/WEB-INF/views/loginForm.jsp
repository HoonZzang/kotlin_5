<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>loginForm</title>
</head>
<body>
   아이디<input type="text" name="memberInfo" placeholder="아이디를 입력해주세요.">
   비밀번호<input type="password" name="memberInfo" placeholder="비밀번호를 입력해주세요.">
   <button onClick="moveLogin()">전송</button>


<P>  The time on the server is ${serverTime}. </P>
</body>
<script>
      function moveLogin() {
         var memberInfo = document.getElementsByName("memberInfo");
         var form = document.createElement.form(memberInfo);
         form.action = "http://192.168.0.91/Login";
         form.method = post;
         document.body.appendChild(form);
         
         form.submit();
      }
   </script>
</html>
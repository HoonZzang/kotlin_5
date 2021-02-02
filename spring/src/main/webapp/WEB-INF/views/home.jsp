<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE HTML>
<html>
<head>
	<title>Home</title>
	<style>
		img{
			margin-right:150px;
		}
		div{
			margin-bottom:30px;
		}
		
		
	</style>
</head>
<body>
	${makeSelect }
	<a href="http://192.168.1.112/LoginForm">로그인폼 이동</a>
	<p> Now Time : ${Access } </p>
</body>
	<script>
		function movieDate(mvCode) {
			alert("ok");
			location.href = "/movieDate?mvCode=" + mvCode;
		}
	</script>
</html>

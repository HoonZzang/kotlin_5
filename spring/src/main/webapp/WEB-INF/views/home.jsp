<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE HTML>
<html>
<head>
	<title>Home</title>
	<link rel="stylesheet" type="text/css" href="/resources/css/kotlin.css" />
</head>
<body>

	<a href="http://192.168.0.252/LoginForm" >로그인폼 이동</a><br />
	<P>  Now Time : ${Access} </P>
	
</body>
<script>
	let movieList = JSON.parse('${jsonData}');
	
	movieList.forEach(movie =>{
		let mvDiv = document.createElement('Div');
    let mvH2 =  document.createElement('h2');
    let mvP1 =  document.createElement('p');
    let mvP2 =  document.createElement('p');
    
    
	})
</script>
</html>

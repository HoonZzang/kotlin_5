<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE HTML>
<html>
<head>
	<title>Home</title>
	<link href="/resources/css/kotlin.css" rel="stylesheet"/> 
</head>
<body onLoad="init()">
	<a href="http://192.168.0.252/LoginForm" >로그인폼 이동</a><br />
	<P>  Now Time : ${Access} </P>
	<section id="movieZone" style="display:flex;">
		<div id="movieInfo"></div>
		<div id="selectionDate">selectionDate</div>
		<div id="selectionTime">selectionTime</div>
	</section>
</body>
<script>
function init(){
	/* Convert Date */
	let dayStr = "${Access}";
	let day = dayStr.split("-");
	let now = new Date();
	now.setFullYear(parseInt(day[0]), parseInt(day[1])-1, parseInt(day[2]));
	
	let movieInfo = document.getElementById("movieInfo");
	/* Append movieInfo Div */
	let movie = JSON.parse('${movieData}');
	
	let mvImage = document.createElement('Div');
	mvImage.className = "movie";
	mvImage.style.height = "300px";
	mvImage.style.backgroundImage = "url(/resources/images/" + movie[0].mvImage + ")";
	movieInfo.appendChild(mvImage);
	
	let mvTitle = document.createElement('Div');              
	mvTitle.textContent = movie[0].mvName;
	mvTitle.className = "movie";
	movieInfo.appendChild(mvTitle);
	
	let mvGrade = document.createElement('Div');              
	mvGrade.textContent = movie[0].mvGrade;
	mvGrade.className = "movie";
	movieInfo.appendChild(mvGrade);
	
	let mvStatus = document.createElement('Div');              
	mvStatus.textContent = movie[0].mvStatus;
	mvStatus.className = "movie";
	movieInfo.appendChild(mvStatus);
	
	let mvComments = document.createElement('Div');              
	mvComments.textContent = movie[0].mvComments;
	mvComments.className = "movie";
	movieInfo.appendChild(mvComments);
}

function divClick(mvCode){
	//서버전송
	let form = document.createElement("form");
	form.action = "Step2?sCode=Step2&mvCode=" + mvCode;
	form.method = "post"
	
	document.body.appendChild(form);
	form.submit();
}
	
</script>
</html>

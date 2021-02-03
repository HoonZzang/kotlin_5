<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE HTML>
<html>
<head>
	<title>Home</title>
</head>
<body>

	<a href="http://192.168.0.91/LoginForm" >로그인폼 이동</a><br />
	<P>  Now Time : ${Access} </P>
	<section id="movieZone"></section>
</body>
<script> 
	let dayStr = "${Access}";
	let day = (dayStr.split(" "))[0].split("-");
	let now = new Date();
	
	now.setFullYear(parseInt(day[0]), parseInt(day[1])-1, parseInt(day[2]));
	let section = document.getElementById("movieZone");
	let movieList = JSON.parse('${jsonData}');
	
	let record = parseInt(movieList.length/5);
	record = (movieList.length%5 > 0)? record + 1: record;
	for(rIndex=0; rIndex < record; rIndex++){
		let div = document.createElement('Div');
		div.style.display = "inline-flex";
		div.setAttribute("name", "line");
		section.appendChild(div);
	}

	for(index=0; index < movieList.length; index++){
		let rDivIndex = parseInt(index/5);
		let mvDiv = document.createElement('Div');
		mvDiv.style.width = "150px";
		mvDiv.style.height = "300px";
		mvDiv.style.margin = "0px 10px 20px 0px";                
		mvDiv.style.backgroundImage = "url(/resources/images/" + movieList[index].mvImage + ")"
		mvDiv.style.backgroundSize = "contain";
		mvDiv.style.cursor = "pointer";
		let mvCode = movieList[index].mvCode;
		mvDiv.addEventListener('click', function(){divClick(mvCode);});
		let line = document.getElementsByName("line")[rDivIndex];
		line.appendChild(mvDiv);
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

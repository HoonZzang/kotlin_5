<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
	<title>MovieDate</title>
	</head>
	<body>
		
		${makeDate } <br>
		
<%-- 		${Access } --%>
</body>
<script>
 
 var dayStr = "${Access}";
 var now = new Date();  
 
 for(var i=0; i<7; i++){
    
    var year = dayStr.split("-")[0];
    var month = dayStr.split("-")[1];
    var day =parseInt(dayStr.split("-")[2])+i;
     
    document.write(year+"년 "+month+"월 "+day+"일 <br/>");  
    
    
    
 }

</script>
</html>
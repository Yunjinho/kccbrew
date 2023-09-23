<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<title>Validation</title>
</head>
<body>
	<h3>유효성 검사</h3>
	<form:form modelAttribute ="holiday"  method="post">
		<p>아이디 : <form:input path="startDate" type="date"/> <form:errors path="startDate"/>		
		<p><input type="submit" value="확인"/> 
		<input type="reset"  value="취소"/>
	</form:form>
</body>
<html>
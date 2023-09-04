<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
	<div class="top-frame">
    	<jsp:include page="/WEB-INF/views/include/header.jsp"/>
	</div>
	<div class="middle-frame">
	    <jsp:include page="/WEB-INF/views/include/contents.jsp"/>
	</div>
	<div class="bottom-frame">
		<%@include file="/WEB-INF/views/include/footer.jsp" %>
	</div>
</html>
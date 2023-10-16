<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"  %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<%@ page import="java.time.LocalDateTime"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:insertAttribute name="title"/></title>
<link rel="icon" type="image/png" href="${path}/resources/img/main/kccbrw-representative-logo.png">
<style>
	@font-face {
		font-family: 'Noto Sans';
		font-style: normal;
		src: url("../../fonts/NotoSansKR-Regular.ttf")
	}
	html {
		font-family: 'Noto Sans', 'Noto Sans KR', sans-serif !important;
	}
</style>
<!-- ------------------ FONTS --------------------------->
<!--------------------- NOTOSANS ------------------------>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans&display=swap" rel="stylesheet">

<!--------------------- NOTOSANS KR --------------------->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans&family=Noto+Sans+KR&display=swap" rel="stylesheet">

<!--------------------- CSS ----------------------------->
<!--------------------- 메인 페이지 및 공통 CSS ------------------>
<link rel="stylesheet" href="${path}/resources/css/comm/mainLayout.css"><link rel="stylesheet" href="${path}/resources/css/comm/reset.css" >
<link rel="stylesheet" href="${path}/resources/css/comm/reset.css" >
<link rel="stylesheet" href="${path}/resources/css/comm/maincontents.css"/>
<link rel="stylesheet" href="${path}/resources/css/comm/footer.css"/>


<!--------------------- Full Calendar CSS --------------------------->
<!-- <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/main.min.css'/> -->
<link href='https://cdn.jsdelivr.net/npm/fullcalendar@5.10.0/main.min.css' rel='stylesheet' />


<!--------------------- JS ------------------------------>
<!-------------------- 공통 JS 및 JQuery ---------------------->
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>

<!--------------------- Full Calendar JS ------------------------->
<%-- <script src='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/main.min.js'></script>
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/locales-all.min.js'></script>--%>
<script src="${path}/resources/js/comm/calendar.js"></script> 
<script	src='https://cdn.jsdelivr.net/npm/fullcalendar@5.10.0/main.min.js'></script>

</head>


<body>
	<div id="mainContainer">
		<div id="mainHeader">
			<tiles:insertAttribute name="header"/>
		</div>
		<div id="mainContents">
			<tiles:insertAttribute name="contents"/>
		</div>
		
		
		<div id="mainFooter">
			<tiles:insertAttribute name="footer"/>
		</div>
	</div>
</body>
</html>
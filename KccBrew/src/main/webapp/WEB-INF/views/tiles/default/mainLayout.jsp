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
<link rel="stylesheet" href="${path}/resources/css/comm/mainpage.css"/>
<link rel="stylesheet" href="${path}/resources/css/comm/footer.css"/>


<!--------------------- BootStrap CSS -------------------------->
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"> -->

<!--------------------- Full Calendar CSS --------------------------->
<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/main.min.css'/>
<link href='https://cdn.jsdelivr.net/npm/fullcalendar@5.10.0/main.min.css' rel='stylesheet' />


<!--------------------- A/S 접수 페이지 CSS -------------------->
<link rel="stylesheet" href="/resources/css/asMng/asList.css" />
<link rel="stylesheet" href="/resources/css/asMng/asReceipt.css" />

<!--------------------- 로그인 및 회원가입 CSS -------------------->
<link href="<c:url value="resources/css/comm/login.css"/>" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="resources/css/comm/register.css">

<!--------------------- 로그 관리 CSS ------------------------------->
<link rel="stylesheet" href="/resources/css/log/mylogtest.css" />

<!--- calendar css 겹침---- 일정 관리 CSS -------------------------------->
<link rel="stylesheet" href="/resources/css/schdl/mycalendar.css" />

<!---------------------- 점포 상세 CSS ------------------------------------>
<link rel="stylesheet" href="${path}/resources/css/store/view.css" type="text/css">

<!---------------------- 점포 등록 CSS------------------------------------->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/store/insert.css" />

<!--------헤더 겹치고 테이블 캡션 사라짐-- 점포 리스트 CSS ------------------------------------->
<!-- <link rel="stylesheet" href="/resources/css/store/store.css" /> -->

<!---------------------- 점포 수정 CSS -------------------------------------->
<link rel="stylesheet" href="${path}/resources/css/store/update.css" type="text/css">

<!---------헤더 겹치고 테이블 캡션 사라짐-- 코드 상세  CSS-------------------------------------->
<!-- <link rel="stylesheet" href="/resources/css/code/cdMngDtl.css" /> -->

<!---------------------- 코드 등록 CSS ------------------------------------>
<link rel="stylesheet" href="/resources/css/code/insert.css" />

<!----------헤더 겹치고 테이블 캡션 사라짐-- 상세 코드 수정 CSS --------------------------------->
<!-- <link rel="stylesheet" href="/resources/css/code/cdMndMod.css" /> -->

<!---------헤더 겹치고 테이블 캡션 사라짐--- 그룹 코드 상세 CSS------------------------------->
<!-- <link rel="stylesheet" href="/resources/css/code/codedetail.css" /> -->


<!--------------------- JS ------------------------------>
<!-------------------- 공통 JS 및 JQuery ---------------------->
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>

<!--------------------- Full Calendar JS ------------------------->
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/main.min.js'></script>
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/locales-all.min.js'></script>
<script src="${path}/resources/js/comm/calendar.js"></script>
<script	src='https://cdn.jsdelivr.net/npm/fullcalendar@5.10.0/main.min.js'></script>

<!-------------------- A/S 접수 JS --------------------------->
<script src="<c:url value="resources/js/asMng/asList.js"/>"></script>
<script src="<c:url value="resources/js/asMng/asReceipt.js"/>"></script>

<!-------------------- 로그인 및 회원 가입 JS ----------------------->
<script src="<c:url value="resources/js/comm/login.js"/>"></script>
<script src="<c:url value="resources/js/comm/register.js"/>"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

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
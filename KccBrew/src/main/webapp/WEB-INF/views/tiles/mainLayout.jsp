<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:insertAttribute name="title"/></title>
<style>
	#container {
		background-color: #eef4ff;
	}
	#contents {
		margin-bottom: 50px;
	}
</style>
<!-- font -->
<!-- notoSans -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans&display=swap" rel="stylesheet">
<!-- notoSans Kr -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans&family=Noto+Sans+KR&display=swap" rel="stylesheet">

<!-- css -->
<link rel="stylesheet" href="${path}/resources/css/comm/reset.css" >
<link rel="stylesheet" href="${path}/resources/css/comm/header.css" >
<link rel="stylesheet" href="${path}/resources/css/comm/footer.css" >
<link rel="stylesheet" href="${path}/resources/css/comm/mainpage.css"/>
<link rel="stylesheet" href="/resources/css/code/codedetail.css" type="text/css">
<link rel="stylesheet" href="/resources/css/code/insert.css" />
<link rel="stylesheet" href="/resources/css/code/code.css" />
<link rel="stylesheet" href="/css/code/search.css" />
<link rel="stylesheet" href="resources/css/comm/register.css">
<link rel="stylesheet" href="${path}/resources/css/store/view.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/store/insert.css" />
<link rel="stylesheet" href="/resources/css/store/store.css" />
<link rel="stylesheet" href="${path}/resources/css/store/update.css" type="text/css">
<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/main.min.css'/>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link href="<c:url value="resources/css/comm/login.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="resources/css/comm/common.css"/>" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="/resources/css/log/mylogtest.css" />
<link rel="stylesheet" href="/resources/css/log/content-template.css" />


<!-- javascript -->
<script src="<c:url value="resources/js/comm/login.js"/>"></script>
<script src="${path}/resources/js/comm/calendar.js"></script>
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/main.min.js'></script>
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/locales-all.min.js'></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
<script src="http://code.jquery.com/jquery-latest.js"></script> 
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="<c:url value="resources/js/comm/register.js"/>"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

</head>
<body>
	<div id="container">
		<div id="header">
			<tiles:insertAttribute name="header"/>
		</div>
		
		<div id="contents">
			<tiles:insertAttribute name="contents"/>
		</div>
		
		<div id="footer">
			<tiles:insertAttribute name="footer"/>
		</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KccBrew A/S 서비스</title>
<link rel="stylesheet" href="${path}/resources/css/comm/reset.css" >
<link rel="stylesheet" href="${path}/resources/css/comm/header.css" >
<link rel="stylesheet" href="${path}/resources/css/comm/mainpage.css"/>
<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/main.min.css'/>
<script src="http://code.jquery.com/jquery-latest.js"></script> 
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/main.min.js'></script>
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/locales-all.min.js'></script>
<script src="${path}/resources/js/comm/calendar.js"></script>
<script src="${path}/resources/js/comm/comm.js"></script>
<script src="${path}/resources/js/comm/header.js"></script>
</head>
	<div class="top-frame">
    	<jsp:include page="/WEB-INF/views/include/admin/adminheader.jsp"/>
	</div>
	<div class="middle-frame">
	    <jsp:include page="/WEB-INF/views/include/admin/admincontents.jsp"/>
	</div>
	<div class="bottom-frame">
		<%@include file="/WEB-INF/views/include/footer.jsp" %>
	</div>
</html>
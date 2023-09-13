<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.js"></script> 
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
<script src="<c:url value="resources/js/comm/login.js"/>"></script>
<link href="<c:url value="resources/css/comm/login2.css"/>" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="${path}/resources/css/comm/footer.css"/>
<link rel="stylesheet" href="${path}/resources/css/comm/reset.css"/>
</head>
<body class="login" onpageshow="if(event.persisted) noBack();" onunload="" marginwidth="0" marginheight="0">
	<div class="body-wrapper">
		<img src="<c:url value="resources/img/logo.png"/>" class="logo">
		
	</div>
</body>
</html>
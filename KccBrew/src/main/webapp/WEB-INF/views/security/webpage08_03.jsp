<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"  uri="http://www.springframework.org/security/tags"%>  
<html>
<head>
<title>Security</title>
</head>
<body>  
<h2>스프링 시큐리티 태그 예</h2>   
<sec:authorize access="hasRole('ROLE_ADMIN')" var="isAdmin">
	<p> <h3>관리자 권한 화면입니다.</h3>	
</sec:authorize>
<c:choose>
	<c:when test="${isAdmin}">
		<p>로그인 중입니다.
		<!-- 인증 자격 표시 -->
		<p>비밀번호: <sec:authentication property="principal.password"/>

		<!-- 역할 표시 -->
		<sec:authentication property="authorities" var="roles" scope="page" />
		<p>권한 :
			<ul>
    			<c:forEach var="role" items="${roles}">
    				<li>${role}</li>
    			</c:forEach>
			</ul> 
		<!-- 사용자명 표시 -->
		<p> 이름 : <sec:authentication property="principal.username"/>
		<p><a href="<c:url value='/exam03' />">[웹 요청 URL /exam03로 이동하기]</a>
	</c:when>
	<c:otherwise>
		<p> 로그인이 아닙니다. 
		<p><a href="<c:url value='/admin/tag' />">[웹 요청 URL /admin/tag로 이동하기]</a>
	</c:otherwise>
</c:choose>		

</body>
</html>



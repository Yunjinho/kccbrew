<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<title>Security</title>
</head>
<body>

	<h1>1. 스프링 시큐리티 태그 예시</h1>

	<sec:authorize access="hasRole('ROLE_MANAGER')" var="isManager">
		<p>
		<h3>매니저 권한 화면입니다.</h3>
	</sec:authorize>
	<c:if test="${isManager}">
		<p>ROLE_MANAGER 권한 로그인 중입니다.</p>
	</c:if>

	<sec:authorize access="hasRole('ROLE_ADMIN')" var="isAdmin">
		<p>
		<h3>관리자 권한 화면입니다.</h3>
	</sec:authorize>
	<c:if test="${isAdmin}">
		<p>ROLE_ADMIN 권한 로그인 중입니다.</p>
	</c:if>

	<sec:authorize access="hasRole('ROLE_MECHA')" var="isMecha">
		<p>
		<h3>수리기사 권한 화면입니다.</h3>
	</sec:authorize>
	<c:if test="${isMecha}">
		<p>ROLE_MECHA 권한 로그인 중입니다.</p>
	</c:if>

	<form action="./logout" method="POST">
		<button type="submit">LOGOUT</button>
		<input name="${_csrf.parameterName}" type="hidden"
			value="${_csrf.token}" />
	</form>



	<!-- 로그인한 회원 접근 가능 -->
	<sec:authorize access="isAuthenticated()">
		<h2>로그인한 회원</h2>
		<h5>
			<sec:authentication property="principal.username" />
			님, 반갑습니다.
		</h5>
	</sec:authorize>



	<!-- 관리자 접근 가능 -->
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<h2>관리자</h2>
		<!-- 역할 표시 -->
		<sec:authentication property="authorities" var="roles" scope="page" />
		<p>권한 :
		<ul>
			<c:forEach var="role" items="${roles}">
				<li>${role}</li>
			</c:forEach>
		</ul>
		<!-- 아디디 표시 -->
		<p>
			아이디 :
			<sec:authentication property="principal.username" />
	</sec:authorize>



	<!-- 점주 접근 가능 -->
	<sec:authorize access="hasRole('ROLE_MANAGER')">
		<h2>점주</h2>
		<!-- 역할 표시 -->
		<sec:authentication property="authorities" var="roles" scope="page" />
		<p>권한 :
		<ul>
			<c:forEach var="role" items="${roles}">
				<li>${role}</li>
			</c:forEach>
		</ul>
		<!-- 아디디 표시 -->
		<p>
			아이디 :
			<sec:authentication property="principal.username" />
	</sec:authorize>



	<!-- 수리기사 접근 가능 -->
	<sec:authorize access="hasRole('ROLE_MECHA')">
		<h2>수리기사</h2>
		<!-- 역할 표시 -->
		<sec:authentication property="authorities" var="roles" scope="page" />
		<p>권한 :
		<ul>
			<c:forEach var="role" items="${roles}">
				<li>${role}</li>
			</c:forEach>
		</ul>
		<!-- 아이디 표시 -->
		<p>
			아이디 :
			<sec:authentication property="principal.username" />
	</sec:authorize>




	<h1>2. 세션에 있는 정보 빼내기</h1>
	<h2>회원정보</h2>
	<c:set var="user" value="${sessionScope.user}" />
	<c:if test="${not empty user}">
		<p>
			<c:out value="${user}" />
		</p>
	</c:if>

	<h2>지점정보</h2>
	<c:set var="store" value="${sessionScope.store}" />
	<c:if test="${not empty store}">
		<p>
			<c:out value="${store}" />
		</p>
	</c:if>



</body>
</html>



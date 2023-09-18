<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${path}/css/comm/header.css">
<link rel="stylesheet" href="${path}/css/comm/common.css">
</head>

<c:set var="user" value="${sessionScope.user}" />
<c:set var="store" value="${sessionScope.store}" />

<sec:authorize access="hasRole('ROLE_ADMIN')">
	<header class="comm-nav">
		<div class="top-bar">
			<p>
				<c:out value="${user.userId}" />
				(관리자)님 환영합니다
			</p>

			<form action="/logout" method="POST">
				<button type="submit" class="logout-btn">로그아웃</button>
				<input name="${_csrf.parameterName}" type="hidden"
					value="${_csrf.token}" />
			</form>

		</div>
		<nav class="nav-bar">
			<div class="nav-bar-brand">
				<c:url var="toMain" value="/adminMain" />
				<a href="${toMain}"> <img alt="logo"
					src="${path}/resources/img/logo.png">
				</a>
			</div>
			<div class="nav-box">
				<ul class="nav-list">
					<li class="nav-item"><a class="nav-links" href="#">A/S 관리</a>
						<ul class="comm-nav-dropdown">
							<c:url var="toAsList" value="/as-list" />
							<!-- 여기에  매핑 url 선언 -->
							<li><a href="${toAsList}">A/S 내역 조회</a></li>
							<!-- 매핑 url 변수 링크로 걸기 -->
						</ul></li>
					<li class="nav-item"><a class="nav-links" href="#">점포 관리</a>
						<ul class="comm-nav-dropdown">
							<c:url var="toStoreRegPage" value="/adminstoreregpage" />
							<li><a href="${toStoreRegPage}">점포 등록</a></li>
							<c:url var="toStoreListPage" value="/adminstorelistpage" />
							<li><a href="${toStoreListPage}">점포 조회</a></li>
							<c:url var="toStoreSearchPage" value="/adminstoresearchpage" />
							<li><a href="${toStoreSearchPage}">점포 검색</a></li>
						</ul></li>
					<li class="nav-item"><a class="nav-links" href="#">회원 관리</a>
						<ul class="comm-nav-dropdown">
							<c:url var="toMemberMngPage" value="/membermngpage" />
							<li><a href="${toMemberMngPage}">회원 조회</a></li>
						</ul></li>
					<li class="nav-item"><a class="nav-links" href="#">시스템 관리</a>
						<ul class="comm-nav-dropdown">
							<c:url var="toAdminLogPage" value="/adminlogpage" />
							<li><a href="${toAdminLogPage}">로그 조회</a></li>
							<c:url var="toAdminFilePage" value="/adminfilepage" />
							<li><a href="${toAdminFilePage}">파일 조회</a></li>
						</ul></li>
					<li class="nav-item"><a class="nav-links" href="#">마이페이지</a>
						<ul class="comm-nav-dropdown">
							<c:url var="toMyPage" value="/userinfo" />
							<li><a href="${toMyPage}">마이페이지</a></li>
						</ul></li>
				</ul>
			</div>
		</nav>
	</header>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_MANAGER')">
	<header class="comm-nav">
		<div class="top-bar">
			<p>
				<c:out value="${user.userId}" />
				(점주)님 환영합니다
			</p>
			
			<form action="/logout" method="POST">
				<button type="submit" class="logout-btn">로그아웃</button>
				<input name="${_csrf.parameterName}" type="hidden"
					value="${_csrf.token}" />
			</form>
			
		</div>
		<nav class="nav-bar">
			<div class="nav-bar-brand">
				<c:url var="toMain" value="/managerMain" />
				<a href="${toMain}"> <img alt="logo"
					src="${path}/resources/img/logo.png">
				</a>
			</div>
			<div class="nav-box">
				<ul class="nav-list">
					<li class="nav-item"><a class="nav-links" href="#">A/S 관리</a>
						<ul class="comm-nav-dropdown">
							<c:url var="toASReceipt" value="/as-receipt" />
							<li><a href="${toASReceipt}">A/S 신청</a></li>
							<c:url var="toAsList" value="/as-list" />
							<li><a href="${toAsList}">A/S 내역 조회</a></li>
						</ul></li>
					<li class="nav-item"><a class="nav-links" href="#">점포 관리</a>
						<ul class="comm-nav-dropdown">
							<c:url var="toStoreList" value="/storesearchpage" />
							<li><a href="${toStoreList}">점포 조회</a></li>
							<c:url var="toStoreModPage" value="/storemodpage" />
							<li><a href="${toStoreModPage}">점포 정보 수정</a></li>
						</ul></li>
					<li class="nav-item"><a class="nav-links" href="#">마이페이지</a>
						<ul class="comm-nav-dropdown">
							<c:url var="toMyPage" value="/userinfo" />
							<li><a href="${toMyPage}">마이페이지</a></li>
						</ul></li>
				</ul>
			</div>
		</nav>
	</header>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_MECHA')">
	<header class="comm-nav">
		<div class="top-bar">
			<p>
				<c:out value="${user.userId}" />
				(수리기사)님 환영합니다
			</p>

			<form action="/logout" method="POST">
				<button type="submit" class="logout-btn">로그아웃</button>
				<input name="${_csrf.parameterName}" type="hidden"
					value="${_csrf.token}" />
			</form>

		</div>
		<nav class="nav-bar">
			<div class="nav-bar-brand">
				<c:url var="toMain" value="/mechanicMain" />
				<a href="${toMain}"> <img alt="logo"
					src="${path}/resources/img/logo.png">
				</a>
			</div>
			<div class="nav-box">
				<ul class="nav-list">
					<li class="nav-item"><a class="nav-links" href="#">A/S 관리</a>
						<ul class="comm-nav-dropdown">
							<c:url var="toAsList" value="/as-list" />
							<li><a href="${toAsList}">A/S 내역 조회</a></li>
						</ul></li>
					<li class="nav-item"><a class="nav-links" href="#">일정 관리</a>
						<ul class="comm-nav-dropdown">
							<li><a href="#">일정 조회</a></li>
							<li><a href="#">일정 등록</a></li>
						</ul></li>
					<li class="nav-item"><a class="nav-links" href="#">마이페이지</a>
						<ul class="comm-nav-dropdown">
							<c:url var="toMyPage" value="/userinfo" />
							<li><a href="${toMyPage}">마이페이지</a></li>
						</ul></li>
				</ul>
			</div>
		</nav>
	</header>
</sec:authorize>



</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${path}/resources/css/comm/reset.css" >
<link rel="stylesheet" href="${path}/resources/css/comm/header.css" >
<script src="http://code.jquery.com/jquery-latest.js"></script> 
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="${path}/resources/js/comm/header.js"></script>
<script src="${path}/resources/js/admin/adminheader.js"></script>
</head>
<c:if test="어드민 계쩡">
<header class="comm-nav"  >
	<div class="top-bar">
		###(관리자)님 환영합니다
		<button class="logout-btn">
			로그아웃
		</button>
	</div>
	<nav class="nav-bar">
		<div class="navbar-brand">
			<c:url var="toMain" value="/adminMain" />
			<a href="${toMain}">
				<img alt="logo"  src="${path}/resources/img/kcc.png">
			</a>
		</div>
		<div class="nav-box">
			<ul class="nav-pills">
				<li class="nav-item">
					<a class="nav-link" href="#" >A/S 관리</a>
					<ul class="comm-nav-dropdown">
						<li><a href="/as-list">A/S 조회</a></li>			<!-- 매핑 url 변수 링크로 걸기 -->
						<c:url var="test2" value="/test2" />
						<li><a href="${test2}">A/S 내역 조회</a></li>
					</ul>	
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#">점포 관리</a>
					<ul class="comm-nav-dropdown">
						<c:url var="test3" value="/test3" />
						<li><a href="${test3}">점포 등록</a></li>
						<li><a href="#">점포 수정</a></li>
						<li><a href="#">점포 삭제</a></li>
						<li><a href="#">점포 조회</a></li>
					</ul>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#">기사 관리</a>
					<ul class="comm-nav-dropdown">
						<li><a href="#">기사 조회</a></li>
						<li><a href="#">기사 관리</a></li>
					</ul>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#">시스템 관리</a>
					<ul class="comm-nav-dropdown">
						<li><a href="#">로그 조회</a></li>
						<li><a href="#">파일 조회</a></li>
					</ul>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#">마이페이지</a>
					<ul class="comm-nav-dropdown">
						<li><a href="#">마이페이지</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</nav>
</header>
</c:if>
</html>
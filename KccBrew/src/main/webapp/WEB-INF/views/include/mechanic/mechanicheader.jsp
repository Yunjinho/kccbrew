<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<header class="comm-nav" >
	<div class="top-bar">
		###(수리기사)님 환영합니다
		<button class="logout-btn">
			로그아웃
		</button>
	</div>
	<nav class="nav-bar">
		<div class="navbar-brand">
			<c:url var="toMain" value="/mechanicMain" />
			<a href="${toMain}">
				<img alt="logo"  src="${path}/resources/img/logo.png">
			</a>
		</div>
		<div class="nav-box">
			<ul class="nav-pills">
				<li class="nav-item">
					<a class="nav-link" href="#" >A/S 관리</a>
					<ul class="comm-nav-dropdown">
						<li><a href="#">A/S 접수 안내</a></li>
						<li><a href="#">A/S 신청</a></li>
						<li><a href="#">A/S 내역 조회</a></li>
					</ul>	
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#">점포 관리</a>
					<ul class="comm-nav-dropdown">
						<li><a href="#">점포 등록</a></li>
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
						<li><a href="#">기사 </a></li>
					</ul>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#">시스템 관리</a>
					<ul class="comm-nav-dropdown">
						<li><a href="#">로그 조회</a></li>
						<li><a href="#">파일 조회</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</nav>
</header>
</html>
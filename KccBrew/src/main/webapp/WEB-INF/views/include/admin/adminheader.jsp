<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<header class="comm-nav" >
	<div class="top-bar">
		###(관리자)님 환영합니다
		<button class="logout-btn">
			로그아웃
		</button>
	</div>
	<nav class="nav-bar">
		<div class="navbar-brand">
			<img alt="logo"  src="${path}/resources/img/kcc.png">
		</div>
		<div class="nav-box">
			<ul class="nav-pills">
				<li class="nav-item">
					<a class="nav-link" href="#" >A/S 관리</a>
					<ul class="comm-nav-dropdown">
						<li><a href="#">A/S 접수 안내</a></li>
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
</html>
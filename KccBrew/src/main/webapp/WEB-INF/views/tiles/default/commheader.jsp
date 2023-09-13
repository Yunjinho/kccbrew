<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="${path}/resources/js/comm/login.js"></script>
<script src="${path}/resources/js/comm/header.js"></script>
<link rel="stylesheet" href="${path}/resources/css/comm/header.css" >
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">

<c:choose>
	<c:when test="${sessionScope.userTypeCd eq '01'}">
		<header class="comm-nav">
			<div class="top-bar">
				<p>
					${sessionScope.userId}님 환영합니다
				</p>
				<c:url var="toLogin" value="/loginpage" />
				<a href="${toLogin}">
					<button class="logout-btn">
						로그아웃
					</button>
				</a>
			</div>
			<nav class="nav-bar">
				<div class="navbar-brand">
					<c:url var="toMain" value="/main" />
					<a href="${toMain}">
						<img alt="logo"  src="${path}/resources/img/logo.png">
					</a>
				</div>
				<div class="nav-box">
					<ul class="nav-pills">
						<li class="nav-item">
							<a class="nav-link" href="#" >A/S 관리</a>
							<ul class="comm-nav-dropdown">
								<c:url var="test" value="/test" />          <!-- 여기에  매핑 url 선언 -->
								<li><a href="${test}">A/S 접수 안내</a></li>	<!-- 매핑 url 변수 링크로 걸기 -->
								<c:url var="toAsList" value="/as-list" />
								<li><a href="${toAsList}">A/S 내역 조회</a></li>
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
	</c:when>
	
	<c:when test="${sessionScope.userTypeCd eq '02'}">
		<c:set var="userName" value="${sessionScope.userName}"/>
		<header class="comm-nav" >
			<div class="top-bar">
				<p>
					${sessionScope.userId}님 환영합니다
				</p>
				<c:url var="toLogin" value="/loginpage" />
				<a href="${toLogin}">
					<button class="logout-btn">
						로그아웃
					</button>
				</a>
			</div>
			<nav class="nav-bar">
				<div class="navbar-brand">
					<c:url var="toMain" value="/main" />
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
								<c:url var="toAsList" value="/as-list" />
								<li><a href="${toAsList}">A/S 내역 조회</a></li>
							</ul>	
						</li>
						<li class="nav-item">
							<a class="nav-link" href="#">점포 관리</a>
							<ul class="comm-nav-dropdown">
								<li><a href="#">점포 조회</a></li>
								<li><a href="#">점포 정보 수정</a></li>
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
	</c:when>
	
	<c:when test="${sessionScope.userTypeCd eq '03'}">
		<header class="comm-nav" >
			<div class="top-bar">
				<p>
					<span id="userName"></span>${sessionScope.userId}님 환영합니다
				</p>
				<c:url var="toLogin" value="/loginpage" />
				<a href="${toLogin}">
					<button class="logout-btn">
						로그아웃
					</button>
				</a>
			</div>
			<nav class="nav-bar">
				<div class="navbar-brand">
					<c:url var="toMain" value="/main" />
					<a href="${toMain}">
						<img alt="logo"  src="${path}/resources/img/logo.png">
					</a>
				</div>
				<div class="nav-box">
					<ul class="nav-pills">
						<li class="nav-item">
							<a class="nav-link" href="#" >A/S 관리</a>
							<ul class="comm-nav-dropdown">
								<c:url var="toAsList" value="/as-list" />
								<li><a href="${toAsList}">A/S 내역 조회</a></li>
							</ul>	
						</li>
						<li class="nav-item">
							<a class="nav-link" href="#">일정 관리</a>
							<ul class="comm-nav-dropdown">
								<li><a href="#">일정 조회</a></li>
								<li><a href="#">일정 등록</a></li>
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
	</c:when>
</c:choose>


</html>
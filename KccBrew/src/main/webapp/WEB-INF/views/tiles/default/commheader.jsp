<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>


<c:choose>
	<c:when test="${sessionScope.userTypeCd eq '01'}">
		<header class="comm-nav">
			<div class="top-bar">
				###(관리자)님 환영합니다
				<c:url var="toLogin" value="/loginpage" />
				<a href="${toLogin}">
					<button class="logout-btn">
						로그아웃
					</button>
				</a>
			</div>
			<nav class="nav-bar">
				<div class="navbar-brand">
					<c:url var="toMain" value="/adminMain" />
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
								<c:url var="toAsList" value="/adminaslist" />
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
		<header class="comm-nav" >
			<div class="top-bar">
				###(점주)님 환영합니다
				<c:url var="toLogin" value="/loginpage" />
				<a href="${toLogin}">
					<button class="logout-btn">
						로그아웃
					</button>
				</a>
			</div>
			<nav class="nav-bar">
				<div class="navbar-brand">
					<c:url var="toMain" value="/managerMain" />
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
								<c:url var="toAsList" value="/manageraslist" />
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
				###(수리기사)님 환영합니다
				<c:url var="toLogin" value="/loginpage" />
				<a href="${toLogin}">
					<button class="logout-btn">
						로그아웃
					</button>
				</a>
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
								<c:url var="toAsList" value="/mechanicaslist" />
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>

<!-- 스프링 시큐리티 통해서 userId, userType사용 -->
<sec:authentication property="principal.username" var="userId" />
<sec:authentication property="authorities" var="roles" />

<script>
	var userId = '${userId}';
	console.log(userId);

	var userType = '${roles}';
	console.log(userType);
	
	var userTypeCd = "<c:out value='${user.userTypeCd}' />";
</script>

<!-- css -->
<link rel="stylesheet" href="${path}/css/comm/header.css">
<link rel="stylesheet" href="${path}/css/comm/common.css">

<!-- javascript -->
<script src="<c:url value="/resources/js/sysMng/alarm/websocket.js"/>"></script>

<!-- socket -->
<script
	src="https://cdn.jsdelivr.net/npm/sockjs-client@1.6.1/dist/sockjs.min.js"></script>
</head>

<script>
	var isClicked = false;
	function handleClick(event) {
		event.preventDefault();
		var olElement = document.getElementById("notification-list");
		if (isClicked) {
			olElement.style.display = "none";
		} else {
			olElement.style.display = "block";
		}
		isClicked = !isClicked;
	}
</script>

<c:set var="user" value="${sessionScope.user}" />
<c:set var="store" value="${sessionScope.store}" />

<sec:authorize access="hasRole('ROLE_ADMIN')">
	<header class="comm-nav">
		<div class="top-bar">
			<div class="wrap">
				<%@ include file="/WEB-INF/views/chat/adminChat.jsp"%>
				<form action="/logout" method="POST">
					<button type="submit" class="logout-btn">로그아웃</button>
					<input name="${_csrf.parameterName}" type="hidden"
						value="${_csrf.token}" />
				</form>

				<p>
					<c:out value="${user.userNm}" />
					(관리자)님 환영합니다
				</p>

				<!-- 비실시간 알람메세지 -->
				<div class="alarm-icon-background">
					<a href="#" id="notification-link" onclick="handleClick(event);">

						<img id="notification-image"
						src="<c:url value='/img/main/icon-notification-bell.png' />"
						alt="Check List" class="header-icon" /> <img
						id="new-notification-image"
						src="<c:url value='/img/main/icon-notification.png' />"
						alt="Check List" class="header-icon" hidden="true" />
					</a>

					<ol class="list-group list-group-numbered" id="notification-list"
						style="display: none;">
					</ol>
				</div>

			</div>

			<div class="mainmenu">

				<!-- 실시간 알림 -->
				<div class="alert alert-primary" role="alert" hidden="true">
					<div id="alert-title"></div>
					<div id="alert-content"></div>
				</div>

				<div>
					<nav class="nav-bar" id="nav-bar">
						<div class="nav-bar-brand">
							<c:url var="toMain" value="/admin/main" />
							<a href="${toMain}"> <img alt="logo"
								src="${path}/resources/img/logo14.png">
							</a>
							<ul class="comm-nav-dropdown">
								<li></li>
							</ul>
						</div>
						<div class="nav-box">
							<ul class="nav-list">
								<li class="nav-item"><a class="nav-links" href="#">A/S
										관리</a>
									<ul class="comm-nav-dropdown">
										<c:url var="toAsList" value="/as-list" />
										<!-- 여기에  매핑 url 선언 -->
										<li><a href="${toAsList}">A/S 내역 조회</a></li>
										<!-- 매핑 url 변수 링크로 걸기 -->
									</ul></li>
								<li class="nav-item"><a class="nav-links" href="#">점포
										관리</a>
									<ul class="comm-nav-dropdown">
										<c:url var="toStoreListPage" value="/store" />
										<li><a href="${toStoreListPage}">점포 조회</a></li>
									</ul></li>
								<li class="nav-item"><a class="nav-links" href="#">회원
										관리</a>
									<ul class="comm-nav-dropdown">
										<c:url var="toMemberMngPage" value="/user" />
										<li><a href="${toMemberMngPage}">회원 조회</a></li>
									</ul></li>
								<li class="nav-item"><a class="nav-links" href="#">스케줄
										관리</a>
									<ul class="comm-nav-dropdown">
										<c:url var="toHldyList" value="/holiday" />
										<li><a href="${toHldyList}">휴가 관리</a></li>
										<c:url var="toSchdlList" value="/admin/schedule" />
										<li><a href="${toSchdlList}">스케줄 조회</a></li>
									</ul></li>
								<li class="nav-item"><a class="nav-links" href="#">시스템
										관리</a>
									<ul class="comm-nav-dropdown">
										<c:url var="toAdminLogPage" value="/admin/log" />
										<li><a href="${toAdminLogPage}">로그 조회</a></li>
										<c:url var="toAdminFilePage" value="/file" />
										<li><a href="${toAdminFilePage}">파일 조회</a></li>
										<c:url var="toAdminCodePage" value="/code" />
										<li><a href="${toAdminCodePage}">공통 코드 조회</a></li>
										<c:url var="toStatistics" value="/statistics" />
										<li><a href="${toStatistics}">통계</a></li>
									</ul></li>
								<li class="nav-item"><a class="nav-links" href="#">마이페이지</a>
									<ul class="comm-nav-dropdown">
										<c:url var="toMyPage" value="/mypage" />
										<li><a href="${toMyPage}">내 정보</a></li>
										<c:url var="toNotification" value="/noticelist" />
										<li><a href="${toNotification}">공지사항</a></li>
									</ul></li>
							</ul>
						</div>
					</nav>
				</div>

				<ul class="comm-nav-dropdown">
					<li></li>
				</ul>
			</div>
		</div>


		<script>
			/*         const navBar = document.getElementById('nav-bar');

			 navBar.addEventListener('mouseenter', () => {navBar.style.zIndex = '999';});

			 navBar.addEventListener('mouseleave', () => {navBar.style.zIndex = '1';}); */
		</script>

	</header>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_MANAGER')">
	<header class="comm-nav">
		<div class="top-bar">
			<div class="wrap">
				<form action="/logout" method="POST">
					<button type="submit" class="logout-btn">로그아웃</button>
					<input name="${_csrf.parameterName}" type="hidden"
						value="${_csrf.token}" />
				</form>

				<p>
					<c:out value="${user.userNm}" />
					(점주)님 환영합니다
				</p>

				<div class="alarm-icon-background">
					<a href="#" id="notification-link" onclick="handleClick(event);">
						<img id="notification-image"
						src="<c:url value='/img/main/icon-notification-bell.png' />"
						alt="Check List" class="header-icon" /> <img
						id="new-notification-image"
						src="<c:url value='/img/main/icon-notification.png' />"
						alt="Check List" class="header-icon" hidden="true" />
					</a>
					<ol class="list-group list-group-numbered" id="notification-list"
						style="display: none;">
					</ol>
				</div>

				<%@ include file="/WEB-INF/views/chat/userChat.jsp"%>
			</div>
		

			<div class="mainmenu">
				<!-- 실시간 알림 -->
				<div class="alert alert-primary" role="alert" hidden="true">
					<div id="alert-title"></div>
					<div id="alert-content"></div>
				</div>
				
				<div>
					<nav class="nav-bar" id="nav-bar">
						<div class="nav-bar-brand">
							<c:url var="toMain" value="/store-mng/main" />
							<a href="${toMain}"> <img alt="logo"
								src="${path}/resources/img/logo14.png">
							</a>
							
							<ul class="comm-nav-dropdown">
								<li></li>
							</ul>
							
						</div>
						<div class="nav-box">
							<ul class="nav-list">
								<li class="nav-item"><a class="nav-links" href="#">A/S 관리</a>
									<ul class="comm-nav-dropdown">
										<c:url var="toASReceipt" value="/as-receipt" />
										<li><a href="${toASReceipt}">A/S 신청</a></li>
										<c:url var="toAsList" value="/as-list" />
										<li><a href="${toAsList}">A/S 내역 조회</a></li>
									</ul>
								</li>
								<li class="nav-item"><a class="nav-links" href="#">점포 관리</a>
									<ul class="comm-nav-dropdown">
										<c:url var="toStoreMngListPage" value="/store-list" />
										<li><a href="${toStoreMngListPage}">점포 조회</a></li>
									</ul>
								</li>
								<li class="nav-item"><a class="nav-links" href="#">스케줄 관리</a>
									<ul class="comm-nav-dropdown">
										<c:url var="toHldyList" value="/holiday" />
										<li><a href="${toHldyList}">휴가 관리</a></li>
										<c:url var="toSchdlList" value="/schedule/calendar" />
										<li><a href="${toSchdlList}">스케줄 조회</a></li>
									</ul>
								</li>
								<li class="nav-item"><a class="nav-links" href="#">마이페이지</a>
									<ul class="comm-nav-dropdown">
										<c:url var="toMyPage" value="/mypage" />
										<li><a href="${toMyPage}">내 정보</a></li>
										<c:url var="toNotification" value="/noticelist" />
										<li><a href="${toNotification}">공지사항</a></li>
									</ul>
								</li>
							</ul>
						</div>
					</nav>
				</div>
				<ul class="comm-nav-dropdown">
					<li></li>
				</ul>
			</div>
		</div>
	</header>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_MECHA')">
	<header class="comm-nav">
		<div class="top-bar">
			<div class="wrap">
				<form action="/logout" method="POST">
					<button type="submit" class="logout-btn">로그아웃</button>
					<input name="${_csrf.parameterName}" type="hidden"
						value="${_csrf.token}" />
				</form>

				<p>
					<c:out value="${user.userNm}" />
					(수리기사)님 환영합니다
				</p>

				<div class="alarm-icon-background">
					<a href="#" id="notification-link" onclick="handleClick(event);">
						<img id="notification-image"
						src="<c:url value='/img/main/icon-notification-bell.png' />"
						alt="Check List" class="header-icon" /> <img
						id="new-notification-image"
						src="<c:url value='/img/main/icon-notification.png' />"
						alt="Check List" class="header-icon" hidden="true" />
					</a>
					<ol class="list-group list-group-numbered" id="notification-list"
						style="display: none;">
					</ol>
				</div>

				<%@ include file="/WEB-INF/views/chat/userChat.jsp"%>
			</div>
			<div class="mainmenu">
				<!-- 실시간 알림 -->
				<div class="alert alert-primary" role="alert" hidden="true">
					<div id="alert-title"></div>
					<div id="alert-content"></div>
				</div>
	
				<nav class="nav-bar" id="nav-bar">
					<div class="nav-bar-brand">
						<c:url var="toMain" value="/mechanic/main" />
						<a href="${toMain}"> <img alt="logo"
							src="${path}/resources/img/logo14.png">
						</a>
						<ul class="comm-nav-dropdown">
							<li></li>
						</ul>
					</div>
					<div class="nav-box">
						<ul class="nav-list">
							<li class="nav-item"><a class="nav-links" href="#">A/S 관리</a>
								<ul class="comm-nav-dropdown">
									<c:url var="toAsList" value="/as-list" />
									<li><a href="${toAsList}">A/S 내역 조회</a></li>
								</ul></li>
							<li class="nav-item"><a class="nav-links" href="#">스케줄 관리</a>
								<ul class="comm-nav-dropdown">
									<c:url var="toHldyList" value="/holiday" />
									<li><a href="${toHldyList}">휴가 관리</a></li>
									<c:url var="toSchdlList" value="/schedule/calendar" />
									<li><a href="${toSchdlList}">스케줄 조회</a></li>
								</ul></li>
							<li class="nav-item"><a class="nav-links" href="#">마이페이지</a>
								<ul class="comm-nav-dropdown">
									<c:url var="toMyPage" value="/mypage" />
									<li><a href="${toMyPage}">내 정보</a></li>
									<c:url var="toNotification" value="/noticelist" />
									<li><a href="${toNotification}">공지사항</a></li>
								</ul></li>
						</ul>
					</div>
				</nav>
	
				<ul class="comm-nav-dropdown">
					<li></li>
				</ul>
	
			</div>
		</div>
	</header>
</sec:authorize>

<script>
	document.addEventListener("DOMContentLoaded", function() {
		getAlarmData();
	});
</script>



</html>
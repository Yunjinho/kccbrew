<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<c:choose>
	<c:when test="${sessionScope.userTypeCd eq '01'}">
		<div class="profileAndguide">
			<div class="user-profile">
				<div class="inner-box">
					
					<div class="nameAndPhoto">
						<div class="name-wrapper">
							<span class="name">${sessionScope.userName}</span>님
						</div>
						<div class="photo-wrapper">
							<img src="${path}/img/kcc.png">
						</div>
					</div>
					<span class="job">관리자</span> 
					<br><br><br><br>
					<br><br><br><br>
					<span class="last-ip">최종 접속 IP &nbsp;&nbsp; </span><span class="ip">111.111.111</span><br><br>
					<span class="last-login">최종 로그인 &nbsp;&nbsp;&nbsp; </span><span class="time">2023-09-12 10:00</span>
				</div>
				<div class="buttons">
					<c:url var="toMyPage" value="/adminprofilepage" />
					<a href="${toMyPage}" class="toMyPage">
						마이페이지
					</a>
					<span>&nbsp;|&nbsp;</span> 
					<c:url var="toLogin" value="/loginpage" />
					<a href="${toLogin}" class="logout">
						로그아웃
					</a>
				</div>
			</div>
			<div class="shortcut-service">
				<div class="inner-box">
					<p>바로 가기 서비스</p> <br><br>
					<ul class="shortcut-list">
						<li class="shortcuts"><span class="round-btn">01</span>&nbsp;&nbsp;<a href="#">A/S 접수</a></li>
						<li class="shortcuts"><span class="round-btn">02</span>&nbsp;&nbsp;<a href="#">A/S 접수</a></li>
						<li class="shortcuts"><span class="round-btn">03</span>&nbsp;&nbsp;<a href="#">A/S 접수</a></li>
						<li class="shortcuts"><span class="round-btn">04</span>&nbsp;&nbsp;<a href="#">A/S 접수</a></li>
					</ul>
				</div>
			</div>
			<div class="toKccBrew">
				<div class="inner-box">
					<a href="#">
						<img alt="logo" class="tomainimg" src="${path}/resources/img/logo.png">
					</a>
					<a href="#">
						<button class="kcclink">
							KccBrew 홈페이지로 이동 ▶
						</button>
					</a>
				</div>
			</div>
		</div>
	</c:when>
	<c:when test="${sessionScope.userTypeCd eq '02'}">
		<div class="profileAndguide">
			<div class="user-profile">
				<div class="inner-box">
					<div class="nameAndPhoto">
						<div class="name-wrapper">
							<span class="name">${sessionScope.userName}</span>님
						</div>
						<div class="photo-wrapper">
							<img src="${path}/img/kcc.png">
						</div>
					</div>
					<span class="job">점주</span>
					<br><br><br><br>
					<br><br><br><br>
					<span class="last-ip">최종 접속 IP &nbsp;&nbsp; </span><span class="ip">111.111.111</span><br><br>
					<span class="last-login">최종 로그인 &nbsp;&nbsp;&nbsp; </span><span class="time">2023-09-12 10:00</span>
				</div>
				<div class="buttons">
					<c:url var="toMyPage" value="/adminprofilepage" />
					<a href="${toMyPage}" class="toMyPage">
						마이페이지
					</a>
					<span>&nbsp;|&nbsp;</span> 
					<c:url var="toLogin" value="/loginpage" />
					<a href="${toLogin}" class="logout">
						로그아웃
					</a>
				</div>
			</div>
			<div class="shortcut-service">
				<div class="inner-box">
					<p>바로 가기 서비스</p> <br><br>
					<ul class="shortcut-list">
						<li class="shortcuts"><span class="round-btn">01</span>&nbsp;&nbsp;<a href="#">A/S 접수</a></li>
						<li class="shortcuts"><span class="round-btn">02</span>&nbsp;&nbsp;<a href="#">A/S 접수</a></li>
						<li class="shortcuts"><span class="round-btn">03</span>&nbsp;&nbsp;<a href="#">A/S 접수</a></li>
						<li class="shortcuts"><span class="round-btn">04</span>&nbsp;&nbsp;<a href="#">A/S 접수</a></li>
					</ul>
				</div>
			</div>
			<div class="toKccBrew">
				<div class="inner-box">
					<a href="#">
						<img alt="logo" class="tomainimg" src="${path}/resources/img/logo.png">
					</a>
					<a href="#">
						<button class="kcclink">
							KccBrew 홈페이지로 이동 ▶
						</button>
					</a>
				</div>
			</div>
		</div>
	</c:when>
	<c:when test="${sessionScope.userTypeCd eq '03'}">
		<div class="profileAndguide">
			<div class="user-profile">
				<div class="inner-box">
					<div class="nameAndPhoto">
						<div class="name-wrapper">
							<span class="name">${sessionScope.userName}</span>님
						</div>
						<div class="photo-wrapper">
							<img src="${path}/img/kcc.png">
						</div>
					</div>
					<span class="job">수리기사</span>
					<br><br><br><br>
					<br><br><br><br>
					<span class="last-ip">최종 접속 IP &nbsp;&nbsp; </span><span class="ip">111.111.111</span><br><br>
					<span class="last-login">최종 로그인 &nbsp;&nbsp;&nbsp; </span><span class="time">2023-09-12 10:00</span>
				</div>
				<div class="buttons">
					<c:url var="toMyPage" value="/adminprofilepage" />
					<a href="${toMyPage}" class="toMyPage">
						마이페이지
					</a>
					<span>&nbsp;|&nbsp;</span> 
					<c:url var="toLogin" value="/loginpage" />
					<a href="${toLogin}" class="logout">
						로그아웃
					</a>
				</div>
			</div>
			<div class="shortcut-service">
				<div class="inner-box">
					<p>바로 가기 서비스</p> <br><br>
					<ul class="shortcut-list">
						<li class="shortcuts"><span class="round-btn">01</span>&nbsp;&nbsp;<a href="#">A/S 접수</a></li>
						<li class="shortcuts"><span class="round-btn">02</span>&nbsp;&nbsp;<a href="#">A/S 접수</a></li>
						<li class="shortcuts"><span class="round-btn">03</span>&nbsp;&nbsp;<a href="#">A/S 접수</a></li>
						<li class="shortcuts"><span class="round-btn">04</span>&nbsp;&nbsp;<a href="#">A/S 접수</a></li>
					</ul>
				</div>
			</div>
			<div class="toKccBrew">
				<div class="inner-box">
					<a href="#">
						<img alt="logo" class="tomainimg" src="${path}/resources/img/logo.png">
					</a>
					<a href="#">
						<button class="kcclink">
							KccBrew 홈페이지로 이동 ▶
						</button>
					</a>
				</div>
			</div>
		</div>
	</c:when>
</c:choose>
</html>
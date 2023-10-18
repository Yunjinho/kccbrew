<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${path}/resources/css/comm/myPage.css" />
<link rel="stylesheet" href="/resources/css/asMng/content-template.css" />
<link rel="stylesheet" href="/resources/css/userMng/userMngList.css" />
</head>
<style>
button {
	cursor: pointer;
}

button:disabled {
	cursor: default;
	box-shadow: none;
	opacity: .65;
	background-color: navy;
}

.modButtons {
	width: 100%;
	height: 24px;
	margin: auto;
	text-align: center;
	padding: 0 0;
	text-decoration: none;
}

.container2 {
	background-color: unset;
	min-height: 250px;
	margin: auto;
	width: 283px;
	margin-top: 50px;
}

.register-msg>li {
	height: 10px;
	width: 283px;
}

.register-msg {
	margin: 0px;
	height: 8px;
	padding: 0;
}
</style>
<body>

	<div id="page-content-wrap">

		<!-- ********** 페이지 네비게이션 끝 ********** -->
		<div id="region-main">
			<div role="main">
				<span id="maincontent"></span>
				<div class="user-past">

					<!-- ********** 회원 리스트 조회 ********** -->
					<div id="content">
						<div style="text-align: center; margin-top:60px;">
							<img src="<c:url value='/resources/img/login/lock.png' />"
								style="height: 30px; width: 30px;" alt="Check List"
								class="header-icon" />
						</div>
						<div
							style="margin-top:30px; font-size: 1.5em; font-weight: bold; text-align: center;">비밀글
							입니다.</div>
						<div style="text-align: center;     color: gray;">
							<sec:authentication property="principal.username" />
							님의 비밀번호 변경 페이지 입니다.
						</div>
						<div  style="text-align: center;     color: gray;">본인이라면 비밀번호를 입력하세요.</div>
						<form action="/mypage/chgpwd" method="post"
							name="chgPwd" onsubmit="return subBtn()">
							<div class="container2">
								<div
									style="display: flex; flex-direction: row; width: max-content;">
									<input class="pwdInput1" type="password" name="currentPassword"
										placeholder="현재 비밀번호" size="30" />
									<button type="button" class="updateBtn" id="test"
										style="margin: 4px;">비밀번호 확인</button>
								</div>
								<br>

								<div id="start" style="display: none;">
									<div style="display: inline-block;">
										<input class="pwdInput2" type="password" name="newPassword"
											placeholder="새 비밀번호" size="30" />
										<ul class="register-msg">
											<li id="userPwdMsg" style="margin-bottom: 20px;">비밀번호:
												필수 정보입니다.</li>
										</ul>
									</div>
									<div style="display: inline-block;">
										<input class="pwdInput3" type="password"
											name="checkNewPassword" placeholder="새 비밀번호 확인" size="30" />

										<ul class="register-msg">
											<li id="userPwdConfirmMsg">비밀번호가 일치하지 않습니다.</li>
										</ul>
									</div>
								</div>
								<div class="modButtons">
									<button type="submit" id="confirmMod" class="updateBtn"
										style=" width: 100%; border-radius: 0;" disabled>확인</button>
									<c:url var="cancel" value="/mypage"></c:url>
									<div style="display: block; margin-top: 150px;">
										<a href="#" onclick="window.close();"> 닫기 </a>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="<c:url value="/resources/js/comm/chgPwd.js"/>"></script>

</body>
</html>
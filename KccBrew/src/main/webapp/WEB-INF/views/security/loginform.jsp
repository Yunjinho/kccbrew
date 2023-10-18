<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="icon" type="image/png" href="${path}/resources/img/main/kccbrw-representative-logo.png">

<!-- ------------------ FONTS --------------------------->
<!--------------------- NOTOSANS ------------------------>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans&display=swap"
	rel="stylesheet">

<!--------------------- NOTOSANS KR --------------------->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans&family=Noto+Sans+KR&display=swap"
	rel="stylesheet">

<script src="http://code.jquery.com/jquery-latest.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
	integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js"
	integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa"
	crossorigin="anonymous"></script>
<script src="<c:url value="resources/js/comm/login.js"/>"
	charset="UTF-8"></script>
<link href="<c:url value="resources/css/comm/login.css"/>"
	rel="stylesheet" type="text/css">
<link href="<c:url value="resources/css/comm/common.css"/>"
	rel="stylesheet" type="text/css">
<link rel="stylesheet" href="${path}/resources/css/comm/footer.css" />
<link rel="stylesheet" href="${path}/resources/css/comm/reset.css" />
</head>
<body class="login" onpageshow="if(event.persisted) noBack();"
	onunload="" marginwidth="0" marginheight="0">

	<div class="wrap">
		<a href="/"><img src="<c:url value="resources/img/logo14.png"/>"
			class="logo"></a>
		<!-- 로그인 영역 -->
		<div class="loginbox">
			<!-- 로그인 입력 -->
			<div class="login_input">
				<h2>
					<img src="<c:url value="resources/img/login/login_logo.png"/>"
						alt="로그인 텍스트 이미지"> <span> <br> <em>KccBrew</em>에
						오신 것을 환영합니다!
					</span>
				</h2>

				<div class="login_01">
					<!-- 로그인 입력 -->
					<fieldset>
						<form action="./login" method="post">
							<div>
								<div class="login-user-input">
									<label for="id"></label> <img
										src="<c:url value="resources/img/login/login_id.png"/>"
										alt="로그인 텍스트 이미지"> <input type="text" id="id"
										name="username" value="${username }" title="아이디"
										placeholder="아이디">
								</div>
								<div class="login-user-input">
									<img src="<c:url value="resources/img/login/login_pwd.png"/>"
										alt="로그인 텍스트 이미지"> <label for="pw"></label> <input
										onkeyup="enterkey();" type="password" id="pw" name="password"
										title="비밀번호" placeholder="비밀번호">
								</div>

								<div class="login_error_wrap">
									<div class="error_message">
										<c:if test="${not empty errorMessage}">
											<p id="login-error-message" style="color: red">${errorMessage}</p>
										</c:if>
									</div>
								</div>
							</div>

							<button type="submit" class="loginbtn">로그인</button>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</form>
					</fieldset>
					<!-- 로그인 입력 //-->
					<!-- 		<a class="login-btn" href="javascript:;" onclick="login()" class="loginbtn">로그인</a>
					<p id="login-msg"></p> -->
				</div>
			</div>
			<!-- 로그인 입력 //-->

		<!-- 로그인 영역 //-->
		<!-- 서비스 영역 -->
		<div class="servicezone">
			<h4>
<!-- 				<p>KccBrew</p> -->
				<p class="title">
					<span>SERVICE</span> ZONE
				</p>
			</h4>
			<div>
				<div>
					<img src="<c:url value="resources/img/login/login_signup.png"/>">
				</div>
				<a href="/signup" class="icon_02">
					<dl>
						<dd class="loginform-service-title">회원 가입</dd>
						<dt>어서오세요!</dt>
					</dl>
				</a>
			</div>

			<div>
				<div>
					<img src="<c:url value="resources/img/login/login_id.png"/>">
				</div>
				<a href="/userSearch" class="icon_01">
					<dl>
						<dd class="loginform-service-title">아이디 찾기</dd>
						<dt>본인 아이디를 잊어버리셨나요?</dt>
					</dl>
				</a>
			</div>

			<div>
				<div>
					<img src="<c:url value="resources/img/login/login_pwd.png"/>">
				</div>
				<a href="/userSearch" class="icon_02">
					<dl>
						<dd class="loginform-service-title">비밀번호 찾기</dd>
						<dt>비밀번호를 잊어버리셨나요?</dt>
					</dl>
				</a>
			</div>
		</div>
	</div>
</body>
</html>
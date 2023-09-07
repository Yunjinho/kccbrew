<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.js"></script> 
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
<script src="<c:url value="resources/js/comm/login.js"/>"></script>
<link href="<c:url value="resources/css/comm/login.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="resources/css/comm/common.css"/>" rel="stylesheet" type="text/css">
</head>
<body class="login" onpageshow="if(event.persisted) noBack();" onunload="" marginwidth="0" marginheight="0">
<div class="wrap">
		<a href="/"><img src="<c:url value="resources/img/logo.png"/>" class="logo"></a>
		
		<!-- 로그인 영역 -->
		<div class="loginbox">
			<!-- 로그인 입력 -->
			<div class="login_input">
				<h2>
					<img src="<c:url value="resources/img/login/login_text.PNG"/>" alt="로그인 텍스트 이미지">
					<span>
						<em>KccBrew</em>에<br>오신 것을 환영합니다.
					</span>
				</h2>
				<div class="login_01">
					<!-- 로그인 입력 -->
					<fieldset>
							<form id="LoginForm" name="LoginForm" method="get" style="display: inline" action="/login">
								<div>
									<label for="id"></label>
									<img src="<c:url value="resources/img/login/login_id.png"/>" alt="로그인 텍스트 이미지">
									<input type="text" id="id" name="userId" title="아이디" placeholder="아이디">
								</div>
								<div>
									<img src="<c:url value="resources/img/login/login_pwd.png"/>" alt="로그인 텍스트 이미지">
							    	<label for="pw"></label>
							    	<input type="password" id="pw" name="userPwd" title="비밀번호" placeholder="비밀번호">
								</div>

							</form>
					</fieldset>
					<p id="login-msg"></p>
					<!-- 로그인 입력 //-->
					<a href="javascript:;" onclick="login()" class="loginbtn">로그인</a>
				</div>
			</div>
			<!-- 로그인 입력 //-->
		</div>
		<!-- 로그인 영역 //-->
		<!-- 서비스 영역 -->
		<div class="servicezone">
			<h4>
				<p>KccBrew </p>
				<p class="title">
					<span>SERVICE</span> ZONE
				</p>
			</h4>
			<div>
				<div>
					<img src="<c:url value="resources/img/login/login_signup.png"/>" >
				</div>
				<a href="/register" class="icon_02">
					<dl>
						<c:url var="toRegister" value="/goRegisterPage" />
						<a href="${toRegister}">
							<dd>회원 가입</dd>
							<dt>어서오세요!</dt>
						</a>
					</dl>
				</a>
			</div>
			
			<div>
				<div>
					<img src="<c:url value="resources/img/login/login_id.png"/>" >
				</div>
				<a href="javascript:;" onclick="javascript:goHelpPage(); return false;" class="icon_01">
				<dl>
					<dd>아이디 찾기</dd>
					<dt>본인 아이디를 잊어버리셨나요?</dt>
				</dl>
			</a> 
			</div>
			
			<div>
				<div>
					<img src="<c:url value="resources/img/login/login_pwd.png"/>" >
				</div>
				<a href="javascript:;" onclick="javascript:goPwHelpPage(); return false;" class="icon_02">
				<dl>
					<dd>비밀번호 찾기</dd>
					<dt>비밀번호를 잊어버리셨나요?</dt>
				</dl>
				</a>
			</div>
		</div>
	</div>
</body>
</html>
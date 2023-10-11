<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<%
	response.setContentType("text/html; charset=utf-8");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
	integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js"
	integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa"
	crossorigin="anonymous"></script>
<script src="<c:url value="/resources/js/comm/login.js"/>"
	charset="UTF-8"></script>
<link href="<c:url value="/resources/css/comm/searchUser.css"/>"
	rel="stylesheet" type="text/css">
<link href="<c:url value="/resources/css/comm/common.css"/>"
	rel="stylesheet" type="text/css">
<link rel="stylesheet" href="/resources/css/comm/footer.css" />
<link rel="stylesheet" href="/resources/css/comm/reset.css" />
</head>
<style>
#searchBtn2 {
	cursor: pointer;
	text-decoration: underline;
	display: inline-block;
	float: right;
	width: 92px;
	height: 92px;
	margin-left: 10px;
	border-radius: 100px;
	-moz-border-radius: 100px;
	-webkit-border-radius: 100px;
	background-color: #094a9a;
	font-size: 18px;
	font-weight: 400;
	color: #fff;
	line-height: 92px;
	text-align: center;
}
</style>
<body class="login" marginwidth="0" marginheight="0">
	<div class="wrap">
		<a href="/"><img src="<c:url value="/resources/img/logo13.png"/>"
			class="logo"></a>
		<!-- 로그인 영역 -->
		<div class="loginbox">
			<!-- 로그인 입력 -->
			<div class="login_input">
				<h2>
					<img src="<c:url value="/resources/img/login/login_text.PNG"/>"
						alt="로그인 텍스트 이미지"> <span> <br> <em>KccBrew</em>에<br>오신
						것을 환영합니다.
					</span>
				</h2>

				<div class="login_01" style="margin-top: 15px;">
					<!-- 로그인 입력 -->
					<fieldset>
						<%@ include file="/WEB-INF/views/security/userIdSearchModal.jsp"%>


						<div>
							<input type="radio" class="custom-control-input" id="search_1"
								name="search_total" onclick="search_check(1)" checked="checked">
							<label for="search_1">아이디 찾기</label> <input type="radio"
								class="custom-control-input" id="search_2" name="search_total"
								onclick="search_check(2)"> <label for="search_2">비밀번호
								찾기</label>
						</div>
						<hr style="margin-bottom: 15px; margin-top: 15px;">
						<div id="searchI">
							<div>
								<label for="userNm">이름</label> <input type="text"
									class="form-control" id="userNm" name="userNm"
									placeholder="ex) 배수연">
							</div>
							<div>
								<label class="font-weight-bold text-white" for="userTelNo">번호</label>
								<input type="text" class="form-control" id="userTelNo"
									name="userTelNo" placeholder="ex) 01077779999">

							</div>
							<div style="margin-top: 60px;">
								<a href="#" id="searchBtn" class="btn btn-danger btn-block"
									onclick="idSearch_click()">확인</a>
							</div>
						</div>
						<div id="searchP" style="display: none;">
							<form action="/searchPassword" name="pwd" method="post">
								<div>
									<label class="font-weight-bold text-white" for="userId">아이디</label>
									<input type="text" class="form-control" id="userId"
										name="userId" placeholder="ex) bsy5133">
								</div>

								<div>
									<label class="font-weight-bold text-white" for="userEmail">이메일</label>
									<input type="email" class="form-control" id="userEmail"
										name="userEmail" placeholder="ex) kccbrew@gmail.com">

								</div>
								<div style="margin-top: 60px;">
									<button id="searchBtn2" type="submit">확인</button>
								</div>
							</form>
						</div>
					</fieldset>

					<!-- 로그인 입력 //-->
					<!-- 		<a class="login-btn" href="javascript:;" onclick="login()" class="loginbtn">로그인</a>
					<p id="login-msg"></p> -->
				</div>
			</div>
			<!-- 로그인 입력 //-->
		</div>
		<!-- 로그인 영역 //-->
		<!-- 서비스 영역 -->
		<div class="servicezone">
			<h4>
				<p>KccBrew</p>
				<p class="title">
					<span>SERVICE</span> ZONE
				</p>
			</h4>
			<div>
				<div>
					<img src="<c:url value="/resources/img/login/login_signup.png"/>">
				</div>
				<a href="/login" class="icon_02">
					<dl>
						<dd>로그인</dd>
						<dt>어서오세요!</dt>
					</dl>
				</a>
			</div>

			<div>
				<div>
					<img src="<c:url value="/resources/img/login/login_id.png"/>">
				</div>
				<a href="javascript:;"
					onclick="javascript:goHelpPage(); return false;" class="icon_01">
					<dl>
						<dd>아이디 찾기</dd>
						<dt>본인 아이디를 잊어버리셨나요?</dt>
					</dl>
				</a>
			</div>

			<div>
				<div>
					<img src="<c:url value="/resources/img/login/login_pwd.png"/>">
				</div>
				<a href="javascript:;"
					onclick="javascript:goPwHelpPage(); return false;" class="icon_02">
					<dl>
						<dd>비밀번호 찾기</dd>
						<dt>비밀번호를 잊어버리셨나요?</dt>
					</dl>
				</a>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {

			/////////모///달///기///능///////////
			// 1. 모달창 히든 불러오기
			$('#searchBtn').click(function() {
				$("html").css("overflow", "hidden");
				$(".modal_contents").css("display", "block");
			});
			// 2. 모달창 닫기 버튼
			$('#close').on('click', function() {
				$("html").css("overflow", "auto");
				$(".modal_contents").css("display", "none");
			});

		});

		//체크 버튼에 따라 아이디/비밀번호 기능이 달라진다
		function search_check(num) {
			if (num == '1') {
				document.getElementById("searchP").style.display = "none";
				document.getElementById("searchI").style.display = "";
			} else {
				document.getElementById("searchI").style.display = "none";
				document.getElementById("searchP").style.display = "";
			}
		}
		var idV = "";
		// 아이디 값 받고 출력하는 ajax
		function idSearch_click() {
			var userNm = $('#userNm').val();
			var userTelNo = $('#userTelNo').val();

			$.ajax({
				type : "POST",
				data : {
					"userNm" : userNm,
					"userTelNo" : userTelNo
				},
				url : "/userSearch",
				success : function(result) {
					console.log(result);
					if (result.id == '') {
						$('#id_value').text("회원 정보를 확인해주세요!");
					} else {
						$('#inputId').text(result.id);
						$('#inputReg').text(result.regDate);

						idV = result;
					}
				}
			});
		}
		
		$('#searchBtn2').on('click', function(event) {
		    event.preventDefault(); // 기본 폼 제출 동작을 막습니다.

		    // 폼 데이터를 가져옵니다.
		    var formData = {
		        userId: $('#userId').val(), // 아이디 입력 필드의 값을 가져옵니다.
		        userEmail: $('#userEmail').val() // 이메일 입력 필드의 값을 가져옵니다.
		    };

		    // AJAX POST 요청을 보냅니다.
		    $.ajax({
		        type: 'POST',
		        url: '/searchPassword', // 서버 엔드포인트 URL을 실제로 사용하는 URL로 업데이트합니다.
		        data: formData,
		        success: function(result) {
		            console.log(result);
		                alert("이메일로 임시 비밀번호를 발신했습니다.");
		                location.href = "/login";
		        },
		        error: function(xhr, status, error) {
		            console.error(error);
		            alert("정보를 확인해 주세요.");
	                location.href = "/searchUser";
		            // 에러가 발생한 경우에 대한 처리를 추가합니다.
		        }
		    });
		});
	</script>
</body>
</html>
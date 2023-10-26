<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디/비밀번호 찾기</title>
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
<style>
#loading-spinner{
position: fixed; /* 화면에 고정 */
	z-index: 1; /* 다른 요소보다 위에 표시되도록 설정 */
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.5); /* 반투명한 배경 색상 */
	text-align: center;
}
#loadingIcon{
	margin: 20% auto; /* 모달을 수직, 수평 가운데로 위치시킴 */
	padding: 20px;
	width: 5%;
	border-radius: 20px;
	display: flex;
	flex-direction: column;
}
</style>
<body class="login" marginwidth="0" marginheight="0">
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
					<fieldset style="margin-top: -50px;">
						<%@ include file="/WEB-INF/views/security/userIdSearchModal.jsp"%>


						<div style="text-align:center; margin-top:15px;">
							<input type="radio" class="custom-control-input" id="search_1"
								name="search_total" onclick="search_check(1)" checked="checked" style="width:15px; vertical-align:middle; margin-bottom: 7px;"
>
							<label for="search_1">아이디 찾기</label> <input type="radio"
								class="custom-control-input" id="search_2" name="search_total"
								onclick="search_check(2)" style="width:15px; vertical-align:middle; margin-bottom: 7px;"> <label for="search_2">비밀번호
								찾기</label>
						</div>
						<hr style="margin-bottom: 15px; margin-top: 2px;">
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
						
								<button type="button" id="searchBtn" class="btn btn-danger btn-block loginbtn"
									onclick="idSearch_click()" style="    margin-top: -80px;">확인</button>
						
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
							<div id="loading-spinner" style="display: none;">
  <img src="/resources/img/loading.gif" alt="로딩 중..." id="loadingIcon"/>
</div>
									<button id="searchBtn2" type="submit" class="loginbtn" style="    margin-top: -80px;">확인</button>
							
							</form>
						</div>
					</fieldset>

					<!-- 로그인 입력 //-->
					<!-- 		<a class="login-btn" href="javascript:;" onclick="login()" class="loginbtn">로그인</a>
					<p id="login-msg"></p> -->
				</div>
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
				location.reload();
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
        type: "POST",
        data: {
            "userNm": userNm,
            "userTelNo": userTelNo
        },
        url: "/userSearch",
        success: function(result) {
            console.log(result);
            var users = result.users;
            if (users.length === 0) {
                $('#id_value').text("회원 정보를 확인해주세요!");
            } else {
                for (var i = 0; i < users.length; i++) {
                    var user = users[i];
                    var id = user.id;
                    var useYn = user.useYn;
                    var regDate = user.regDate;
					if(useYn=="Y"){
                    // 처리할 작업 수행
                    // 예를 들어, 각 사용자의 id와 regDate를 화면에 출력
                    $('#inputId').append(id + "<br>");
                    $('#inputReg').append(regDate + "<br>");}
					else{
						
						$('#inputId').append(id + "<br>");
						$('#inputReg').append("사용할 수 없는 계정입니다." + "<br>");}
					
                }
            }
        }
    });
}
		$('#searchBtn2').on('click', function(event) {
		    event.preventDefault(); // 기본 폼 제출 동작을 막습니다.
		    $('#loading-spinner').show();
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
		        	
		        	if(result==="true"){
		        		console.log(result);
		        		 $('#loading-spinner').hide();
		                alert("이메일로 임시 비밀번호를 발신했습니다.");
		                location.href = "/login";
		        	}else if(result==="false"){
		                console.log(result);
		                $('#loading-spinner').hide();
		        		alert("가입하신 아이디와 이메일이 일치하지 않습니다.");
		        	}
		        },
		        error: function(xhr, status, error) {
		            console.error(error);
		            $('#loading-spinner').hide();
		            alert("정보를 확인해 주세요.");
	                location.href = "/searchUser";
		            // 에러가 발생한 경우에 대한 처리를 추가합니다.
		        }
		    });
		});
	</script>
</body>
</html>
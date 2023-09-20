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
<link rel="stylesheet" href="${path}/resources/css/comm/myPage.css"/>
</head>
<body>
	<section id="notice" class="notice">
		<div class="container2">
			<div class="category">마이페이지</div>
			<hr class="line">
				<form action='<c:url value= '/confirmmod'/>' method="post">
				<div class="form-wrapper">
					<div class="formAndBtns">
						<div class="changePasswordForm">
							<h2>비밀번호 변경</h2>
							<input class="pwdInput1" type="password" name="currentPassword" placeholder="현재 비밀번호"/><br>
							<input class="pwdInput2" type="password" name="newPassword" placeholder="새 비밀번호"/><br>
							<input class="pwdInput3" type="password" name="checkNewPassword" placeholder="새 비밀번호 확인"/><br>
						</div>
						<ul class="register-msg">
							<li id="userIdMsg">아이디: 필수 정보입니다.</li>
							<li id="userPwdMsg">비밀번호: 필수 정보입니다.</li>
							<li id="userPwdConfirmMsg">비밀번호가 일치하지 않습니다.</li>
						</ul>
						<div class="modButtons">
							<button type="submit" id="confirmMod">확인</button>
							<c:url var="cancel" value="/mypage"></c:url>
							<a href="${cancel}">
								<button type="button" class="cancel">취소</button>
							</a>
						</div>
					</div>
				</div>
			</form>
		</div>
	</section>
	<script>
	$(document).ready(function(){
		//수정 확인 버튼
	    $("#").click(function(){
	        var selectedMachineCode = $("#chooseMachineCode").val();
	        var selectedLocationCode = $("#chooseLocationCode").val();
	        
	        console.log(selectedMachineCode + "machine");
	        console.log(selectedLocationCode + "location");
	        
	        $("#machineCodeHidden").val(selectedMachineCode);
	        $("#locationCodeHidden").val(selectedLocationCode);
	        
	        $("form").submit();
	    });
	    
	  	//사용자 비밀번호 조건 확인
		function checkUserPwd(){
			var userPwd=$("input[name=newPassword]");
			if(userPwd.val()==""){
				userPwd.parent().css("border","1px solid red");
				$("#userPwdMsg").css("display","block");
				return false;
			}else{
				//비밀번호 정규식 체크
				var regex=/^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@#$%^&+=!_])[A-Za-z\d@#$%^&+=!_]{8,16}$/
					if(!(regex).test(userPwd.val())){
						$("#userPwdMsg").html("비밀번호: 8~16자의 최소 1개 이상의 영문 대/소문자, 숫자, 특수문자(@#$%^&+=!_)를 포함해야 합니다.")
						$("#userPwdMsg").css("display","block");
						userPwd.parent().css("border","1px solid red");
						return false;
					}
				userPwd.parent().css("border","none");
				$("#userPwdMsg").css("display","none");
				return true;
			}
		}
	});
	</script>
</body>
</html>
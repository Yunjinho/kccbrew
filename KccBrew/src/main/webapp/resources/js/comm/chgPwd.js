var confirmMod = document.querySelector("#confirmMod");
var regex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@#$%^&+=!_])[A-Za-z\d@#$%^&+=!_]{8,16}$/;
$(document)
		.ready(
				function() {
					// 사용자 비밀번호 조건 확인 및 입력란 유효성 검사
					$("input[name=newPassword], input[name=checkNewPassword]")
							.on(
									"keyup",
									function() {
										var userPwd = $(
												"input[name=newPassword]")
												.val();
										var checkPwd = $(
												"input[name=checkNewPassword]")
												.val();
										var userPwdMsg = $("#userPwdMsg");
										var userPwdConfirmMsg = $("#userPwdConfirmMsg");

										if (userPwd === "") {
											userPwdMsg.html("비밀번호를 입력해주세요.");
											userPwdMsg.css("display", "block");
											userPwdConfirmMsg.css("display",
													"none");
											$("input[name=newPassword]").css(
													"border", "1px solid red");
											confirmMod.disabled = true;
										} else if (!(regex.test(userPwd))) {
											userPwdMsg
													.html("비밀번호: 8~16자의 최소 1개 이상의 영문 대/소문자, 숫자, 특수문자(@#$%^&+=!_))를 포함해야 합니다.");
											userPwdMsg.css("display", "block");
											userPwdConfirmMsg.css("display",
													"none");
											$("input[name=newPassword]").css(
													"border", "1px solid red");
											confirmMod.disabled = true;
										} else {
											userPwdMsg.css("display", "none");
											$("input[name=newPassword]")
													.css("border",
															"1px solid black");

											if (checkPwd !== userPwd) {
												userPwdConfirmMsg
														.html("비밀번호가 일치하지 않습니다.");
												userPwdConfirmMsg.css(
														"display", "block");
												userPwdMsg.css("display",
														"none");
												$(
														"input[name=checkNewPassword]")
														.css("border",
																"1px solid red");
												confirmMod.disabled = true;
											} else {
												userPwdMsg.css("display",
														"none");
												userPwdConfirmMsg.css(
														"display", "none");
												$(
														"input[name=checkNewPassword]")
														.css("border",
																"1px solid black");
												confirmMod.disabled = false;
											}
										}
									});
				});

function subBtn() {
	var password = $("input[name=password]").val();
	var currentPassword = $("input[name=currentPassword]").val();
	if (currentPassword === "") {
		alert("현재 비밀번호를 입력해주세요.");
		$("input[name=currentPassword]").css("border", "1px solid red");
		return false;
	} else if ($("input[name=newPassword]").val() !== $(
			"input[name=checkNewPassword]").val()) {
		alert("새비밀번호가 일치하지 않습니다.");
		return false;
	} else if (!(regex.test($("input[name=newPassword]").val()))) {
		alert("새 비밀번호를 양식에 맞추어 입력하세요.");
		return false;
	} else {
		alert("비밀번호를 변경하였습니다.");
		window.close();
		return true;


	}
}

$("#test").click(function() {

	let json = new Object();
	json.currentPassword = $("input[name=currentPassword]").val();

	console.log(json);

	$.ajax({
		url : "/chgpwd",
		type : 'POST',
		contentType : 'application/json',
		data : JSON.stringify(json),
		dataType : 'json',
		success : function(result) {
			var start = document.getElementById("start");
			if (result) {
				alert("비밀번호 변경이 가능합니다.");
				start.style.display = 'block';
				$("input[name=currentPassword]").css("pointer-events", "none");
			} else {
				alert("비밀번호를 확인해주세요.");
			}
		},
		error : function(xhr, status, error) {
			console.error(error);
		}
	});
});
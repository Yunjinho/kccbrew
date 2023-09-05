function login(){
	var id=$("input[name=userId]").val();
	var pwd=$("input[name=userPwd]").val();
	$.ajax({
	    type : "get",           // 타입 (get, post, put 등등)
	    url : "/login",           // 요청할 서버url
	    dataType : "text",       // 데이터 타입 (html, xml, json, text 등등)
	    data : {
			'id' : id,
			'pwd':pwd
		},
	    success : function(data) { // 결과 성공 콜백함수
			if(data=="1"){
				location.href="/main"
			}else{
				if(data=="2"){
					$("#login-msg").html("인증 진행중인 계정입니다.");
				}else{
					$("#login-msg").html("아이디(로그인 전용 아이디) 또는 비밀번호를 잘못 입력했습니다.<br> 입력하신 내용을 다시 확인해주세요.");
				}
				$("#login-msg").css("display","block");
			}
	    }
	});
}

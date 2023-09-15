function login(){
	var id=$("input[name=userId]").val();
	var pwd=$("input[name=userPwd]").val();
//	var userName = $("#userName").text();
//	$("#userName").text("userName");

	$.ajax({
	    type : "get",           // 타입 (get, post, put 등등)
	    url : "/login",           // 요청할 서버url
	    dataType : "text",       // 데이터 타입 (html, xml, json, text 등등)
	    data : {
			'id' : id,
			'pwd':pwd
		},
	    success : function(data) { // 결과 성공 콜백함수
			if(data=="t"){
				//로그인 성공 시 userTypeCd 값을 받아와서 사용자 유형 확인
				$.ajax({
                    type: "GET",
                    url: "/getUserTypeCd", // 사용자의 userTypeCd 값을 가져오는 컨트롤러 경로
                    dataType: "text",
                    success: function(userTypeCd) {
                        // userTypeCd 값에 따라 다른 페이지로 리다이렉트
                        if (userTypeCd === "01") {
                            location.href = "/adminMain";
                        } else if (userTypeCd === "02") {
                            location.href = "/managerMain";
                        } else if (userTypeCd === "03") {
                            location.href = "/mechanicMain";
                        } else {
                            // 다른 경우에 대한 처리
                            alert("알 수 없는 사용자 유형 코드: " + userTypeCd);
                        }
                    }
                });
				
				$.ajax({
					type:"GET",
					url: "/getUserName",
					dataType:"text",
					success:function(userName){
						$("#userName").text(userName);
					}
				});
			}else{
				var text;
				if(data=="f"){text='아이디 혹은 비밀번호를 잘못 입력하셨습니다.'}else{ text='미승인된 계정입니다.'}
				$("#login-msg").html(text);
				$("#login-msg").css("display","block");
			}
	    }
	});
}

function enterkey() {
	if (window.event.keyCode == 13) {
		 
        // 엔터키가 눌렸을 때 실행할 내용
        login();
   }
}

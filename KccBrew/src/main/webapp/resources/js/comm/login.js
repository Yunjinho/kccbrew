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
			if(data=="t"){
				location.href="/main"
			}else{
				$("#login-msg").html(data);
				$("#login-msg").css("display","block");
			}
	    }
	});
}

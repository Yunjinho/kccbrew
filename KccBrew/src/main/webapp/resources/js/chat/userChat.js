var url = "ws://192.168.0.103:8080/userchat";
var webSocket = null;

//var webSocket = new WebSocket("ws://localhost:8080/userchat");

var messageTextArea = document.getElementById("messageTextArea");
var sessionUserId = document.querySelector('#sessionUserId').value;
var chatIcon = document.querySelector('#chatIcon');
var chatArea = document.querySelector(".chatArea");
var uuid = null;

chatIcon.addEventListener('click', function(){
	chatArea.classList.toggle("hidden");
	
	//첫 클릭 시 소켓 생성 
	if(webSocket == null){
		webSocket = new WebSocket(url);
		
		//접속 시
		webSocket.onopen = function(message) {

			//기존 데이터 찾기
			$.ajax({
				url : '/getChatLog',
				type : 'post',
				data : {"id" : sessionUserId},
				dataType : 'json',
				success : function(data){
					if(data.length>0){//기존 채팅이 있을 때
						data.forEach(function(d){
							let user_id = d.user_id;
							let sender = d.sender;
							let msg = d.msg;
							//내 메세지
							if(!msg.includes("입장")){	
								if(user_id == sender){
									messageTextArea.value += "(나) : "+msg+"\n";
								}
								
								if(sender == "admin"){
									messageTextArea.value += "(관리자) : "+msg+"\n";
								}
							}
						})
					}
					else{//첫 입장
						messageTextArea.value += "문의사항을 남겨주세요. \n";
					}
				},
				error : function(){
					alert("에러");
				}
			})
		};
		
		//종료
		webSocket.onclose = function(message) {
		};

		//에러 발생
		webSocket.onerror = function(message) {
			messageTextArea.value += "error";
		};

		//서버-> 뷰 메세지 도착 
		webSocket.onmessage = function(message) {
			if(message.data.includes("uuid:")){
				uuid = message.data.split(':')[1];
				$.ajax({
					url : '/chatCreate',
					data : {"uuid" : uuid, "id" : sessionUserId, "msg" : "입장"},
					type : 'post'
				});
			}
			else {
				messageTextArea.value += "(admin) : " + message.data + "\n";
			}
		};
	}

});

//뷰 -> 서버 메세지 전송
function sendMessage() {	
	let message = document.getElementById("textMessage");
	messageTextArea.value += "(나) : " + message.value + "\n";
	$.ajax({
		url : '/chatCreate',
		data : {"uuid" : uuid, "id" : sessionUserId, "msg" : message.value},
		type : 'post'
	});
	
	webSocket.send(sessionUserId+","+message.value);
	message.value = "";
}

//종료
function closeChat(){
	if(confirm("종료 시 채팅 로그는 삭제됩니다.")){
		messageTextArea.value += "이용해 주셔서 감사합니다.";
		$.ajax({
			url : '/chatDelete',
			data : {"id" : sessionUserId},
			type : 'post'
		});
		chatArea.classList.add("hidden");
		
		webSocket.close();
		
		var chatD = document.querySelector('#userChat');
		chatD.style.display = "none";
	}
	else return;
}

function enter() {
	if(event.keyCode === 13) {
		sendMessage();
		return false;
	}
	return true;
}
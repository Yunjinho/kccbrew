
//var webSocket = new WebSocket("ws://localhost:8080/userchat");

var messageTextArea = document.getElementById("messageTextArea");
var sessionUserId = document.querySelector('#sessionUserId').value;
var chatIcon = document.querySelector('#chatIcon');
var chatArea = document.querySelector(".chatArea");
var uuid = null;
var url = "ws://localhost:8080/userchat/" + sessionUserId;
var webSocket = null;

function scrollToBottom() {
    let chatboxBody = document.querySelector('.chatbox_body');
    chatboxBody.scrollTop = chatboxBody.scrollHeight;
}

chatIcon.addEventListener('click', function(){
	chatArea.classList.toggle("hidden");
	
	//첫 클릭 시 소켓 생성 
	if(webSocket == null){
		webSocket = new WebSocket(url);
		
		//접속 시
		webSocket.onopen = function(message) {
			 webSocket.send("user_id:" + sessionUserId);
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
									 let messageElement = document.createElement("div");
									    messageElement.className = "message sender";

									    let messageText = document.createElement("div");
									    messageText.className = "message_text";
									    messageText.innerText = msg;

									    messageElement.appendChild(messageText);

									    // Add the new message element to .chatbox_body
									    let chatboxBody = document.querySelector('.chatbox_body');
									    chatboxBody.appendChild(messageElement);
									    scrollToBottom();
								}
								
								if(sender == "admin"){
									let messageElement = document.createElement("div");
								    messageElement.className = "message receive";

								    let messageText = document.createElement("div");
								    messageText.className = "message_text";
								    messageText.innerText = msg;

								    messageElement.appendChild(messageText);

								    // Add the new message element to .chatbox_body
								    let chatboxBody = document.querySelector('.chatbox_body');
								    chatboxBody.appendChild(messageElement);
								    scrollToBottom();
								}
							}
						})
					}
					else{//첫 입장
						 let messageElement = document.createElement("div");
						    messageElement.className = "message receive";

						    let messageText = document.createElement("div");
						    messageText.className = "message_text";
						    messageText.innerText = "무엇을 도와드릴까요?";

						    messageElement.appendChild(messageText);

						    // Add the new message element to .chatbox_body
						    let chatboxBody = document.querySelector('.chatbox_body');
						    chatboxBody.appendChild(messageElement);
						    scrollToBottom();
						    
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
		webSocket.onmessage = function (message) {
		    let parts = message.data.split(",");
		    console.log(message);
		    if (parts[0].includes("uuid:")) {
		        uuid = parts[0].split(":")[1];
		        // user_id는 이제 parts[1]에 있습니다
		        $.ajax({
		            url: '/chatCreate',
		            data: { "uuid": uuid, "id": sessionUserId, "msg": "입장" },
		            type: 'post'
		        });
		    } else {
		        /*messageTextArea.value += "(관리자) : " + message.data + "\n";*/
		        let messageElement = document.createElement("div");
			    messageElement.className = "message receive";

			    let messageText = document.createElement("div");
			    messageText.className = "message_text";
			    messageText.innerText =message.data;

			    messageElement.appendChild(messageText);

			    // Add the new message element to .chatbox_body
			    let chatboxBody = document.querySelector('.chatbox_body');
			    chatboxBody.appendChild(messageElement);
			    scrollToBottom();
		    }
		};
	}

});

//뷰 -> 서버 메세지 전송
function sendMessage() {
    let message = document.getElementById("textMessage").value;
    if (message.trim() === "") {
        return;
    }

    // Create a new message element
    let messageElement = document.createElement("div");
    messageElement.className = "message sender";

    let messageText = document.createElement("div");
    messageText.className = "message_text";
    messageText.innerText = message;

    messageElement.appendChild(messageText);

    // Add the new message element to .chatbox_body
    let chatboxBody = document.querySelector('.chatbox_body');
    chatboxBody.appendChild(messageElement);

    scrollToBottom();
	$.ajax({
		url : '/chatCreate',
		data : {"uuid" : uuid, "id" : sessionUserId, "msg" : message},
		type : 'post'
	});
	
	webSocket.send(sessionUserId+","+message);
	document.getElementById("textMessage").value = "";
}

//종료
function closeChat(){
	if(confirm("종료 시 채팅 로그는 삭제됩니다.")){
		/*messageTextArea.value += "이용해 주셔서 감사합니다.";*/
		$.ajax({
			url : '/chatDelete',
			data : {"id" : sessionUserId},
			type : 'post'
		});
		chatArea.classList.add("hidden");
		webSocket.send(sessionUserId+","+uuid + "님이 퇴장합니다.");
		webSocket.close();
		location.reload();
		
		/*var chatD = document.querySelector('#userChat');
		chatD.style.display = "none";*/
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
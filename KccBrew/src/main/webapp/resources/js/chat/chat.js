$("#sendBtn").click(function() {
    sendMessage();
    $('#message').val('')
});
 
let sock = new SockJS("/chat");
sock.onmessage = onMessage;
sock.onclose = onClose;
// 메시지 전송
function sendMessage() {
	var url = window.location.href;
	if(url.indexOf("admin") != -1){
        sock.send("admin : " + $("#message").val());
	}
	else{
		sock.send($('.sessionID').val() + " : " + $("#message").val());
	}
}
// 서버로부터 메시지를 받았을 때
function onMessage(msg) {
	var data = msg.data;        
	$("#messageArea").append(data + "<br/>");  
}

// 서버와 연결을 끊었을 때
function onClose(evt) {
	$("#messageArea").append("연결 끊김"); 
}


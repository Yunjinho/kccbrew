var webSocket = new WebSocket("ws://192.168.0.103:8080/adminchat");
var userUuid = null;
var id = null;
webSocket.onopen = function(message) {
};

webSocket.onclose = function(message) {
};

webSocket.onerror = function(message) {
};

webSocket.onmessage = function(message) {
	let node = JSON.parse(message.data);

	if (node.status === "visit") { // 유저 접속 시
		// 알맞은 자리에 새 채팅 영역 추가
		let form = $(".template").html();
		let uuid = node.key;
		form = $("<div class='float-left'></div>").attr("data-key", uuid)
				.append(form);
		$(".container-fluid").append(form);
	

		$.ajax({
			url : '/getChatLog',
			type : 'post',
			data : {
				"uuid" : uuid
			},
			dataType : 'json',
			success : function(data) {
				if (data.length > 0) { // 기존 채팅이 있을 때
					data.forEach(function(d) {
						var id = d.id;
						let msg = d.msg;
						if (!msg.includes('입장')) {
							$(`[data-key="${uuid}"] .console`).append(
									"(" + id + ") : " + msg + "\n");
						}
					})
				}
				
				let uuidSpan = $("<span></span>").text(id);
				form.append(uuidSpan);
			},
			error : function() {
				alert("에러");
			}
		});
	} else if (node.status === "message") { // 유저메세지 받을 때
		// UK를 가지고 해당 div영역에 메세지 뿌림
		let $div = $("[data-key='" + node.key + "']");
		let log = $div.find(".console").val();
		// 받아온 유저 세션, 메세지 정제해서 사용
		let user_id = node.message.split(",")[0];
		let msg = node.message.split(",")[1];
		$div.find(".console").val(log + "(" + user_id + ") : " + msg + "\n");
	} else if (node.status === "bye") { // 퇴장
		$("[data-key='" + node.key + "']").remove();
	}
};

// 관리자가 메세지 전송
$(document).on("click", ".sendBtn", function() {
	// 해당 자리에 view
	let $div = $(this).closest(".float-left");
	let message = $div.find(".message").val();
	let key = $div.data("key"); // 유저 UK
	let log = $div.find(".console").val();
	$div.find(".console").val(log + "(me) : " + message + "\n");
	$div.find(".message").val("");
	// js->adminsocket->usersocket으로 UK별 메세지 분류해서 보냄
	webSocket.send(key + "#####" + message);

	// db저장
	$.ajax({
		url : '/adminChatCreate',
		data : {
			"uuid" : key,
			"id" : "admin",
			"msg" : message
		},
		type : 'post'
	});

});

$(document).on("keydown", ".message", function() {
	if (event.keyCode === 13) {
		$(this).closest(".float-left").find(".sendBtn").trigger("click");
		return false;
	}
	return true;
});

const dropdown = document.querySelector("#dropdownLI");
const dropdiv = document.querySelector('.collapse');

dropdown.addEventListener('click', function() {
	dropdiv.classList.toggle('show');
});
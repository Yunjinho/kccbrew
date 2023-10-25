const webSocket = new WebSocket("ws://localhost:8080/adminchat");
let userUuid = null;
let name = null;
var storageLocation =null;
var fileServerNm =null;
var chatIcon = document.querySelector('#chatIcon');
var chatArea = document.querySelector(".chatArea");
chatIcon.addEventListener('click', function(){
	chatArea.classList.toggle("hidden");});
webSocket.onopen = handleWebSocketOpen;
webSocket.onclose = handleWebSocketClose;
webSocket.onerror = handleWebSocketError;
webSocket.onmessage = handleWebSocketMessage;

function handleWebSocketOpen(event) {
  // WebSocket 연결이 열렸을 때 실행되는 코드
}

function handleWebSocketClose(event) {
  // WebSocket 연결이 닫혔을 때 실행되는 코드
}

function handleWebSocketError(event) {
  // WebSocket 오류가 발생했을 때 실행되는 코드
}

function handleWebSocketMessage(event) {
  const node = JSON.parse(event.data);

  if (node.status === "visit") {
    handleUserVisit(node);
  } else if (node.status === "message") {
    handleUserMessage(node);
  } else if (node.status === "bye") {
    handleUserBye(node);
  }
}

function handleUserVisit(node) {
//  const form = document.querySelector(".template");
  const uuid = node.key;
//  form.setAttribute("data-key", uuid);
 const name = node.user_id;
  $.ajax({
	    url: '/getUser',
	    type: 'post',
	    data: {
	      "uuid": uuid
	    },
	    success: function(data) {
	        	storageLocation = data.storageLocation;
				fileServerNm = data.fileServerNm;
				  const discussion = createDiscussionElement(uuid, name, storageLocation, fileServerNm);
				  $(".discussions").append(discussion);
	    },
	     error:function(request,status,error){
	         console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	        }
	        		  
	        	  });


/*  const element = document.querySelector(".name2");
  element.innerHTML = name;*/

  /*loadChatLog(uuid);*/
}

function createDiscussionElement(uuid, name, storageLocation, fileServerNm) {
  const discussion = document.createElement("div");
  discussion.classList.add("discussion");

  const photo = document.createElement("div");
  photo.classList.add("photo");
  const url= storageLocation + fileServerNm;
  console.log(storageLocation);
  console.log(fileServerNm);
  photo.style.backgroundImage = `url(/${url})`;
  console.log(name+"22#######################"+url);
  const descContact = document.createElement("div");
  descContact.classList.add("desc-contact");

  const p1 = document.createElement("p");
  p1.classList.add("name");
  p1.innerHTML = name;

  const p2 = document.createElement("p");
  p2.classList.add("message");

  discussion.setAttribute("data-key", uuid);
  descContact.appendChild(p1);
  descContact.appendChild(p2);
  
  discussion.appendChild(photo);
  discussion.appendChild(descContact);
  return discussion;
}
 
function loadChatLog(uuid) {
  $.ajax({
    url: '/getChatLog',
    type: 'post',
    data: {
      "uuid": uuid
    },
    dataType: 'json',
    success: function(data) {
      if (data.length > 0) {
        data.forEach(function(d) {
        	let user_id = d.user_id;
			let sender = d.sender;
          let id = d.id;
          let msg = d.msg;
          if (!msg.includes('입장')) {
        	  if(user_id == sender){
            const message1 = createMessageElementUser(msg);
            $(`[data-key="${uuid}"] .messages-chat`).append(message1);
        	  } else {
        		  const message1 = createMessageElementAdmin(msg);
                  $(`[data-key="${uuid}"] .messages-chat`).append(message1);
              	  	  
        		  
        		  
        	  }
        	  scrollToBottom();
        	 }
        });
      }
    },
    error: function() {
      alert("에러");
    }
  });
}

function createMessageElementUser(msg) {
  const message1 = document.createElement('div');
  message1.classList.add('message1');
  const message2 = document.createElement('p');
  message2.classList.add('text');
  message2.innerHTML = msg;
  message1.append(message2);
  return message1;
}

function createMessageElementAdmin(msg) {
	  const message1 = document.createElement('div');
	  message1.classList.add('message1');
	  const message2 = document.createElement('div');
	  message2.classList.add('response');
	  const message3 = document.createElement('p');
	  message3.classList.add('text');
	  message3.innerHTML = msg;
	  message2.append(message3);
	  message1.append(message2);
	  return message1;
	}


function handleUserMessage(node) {
  const $div = $("[data-key='" + node.key + "']");
  let user_id = node.message.split(",")[0];
  let msg = node.message.split(",")[1];
  
  if(msg.includes(node.key + "님이 퇴장합니다.")){
	  alert(user_id + "님이 퇴장합니다.");
  }
  let log = $div.find(".messages-chat").html();
  const message1 = createMessageElementUser(msg);
  $div.find(".messages-chat").append(message1);
  scrollToBottom();
  
}

function handleUserBye(node) {
	console.log(node);
 const $template = $(".template");
 $("[data-key='" + node.key + "']").remove();
 $template.attr("data-key", ""); // data-key 초기화
  $template.find(".name2").text(""); // 이름 초기화
  $template.find(".messages-chat").empty(); // 메시지 초기화
  $template.find(".message").val(""); // 메시지 입력 필드 초기화
//section .chat에 $template를 추가
  $("section.chat").append($template);
}

// .discussion 클릭 이벤트를 추가합니다.
$(document).on("click", ".discussion", function() {
  const uuid = $(this).attr("data-key"); // 클릭한 .discussion의 data-key 값을 가져옵니다.
  console.log(uuid);
  const name = $(this).find(".name").text();
  // .template의 내용을 업데이트합니다.
  const $template = $(".template");
  const test =document.querySelector('.template');
  console.log(test);
  $template.attr("data-key", uuid); // data-key 업데이트
  const $nameElement = $template.find(".name2");
  $nameElement.text(name);
  $(".message-active").removeClass('message-active');
  $(this).addClass('message-active');
  // .messages-chat 내용을 비웁니다.
  $template.find(".messages-chat").empty();
  if(uuid==null){
	  
  }else{
  // 여기에서 채팅 내용을 업데이트하는 코드를 작성하세요.
  loadChatLog(uuid);
  }
 
});

$(document).on("click", ".sendBtn", function() {
	 const template = document.querySelector('.template');
	    let message = template.querySelector(".message").value;
	    let key = template.getAttribute("data-key");
	    let log = template.querySelector(".messages-chat").innerHTML;
	    const message1 = createMessageElementAdmin(message);
	    template.querySelector(".messages-chat").appendChild(message1);
	    template.querySelector(".message").value = "";
	    webSocket.send(key + "#####" + message);
	    saveToDatabase(key, message);
	    scrollToBottom();
});

function saveToDatabase(key, message) {
  $.ajax({
    url: '/adminChatCreate',
    data: {
      "uuid": key,
      "id": "admin",
      "msg": message
    },
    type: 'post'
  });
}

$(document).on("keydown", ".message", function(event) {
  if (event.keyCode === 13) {
    $(this).closest(".template").find(".sendBtn").trigger("click");
    return false;
  }
  return true;
});

function scrollToBottom() {
    let chatboxBody = document.querySelector('.messages-chat');
    chatboxBody.scrollTop = chatboxBody.scrollHeight;
}

function closeChat(){
	chatArea.classList.add("hidden");

}
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css" />
<link rel="stylesheet" href="/resources/css/chat/userchat.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<head>
</head>
<body>
	<div id="userChat">
		<div class="chatArea hidden">
			<div class='chatbox'>
				<div class="chatbox_header">
					<button type="button" onclick="closeChat()">
						<svg height='18' viewbox='0 0 18 18' width='18'
							xmlns='http://www.w3.org/2000/svg'>
      <path
								d='M15 8.25H5.87l4.19-4.19L9 3 3 9l6 6 1.06-1.06-4.19-4.19H15v-1.5z'></path>
    </svg>
					</button>
					<div class='title'>Customer Service</div>
				</div>
				<div class='chatbox_body'>
					<input type="hidden" id="sessionUserId"
						value="<sec:authentication property="principal.username" />">
					
				</div>
				<div class="chatbox_input" style="display: flex";>
					<input id="textMessage" placeholder='Type something here'
						type="text" onkeydown="return enter()">
					<!-- <input onclick="sendMessage()" value="보내기" type="button">  -->
					<button type="button" onclick="sendMessage()">
						<svg height='24' viewbox='0 0 24 24' width='24'
							xmlns='http://www.w3.org/2000/svg'>
        <path d='M2.01 21L23 12 2.01 3 2 10l15 2-15 2z'></path>
      </svg>
					</button>

					<!-- <input
					onclick="closeChat()" value="채팅종료" type="button"> -->
				</div>
			</div>
		</div>
		<i id="chatIcon" class="fa-solid fa-comment-dots"></i>
	</div>
	<script src="/resources/js/chat/userChat.js"></script>
</body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/resources/css/chat/adminchat.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
<div id="userChat">
	<div class="chatArea hidden" id="container">
		<div class="row" style="  margin-right: 0px;
     margin-left: 0px;">
			<!-- 리스트 -->
			<section class="discussions">
				<div class="discussion">
				 <!-- <div class="photo" style="background-image: url(test);"> -->
				<button type="button" onclick="closeChat()" style="    all: unset;
    position: relative;
        width: 100%;
    height: 100%;
    transition: 0.3s ease-in-out;">
						<img src="/resources/img/backIcon.png" style="width: 25%; float: left;
    margin-left: 30px;" alt="로딩 중..." />
					</button>
				</div>
			</section>

			<!-- Begin Page Content -->

			<section class="chat">
				<div class="template">
					<div class="header-chat">
						<i class="icon fa fa-user-o" aria-hidden="true"></i>
						<p class="name2"></p>
					</div>
					<div class="messages-chat"></div>
					<div class="footer-chat">
						<input type="text" class="message write-message"
							placeholder="Type your message here"
							onkeydown="if(event.keyCode === 13) return false;"></input> <i
							class="icon send fa fa-paper-plane-o clickable sendBtn"
							aria-hidden="true"> </i>
					</div>
				</div>
			</section>

		</div>
	</div>
		<i id="chatIcon" class="fa-solid fa-comment-dots"></i>
		</div>
	<script src="/resources/js/chat/adminChat.js"></script>
</body>
</html>
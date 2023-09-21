<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
.modal_contents {
	display: none; /* 모달 초기에는 숨김 상태로 설정 */
	position: fixed; /* 화면에 고정 */
	z-index: 1; /* 다른 요소보다 위에 표시되도록 설정 */
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.5); /* 반투명한 배경 색상 */
	text-align: center;
}
.reject-content{
	background-color: #fefefe;
	margin: 10% auto; /* 모달을 수직, 수평 가운데로 위치시킴 */
	padding: 20px;
	border: 1px solid #888;
	width: 30%;
	border-radius: 20px;
	display: flex;
	flex-direction: column;
}
</style>
</head>
<body>

	<div class="modal_contents" style="display: none;">
		<div class="reject-content">
			<h4>
				<b>손님 아이디는?</b><span class="close"></span>
			</h4>
			<br>
			<h2 id="id_value"></h2>
			<br>
			<button type="button" id="pwSearch_btn"
				class="btn peach-gradient btn-rounded waves-effect">
				비밀번호 찾기
			</button>
			<button type="button" 
				id="close">
				닫기
			</button>
		</div>
	</div>
</body>
</html>
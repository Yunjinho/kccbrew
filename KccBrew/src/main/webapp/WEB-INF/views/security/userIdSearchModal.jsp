<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	margin: 20% auto; /* 모달을 수직, 수평 가운데로 위치시킴 */
	padding: 20px;
	border: 1px solid #888;
	width: 30%;
	border-radius: 20px;
	display: flex;
	flex-direction: column;
}
#strTable {
	width: 100%;
	margin: auto;
	height: 80%;
}

#strTable table {
	border-collapse: collapse;
	margin: auto;
	width: 100%;
	height: 80%;
}
#strTable table thead{
	font-weight: bolder;
	
	}
	
	#strTable table thead tr th{
}

#strTable th, td {
	text-align: left !important;
	padding: 8px !important;
	text-align: center !important;
	border-bottom: 1px solid #d6d7d9;
	height: 32px;
}
.btn1{
background-color: navy;
    color: #ffffff;
    border: none;
    padding: 5px 10px;
    border-radius: 4px;
    display: inline-block;
    align-items: center;
    text-decoration: none;
    position: relative;
    margin-right: 20px;}
</style>
<script>
$(document).ready(function(){
	$("#pwSearch_btn").click(function(){
		$(".modal_contents").css("display", "none");
		$("#searchP").css("display", "");
		$("#searchI").css("display", "none");
		$("#search_1").prop("checked",false);
		$("#search_2").prop("checked",true);
	})	
})
</script>
</head>
<body>

	<div class="modal_contents" style="display: none;">
		<div class="reject-content">
		<div>
			<h4 style="border-bottom:1px solid black; padding: 0px 0px 10px 0px;">
				<b>고객님의 정보와 일치하는 아이디 입니다.</b><span class="close"></span>
			</h4>
		</div>
			<br>
				<div id="strTable">
				<table class="table">
					<thead>
						<tr>
							<th>아이디</th>
							<th>가입일</th>
						</tr>
					</thead> 
					<tbody>
							<tr>
								<td id="inputId"></td>
								<td id="inputReg"></td>
							</tr>
					</tbody>
				</table>
			</div>
				<h2 id="id_value" 
    style="margin-top: 10px;"
				></h2>
			<br>
			<div>
				<button type="button" id="pwSearch_btn" class="btn1">
					비밀번호 찾기
				</button>
				<button type="button" id="close" class="btn1">
					닫기
				</button>
			</div>
		</div>
	</div>
</body>
</html>
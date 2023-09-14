<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<link rel="stylesheet" href="/resources/css/code/cdMndMod.css" />

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
</head>
<body>
		<section id="notice" class="notice">
		<div>
			<div class="category">그룹코드 수정</div>
			<hr style="border: solid 1.2px; width: 97%;">
			<form action="/code/grpupdate" method="post" id="update" onsubmit="return submitForm()">
				<div class="container2">
					<table id="search-box">
						<tr>
							<th>그룹코드ID</th>
							<td>${codeMng.cdId}
							<input type="hidden" value="${codeMng.cdId}" name="cdId"></td>
						</tr>
						<tr>
							<th>그룹코드이름</th>
							<td><input type="text" name="cdNm" id="cdNm"
								value="${codeMng.cdNm}" required></td>
						</tr>
						<tr>
						<th>사용여부</th>
						<td><select name="cdUseYn">
									<option value="Y">Y</option>
									<option value="N">N</option>
							</select></td>
							</tr>
						<tr>
							<th>최초등록일</th>
							<td>${codeMng.cdRegDttm}
							</td>
							</tr>
							<tr>
							<th>최초등록자ID</th>
							<td>${codeMng.cdRegUser}
							</td>
							</tr>
							<tr>
							<th>최종수정일</th>
							<td>${codeMng.cdModDttm}
							</td>
							</tr>
							<tr>
							<th>최종수정자ID</th>
							<td>${codeMng.cdModUser}
							</td>
							</tr>
						
					</table>
</div>

					<div class="savecancle" style="text-align: center;">
					<button type="button" name="save" class="form-btn"
						onClick="submitForm()" value="저장" id="find-btn1">저장</button>
					<button type="button" class="form-btn" value="취소"
						onclick="history.back()" id="find-btn1">취소</button>
				</div>
			</form>
			</div>
		</section>
		<script>function submitForm() {
		var form = document.getElementById("update");

		// AJAX 요청 보내기
		$.ajax({
			type : "POST", // 또는 "GET" 등 요청 방식 설정
			url : form.getAttribute("action"),
			data : $(form).serialize(), // 폼 데이터 시리얼라이즈하여 전송
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			success : function(response) {
				// 응답 처리 로직 추가
				var Close = confirm("저장이 완료되었습니다. 창을 닫으시겠습니까?");
				if (Close) {
					window.opener.location.reload();
					window.close(); // 팝업 창 닫기
				}
			},
			error : function(error) {
				console.error("AJAX 요청 실패:", error);
				alert("저장에 실패하였습니다.");
			}
		});
	}</script>
</body>
</html>
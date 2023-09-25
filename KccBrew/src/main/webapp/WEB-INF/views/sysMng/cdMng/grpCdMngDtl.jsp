<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>코드정보</title>
</head>
<style>
#search-box th, #search-box td {
	/*   border: 1px solid #444444; */
	
}
#search-box th {
	width: 92px;
	min-width: 92px;
	max-width: 92px;
}

#search-box td {
	width: calc(50% - 92px);
	max-width: calc(50% - 92px);
	min-width: calc(50% - 92px);
}
.form-btn{
display:inline;
}
</style>
<body>
	<hr style="border: solid 1.2px; width: 97%;">
	<form method="post" id="update">
	<table id="search-box"   style="width: 100%;">
		<thead>
			<tr>
				<th>코드이름</th>
				<td colspan="3"><input type="text" name="cdNm" id="cdNm"
								value="${codeMng.cdNm}" required></td>
			</tr>
			<tr>
				<th>사용여부</th>
				<td><select name="cdUseYn">
									<option value="Y">Y</option>
									<option value="N">N</option>
							</select></td>
				<th>코드ID</th>
				<td><input type="text" value="${codeMng.cdId}" name="cdId" required></td>

			</tr>

			<tr>
				<th>등록일</th>
				<td>${codeMng.cdRegDttm}</td>
				<th>등록자ID</th>
				<td>${codeMng.cdRegUser}</td>
			</tr>
	</table>
		
	
	<div class="updatecancle" style="text-align: center;  float: right;
    margin: 10px;">
			<button type="button" name="save" class="form-btn"
						onClick="submitForm()" value="저장" id="find-btn1">저장</button>
			<button type="button" name="insert" class="form-btn"
						onClick="insertForm()" value="추가" id="find-btn1">추가</button>			
	</div></form>
	<script>
	
	function submitForm() {
		var form = document.getElementById("update");

		// AJAX 요청 보내기
		$.ajax({
			type : "POST", // 또는 "GET" 등 요청 방식 설정
			url : "<c:url value='/code/grpupdate' />" ,
			data : $(form).serialize(), // 폼 데이터 시리얼라이즈하여 전송
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			success : function(response) {
				// 응답 처리 로직 추가
				var Close = confirm("저장이 완료되었습니다");
				if (Close) {
					location.replace(location.href);
				}
			},
			error : function(error) {
				console.error("AJAX 요청 실패:", error);
				alert("저장에 실패하였습니다.");
			}
		});
	}
	
	function insertForm() {
		var form = document.getElementById("update");

		// AJAX 요청 보내기
		$.ajax({
			type : "POST", // 또는 "GET" 등 요청 방식 설정
			url : "<c:url value='/code/insert1' />" ,
			data : $(form).serialize(), // 폼 데이터 시리얼라이즈하여 전송
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			success : function(response) {
				// 응답 처리 로직 추가
				var Close = confirm("저장이 완료되었습니다");
				if (Close) {
					location.replace(location.href);
				}
			},
			error : function(error) {
				console.error("AJAX 요청 실패:", error);
				alert("저장에 실패하였습니다.");
			}
		});
	}
	
	</script>
</body>
</html>
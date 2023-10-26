<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="java.time.LocalDateTime"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
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
</style>
<body>
	<hr style="border: solid 1.2px; width: 97%;">
	<form method="post" name="updateCd" id="updateCd">
		<input type="hidden" name="cdIdx" value="${codeMng.cdIdx}" /> <input
			type="hidden" name="cdId" id="cdId" value="${codeMng.cdId}" />
		<table id="search-box" style="width: 100%;">

			<tr>
				<th>코드이름</th>
				<td colspan="3"><input type="text" name="cdDtlNm" id="cdDtlNm"
					value="${codeMng.cdDtlNm}" required></td>
			</tr>
			<tr>
				<th>코드ID</th>
				<td><input type="text" name="cdDtlId" id="cdDtlId"
					value="${codeMng.cdDtlId}" required></td>
				<th>사용여부</th>
				<td>
					<select name="cdUseYn">
						<c:choose>
							<c:when test="${codeMng.cdDtlUseYn eq 'Y'}">
								<option value="Y" selected>Y</option>
								<option value="N">N</option>
							</c:when>
							<c:otherwise>
								<option value="Y">Y</option>
								<option value="N" selected>N</option>
							</c:otherwise>
						</c:choose>
					</select>
				</td>
			</tr>
			<tr>
				<th>등록일</th>
				<td>${codeMng.cdDtlRegDttm}</td>
				<th>등록자ID</th>
				<td>${codeMng.cdDtlRegUser}</td>
			</tr>

		</table>
		<div class="updatecancle"
			style="text-align: center; float: right; margin: 10px;">
			<button type="button" name="save" class="form-btn"
				onClick="submitForm1()" value="저장" id="find-btn1">수정</button>
			<button type="button" name="insert" class="form-btn"
				onClick="insertCdForm()" value="추가" id="find-btn1">추가</button>
		</div>
	</form>
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
	<script type="text/javascript">
	var uu = "<c:url value='/code'/>";
		$(document).ready(function() {
			$('#cdId').val(id);
		});

		function submitForm1() {
			var form = document.getElementById("updateCd");

			// AJAX 요청 보내기
			$.ajax({
						type : "POST",
						url : "<c:url value='/code/update' />",
						data : $(form).serialize(),
						contentType : "application/x-www-form-urlencoded; charset=UTF-8",
						success : function(response) {
							console.log(response);
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
			return false;
		}

		function insertCdForm() {
			cont = id;
			document.updateCd.cdId.value = cont;

			console.log(cont);
			var local = "<c:url value='/code/insert2'/>";
			var params = $("#updateCd").serialize()
			console.log(cdId);
			console.log(local);
			console.log(JSON.stringify(params));
			// AJAX 요청 보내기
			$.ajax({
				type : "POST", // 또는 "GET" 등 요청 방식 설정
				url : local,
				data : JSON.stringify(params), // JSON 형식으로 데이터 변환
				success : function(response) {
					// 응답 처리 로직 추가
					alert("저장이 완료되었습니다");
					location.href = uu;
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
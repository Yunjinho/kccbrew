<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="/resources/css/code/cdMngDtl.css" />
<meta charset="UTF-8">
<title>회원정보</title>
</head>
<body>
	<section id="notice" class="notice">
		<div class="container2">
			<div class="category">회원정보</div>
			<hr style="border: solid 1.2px; width: 97%;">
			<table id="search-box">
				<tr>
					<th rowspan="4" colspan="2" style="width: 140px; height: 188px;">
						<div style="text-align: center;">
							<img src="${userDtl.imgUrl}${userDtl.imgNm}" border="0"
								style="margin: auto; width: 120px; height: 168px; ">
						</div>
						</th>
					<th>ID</th>
					<td>${user.userId}</td>
					<th>이름</th>
					<td>${user.userNm}</td>
					<th>이메일</th>
					<td>${user.userEmail}</td>

				</tr>
				<tr>
					<th>전화번호</th>
					<td>${user.userTelNo}</td>
					<th>주소</th>
					<td colspan="3">${user.userAddr}</td>
				</tr>
				<tr>
					<th>사용자구분</th>
					<td style="color: red;"><c:if
							test="${user.userTypeCd eq '01' }">관리자</c:if> <c:if
							test="${user.userTypeCd eq '02' }">점주</c:if> <c:if
							test="${user.userTypeCd eq '03' }">기사</c:if></td>
					<th>가입일자</th>
					<td>${user.regDttm}</td>
					<th>수정일자</th>
					<td>${user.modDttm}</td>
				</tr>
				<tr>
					<th>사용여부</th>
					<td>${user.useYn}</td>
					<th>승인여부</th>
					<td>${user.approveYn}</td>
					<th>승인자</th>
					<td>${user.approveAdmin}</td>
				</tr>
			</table>

			<c:if test="${user.userTypeCd eq '02' }">
				<div class="category">상세정보</div>
				<hr style="border: solid 1.2px; width: 97%;">
				<table id="search-box">
					<tr>
						<th>점포명</th>
						<th>점포전화번호</th>
					</tr>
					<tr>
						<td>${userDtl.storeNm}</td>
						<td>${userDtl.storeTelNo}</td>
					</tr>
				</table>
			</c:if>
			<c:if test="${user.userTypeCd eq '03' }">
				<div class="category">상세정보</div>
				<hr style="border: solid 1.2px; width: 97%;">
				<table id="search-box">
					<tr>
						<th>장비코드</th>
						<th>활동지역</th>
					</tr>
					<tr>
						<td>${userDtl.eqpmnNm}</td>
						<td>${userDtl.locationNm}</td>
					</tr>
				</table>
			</c:if>
			<div class="modal-footer" style="width: 100%; margin:auto; display: flex;">
				<button type="button" class="update">수정</button>
				<button type="button" class="cancel1">닫기</button>
			</div>
		</div>
	</section>
	<script>
	$(".cancel1").click(function() {
		$(".Modal1").css("display", "none");
		selectedUserId = null;
	});
	$(".update").click(function() {
		$(".Modal1").css("display", "none");
		$(".ModalMod").css("display", "block");
	});
	</script>
</body>
</html>
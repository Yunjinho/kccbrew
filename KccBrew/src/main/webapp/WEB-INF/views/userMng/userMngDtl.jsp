<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"> -->
<!-- <link rel="stylesheet" href="/resources/css/code/cdMngDtl.css" /> -->
<meta charset="UTF-8">
<title>회원정보</title>
</head>
<style>
</style>
<body>
	<section id="notice" class="notice"
		style="width: 80%; margin-top: 40px;">
		<div class="container2">
			<div class="category">회원정보</div>
			<hr style="border: solid 1.2px; width: 100%;">
			<table id="search-box">
				<tr>
					<th rowspan="4" colspan="2"
						style="position:relative; height:140px; width: 120px;">
							 <img src="${userDtl.get(0).imgUrl}" border="0"
								style="position:relative; width:120px; top: calc(  50% - 70px ); height:140px; "> 
					</th>


					<th>ID</th>
					<td>${userMngVo.userId}</td>
					<th>이름</th>
					<td>${userMngVo.userNm}</td>
					<th>이메일</th>
					<td>${userMngVo.userEmail}</td>

				</tr>
				<tr>
					<th>전화번호</th>
					<td>${userMngVo.userTelNo}</td>
					<th>주소</th>
					<td colspan="3">${userMngVo.userAddr}</td>
				</tr>
				<tr>
					<th>사용자구분</th>
					<td style="color: red;"><c:if
							test="${userMngVo.userTypeCd eq '01' }">관리자</c:if> <c:if
							test="${userMngVo.userTypeCd eq '02' }">점주</c:if> <c:if
							test="${userMngVo.userTypeCd eq '03' }">기사</c:if></td>
					<th>가입일자</th>
					<td>${userMngVo.regDttm}</td>
					<th>수정일자</th>
					<td>${userMngVo.modDttm}</td>
				</tr>
				<tr>
					<th>사용여부</th>
					<td>${userMngVo.useYn}</td>
					<th>승인여부</th>
					<td>${userMngVo.approveYn}</td>
					<th>승인자</th>
					<td>${userMngVo.approveAdmin}</td>
				</tr>
			</table>

			<c:if test="${userMngVo.userTypeCd eq '02' }">
				<div class="category" style="margin-top: 40px;">상세정보</div>
				<hr style="border: solid 1.2px; width: 100%;">
				<table id="search-box">
					<tr>
						<th>점포명</th>
						<th>점포전화번호</th>
					</tr>
					<c:forEach var="userDtl" items="${userDtl}">
						<tr>
							<td>${userDtl.storeNm}</td>
							<td>${userDtl.storeTelNo}</td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
			<c:if test="${userMngVo.userTypeCd eq '03' }">
				<div class="category" style="margin-top: 40px;">상세정보</div>
				<hr style="border: solid 1.2px; width: 100%;">
				<table id="search-box">
					<tr>
						<th>장비코드</th>
						<th>활동지역</th>
					</tr>
					<c:forEach var="userDtl" items="${userDtl}">
						<tr>
							<td>${userDtl.eqpmnNm}</td>
							<td>${userDtl.locationNm}</td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
			<div class="modal-footer"
				style="width: 100%; margin: auto;">
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- <link rel="stylesheet" href="/resources/css/code/cdMngDtl.css" /> -->
<meta charset="UTF-8">
<title>회원정보</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
</head>
<body>
	<section id="notice" class="notice" style="width: 80%; margin-top:40px;">
		<div class="container2" style="width: 100%;">
			<div class="category">회원정보</div>
			<hr style="border: solid 1.2px; width: 100%;">
			<table id="search-box">
				<tr>
					<th rowspan="4" colspan="2" style="position:relative; height:140px; width:120px;">
							<img src="${userDtl.get(0).imgUrl}" border="0"
								style="position:relative; width:120px;  top: calc(  50% - 70px ); height:140px; ">
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
					<td>
						<select id="useYn">
							<option value="">선택</option>
							<option value="Y" ${userMngVo.useYn == "Y" ? 'selected' : ''}>사용</option>
							<option value="N" ${userMngVo.useYn == "N" ? 'selected' : ''}>탈퇴</option>
					</select></td>
					<th>승인여부</th>
					<td>
						<select id="approveYn">
							<option value="" ${userMngVo.approveYn == "" ? 'selected' : ''}>연기</option>
							<option value="Y" ${userMngVo.approveYn == "Y" ? 'selected' : ''}>승인</option>
							<option value="N" ${userMngVo.approveYn == "N" ? 'selected' : ''}>반려</option>
					</select></td>
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
		</div>
		<div class="modal-footer" style="width: 100%; margin:auto;">
				<button type="submit" class="submitMod">저장</button>
				<button type="button" class="cancelMod">취소</button>
			</div>
	</section>
<script>
$(".cancelMod").click(function() {
	$(".Modal1").css("display", "none");
	$(".ModalMod").css("display", "none");
	selectedUserId = null;
});
$(".submitMod").click(function() {
	if (selectedUserId) {
		// <select> 요소의 값을 가져오기
		const use = $("#useYn").val();
		const approve = $("#approveYn").val();
		console.log("useYn:" + use);
		console.log("approveYn:" + approve);
		// AJAX 요청을 통해 서버로 사용자 ID와 <select> 요소의 값을 전달하고 업데이트 수행
		$.ajax({
			url : '/user/mod', // 사용자 수정 업데이트를 수행하는 서버 엔드포인트
			type : 'POST',
			dataType : 'text',
			data : {
				userId : selectedUserId,
				useYn : use, // <select> 요소의 값을 사용
				approveYn : approve
			},
			success : function(response) {
				if (response.trim() === "ok") {
					alert('사용자를 수정하였습니다.');
				} else {
					alert('빈칸을 모두 입력해주세요.');
				}
				// 모달 창 닫기 및 페이지 새로고침
				$(".ModalMod").css("display", "none");
				location.href = "/user";
			},
			error : function() {
				alert('빈칸을 모두 입력해주세요.');
				/* $(".ModalMod").css("display", "none"); */
			}
		});
	}
});
 </script>
</body>
</html>
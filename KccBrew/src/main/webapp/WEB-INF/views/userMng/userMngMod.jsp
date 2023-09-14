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
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
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
								style="margin: auto;">
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
					<td>
						<select id="useYn">
							<option value="">선택</option>
							<option value="Y">활성화</option>
							<option value="N">비활성화</option>
					</select></td>
					<th>승인여부</th>
					<td>
						<select id="approveYn">
							<option value="">미정</option>
							<option value="Y">수락</option>
							<option value="N">거절</option>
					</select></td>
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
		</div>
		<div class="modal-footer" style="width: 100%; margin:auto; display: flex;">
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
                 url: '/user/mod', // 사용자 수정 업데이트를 수행하는 서버 엔드포인트
                 type: 'POST',
                 dataType: 'text',
                 data: {
                     userId: selectedUserId,
                     useYn: use, // <select> 요소의 값을 사용
                     approveYn: approve
                 },
                 success: function(response) {
                     if (response.trim() === "ok") {
                         alert('사용자를 수정하였습니다.');
                     } else {
                         alert('사용자 수정에 실패하였습니다.');
                     }
                     // 모달 창 닫기 및 페이지 새로고침
                     $(".ModalMod").css("display", "none");
                     location.reload();
                 },
                 error: function() {
                     alert('서버 오류로 수정에 실패하였습니다.');
                     $(".ModalMod").css("display", "none");
                 }
             });
         }
     });
 </script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<link rel="stylesheet" href="/resources/css/userMng/userMngList.css" />
<!-- notoSans -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans&family=Noto+Sans+KR&display=swap"
	rel="stylesheet">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
</head>
<style>
input {
	width: auto;
}

#notice {
	margin: 40px;
}

.form-btn {
	display: unset;
}

.category {
	font-size: 1.2em;
}
</style>
<body>
	<section id="notice" class="notice">
		<div class="category">점포등록</div>
		<hr style="border: solid 1.2px; ">
		<div class="container2">
			<form action="${pageContext.request.contextPath}/store/insert"
				method="post" id="storeForm" onsubmit="return submitForm()">
				<table id="search-box">
					<tr>
						<th>점포명</th>
						<td><input type="text" id="storeNm" name="storeNm" required>
							<input type="button" name="strnm" value="중복확인" class="check"
							id="name-check-btn" oninput="checkName()" required> <input
							type="hidden" id="storeNmHidden" name="storeNm"></td>
					</tr>
					<tr>
						<th>주소검색</th>
						<td><input type="text" id="address_kakao" name="storeAddr"
							placeholder="클릭해주세요" readonly /></td>
					</tr>
					<tr>
						<th>상세주소</th>
						<td><input type="text" name="storeAddrDtl" id="storeAddrDtl" />
						</td>
					</tr>
					<tr>
						<th>점포 전화번호</th>
						<td><input type="text" id="storeTelNo" name="storeTelNo"
							oninput="hypenTel(this)" maxlength="13"
							placeholder="하이픈(-)은 자동입력 됩니다.."></td>
					</tr>
					<tr>
						<th>좌표</th>
						<td><input class="field" id="lat" name="latitude"
							placeholder="위도" /> <input class="field" id="lng"
							name="longitude" placeholder="경도"></td>
					</tr>
					<tr>
						<th>지역분류</th>
						<td><select id="location" name="locationCd" size="1"
							onchange="changeLocationCd()" style="width: 98px;height: 32px;">
								<option value="" selected="selected">지역선택</option>
								<c:forEach var="list" items="${list}">
									<option value="${list.locationCd}"
										<c:if test="${store.locationCd == list.locationCd}">selected="selected"</c:if>>${list.locationNm}</option>
								</c:forEach>
						</select> <select id="locationSeoul" name="locationCdSeoul" size="1"
							style="width: 250px; height: 32px;">
								<option value="">지역 상세 선택</option>
						</select></td>
					</tr>
				</table>
				<div class="updatecancle" style="text-align: center; margin-top: 20px;">
					<button type="submit" name="save" class="form-btn" value="저장"
						id="submitBtn"
						style="display: inline-block; margin-left: 5px; margin-right: 5px;"
						disabled>저장</button>
					<button type="button" class="form-btn" value="취소"
						onclick="window.close()"
						style="display: inline-block; margin-left: 5px; margin-right: 5px;">취소</button>
				</div>
			</form>
		</div>
	</section>

	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAY5-GHYNs9VdzbExAcRYn5NPHIsu727XI&libraries=places&callback=initAutocomplete"
		async defer></script>
	<script
		src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

	<script type="text/javascript" src="/resources/js/strMng/strMngIns.js"></script>
	<script type="text/javascript">
		const hypenTel = function(target) {
			target.value = target.value.replace(/[^0-9]/g, '').replace(
					/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);
		}

		const storeNmHidden = document.querySelector("#storeNmHidden"); // 숨겨진 필드
		const submitBtn = document.querySelector("#submitBtn"); // 폼 제출 버튼
		document
				.getElementById("name-check-btn")
				.addEventListener(
						"click",
						function() {
							const storeNm = document.getElementById("storeNm").value;

							if (!storeNm) {
								alert("점포명을 입력해주세요.");
								return;
							}
							console.log(storeNm);
							// AJAX 요청 보내기
							$
									.ajax({
										async : true,
										type : 'POST',
										url : "${pageContext.request.contextPath}/user/namecheck", // 경로 수정
										data : JSON.stringify(storeNm), // JSON으로 데이터 전송
										dataType : "json", // 서버가 JSON 응답을 반환하는 것으로 예상
										contentType : "application/json; charset=UTF-8",
										success : function(check) {
											if (check == "1") {
												alert("사용 가능한 점포명입니다.");
												// 이후 원하는 동작 수행
												document
														.getElementById("submitBtn").disabled = false;
												document
														.getElementById("storeNm").disabled = true;
												document
														.getElementById("storeNmHidden").value = storeNm;
											} else {
												alert("이미 존재하는 점포명입니다.");
												// 이후 원하는 동작 수행
											}
										},
										error : function(error) {
											console.error("에러 발생:", error);
											alert("서버와의 통신 중 오류가 발생했습니다.");
										}
									});
						});

		function submitForm() {
			var form = document.getElementById("storeForm");
			console.log(storeNm);
			$
					.ajax({
						type : "POST",
						url : form.getAttribute("action"),
						data : $(form).serialize(),
						contentType : "application/x-www-form-urlencoded; charset=UTF-8",
						success : function(response) {
							alert("저장이 완료되었습니다.");
							
								window.opener.location.href = "/store";
								window.close();
							
						},
						error : function(error) {
							console.error("AJAX 요청 실패:", error);
							alert("빈칸을 모두 입력해주세요.");
						}
					});

			return false;
		}
	</script>
</body>
</html>
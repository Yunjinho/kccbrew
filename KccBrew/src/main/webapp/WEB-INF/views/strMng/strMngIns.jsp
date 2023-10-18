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

.register-msg>li {
	height: 10px;
	width: 283px;
	margin: auto;
}

.register-msg {
	margin: 0px;
	height: 8px;
	padding: 0;
	list-style: none;
	color: red;
}
</style>
<body>
	<section id="notice" class="notice">
		<div class="category">점포등록</div>
		<hr style="border: solid 1.2px;">
		<div class="container2">
			<form action="${pageContext.request.contextPath}/store/insert"
				method="post" id="storeForm" onsubmit="return submitForm()">
				<table id="search-box">
					<tr>
						<th>점포명</th>
						<td><input type="text" id="storeNm" name="storeNm" required>
							<input type="button" name="strnm" value="중복확인" class="check"
							id="name-check-btn" oninput="checkName()" required> <input
							type="hidden" id="storeNmHidden"></td>
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
							onchange="changeLocationCd()" style="width: 98px; height: 32px;">
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
				<div class="updatecancle"
					style="text-align: center; margin-top: 20px;">
					<ul class="register-msg" id="msg" style="display: none;">
						<li id="userPwdConfirmMsg">중복확인이 필요합니다.</li>
					</ul>
					<button type="submit" name="save" class="form-btn" value="저장"
						id="submitBtn"
						style="display: inline-block; margin-left: 5px; margin-right: 5px; margin-top:20px"
						disabled>저장</button>
					<button type="button" class="form-btn" value="취소"
						onclick="window.close()"
						style="display: inline-block; margin-left: 5px; margin-right: 5px;  margin-top:20px">취소</button>
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
	    if (!target) {
	        return "";
	    }
	    const value = target.value.replace(/[^0-9]/g, ""); // 숫자 이외의 모든 문자를 제거
	    let result = [];
	    let restNumber = "";

	    // 지역번호와 나머지 번호로 나누기
	    if (value.startsWith("02")) {
	        // 서울 02 지역번호
	        result.push(value.substr(0, 2));
	        restNumber = value.substring(2);
	        if (restNumber.length == 8) {
		        // 4자리 이상인 경우
		        result.push(restNumber.substring(0, 4));
		        result.push(restNumber.substring(4));
		    } else if (7 >= restNumber.length) {
		        // 4자리 이상인 경우
		        result.push(restNumber.substring(0, 3));
		        result.push(restNumber.substring(3));
		    } else {
		        // 4자리 미만인 경우
		    	 result.push(restNumber.substring(0, 4));
			        result.push(restNumber.substring(4));
		    }

	        
	    } else if (value.startsWith("1")) {
	        // 지역 번호가 없는 경우
	        // 1xxx-yyyy
	        restNumber = value;
	        if (restNumber.length >= 4) {
		        // 4자리 이상인 경우
		        result.push(restNumber.substring(0, 4));
		        result.push(restNumber.substring(4));
		    } else {
		        // 4자리 미만인 경우
		        result.push(restNumber);
		    }
	    } else {
	        // 나머지 3자리 지역번호
	        // 0xx-yyyy-zzzz
	        result.push(value.substr(0, 3));
	        restNumber = value.substring(3);
	        if (restNumber.length == 8) {
		        // 4자리 이상인 경우
		        result.push(restNumber.substring(0, 4));
		        result.push(restNumber.substring(4));
		    } else if (7 >= restNumber.length) {
		        // 4자리 이상인 경우
		        result.push(restNumber.substring(0, 3));
		        result.push(restNumber.substring(3));
		    } else {
		        // 4자리 미만인 경우
		    	 result.push(restNumber.substring(0, 4));
			        result.push(restNumber.substring(4));
		    }
	    }

	  

	    const hyphenatedValue = result.filter(function(val) {
	        return val;
	    }).join("-");

	    // 입력 필드에 결과 설정
	    target.value = hyphenatedValue;
	}



		var con = document.getElementById("msg");
		const storeNmHidden = document.querySelector("#storeNmHidden"); // 숨겨진 필드
		const submitBtn = document.querySelector("#submitBtn"); // 폼 제출 버튼
		var storeNm = document.getElementById("storeNm"); // 엘리먼트를 가져옵니다.

		storeNm.addEventListener("input", function() {
			// 이후의 코드는 이벤트 핸들러 내부로 이동합니다.
			if (storeNm.value === storeNmHidden.value) {
				submitBtn.disabled = false; // 저장 버튼 활성화
				msg.style.display = "none";
			} else {
				submitBtn.disabled = true; // 저장 버튼 비활성화
				msg.style.display = "block";
			}
		});
		document
				.getElementById("name-check-btn")
				.addEventListener(
						"click",
						function() {

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
												// Update the hidden field value
												document.getElementById("storeNmHidden").value = storeNm.value;
												// Check if storeNm and storeNmHidden are equal
												if (storeNm.value === storeNmHidden.value) {
													submitBtn.disabled = false;
													msg.style.display = "none";// Enable the submit button
												} else {
													submitBtn.disabled = true; // Disable the submit button
													msg.style.display = "block";
												}
											} else {
												alert("이미 존재하는 점포명입니다.");
												document
														.getElementById("storeNmHidden").value = storeNm;
												// Check if storeNm and storeNmHidden are equal
												if (storeNm === storeNmHidden.value) {
													submitBtn.disabled = false;
													msg.style.display = "none";// Enable the submit button
												} else {
													submitBtn.disabled = true; // Disable the submit button
													msg.style.display = "block";
												}
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
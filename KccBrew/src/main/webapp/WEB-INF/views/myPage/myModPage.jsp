<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${path}/resources/css/comm/myPage.css"/>
</head>
<body>
	<section id="notice" class="notice">
		<div class="container2">
			<div class="category">마이페이지</div>
			<hr class="line">
				<form action='<c:url value= '/confirmmod'/>' method="post">
				<table id="search-box">
					<c:forEach var="user" items="${userInfoList}" >
						<tr>
							<td rowspan="4">
								<img src="${path}/img/sample.jpg" id="profileImg" >
								<input type="file" id="file" name="file" onchange="uploadFile(this)" style="display:none"/>
								<button type="button" id="changeImageBtn">이미지 변경</button>
							</td>
							<th><label for="userId">ID</label></th>
							<td>
								<c:out value="${user.userId}" />
							</td>
							<th><label for="userName">이름</label></th>
							<td>
								<input type="text" name="userName" id="userName" value="${user.userName}" required>
							</td>
							<th><label for="userEmail">이메일</label></th>
							<td>
								<input type="text" name="userEmail" id="userEmail" value="${user.userEmail}" required>
							</td>
						</tr>
						<tr>
							<th><label for="userTelNo">전화번호</label></th>
							<td>
								<input type="text" name="userTelNo" id="userTelNo" value="${user.userTelNo}" required>
							</td>
							<th><label for="userAddress">주소</label></th>
							<td colspan="3">
								<input type="text" name="userAddress" id="userAddress" value="${user.userAddress}" required>
							</td>
						</tr>
						<tr>
							<th>사용자구분</th>
							<td style="color: red;">
								<c:if test="${user.userType eq '01' }">관리자</c:if> 
								<c:if test="${user.userType eq '02' }">점주</c:if> 
								<c:if test="${user.userType eq '03' }">기사</c:if>
							</td>
							<th>가입일자</th>
							<td>
								<fmt:formatDate value="${user.userRegDate}" pattern="yyyy-MM-dd" />
							</td>
							<th>수정일자</th>
							<td>
								<fmt:formatDate value="${user.userModDate}"	pattern="yyyy-MM-dd" />
							</td>
						</tr>
						<tr>
							<th>사용여부</th>
							<td><c:out value="${user.userInUse}" /></td>
							<th>승인여부</th>
							<td><c:out value="${user.approveStatus}" /></td>
							<th>승인자</th>
							<td><c:out value="${user.admWhoAcp}" /></td>
						</tr>
					</c:forEach>
				</table>

				<sec:authorize access="hasRole('ROLE_MANAGER')">
					<div class="category">상세정보</div>
					<hr style="border: solid 1.2px; width: 97%;">
					<table id="search-box">
						<c:forEach var="store" items="${storeInfoList}">
							<tr>
								<th><label for="storeName">점포명</label></th>
								<th><label for="storeTelNo">점포 번호</label></th>
							</tr>
							<tr>
								<td>
									<input type="text" name="storeName" id="storeName" value="${store.storeName}" required>
								</td>
								<td>
									<input type="text" name="storeTelNo" id="storeTelNo" value="${store.storeTelNo}" required>
								</td>
							</tr>
						</c:forEach>
					</table>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_MECHA')">
					<div class="category">상세정보</div>
					<hr style="border: solid 1.2px; width: 97%;">
					<table id="search-box">
						<c:forEach var="mecha" items="${userInfoList}">
							<tr>
								<th>담당 장비</th>
								<th>활동지역</th>
							</tr>
							<tr>
								<td>
									<select id="chooseMachineCode">
										<option value="" disabled>== 선택 ==</option>
										<option value="01">커피머신</option>
										<option value="02">냉장고</option>
										<option value="03">제빙기</option>
										<option value="04">에어컨</option>
										<option value="05">온수기</option>
									</select>	
								</td>
								<td>
									<select id="chooseLocationCode">
										<option value="" disabled>== 선택 ==</option>
										<option value="02-200">양천</option>
										<option value="02-300">은평,마포,서대문,강서</option>
										<option value="02-400">송파,강동,중랑,광진,성동</option>
										<option value="02-500">서초, 광명시, 과천시</option>
										<option value="02-700">마포,용산,종로</option>
										<option value="02-800">영등포,동작,구로,금천,양서,관악,광명시</option>
										<option value="02-900">노원,동대문,도봉,강북,성북</option>
									</select>
								</td>
							</tr>
						</c:forEach>
					</table>
				</sec:authorize>
				<div class="modButtons">
					<button type="submit" id="confirmMod">확인</button>
					<c:url var="cancel" value="/mypage"></c:url>
					<a href="${cancel}">
						<button type="button" class="cancel">취소</button>
					</a>
				</div>
				<input type="hidden" id="machineCodeHidden" name="machineCode" value="" required>
				<input type="hidden" id="locationCodeHidden" name="mechaLocationCode" value="" required>
			</form>
		</div>
	</section>
	<script>
	$(document).ready(function(){
		//수정 확인 버튼
	    $("#confirmMod").click(function(){
	        var selectedMachineCode = $("#chooseMachineCode").val();
	        var selectedLocationCode = $("#chooseLocationCode").val();
	        
	        console.log(selectedMachineCode + "machine");
	        console.log(selectedLocationCode + "location");
	        
	        $("#machineCodeHidden").val(selectedMachineCode);
	        $("#locationCodeHidden").val(selectedLocationCode);
	        
	        $("form").submit();
	    });
	    
		$('#changeImageBtn').click(function (e){
			e.preventDefault();
			$('#file').click();
		});
	
	});
	</script>
</body>
</html>
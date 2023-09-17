<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="/resources/css/code/cdMngDtl.css" />
</head>
<body>
	<section id="notice" class="notice">
		<div class="container2">
			<div class="category">회원정보</div>
			<hr style="border: solid 1.2px; width: 97%;">
				<table id="search-box">
					<c:forEach var="user" items="${userInfoList}">
						<tr>
							<th rowspan="4" colspan="2" style="width: 140px; height: 188px;">
								<div style="text-align: center;">
									<img src="${userDtl.imgUrl}${userDtl.imgNm}" border="0"
										style="margin: auto; width: 120px; height: 168px; ">
								</div>
							</th>
							<th>ID</th>
							<td><c:out value="${userId}"/></td>
							<th>이름</th>
							<td><c:out value="${user.userName}"/></td>
							<th>이메일</th>
							<td><c:out value="${user.userEmail}"/></td>
						</tr>
						<tr>
							<th>전화번호</th>
							<td><c:out value="${user.userTelNo}"/></td>
							<th>주소</th>
							<td colspan="3"><c:out value="${user.userAddress}"/></td>
						</tr>
						<tr>
							<th>사용자구분</th>
							<td style="color: red;">
								<c:if test="${user.userType eq '01' }">관리자</c:if>
								<c:if test="${user.userType eq '02' }">점주</c:if>
								<c:if test="${user.userType eq '03' }">기사</c:if>
							</td>
							<th>가입일자</th>
							<td><fmt:formatDate value="${user.userRegDate}" pattern="yyyy-MM-dd"/></td>
							<th>수정일자</th>
							<td><fmt:formatDate value="${user.userModDate}" pattern="yyyy-MM-dd"/></td>
						</tr>
						<tr>
							<th>사용여부</th>
							<td><c:out value="${user.userInUse}"/></td>
							<th>승인여부</th>
							<td><c:out value="${user.approveStatus}"/></td>
							<th>승인자</th>
							<td><c:out value="${user.admWhoAcp}"/></td>
						</tr>
					</c:forEach>
				</table>
			
			<c:choose>
				<c:when test="${sessionScope.userTypeCd eq '02' }">
					<div class="category">상세정보</div>
					<hr style="border: solid 1.2px; width: 97%;">
					<table id="search-box">
						<c:forEach var="store" items="${storeInfoList}">
							<tr>
								<th>점포명</th>
								<th>점포전화번호</th>
							</tr>
							<tr>
								<td>${store.storeName}</td>
								<td>${store.storeTelNo}</td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:when test="${sessionScope.userTypeCd eq '03' }">	
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
									<c:choose>
										<c:when test= "${mecha.machineCode == '01'}">커피머신</c:when>
										<c:when test= "${mecha.machineCode == '02'}">냉장고</c:when>
										<c:when test= "${mecha.machineCode == '03'}">제빙기</c:when>
										<c:when test= "${mecha.machineCode == '04'}">에어컨</c:when>
										<c:when test= "${mecha.machineCode == '05'}">온수기</c:when>
									</c:choose>
								</td>
								<td>
									<c:choose>
										<c:when test= "${mecha.mechaLocationCd == '02-200'}">양천</c:when>
										<c:when test= "${mecha.mechaLocationCd == '02-300'}">은평,마포,서대문,강서</c:when>
										<c:when test= "${mecha.mechaLocationCd == '02-400'}">송파,강동,중랑,광진,성동</c:when>
										<c:when test= "${mecha.mechaLocationCd == '02-500'}">서초, 광명시, 과천시</c:when>
										<c:when test= "${mecha.mechaLocationCd == '02-700'}">마포,용산,종로</c:when>
										<c:when test= "${mecha.mechaLocationCd == '02-800'}">영등포,동작,구로,금천,양서,관악,광명시</c:when>
										<c:when test= "${mecha.mechaLocationCd == '02-900'}">노원,동대문,도봉,강북,성북</c:when>
									</c:choose>
								</td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
			</c:choose>
			<div class="modal-footer" style="width: 100%; margin:auto; display: flex;">
				<button type="button" class="update">수정</button>
				<button type="button" class="cancel1">닫기</button>
			</div>
		</div>
	</section>
</body>
</html>
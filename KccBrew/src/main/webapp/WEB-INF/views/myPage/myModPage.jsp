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
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="${path}/resources/js/comm/myPage.js"></script>
</head>
<body>
	<div id="mypage-main-wrapper">
		<section id="mypage-wrapper" class="mypage-wrapper">
			<div class="arrowAndContents">
				<!-- ********** 페이지 네비게이션 시작 ********** -->
				<div class="page-content-navigation">
				<h2 class="noti-head">내 정보</h2>
					<ol class="breadcrumb">
						<li>
							<div class="header-icon-background">
								<a href="/">
									<img src="<c:url value='${path}/resources/img/common/free-icon-house.png' />" alt="Check List" class="header-icon" />
								</a>
							</div>
						</li>
						<li>
							<div class="header-icon-background">
								<img src="<c:url value='${path}/resources/img/common/free-icon-arrow-right.png' />" alt="Check List" class="header-icon" />
							</div>
						</li>
						<li class="breadcrumb-home">마이페이지</li>
						<li>
							<div class="header-icon-background">
								<img src="<c:url value='${path}/resources/img/common/free-icon-arrow-right.png' />" alt="Check List" class="header-icon" />
							</div>
						</li>
						<li class="curPage">내 정보 수정</li>
					</ol>
				</div>
				<!-- ********** 페이지 네비게이션 끝 ********** -->
				<div class="myInfo-wrapper">
						<form method="post" id="updateProfileForm" name="updateProfileForm" enctype="multipart/form-data">
						<table id="search-box">
							<c:forEach var="user" items="${userInfoList}" >
								<tr>
									<td rowspan="4">
										<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_MECHA')">
											<c:set var="imagePath" value="${path}/${user.fileDetailLocation}${user.fileDetailServerName}" />
											<img src="<c:out value='${imagePath}'/>" id="profileImg" >
											<input type="file" id="file" name="userImg" style="display:none" accept=".jpg, .jpeg, .png" onchange="imgTypeCheck(this)"/>
											<button type="button" id="changeImageBtn" >이미지 변경</button>
										</sec:authorize>
										<sec:authorize access="hasRole('ROLE_MANAGER')">
											<c:set var="imagePath" value="${path}/resources/img/baristar.png" />
											<img src="<c:out value='${imagePath}'/>" id="profileImg">
										</sec:authorize>
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
									<td colspan="3" class="modAddress">
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
						<ul class="register-msg">
							<li id="imgFileMsg">사용자 사진: 필수 정보입니다.</li>
						</ul>
		
						<sec:authorize access="hasRole('ROLE_MANAGER')">
							<div class="category">상세정보</div>
							<table id="search-box">
								<c:forEach var="store" items="${storeInfoList}">
									<tr>
										<th><label for="storeName">점포명</label></th>
										<th><label for="storeTelNo">점포 번호</label></th>
									</tr>
									<tr>
										<td>
											${store.storeName}
										</td>
										<td>
											${store.storeTelNo}
										</td>
									</tr>
								</c:forEach>
							</table>
						</sec:authorize>
						<sec:authorize access="hasRole('ROLE_MECHA')">
							<div class="category">상세정보</div>
							<table id="search-box">
								<c:forEach var="mecha" items="${userInfoList}">
									<tr>
										<th>담당 장비</th>
										<th>활동지역</th>
										<th>활동 지역 상세</th>
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
											<select id="chooseLocationCode" name="mechaLocation" onchange="changeLocationCd()">
												<option value="">== 선택 ==</option>
												<c:forEach var="locationList" items="${locationList}">
													<option value="${locationList.grpCdDtlId}">
														${locationList.grpCdDtlNm}
													</option>
												</c:forEach>
											</select>
										</td>
										<td>
											<select id="chooseDetailLocationCode" name="mechaLocationCode">
												<option>== 선택 ==</option>
											</select>
										</td>
									</tr>
								</c:forEach>
							</table>
						</sec:authorize>
						<div class="modButtons">
							<button type="button" id="confirmModBtn">확인</button>
							<button type="button" id="confirmModExceptImgBtn">확인</button>
							<c:url var="cancel" value="/mypage"></c:url>
							<a href="${cancel}">
								<button type="button" class="cancel">취소</button>
							</a>
						</div>
						<input type="hidden" id="machineCodeHidden" name="machineCode" value="" required>
						<input type="hidden" id="locationCodeHidden" name="mechaLocationCode" value="" required>
					</form>
				</div>
			</div>
		</section>
	</div>
</body>
</html>
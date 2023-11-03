<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page import="java.time.LocalDateTime"%>
<%@ page import="java.time.format.DateTimeFormatter"%>

<%@ page import="kr.co.kccbrew.comm.util.ObjectUtilController"%>
<%@ page import="kr.co.kccbrew.comm.security.model.UserVo"%>
<%@ page import="javax.servlet.http.HttpSession"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- js -->
<script src="<c:url value="/resources/js/comm/commsidemenu.js"/>"></script>
<!-- jquery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>



<c:set var="user" value="${sessionScope.user}" />
<c:set var="store" value="${sessionScope.store}" />

<!------------------------------------------------------------------------------ 관리자 ------------------------------------------------------------------------------>
<sec:authorize access="hasRole('ROLE_ADMIN')">
	<div class="profileAndguide">

		<div class="user-profile">
			<div class="inner-box">
				<div class="photo-wrapper">
				<!-- s3 미사용 버전 -->
				<c:set var="imagePath" value="${user.storageLocation}${user.fileServerNm}" />
				<!-- s3 사용 버전 -->
				<!--<c:set var="imagePath" value="${user.storageLocation}" />-->
					<img src="<c:out value='${imagePath}'/>">
				</div>

				<h2 class="user-info-wrapper">
					<c:out value='${user.userNm}' />
					<span>님</span> <br> <span class="user-type"
						style="margin: 0px"> (관리자)</span>
				</h2>

				<div class="buttons">
					<c:url var="toMyPage" value="/mypage" />
					<a href="${toMyPage}" id="toMyPage">마이페이지</a> <span
						class="separator">&nbsp;&nbsp;|&nbsp;</span>
					<form action="/logout" method="POST">
						<button type="submit" class="logout">로그아웃</button>
						<input name="${_csrf.parameterName}" type="hidden"
							value="${_csrf.token}" />
					</form>
				</div>

				<div class="user-task-wrapper">
					<div class="user-task">
						<a class="user-task-value" href="/as-list"><b
							class="font-bold"><c:out value="${sessionScope.asListCount}" /></b>건</a> <br>
						<span class="task-title">AS신청건수</span>
					</div>
					<div class="user-task last-task">
						<a class="user-task-value" href="/user"><b class="font-bold"><c:out
									value="${sessionScope.unapprovedMemberList}"/></b>건</a> <br> <span class="task-title">회원가입
							대기건수</span>
					</div>
				</div>
			</div>
		</div>

		<div class="shortcut-service">
			<div class="inner-box">
				<p id="shortcuts-mark">바로가기 서비스</p>
				<ul class="shortcut-list">
					<li class="shortcuts"><img class="round-btn" src="${path}/resources/img/main/icon-repair.png"></img><a
						href="/as-list">A/S내역조회</a></li>
					<li class="shortcuts"><img class="round-btn" src="${path}/resources/img/main/icon-chair.png"></img><a
						href="/holiday">휴가현황조회</a></li>
					<li class="shortcuts last"><img class="round-btn" src="${path}/resources/img/main/icon-schedule.png"></img><a
						href="/admin/schedule">월근태현황조회</a></li>
				</ul>
			</div>
		</div>

	</div>
</sec:authorize>


<!------------------------------------------------------------------------------ 점주 ------------------------------------------------------------------------------>
<sec:authorize access="hasRole('ROLE_MANAGER')">
	<div class="profileAndguide">

		<div class="user-profile">
			<div class="inner-box">

				<div class="photo-wrapper">
				<!-- s3 미사용 버전 -->
				<c:set var="imagePath" value="${user.storageLocation}${user.fileServerNm}" />
				<!-- s3 사용 버전 -->
				<!--<c:set var="imagePath" value="${user.storageLocation}" />-->
					<img src="${path}/resources/img/baristar.png">	
				</div>

				<h2 class="user-info-wrapper">
					<c:out value='${user.userNm}' />
					<span>님</span> <br> <span class="user-type"
						style="margin: 0px"> (점주)</span>
				</h2>

				<div class="user-info">
				<div>
						<span class="user-info-index">지역 </span> <span id="locationCd"
							hidden="true"> <c:out value="${store.locationCd}" /></span>
					</div> 
					<div>
						<span class="user-info-index">지점명 </span> <span><c:out
								value='${store.storeNm}' /></span>
					</div>
				</div>

				<div class="buttons">
					<c:url var="toMyPage" value="/mypage" />
					<a href="${toMyPage}" id="toMyPage">마이페이지</a> <span
						class="separator">&nbsp;&nbsp;|&nbsp;</span>
					<form action="/logout" method="POST">
						<button type="submit" class="logout">로그아웃</button>
						<input name="${_csrf.parameterName}" type="hidden"
							value="${_csrf.token}" />
					</form>
				</div>

				<div class="user-task-wrapper">
					<div class="manager-task"
						style="border-right: dashed 0.5px #a2c3f5;">
						<a class="manager-task-value" href="/as-list"><b
							class="font-bold"><c:out
									value="${sessionScope.asAssignListByIdCount}"/></b>건</a> <br> <span class="task-title"
							style="padding: 7px;">AS신청건수</span>
					</div>
					<div class="manager-task">
						<a class="manager-task-value" href="/as-list"><b
							class="font-bold"><c:out
									value="${sessionScope.asListByIdCount}"/></b>건</a> <br> <span class="task-title"
							style="padding: 7px;">AS배정건수</span>
					</div>
					<div class="manager-task last-task"
						style="border-right: dashed 0.5px #a2c3f5;">
						<a class="manager-task-value" href="/as-list"><b
							class="font-bold"><c:out
									value="${sessionScope.resultListByIdCount}"/></b>건</a> <br> <span class="task-title"
							style="padding: 7px;">AS결과건수</span>
					</div>
					<div class="manager-task last-task">
						<a class="manager-task-value" href="/holiday"><b
							class="font-bold"><c:out
									value="${sessionScope.usedHolidays}"/></b>일</a> <br> <span class="task-title"
							style="padding: 7px;">휴점일수</span>
					</div>

				</div>
			</div>



		</div>
		<div class="shortcut-service">
			<div class="inner-box">
				<p id="shortcuts-mark">바로가기 서비스</p>
				<ul class="shortcut-list">
					<li class="shortcuts"><img class="round-btn" src="${path}/resources/img/main/icon-repair.png"></img><a
						href="/as-list">A/S신청내역조회</a></li>
					<li class="shortcuts last"><img class="round-btn" src="${path}/resources/img/main/icon-chair.png"></img><a
						href="/holiday">휴가신청내역조회</a></li>
				</ul>
			</div>
		</div>

	</div>
</sec:authorize>

<!------------------------------------------------------------------------------ 수리기사 ------------------------------------------------------------------------------>
<sec:authorize access="hasRole('ROLE_MECHA')">
	<div class="profileAndguide">

		<div class="user-profile">
			<div class="inner-box">
				<div class="photo-wrapper">
					<c:set var="imagePath"
						value="${user.storageLocation}" />
					<img src="<c:out value='${imagePath}'/>">
				</div>

				<h2 class="user-info-wrapper">
					<c:out value='${user.userNm}' />
					<span>님</span> <br> <span class="user-type"
						style="margin: 0px"> (수리기사)</span>
				</h2>

				<div class="user-info">
					<div>
						<span class="user-info-index">지역 </span> 
						<span id="locationCd" hidden="true" hidden="true"> 
							<c:out value="${user.locationCd}"/>
						</span>
					</div>
					<div>
						<span class="user-info-index">장비 </span> <span id="mecha-eqpmnCd"
							hidden="true"><c:out value='${user.eqpmnCd}' /></span>
					</div>
				</div>

				<div class="buttons">
					<c:url var="toMyPage" value="/mypage" />
					<a href="${toMyPage}" id="toMyPage">마이페이지</a> <span
						class="separator">&nbsp;&nbsp;|&nbsp;</span>
					<form action="/logout" method="POST">
						<button type="submit" class="logout">로그아웃</button>
						<input name="${_csrf.parameterName}" type="hidden"
							value="${_csrf.token}" />
					</form>
				</div>

				<div class="user-task-wrapper">
					<div class="user-task">
						<a class="user-task-value" href="/as-list"><b
							class="font-bold"><c:out
									value="${sessionScope.asAssignListCount}"/></b>건</a> <br> <span class="task-title">AS배정건수</span>
					</div>
					<div class="user-task last-task">
						<a class="user-task-value" href="/holiday"><b
							class="font-bold"><c:out
									value="${sessionScope.usedHolidays}"/></b>/15건</a> <br> <span class="task-title">휴가사용일수</span>
					</div>
				</div>
			</div>
		</div>



		<div class="shortcut-service">
			<div class="inner-box">
				<p id="shortcuts-mark">바로가기 서비스</p>
				<ul class="shortcut-list">
					<li class="shortcuts"><img class="round-btn" src="${path}/resources/img/main/icon-repair.png"></img><a
						href="/as-list">A/S배정내역조회</a></li>
					<li class="shortcuts last"><img class="round-btn" src="${path}/resources/img/main/icon-chair.png"></img><a
						href="/holiday">휴가신청내역조회</a></li>
				</ul>
			</div>
		</div>

	</div>
</sec:authorize>

</html>
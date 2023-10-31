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
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<!-- css -->
<link rel="stylesheet" href="/resources/css/schdl/schdl-common.css" />
<link rel="stylesheet" href="/resources/css/log/mylogtest.css" />
<link rel="stylesheet" href="/resources/css/asMng/content-template.css" />
<link rel="stylesheet" href="/resources/css/schdl/myschedulelist.css" />
<link rel="stylesheet" href="/resources/css/schdl/myHldyList.css" />


<!-- javascript -->
<script src="<c:url value="/resources/js/schdl/schdl-common.js"/>"></script>
<script src="<c:url value="/resources/js/schdl/board.js"/>"></script>
<script src="<c:url value="/resources/js/schdl/search.js"/>"></script>
<script src="<c:url value="/resources/js/schdl/holidayList.js"/>"></script>

<meta charset="UTF-8">
<title>Insert title here</title>

</head>

<body>
	<c:set var="user" value="${sessionScope.user}" />

	<script>
	var storeNm = "${sessionScope.store.storeNm}";
	var storeSeq = "${sessionScope.store.storeSeq}";
	
    var equipmentCodeValue = "<c:out value='${user.eqpmnCd}' />";
    var locationCodeValue = "<c:out value='${user.locationCd}' />";
    var userTypeCd = "<c:out value='${user.userTypeCd}' />";
</script>

	<c:set var="currentDate" value="<%=new java.util.Date()%>" />




	<div id="page" class="page-nosubmenu">
		<div id="page-mask">
			<div id="page-container" class="">
				<div id="page-content" class="clearfix">
					<div id="page-content-wrap">

						<div class="page-content-navigation">
						<h2 class="heading">휴가사용현황</h2>
							<ol class="breadcrumb">
							<li>
								<div class="header-icon-background">
								<a href="/">
									<img
										src="<c:url value='resources/img/common/free-icon-house.png' />"
										alt="Check List" class="header-icon" />
										</a>
								</div>
							</li>
							<li>
								<div class="header-icon-background">
									<img
										src="<c:url value='resources/img/common/free-icon-arrow-right.png' />"
										alt="Check List" class="header-icon" />
								</div>
							</li>
							
								<li class="breadcrumb-home"><a href="">스케줄관리</a></li>
										<li>
								<div class="header-icon-background">
									<img
										src="<c:url value='resources/img/common/free-icon-arrow-right.png' />"
										alt="Check List" class="header-icon" />
								</div>
							</li>
								<li><a href="<c:url value='/holiday' />">휴가사용현황</a></li>
							</ol>
						</div>


						<!-- ********** 페이지 네비게이션 끝 ********** -->

						<div id="region-main">
							<div role="main">
								<div class="user-past">

									<!-- ********** 세은 로그 관련 내용 시작 ********** -->
									<div id="content">

										<%-- <div class="tabNav">
											<ul class="tab-ul">
												<li class="active"><a href=""><span>휴가사용현황</span></a></li>
												<sec:authorize
													access="hasAnyRole('ROLE_MANAGER', 'ROLE_MECHA')">
													<li class="last"><a href="/schedule/calendar"><span>나의캘린더</span></a></li>
												</sec:authorize>
												<sec:authorize access="hasRole('ROLE_ADMIN')">
													<li class="last"><a href="/admin/schedule"><span>근태현황</span></a></li>
												</sec:authorize>
											</ul>
										</div> --%>

										<!-- 사용자 검색 -->
										<sec:authorize access="hasRole('ROLE_ADMIN')">
											<form name="srhForm" action="/admin/holiday/search"
												method="post">

												<input type="hidden" name="currentPage" value="1">

												<div class="search-info">
													<fieldset>
														<legend class="blind">사용자검색</legend>
														<table id="search-box">
															<tr>
																<th>위치</th>
																<td><select class="tx2" name="location"
																	onchange="changeLocationCd()">
																		<option value="">지역 대분류</option>
																		<c:forEach var="location" items="${locationList}">
																			<c:if test="${location.grpCdId eq 'L'}">
																				<option value="${location.grpCdDtlId}"
																					${param.superGrpCdDtlId == location.grpCdDtlId ? 'selected' : ''}>
																					${location.grpCdDtlNm}</option>
																			</c:if>
																		</c:forEach>
																</select></td>
																<td><select class="tx2" name="locationCd">
																		<option value="">지역 소분류</option>
																</select></td>

																<th>휴가기간</th>
																<td id="form-holiday-period"><span><label
																		for="startDate">시작일:</label> <input type="date"
																		id="startDate" name="startDate" value=""></span> <span><label
																		for="endDate">종료일:</label> <input type="date"
																		id="endDate" name="endDate" value=""></span></td>
															</tr>



															<tr>
																<th>검색어</th>
																<td><select class="tx2" name="userType"
																	id="selectUserType"
																	onchange="updateUserType(); inputDisableSetting('selectUserType', 'selectKeyword');">
																		<option value="">사용자 유형</option>
																		<option value="03"
																			${param.userType == '기사' ? 'selected' : ''}>기사</option>
																		<option value="02"
																			${param.userType == '점주' ? 'selected' : ''}>점주</option>
																</select></td>

																<td colspan="4"><select class="tx2" name="searchKeword"
																	id="selectKeyword" onchange="chgName(this)" disabled>
																		<option value="">검색어</option>
																		<option value="userId"
																			${param.searchKeword == '회원ID' ? 'selected' : ''}>회원ID</option>
																		<option value="userName"
																			${param.searchKeword == '회원이름' ? 'selected' : ''}>회원이름</option>
																		<option value="storeId"
																			${param.searchKeword == '지점ID' ? 'selected' : ''}>지점ID</option>
																		<option value="storeName"
																			${param.searchKeword == '지점명' ? 'selected' : ''}>지점명</option>
																</select> <input type="text" id="search-word" name="searchKeword"
																	placeholder="사용자유형 및 검색어를 선택 후 입력해주세요." required
																	disabled></td>
															</tr>

														</table>
														<div class="form-btn-box">
															<fieldset>
																<button type="button" class="form-btn"
																	onClick="performSearch();">검색</button>
																<button type="reset" class="form-btn">초기화</button>
															</fieldset>
														</div>
													</fieldset>
												</div>
											</form>
										</sec:authorize>




										<!-- 검색어 선택한 것이 input요소의 name으로 설정 -->
										<script>
											function chgName(selectElement) {
												const selectedValue = selectElement.value; // 선택한 값
												const inputElement = document
														.getElementById('search-word'); // 검색어 입력 필드

												if (selectedValue === '') {
													// 선택한 값이 빈 문자열인 경우, input 요소를 비활성화
													inputElement.disabled = true;
												} else {
													// 선택한 값이 빈 문자열이 아닌 경우, input 요소를 활성화
													inputElement.disabled = false;
													inputElement.name = selectedValue;
												}
											}
										</script>


										<!-- 사용자 유형 선택에 따른 검색어 추출 -->
										<script>
											function updateUserType() {

												const userTypeSelect = document
														.getElementsByName('userType')[0]; 
												const searchSelect = document
														.getElementsByName('searchKeword')[0]; 

												if (userTypeSelect.value === '03') {
													removeAllOptions(searchSelect);
													addOption(searchSelect,
															'검색어', '');
													addOption(searchSelect,
															'회원ID', 'userId');
													addOption(searchSelect,
															'회원이름', 'userName');
												} else {
													removeAllOptions(searchSelect);
													addOption(searchSelect,
															'검색어', '');
													addOption(searchSelect,
															'회원ID', 'userId');
													addOption(searchSelect,
															'회원이름', 'userName');
													addOption(searchSelect,
															'지점ID', 'storeId');
													addOption(searchSelect,
															'지점명', 'storeName');
												}

												if (typeof searchSelect.onchange === 'function') {
													searchSelect.onchange();
												}
											}

											function removeAllOptions(
													selectElement) {
												while (selectElement.options.length > 0) {
													selectElement.remove(0);
												}
											}

											function addOption(selectElement,
													text, value) {
												const option = new Option(text,
														value);
												selectElement.add(option);
											}
										</script>

										<div class="info-box">

											<sec:authorize access="hasRole('ROLE_MANAGER')">

												<form name="srhForm" action="/holiday/search" method="post"
													style="display: none;">
													<input type="hidden" name="startDate" value=""> <input
														type="hidden" name="endDate" value=""> <input
														type="hidden" name="currentPage" value="1">
												</form>

												<!-- 점포일 경우 -->
												<table id="search-box" hidden="true">
													<!-- 점주정보 -->
													<tr>
														<th>유형</th>
														<td><c:out value="점주" /></td>

														<th>ID</th>
														<td><c:out value="${user.userId}" /></td>

														<th>이름</th>
														<td><c:out value="${user.userNm}" /></td>

														<th>이메일</th>
														<td><c:out value="${user.userEmail}" /></td>

														<th>연락처</th>
														<td><c:out value="${user.userTelNo}" /></td>

													</tr>

													<!-- 점포정보 -->
													<tr>
														<th>점포ID</th>
														<td><c:out value="${store.storeSeq}" /></td>

														<th>점포명</th>
														<td><c:out value="${store.storeNm}" /></td>

														<th>점포주소</th>
														<td colspan="3"><c:out
																value="${store.storeAddr} ${store.storeAddrDtl}" /></td>

														<th>점포연락처</th>
														<td><c:out value="${store.storeTelNo}" /></td>

													</tr>
												</table>
											</sec:authorize>

											<sec:authorize access="hasRole('ROLE_MECHA')">

												<form name="srhForm" action="/holiday/search" method="post"
													style="display: none;">
													<input type="hidden" name="startDate" value=""> <input
														type="hidden" name="endDate" value=""> <input
														type="hidden" name="currentPage" value="1">
												</form>

												<table id="search-box" hidden="true">
													<tr>
														<th>유형</th>
														<td>수리기사</td>

														<th>ID</th>
														<td><c:out value="${user.userId}" /></td>

														<th>이름</th>
														<td><c:out value="${user.userNm}" /></td>

														<th>연락처</th>
														<td><c:out value="${user.userTelNo}" /></td>

													</tr>

													<tr>
														<th>사용장비코드</th>
														<td id="equipmentCode"><c:out value="${user.eqpmnCd}" /></td>

														<th>장비명</th>
														<td id="equipmentName"></td>

														<th>지역코드</th>
														<td id="locationCode"><c:out
																value="${user.locationCd}" /></td>

														<th>지역명</th>
														<td id="locationName"></td>

													</tr>
												</table>
											</sec:authorize>
										</div>

										<div id="logTable">

											<div class="board-info">
												<span class="data-info"> 전체<b><span
														id="totalDataNumber"><c:out
																value="${totalDataNumber}" /></span></b>건<span id="text-separator">
														| </span> <b><span id="currentPage"><c:out
																value="${currentPage}" /></span></b>/<b><span id="totalPage"><c:out
																value="${totalPage}" /></span></b>쪽
												</span>

												<div>
													<sec:authorize
														access="hasAnyRole('ROLE_MANAGER', 'ROLE_MECHA')">
														<fieldset>
															<legend class="blind">날짜 검색</legend>
															<label>휴가일</label> <input type="date"
																id="selectedStartDate" name="selectedStartDate" value=""
																required> <input type="date" id="selectedEndDate"
																name="selectedEndDate" value="" required> <input
																type="button" value="검색"
																onclick="goDate(); return false;">
														</fieldset>
													</sec:authorize>
												</div>
											</div>

											<table id="holiday-info-table">
												<thead>
													<tr>
														<th>순번</th>
														<sec:authorize access="hasRole('ROLE_ADMIN')">
															<th>사용자분류</th>
															<th>ID</th>
															<th>휴가번호</th>
														</sec:authorize>
														<sec:authorize access="hasRole('ROLE_MANAGER')">
															<th>지점명</th>
														</sec:authorize>
														<th>신청일</th>
														<th>시작일</th>
														<th>종료일</th>
														<th>휴가상태</th>
														<th>사용여부</th>
													</tr>
												</thead>
												<tbody id="holiday-list-tbody">
													<c:choose>
														<c:when test="${empty schedules}">
															<tr>
																<td colspan="10">데이터가 없습니다.</td>
															</tr>
														</c:when>
														<c:otherwise>
															<c:forEach var="schedule2" items="${schedules}">

																<c:set var="sqlDate" value="${schedule2.startDate}" />
																<c:set var="currentDate"
																	value="<%=new java.util.Date()%>" />

																<tr>
																	<td><c:out value="${schedule2.rowNumber}" /></td>
																	<sec:authorize access="hasRole('ROLE_ADMIN')">
																		<td><c:out value="${schedule2.userType}" /></td>
																		<td><c:out value="${schedule2.userId}" /></td>
																		<td><c:out value="${schedule2.scheduleId}" /></td>
																	</sec:authorize>
																	<sec:authorize access="hasRole('ROLE_MANAGER')">
																		<td data-store-id="${schedule2.storeId}"><c:out
																				value="${schedule2.storeName}" /></td>
																	</sec:authorize>
																	<td><c:out value="${schedule2.appDate}" /></td>
																	<td><c:out value="${schedule2.startDate}" /></td>
																	<td><c:out value="${schedule2.endDate}" /></td>
																	<c:choose>
																		<c:when test="${schedule2.actualUse eq 'N'}">
																			<td>취소완료</td>
																		</c:when>
																		<c:when test="${currentDate.after(sqlDate)}">
																			<td>이미 사용된 휴가</td>
																		</c:when>
																		<c:otherwise>
																			<td>
																				<button class="form-btn"
																					onclick="openCancelModal('${schedule2.scheduleId}', '${schedule2.startDate}', '${schedule2.endDate}'); 
																					getStoreData('${schedule2.storeId}', '${schedule2.storeName}');">취소
																				</button>
																			</td>
																		</c:otherwise>
																	</c:choose>
																	<td><c:out value="${schedule2.actualUse}" /></td>
																</tr>
															</c:forEach>
														</c:otherwise>
													</c:choose>
												</tbody>
											</table>
										</div>
										<div style="display:flex; float:right">
											<input type="hidden" value="${currentPage}" name="page">
											<p class="download-excel" onclick="downExcel(1)">현재 페이지 다운로드</p>
											<p class="download-excel" onclick="downExcel(2)">전체 페이지 다운로드</p>
										</div>
										<!-- 페이징 -->
										<div class="paging pagination">

											<!-- 맨 앞 버튼 -->
											<a href="" onclick="goPage(1); return false;"
												class="pageFirst"> <img
												src="/resources/img/log/free-icon-left-chevron-6015759.png"
												alt="처음" />
											</a>

											<!-- 이전 버튼 -->
											<c:choose>
												<c:when test="${currentPage > 1}">
													<a href=""
														onclick="goPage(${currentPage - 1}); return false;"
														class="pagePrev"> <img
														src="/resources/img/log/free-icon-left-arrow-271220.png"
														alt="이전" />
													</a>
												</c:when>
												<c:otherwise>
													<a href="" class="disabled-link pagePrev"> <img
														src="/resources/img/log/free-icon-left-arrow-271220.png"
														alt="이전" />
													</a>
												</c:otherwise>
											</c:choose>

											<!-- 리스트 목록 나열 -->
											<div id="number-list">
												<div class="page-btn">
													<c:forEach var="page" begin="${sharePage * 10 + 1}"
														end="${(sharePage + 1) * 10}" step="1">
														<c:if test="${page <= totalPage}">
															<a href="" onclick="goPage(${page}); return false;"
																class="pageNow pagination page-btn ${currentPage == page ? 'selected' : ''}">
																${page} </a>




														</c:if>
													</c:forEach>
												</div>
											</div>

											<!-- 뒤로 버튼 -->
											<c:if test="${currentPage < totalPage}">
												<a href=""
													onclick="goPage(${currentPage + 1}); return false;"
													class="pageNext"> <img
													src="/resources/img/log/free-icon-right-arrow-271228.png"
													alt="다음" />
												</a>
											</c:if>
											<c:if test="${currentPage == totalPage}">
												<a href="" class="disabled-link pageNext"> <img
													src="/resources/img/log/free-icon-right-arrow-271228.png"
													alt="다음" />
												</a>
											</c:if>

											<!-- 맨 뒤로 버튼 -->
											<a href="" onclick="goPage(${totalPage}); return false;"
												class="pageLast"> <img
												src="/resources/img/log/free-icon-fast-forward-double-right-arrows-symbol-54366.png"
												alt="마지막" />
											</a>
										</div>

										<!-- 취소확인 모달 창 -->
										<div id="cancelModal" class="modal">
											<div class="modal-content">
												<p>정말로 휴가를 취소하시겠습니까?</p>
												<button id="cancelYes">예</button>
												<button id="cancelNo">아니오</button>
											</div>
										</div>

										<!-- 결과 모달 창 -->
										<div id="resultModal" class="modal">
											<div class="modal-content">
												<p id="modalMessage">휴가가 취소되었습니다.</p>
												<button id="modal-result-confirmButton">확인</button>
											</div>
										</div>

										<sec:authorize
											access="hasAnyRole('ROLE_MANAGER', 'ROLE_MECHA')">
											<div class="right-buttons">
												<div>
													<button type="button" id="" onclick="postPopup();"
														class="form-btn">휴가신청</button>
												</div>
											</div>
										</sec:authorize>
									</div>
									<!-- ********** 세은 로그 관련 내용 끝 ********** -->
								</div>
							</div>
						</div>
					</div>



				</div>
			</div>
		</div>
	</div>
</body>
</html>
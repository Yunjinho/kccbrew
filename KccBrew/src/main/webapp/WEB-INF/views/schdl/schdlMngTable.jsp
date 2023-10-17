<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="java.time.LocalDateTime"%>
<%@ page import="java.time.format.DateTimeFormatter"%>

<%@ page import="java.util.Calendar"%>


<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />


<!-- css -->
<link rel="stylesheet" href="/resources/css/schdl/schdl-common.css" />
<link rel="stylesheet" href="/resources/css/log/mylogtest.css" />
<link rel="stylesheet" href="/resources/css/asMng/content-template.css" />
<link rel="stylesheet" href="/resources/css/schdl/mycalendar.css" />
<link rel="stylesheet" href="/resources/css/schdl/myschedulelist.css" />

<!-- javascript -->
<script src="<c:url value="/resources/js/schdl/board.js"/>"></script>
<script src="<c:url value="/resources/js/schdl/search.js"/>"></script>
<script src="<c:url value="/resources/js/schdl/scheduleTable.js"/>"></script>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<!-- font -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans&display=swap"
	rel="stylesheet">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans&family=Noto+Sans+KR&display=swap"
	rel="stylesheet">



<title>Insert title here</title>

</head>

<body>


	<div id="page" class="page-nosubmenu">

		<div id="page-mask">
			<div id="page-container" class="">
				<div id="page-content" class="clearfix">
					<div id="page-content-wrap">

						<!-- ********** 페이지 네비게이션 시작 ********** -->
						<div class="page-content-navigation">
						<h2 class="heading">월근태현황</h2>
							<ol class="breadcrumb">
								<li>
								<div class="header-icon-background">
								<a href="/">
									<img
											src="<c:url value='/img/common/free-icon-house.png' />"
											alt="Check List" class="header-icon" />
										</a>
								</div>
							</li>
							<li>
									<div class="header-icon-background">
										<img
											src="<c:url value='/img/common/free-icon-arrow-right.png' />"
											alt="Check List" class="header-icon" />
									</div>
								</li>
								<li class="breadcrumb-home"><a href="">스케줄관리</a></li>
								<li>
									<div class="header-icon-background">
										<img
											src="<c:url value='/img/common/free-icon-arrow-right.png' />"
											alt="Check List" class="header-icon" />
									</div>
								</li>
								<li><a href="<c:url value='/schedule' />">월근태현황</a></li>
							</ol>
						</div>
						<!-- ********** 페이지 네비게이션 끝 ********** -->

						<div id="region-main">
							<div role="main">
								<span id="maincontent"></span>
								<div class="user-past">

									<!-- ********** 세은 컨텐츠 ********** -->

									<div id="content">
										<!-- 탭메뉴 -->
				<!-- 						<div class="tabNav">
											<ul class="tab-ul">
												<li class="last"><a href="/holiday"><span>휴가사용현황</span></a></li>
												<li class="active"><a href="/schedule"><span>월근태현황</span></a></li>
											</ul>
										</div> -->

										<!-- 회원 검색 -->
										<form name="srhForm" action="admin/schedule" method="post">

											<input type="hidden" name="startDate" id="startDate" value=""> 
											<input type="hidden" name="endDate" id="endDate" value=""> 
											<input type="hidden" name="userTypeCd" value="03">

											<div class="search-info">
												<fieldset>
													<legend class="blind">사용자검색</legend>
													<table id="search-box">
														<c:set var="today" value="<%=new java.util.Date()%>" />
														<fmt:formatDate value="${today}" pattern="yyyy"
															var="nowYear" />
														<tr>
															<th colspan="4">근무년월</th>
															<!-- 시작 연도 선택 필드 -->
															<td colspan="4">
																<div id="dateSelect">
																	<select id="yearSelect" name="selectedYear" required onchange="getPeriod();">
																		<c:forEach var="year" begin="2020" end="2030">
																			<option value="${year}">${year}년</option>
																		</c:forEach>
																	</select> 
																	<select id="monthSelect" name="selectedMonth" required onchange="getPeriod();">
																		<c:forEach var="month" begin="1" end="12">
																			<option value="${month}">${month}월</option>
																		</c:forEach>
																	</select>
																</div>
															</td>
														</tr>

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

															<th>유형</th>
															<td>수리기사</td>

															<th>검색어</th>
															<td><select class="tx2" name="searchKeword"
																onchange="chgName(this)">
																	<option value="">검색어</option>
																	<option value="userId"
																		${param.searchKeword == 'userId' ? 'selected' : ''}>ID</option>
																	<option value="userNm"
																		${param.searchKeword == 'userNm' ? 'selected' : ''}>이름</option>
																	<option value="eqpmnCd"
																		${param.searchKeword == 'eqpmnCd' ? 'selected' : ''}>장비</option>
															</select></td>
															<td><input type="text" id="search-word"
																name="searchKeword" placeholder="키워드 선택 후 입력해주세요"
																required disabled></td>
														</tr>

													</table>
													<div class="form-btn-box">
														<fieldset>
															<button type="button" class="form-btn" id="searchButton"
																onclick="performSearch(); getLastDayAndPopulateTable();">검색</button>
															<button type="button" class="form-btn" onclick="resetForm();">초기화</button>
														</fieldset>
													</div>
												</fieldset>
											</div>
										</form>

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

										<div id="content-table">

											<div class="board-info">
												<span class="data-info"> 전체<b><span
														id="dataCount"></span></b>건
												</span>
											</div>

											<div class="board-info index"
												style="background-color: #f2f0e4;">
												<div
													style="background-color: black; width: 1em; height: 1em;"> </div>
												를 클릭 시 상세내용이 보입니다.

												<div id="holiday-index"
													style="background-color: #b8cff5; width: 1em; height: 1em;"></div>
												: 휴무
												<div id="assign-index"
													style="background-color: rgb(247, 212, 116); width: 1em; height: 1em;"></div>
												: 배정
												<div id="result-index"
													style="background-color: #e8a9ac; width: 1em; height: 1em;"></div>
												: 처리
											</div>



											<table Id="cal-table">
												<thead>
													<tr>
														<td rowspan="2">ID</td>
														<td rowspan="2">이름</td>
														<td rowspan="2">지역코드</td>
														<td rowspan="2">장비코드</td>
														<td rowspan="2">비고</td>
													</tr>
													<tr>
													</tr>
												</thead>
												<tbody>

												</tbody>
											</table>

											<!-- 모달 -->
											<div id="schedule-index-modal" class="modal">
												<div class="modal-content">
													<div id="modalHeader">
														<span id="contentTitle"></span> <span class="close"
															onclick="closeModal()">&times;</span>
													</div>
													<div id="modalContent">
														<ul id="listContainer">
														</ul>
													</div>
												</div>
											</div>
										</div>




									</div>




									<!-- ********** 세은 컨텐츠 끝 ********** -->
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
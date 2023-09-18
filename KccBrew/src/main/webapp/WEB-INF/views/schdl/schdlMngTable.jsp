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
<link rel="stylesheet" href="/resources/css/log/mylogtest.css" />
<link rel="stylesheet" href="/resources/css/log/content-template.css" />
<link rel="stylesheet" href="/resources/css/schdl/mycalendar.css" />
<link rel="stylesheet" href="/resources/css/schdl/common.css" />
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
		<!-- ********** header영역 시작********** -->
		<div id="page-header">
			<nav
				class="navbar navbar-default navbar-fixed-top headroom animated slideDown">
				<div class="container-fluid">

					<!-- 브랜드 로고 -->
					<div class="navbar-brand">
						<a href="" class="logo"> <img src="/resources/img/kcc.png"
							style="height: 100%; background: white;" />
						</a>
					</div>

					<!-- ********** header메뉴 시작********** -->
					<div class="usermenu">
						<ul class="nav nav-pills" role="tablist">
							<!-- 사용자 이름 -->
							<li class="user_department hidden-xs">이세은</li>

							<!-- 사용자 프로필 사진 -->
							<li class="user-info-menu"><a class="user-info-popover"
								tabindex="0" role="button" data-toggle="popover"
								data-placement="bottom" data-original-title="" title=""> <img
									src="/resources/img/log/f2.png" />
							</a>
								<div class="user-info-box popover-box">
									<div class="user-info-picture">
										<img src="./게시판_files/f1.png" alt="이세은 사진" class="userpicture">
										<h4>이세은</h4>
										<p class="department">화학과</p>
									</div>
									<ul class="user-info-submenu clearfix">
										<li class="items"><a
											href="https://cyber.inu.ac.kr/user/edit.jsp?id=86992">개인정보
												수정</a></li>
										<li class="items"><a
											href="https://cyber.inu.ac.kr/login/logout.jsp?sesskey=RWLqOGp4Jq">로그아웃</a></li>
									</ul>
									<ul class="user-info-shortcut">
										<li><a href="https://cyber.inu.ac.kr/user/files.jsp">파일
												관리</a></li>
									</ul>
								</div></li>

							<!-- 사용자 할일 -->
							<li class="user-courses"><a
								class="user-courses-popover icon-a" tabindex="0" role="button"
								data-toggle="popover" data-placement="bottom"
								data-original-title="" title="">
									<div class="header-icon-background">
										<img src="/resources/img/log/free-icon-checklist-3478402.png"
											alt="Check List" class="header-icon" />
									</div>
							</a>
								<div class="user-courses-box popover-box">
									<div class="popover-header">진행중인 강좌 (0)</div>
									<ul class="my-course-lists">
										<li class="nocourses">Loading...</li>
									</ul>
									<div class="popover-footer">
										<a href="https://cyber.inu.ac.kr/">MY COURSES</a>
									</div>
								</div></li>

							<!-- 알림 -->
							<li class="user-notification"><a
								class="icon-a user-noti-popover" tabindex="0" role="button"
								data-toggle="popover" data-placement="bottom"
								data-original-title="" title="">
									<div class="header-icon-background">
										<img
											src="/resources/img/log/free-icon-notification-bell-3913239.png"
											alt="Check List" class="header-icon" />
									</div>
							</a>
								<div class="user-noti-box popover-box">
									<div class="popover-header">
										<span>전체 알림</span> <img
											src="/resources/img/log/popover-icon.png" alt="" class="gear" />
									</div>
									<div class="user-noti-lists">
										<div class="nomessage">Loading...</div>
									</div>
									<div class="popover-footer">
										<a href="https://cyber.inu.ac.kr/local/ubnotification/">모두
											보기</a>
									</div>
								</div></li>

							<!-- 마이 메뉴 -->
							<li class="user-product"><a class="user-menu-product icon-a"
								tabindex="0" role="button" data-toggle="popover"
								data-original-title="" title=""><div
										class="header-icon-background">
										<img src="/resources/img/log/free-icon-menu-7421181.png"
											alt="Check List" class="header-icon" />
									</div></a>
								<div class="hide menu-product">
									<div class="menu-product-itl">
										<h5>
											교육혁신원 홈페이지 <a href="http://ctl.inu.ac.kr/" target="_blank"><img
												src="./게시판_files/itl_home.png" alt=""></a>
										</h5>
										<ul>
											<li><a href="http://www.inu.ac.kr/" target="_blank">인천대학교
													홈페이지</a></li>
											<li><a href="http://www.kocw.net/home/index.do"
												target="_blank">KOCW 강의공개 서비스</a></li>
											<li><a href="http://starinu.inu.ac.kr/" target="_blank">학생역량관리시스템(STARinU)</a></li>
											<li><a href="http://lib.inu.ac.kr/" target="_blank">도서관</a></li>
											<li><a href="http://job.inu.ac.kr/" target="_blank">취업경력개발원</a></li>
											<li><a
												href="http://rnd.inu.ac.kr/user/indexMain.do?siteId=rnd"
												target="_blank">산학협력단</a></li>
											<li><a href="http://scc.inu.ac.kr/" target="_blank">학생생활상담소</a></li>
										</ul>
									</div>
								</div></li>

							<!-- 로그아웃 -->
							<li class="active user-login user-logout hidden-xs"><a
								href="" class="btn btn-person">로그아웃</a></li>
						</ul>
					</div>
					<!-- ********** header메뉴 끝********** -->

					<div class="coursename">
						<h1>&nbsp;</h1>
					</div>
				</div>
			</nav>
		</div>
		<!-- ********** header영역 끝********** -->

		<!-- ********** 왼쪽 메뉴 시작 ********** -->
		<div id="page-lnb"></div>
		<!-- ********** 왼쪽 메뉴 끝 ********** -->

		<div id="page-mask">
			<div id="page-container" class="">
				<div id="page-content" class="clearfix">
					<div id="page-content-wrap">

						<!-- ********** 페이지 네비게이션 시작 ********** -->
						<div class="page-content-navigation">
							<ol class="breadcrumb">
								<li class="breadcrumb-home"><a href="">스케줄관리</a></li>
								<li>
									<div class="header-icon-background">
										<img
											src="<c:url value='/img/log/free-icon-right-arrow-271228.png' />"
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

										<h2 class="heading">월근태현황</h2>

										<!-- 탭메뉴 -->
										<div class="tabNav">
											<ul class="tab-ul">
												<li class="last"><a href="/holiday"><span>휴가사용현황</span></a></li>
												<li class="active"><a href="/schedule"><span>월근태현황</span></a></li>
											</ul>
										</div>

										<!-- 회원 검색 -->
										<form name="srhForm" action="/schedule" method="post">

											<input type="hidden" name="startDate" value=""> <input
												type="hidden" name="endDate" value=""> <input
												type="hidden" name="userTypeCd" value="03">

											<div>
												<span> 사용자검색 </span>
											</div>

											<div class="search-info">
												<fieldset>
													<legend class="blind">사용자검색</legend>
													<table id="search-box">
														<tr>
															<th>위치<span style="color: red;">(필수)</span></th>
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
																onclick="performSearch()">검색</button>
															<button type="reset" class="form-btn">초기화</button>
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
												<fieldset>
													<label>근무년월</label> <select id="yearSelect"
														name="selectedYear" required>
														<c:forEach var="year" begin="2020" end="2030">
															<option value="${year}">${year}</option>
														</c:forEach>
													</select> <select id="monthSelect" name="selectedMonth" required>
														<c:forEach var="month" begin="1" end="12">
															<option value="${month}">${month}월</option>
														</c:forEach>
													</select> <input type="button" value="이동"
														onclick="getLastDayAndPopulateTable(); getPeriod();">
												</fieldset>
											</div>

											<div class="board-info index"
												style="background-color: #f2f0e4;">
												<div
													style="background-color: black; width: 1em; height: 1em;"></div>
												에 마우스오버나 클릭 시 상세내용이 보입니다.

												<div id="holiday-index"
													style="background-color: pink; width: 1em; height: 1em;"></div>
												: 휴무
												<div id="assign-index"
													style="background-color: #f7d474; width: 1em; height: 1em;"></div>
												: 배정
												<div id="result-index"
													style="background-color: #5b8554; width: 1em; height: 1em;"></div>
												: 근무
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
													<span class="close" onclick="closeModal()">&times;</span>
													<!-- 닫기 버튼 -->
													<div id="modalContent"></div>
													<!-- 데이터를 표시할 영역 -->
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page import="java.time.LocalDateTime"%>
<%@ page import="java.time.format.DateTimeFormatter"%>

<!DOCTYPE html>
<html>
<head>
<!-- css -->
<link rel="stylesheet" href="/resources/css/schdl/schdl-common.css" />
<link rel="stylesheet" href="/resources/css/log/mylogtest.css" />
<link rel="stylesheet" href="/resources/css/asMng/content-template.css" />
<link rel="stylesheet" href="/resources/css/schdl/mycalendar.css" />
<link rel="stylesheet" href="/resources/css/schdl/myschedulelist.css" />

<!-- javascript -->
<script src="<c:url value="/resources/js/schdl/mySchdlMngClndr.js"/>"></script>


<!-- 캘린더 -->
<meta charset='utf-8' />
<link
	href='https://cdn.jsdelivr.net/npm/fullcalendar@5.10.0/main.min.css'
	rel='stylesheet' />
<script
	src='https://cdn.jsdelivr.net/npm/fullcalendar@5.10.0/main.min.js'></script>

<!-- jqeury -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<meta charset="UTF-8">
<title>Insert title here</title>

</head>

<body>

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
							<li><a href="<c:url value='/schedule/calendar' />">캘린더</a></li>
						</ol>
					</div>
					<!-- ********** 페이지 네비게이션 끝 ********** -->

					<div id="region-main">
						<div role="main">
							<span id="maincontent"></span>
							<div class="user-past">

								<!-- ********** 세은 컨텐츠 ********** -->
								<div id="content">
									<h2 class="heading">캘린더</h2>

									<div class="tabNav">
										<ul class="tab-ul">
											<li class="last"><a href="/holiday"><span>휴가사용현황</span></a></li>
											<sec:authorize
												access="hasAnyRole('ROLE_MANAGER', 'ROLE_MECHA')">
												<li class="active"><a href="/schedule/calendar"><span>나의캘린더</span></a></li>
											</sec:authorize>
											<sec:authorize access="hasRole('ROLE_ADMIN')">
												<li class="last"><a href="/schedule"><span>근태현황</span></a></li>
											</sec:authorize>
										</ul>
									</div>

									<div id="calendar"></div>
									<script>
										$(document)
												.ready(
														function() {
															var calendarEl = document
																	.getElementById('calendar');

															calendar = new FullCalendar.Calendar(
																	calendarEl,

																	{
																		height : 'auto',
																		initialView : 'dayGridMonth',
																		headerToolbar : {
																			left : 'prev,next',
																			center : 'title',
																			right : 'today',
																		},

																		dateClick : function(
																				info) {
																			var selectedDate = prompt(
																					'날짜를 선택하세요 (YYYY-MM-DD)',
																					info.dateStr);

																			if (selectedDate) {
																				var dateObj = new Date(
																						selectedDate);
																				calendar
																						.gotoDate(selectedDate);

																				/* 이벤트 확인 */
																				console
																						.log("datepicker 날짜 선택")

																				/* 날짜 정보 추출 */
																				dateInfo = new Date(
																						calendar.view.title);
																				year = dateInfo
																						.getFullYear();
																				month = dateInfo
																						.getMonth() + 1;

																				console
																						.log("year: "
																								+ year);
																				console
																						.log("month: "
																								+ month);

																				/* ajax함수로 데이터 서버로 전송 */
																				sendAjaxRequest(
																						year,
																						month);
																			}
																		},

																	});

															var view = calendar.view;
															console
																	.log(
																			"현재 뷰(View)의 정보:",
																			view);

															calendar.render();
														});

										$(document)
												.ready(
														function() {

															$(
																	"button[title='Previous month']")
																	.attr('id',
																			'prevButton');
															$(
																	"button[title='Next month']")
																	.attr('id',
																			'nextButton');
															$(
																	"button[title='This month']")
																	.attr('id',
																			'thisButton');

															// 이전 달 버튼 클릭 이벤트 핸들러
															$('#prevButton')
																	.click(
																			function() {
																				/* 로그 확인 */
																				console
																						.log("prevButton 버튼 클릭")

																				/* 날짜 정보 추출 */
																				dateInfo = new Date(
																						calendar.view.title);
																				console.log("dateInfo: " + dateInfo);
																				year = dateInfo
																						.getFullYear();
																				month = dateInfo
																						.getMonth() + 1;

																				console
																						.log("year: "
																								+ year);
																				console
																						.log("month: "
																								+ month);

																				/* ajax함수로 데이터 서버로 전송 */
																				sendAjaxRequest(
																						year,
																						month);
																			});

															// 다음 달 버튼 클릭 이벤트 핸들러
															$('#nextButton')
																	.click(
																			function() {
																				/* 로그 확인 */
																				console
																						.log("nextButton 버튼 클릭")

																				/* 날짜 정보 추출 */
																				dateInfo = new Date(
																						calendar.view.title);
																				year = dateInfo
																						.getFullYear();
																				month = dateInfo
																						.getMonth() + 1;

																				console
																						.log("year: "
																								+ year);
																				console
																						.log("month: "
																								+ month);

																				/* ajax함수로 데이터 서버로 전송 */
																				sendAjaxRequest(
																						year,
																						month);

																			});

															// 이번 달 버튼 클릭 이벤트 핸들러
															$('#thisButton')
																	.click(
																			function() {
																				/* 로그 확인 */
																				console
																						.log("thisButton 버튼 클릭")

																				/* 날짜 정보 추출 */
																				dateInfo = new Date(
																						calendar.view.title);
																				year = dateInfo
																						.getFullYear();
																				month = dateInfo
																						.getMonth() + 1;

																				console
																						.log("year: "
																								+ year);
																				console
																						.log("month: "
																								+ month);

																				/* ajax함수로 데이터 서버로 전송 */
																				sendAjaxRequest(
																						year,
																						month);

																			});
														});
									</script>

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
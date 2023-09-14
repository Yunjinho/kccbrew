<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="java.time.LocalDateTime"%>
<%@ page import="java.time.format.DateTimeFormatter"%>

<!DOCTYPE html>
<html>
<head>
<!-- css -->
<link rel="stylesheet" href="/resources/css/log/mylogtest.css" />
<link rel="stylesheet" href="/resources/css/log/content-template.css" />
<link rel="stylesheet" href="/resources/css/schdl/mycalendar.css" />


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
		<div id="page-lnb">
			<ul class="left-menus">

				<!-- 관리자 메뉴 -->

				<!-- 메뉴1 -->
				<li class="" data-original-title="" title=""><a href=""
					class="left-menu-link left-menu-link-mypage" title="My Page"
					data-toggle="tooltip" data-placement="right">
						<h5 class="">A/S관리</h5>
				</a>
					<ul>
						<li class=""><a href="https://cyber.inu.ac.kr/" title="">Dashboard</a></li>
						<li class=""><a href="https://cyber.inu.ac.kr/user/files.jsp"
							title="">파일 관리</a></li>
						<li class=""><a
							href="https://cyber.inu.ac.kr/mod/ubboard/my.jsp" title="">진행강좌
								공지</a></li>
						<li class=""><a
							href="https://cyber.inu.ac.kr/user/edit.jsp?id=86992" title="">개인정보
								수정</a></li>
					</ul></li>

				<!-- 메뉴2 -->
				<li class="active active-fix" data-original-title="" title=""><a
					href="" class="left-menu-link left-menu-link-mycourses"
					title="교과 과정" data-toggle="tooltip" data-placement="right">
						<h5 class="">시스템관리</h5>
				</a>
					<ul>
						<li class="active"><a href="" title="active">로그조회</a></li>
						<li class=""><a href="" title="">파일조회</a></li>
					</ul></li>

				<!-- 메뉴3 -->
				<li class="" data-original-title="" title=""><a href=""
					class="left-menu-link left-menu-link-irrcourse" title="비교과 과정"
					data-toggle="tooltip" data-placement="right">
						<h5 class="nosubmenu">코드관리</h5>
				</a></li>

				<!-- 메뉴4 -->
				<li class="" data-original-title="" title=""><a href=""
					class="left-menu-link left-menu-link-eclass" title="자율강좌"
					data-toggle="tooltip" data-placement="right">
						<h5 class="nosubmenu">수리기사관리</h5>
				</a></li>

				<!-- 메뉴5 -->
				<li class="" data-original-title="" title=""><a href=""
					class="left-menu-link left-menu-link-onlinep" title="교수지원"
					data-toggle="tooltip" data-placement="right">
						<h5 class="">점포관리</h5>
				</a>
					<ul>
						<li class=""><a href="" title="">수강 신청</a></li>
						<li class=""><a href="" title="">수강 현황</a></li>
						<li class=""><a href="" title="">수료 확인</a></li>
					</ul></li>

				<!-- 메뉴6 -->
				<li t class="" data-original-title="" title=""><a href=""
					class="left-menu-link left-menu-link-online" title="학습지원"
					data-toggle="tooltip" data-placement="right">
						<h5 class="">회원관리</h5>
				</a>
					<ul>
						<li class=""><a href="" title="">수강 신청</a></li>
						<li class=""><a href="" title="">수강 현황</a></li>
						<li class=""><a href="" title="">수료 확인</a></li>
					</ul></li>
			</ul>
		</div>
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
								<li><a href="<c:url value='/calendar' />">나의 캘린더</a></li>
							</ol>
						</div>
						<!-- ********** 페이지 네비게이션 끝 ********** -->

						<div id="region-main">
							<div role="main">
								<span id="maincontent"></span>
								<div class="user-past">

									<!-- ********** 세은 컨텐츠 ********** -->
									<div id="calendar"></div>
									<script
										src="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.0/main.min.js"></script>
									<script>
										/* 전역변수 */
										var calendar;
										var year;
										var month;
										var dateInfo;

										/* ajax로 날짜 정보 보내는 함수 */
										function sendAjaxRequest(year, month) {
											$
													.ajax({
														url : '/calendar',
														method : 'POST',
														data : {
															"year" : year,
															"month" : month,
															"dateInfo" : dateInfo
														},
														success : function(
																response) {
															var schedules = JSON
																	.stringify(response);

															var events = [];


															for (var i = 0; i < response.length; i++) {
																var schedule = response[i];
																var scheduleType = schedule.scheduleType;
																var scheduleDate = schedule.scheduleDate;


																events
																		.push({
																			title : scheduleType,
																			date : scheduleDate
																		});
															}

															calendar.setOption(
																	'events',
																	events);

														},

														error : function(error) {
															console
																	.error("Ajax 요청 실패: "
																			+ error);
														}
													});
										}

										$(document)
												.ready(
														function() {
															var calendarEl = document
																	.getElementById('calendar');

															calendar = new FullCalendar.Calendar(
																	calendarEl,

																	{
																		initialView : 'dayGridMonth',
																		/* events : [
																				{
																					title : '예제 이벤트',
																					date : '2023-09-07'
																				}, ], */

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
																						month,
																						dateInfo);
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
																						month,
																						dateInfo);

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
																						month,
																						dateInfo);

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
																						month,
																						dateInfo);

																			});
														});
									</script>



									<!-- 이전, 다음 버튼 클릭 시 날짜 정보 받아오기 -->
									<!-- 		<script>
										$(document)
												.ready(
														function() {
															var calendarEl = document
																	.getElementById('calendar');
															var calendar = new FullCalendar.Calendar(
																	calendarEl,
																	{
																	// 캘린더 설정 옵션들
																	});

															// 이전 달 버튼 찾아 id 속성 추가
															$(
																	"button[title='Previous month']")
																	.attr('id',
																			'prevButton');
															// 다음 달 버튼 찾아 id 속성 추가
															$(
																	"button[title='Next month']")
																	.attr('id',
																			'nextButton');

															// 이전 달 버튼 클릭 이벤트 핸들러
															$('#prevButton')
																	.click(
																			function() {
																				calendar
																						.prev(); // 이전 달로 이동
																				var info = calendar
																						.getCalendar()
																						.getView(); // 현재 보이는 뷰 정보 가져오기
																				console
																						.log(info);
																			});

															// 다음 달 버튼 클릭 이벤트 핸들러
															$('#nextButton')
																	.click(
																			function() {
																				calendar
																						.next(); // 다음 달로 이동
																				var info = calendar
																						.getCalendar()
																						.getView(); // 현재 보이는 뷰 정보 가져오기
																				console
																						.log(info);
																			});

															calendar.render();
														});
									</script> -->


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
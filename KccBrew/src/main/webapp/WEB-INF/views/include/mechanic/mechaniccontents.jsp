<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet"
	href="${path}/resources/css/comm/maincontents-mecha.css" />
</head>
<body>
	<div class="body-wrapper">

		<!------------------ 상단 메뉴 ---------------------------->

		<div class="bottom-menu">

			<div class="calAndetc">
				<div id='calendar-container'>
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
																		 locale: 'ko',
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
																				dateInfo = parseDateStr(calendar.view.title);
																				year = dateInfo
																						.year;
																				month = dateInfo
																						.month;

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
																				dateInfo = parseDateStr(calendar.view.title);
																				year = dateInfo
																						.year;
																				month = dateInfo
																						.month;

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
																				dateInfo = parseDateStr(calendar.view.title);
																				year = dateInfo
																						.year;
																				month = dateInfo
																						.month;

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
																				dateInfo = parseDateStr(calendar.view.title);
																				year = dateInfo
																						.year;
																				month = dateInfo
																						.month;

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
				</div>
			</div>

			<div class="todoAndetc">
				<div class="to-do-list">
					<ul class="tabnav">
						<li><a href="#today">오늘</a></li>
						<li><a href="#weekly">주간</a></li>
						<li><a href="#monthly">월간</a></li>
					</ul>
					<div class="tabcontent">
						<div id="today" class="tab-pane">
							<table class="task-list">
								<thead>
									<tr>
										<th>배정번호</th>
										<th>방문 예정일</th>
										<th>접수 장비</th>
										<th>지점</th>
										<th>수리 기사</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="main" items="${mechaDailyData}">
										<tr>
											<td><c:out value="${main.asAssignNum}" /></td>
											<td><fmt:formatDate value="${main.visitDate}"
													pattern="yyyy-MM-dd" /></td>
											<td><c:choose>
													<c:when test="${main.machineCode == '01'}">커피머신</c:when>
													<c:when test="${main.machineCode == '02'}">냉장고</c:when>
													<c:when test="${main.machineCode == '03'}">제빙기</c:when>
													<c:when test="${main.machineCode == '04'}">에어컨</c:when>
													<c:when test="${main.machineCode == '05'}">온수기</c:when>
												</c:choose></td>
											<td><c:out value="${main.storeNm}" /></td>
											<td><c:out value="${main.mechanicName}" /></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<div id="weekly" class="tab-pane">
							<table class="task-list">
								<thead>
									<tr>
										<th>배정번호</th>
										<th>방문 예정일</th>
										<th>접수 장비</th>
										<th>지점</th>
										<th>수리 기사</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="main" items="${mechaWeeklyData}">
										<tr>
											<td><c:out value="${main.asAssignNum}" /></td>
											<td><fmt:formatDate value="${main.visitDate}"
													pattern="yyyy-MM-dd" /></td>
											<td><c:choose>
													<c:when test="${main.machineCode == '01'}">커피머신</c:when>
													<c:when test="${main.machineCode == '02'}">냉장고</c:when>
													<c:when test="${main.machineCode == '03'}">제빙기</c:when>
													<c:when test="${main.machineCode == '04'}">에어컨</c:when>
													<c:when test="${main.machineCode == '05'}">온수기</c:when>
												</c:choose></td>
											<td><c:out value="${main.storeNm}" /></td>
											<td><c:out value="${main.mechanicName}" /></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<div id="monthly" class="tab-pane">
							<table class="task-list">
								<thead>
									<tr>
										<th>배정번호</th>
										<th>방문 예정일</th>
										<th>접수 장비</th>
										<th>지점</th>
										<th>수리 기사</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="main" items="${mechaMonthlyData}">
										<tr>
											<td><c:out value="${main.asAssignNum}" /></td>
											<td><fmt:formatDate value="${main.visitDate}"
													pattern="yyyy-MM-dd" /></td>
											<td><c:choose>
													<c:when test="${main.machineCode == '01'}">커피머신</c:when>
													<c:when test="${main.machineCode == '02'}">냉장고</c:when>
													<c:when test="${main.machineCode == '03'}">제빙기</c:when>
													<c:when test="${main.machineCode == '04'}">에어컨</c:when>
													<c:when test="${main.machineCode == '05'}">온수기</c:when>
												</c:choose></td>
											<td><c:out value="${main.storeNm}" /></td>
											<td><c:out value="${main.mechanicName}" /></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>

			<div id="education-mark">
				<img src="${path}/resources/img/main/coffee-machine3.jpg">
				<div id="education-content">
					<span>수리대상 장비 교육 안내</span> <a href=""><img
						src="${path}/resources/img/main/icon-direction.png"></a>
				</div>
			</div>

			<div id="application-mark">
				<div id="application-title">서비스 신청</div>
				<div id="application-content">
					<div class="last">
						<img src="${path}/resources/img/main/icon-close-sign.png"> <a
							href="/holiday">휴가신청</a>
					</div>
				</div>
			</div>

		</div>

		<div class="top-menu">

			<div class="assigned-as btm">
				<table class="task-list">
					<caption class="tablecap">내게 온 A/S 배정</caption>
					<thead>
						<tr>
							<th>배정번호</th>
							<th>방문 예정일</th>
							<th>접수 장비</th>
							<th>지점</th>
							<th>수리 기사</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="main" items="${asAssignListbyMechaId}">
							<tr>
								<td><c:out value="${main.asAssignNum}" /></td>
								<td><fmt:formatDate value="${main.visitDate}"
										pattern="yyyy-MM-dd" /></td>
								<td><c:choose>
										<c:when test="${main.machineCode == '01'}">커피머신</c:when>
										<c:when test="${main.machineCode == '02'}">냉장고</c:when>
										<c:when test="${main.machineCode == '03'}">제빙기</c:when>
										<c:when test="${main.machineCode == '04'}">에어컨</c:when>
										<c:when test="${main.machineCode == '05'}">온수기</c:when>
									</c:choose></td>
								<td><c:out value="${main.storeNm}" /></td>
								<td><c:out value="${main.mechanicName}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<div class="as-result btm">
				<table class="task-list">
					<caption class="tablecap">처리 완료한 A/S</caption>
					<thead>
						<tr>
							<th>배정번호</th>
							<th>접수 장비</th>
							<th>결과</th>
							<th>지점</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="main" items="${resultListbyMechaId}">
								<tr>
									<td><c:out value="${main.asAssignNum}" /></td>
									<td><c:choose>
											<c:when test="${main.machineCode == '01'}">커피머신</c:when>
											<c:when test="${main.machineCode == '02'}">냉장고</c:when>
											<c:when test="${main.machineCode == '03'}">제빙기</c:when>
											<c:when test="${main.machineCode == '04'}">에어컨</c:when>
											<c:when test="${main.machineCode == '05'}">온수기</c:when>
										</c:choose></td>
									<td><c:choose>
											<c:when test="${main.reapply == 'Y'}">재접수</c:when>
											<c:when test="${main.asStatus == '01'}">접수 중</c:when>
											<c:when test="${main.asStatus == '02'}">반려</c:when>
											<c:when test="${main.asStatus == '03'}">접수 완료</c:when>
											<c:when test="${main.asStatus == '04'}">수리 완료</c:when>
										</c:choose></td>
									<td><c:out value="${main.storeNm}" /></td>
								</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<div class="notice btm">
				<div class="tablecap">공지사항</div>
				<ul>
					<c:forEach var="noticeVo" items="${noticeVo}">
						<li class="notice"><a href="/noticedetail/${noticeVo.noticeId}">${noticeVo.noticeTitle}</a></li>
					</c:forEach>
				</ul>
			</div>

		</div>
	</div>


</body>
</html>
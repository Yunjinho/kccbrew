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
	href="${path}/resources/css/comm/maincontents-manager.css" />

</head>
<body>
	<div class="body-wrapper">

		<!------------------ 상단 메뉴 ---------------------------->



		<!-------------- 하단 메뉴 ----------------------->


		<div class="bottom-menu">

			<div class="calAndetc">
				<div id='calendar-container'>
					<div id='calendar'></div>
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
									<c:forEach var="main" items="${managerDailyData}">
										<c:if test="${main.managerId == sessionScope.userId}">
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
												<td>강남점</td>
												<td><c:out value="${main.mechanicName}" /></td>
											</tr>
										</c:if>
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
									<c:forEach var="main" items="${managerWeeklyData}">
										<c:if test="${main.managerId == sessionScope.userId}">
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
												<td>강남점</td>
												<td><c:out value="${main.mechanicName}" /></td>
											</tr>
										</c:if>
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
									<c:forEach var="main" items="${managerMonthlyData}">
										<c:if test="${main.managerId == sessionScope.userId}">
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
												<td>강남점</td>
												<td><c:out value="${main.mechanicName}" /></td>
											</tr>
										</c:if>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			
			<div id="education-mark">
			</div>
			
			
			<div id="application-mark">
			</div>

		</div>


		<div class="top-menu">
			<div class="new-receipt btm">
				<table class="task-list">
					<caption class="tablecap">나의 접수 목록</caption>
					<thead>
						<tr>
							<th>접수번호</th>
							<th>접수 상태</th>
							<th>접수 장비</th>
							<th>점주</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="main" items="${asListById}">
							<c:if test="${main.asStatus == '01'}">
								<tr>
									<td><c:out value="${main.asInfoNum}" /></td>
									<td><c:choose>
											<c:when test="${main.asStatus == '01'}">접수 중</c:when>
											<c:when test="${main.asStatus == '02'}">반려</c:when>
											<c:when test="${main.asStatus == '03'}">접수 완료</c:when>
											<c:when test="${main.asStatus == '04'}">처리 완료</c:when>
										</c:choose></td>
									<td><c:choose>
											<c:when test="${main.machineCode == '01'}">커피머신</c:when>
											<c:when test="${main.machineCode == '02'}">냉장고</c:when>
											<c:when test="${main.machineCode == '03'}">제빙기</c:when>
											<c:when test="${main.machineCode == '04'}">에어컨</c:when>
											<c:when test="${main.machineCode == '05'}">온수기</c:when>
										</c:choose></td>
									<td><c:out value="${main.managerName}" /></td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<div class="assigned-as btm">
				<table class="task-list">
					<caption class="tablecap">배정 완료 목록</caption>
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
						<c:forEach var="main" items="${asAssignListById}">
							<c:if test="${main.asStatus == '03'}">
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
									<td>강남점</td>
									<td><c:out value="${main.mechanicName}" /></td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<div class="as-result btm">
				<table class="task-list">
					<caption class="tablecap">처리 완료 목록</caption>
					<thead>
						<tr>
							<th>배정번호</th>
							<th>접수 장비</th>
							<th>결과</th>
							<th>지점</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="main" items="${resultListById}">
							<c:if test="${main.asStatus == '04'}">
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
											<c:when test="${main.asStatus == '01'}">접수 중</c:when>
											<c:when test="${main.asStatus == '02'}">반려</c:when>
											<c:when test="${main.asStatus == '03'}">접수 완료</c:when>
											<c:when test="${main.asStatus == '04'}">수리 완료</c:when>
										</c:choose></td>
									<td>동대문점</td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</div>


		</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="body-wrapper">
	
	<!------------------ 상단 메뉴 ---------------------------->
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
								<td><c:out value="${main.asAssignNum}"/></td>
								<td><fmt:formatDate value="${main.visitDate}" pattern="yyyy-MM-dd" /></td>
			                    <td>
			                        <c:choose>
			                            <c:when test="${main.machineCode == '01'}">커피머신</c:when>
			                            <c:when test="${main.machineCode == '02'}">냉장고</c:when>
			                            <c:when test="${main.machineCode == '03'}">제빙기</c:when>
			                            <c:when test="${main.machineCode == '04'}">에어컨</c:when>
			                            <c:when test="${main.machineCode == '05'}">온수기</c:when>
			                        </c:choose>
			                    </td>
			                    <td>강남점</td>
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
							<c:if test="${main.asStatus == '04'}">
								<tr>
									<td><c:out value="${main.asAssignNum}"/></td>
									 <td>
				                        <c:choose>
				                            <c:when test="${main.machineCode == '01'}">커피머신</c:when>
				                            <c:when test="${main.machineCode == '02'}">냉장고</c:when>
				                            <c:when test="${main.machineCode == '03'}">제빙기</c:when>
				                            <c:when test="${main.machineCode == '04'}">에어컨</c:when>
				                            <c:when test="${main.machineCode == '05'}">온수기</c:when>
				                        </c:choose>
				                    </td>
									<td>
										<c:choose>
											<c:when test="${main.asStatus == '01'}">접수 중</c:when>
											<c:when test="${main.asStatus == '02'}">반려</c:when>
											<c:when test="${main.asStatus == '03'}">접수 완료</c:when>
											<c:when test="${main.asStatus == '04'}">수리 완료</c:when>
										</c:choose>
									 </td>
									<td>동대문점</td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		
		
		<!-------------- 하단 메뉴 ----------------------->
		
		
		<div class="bottom-menu">
			<div class="profileAndguide">
				<div class="user-profile">
					<div class="inner-box">
						<span class="name">${sessionScope.userName}</span>님 <br><br>
						<span class="job">수리기사</span> <br><br><br><br><br><br>
					</div>
					<div class="buttons">
						<button class="chpwd">비밀번호 변경</button> 
						<span> | </span> 
						<c:url var="toLogin" value="/loginpage" />
						<a href="${toLogin}">
							<button class="logout">
								로그아웃
							</button>
						</a>
					</div>
				</div>
				<div class="as-guide">
					<div class="inner-box">
						as 접수 안내
					</div>
				</div>
				<div class="toKccBrew">
					<div class="inner-box">
						<a href="#">
							<img alt="logo"  src="${path}/resources/img/logo.png">
						</a>
						<a href="#">
							<button class="kcclink">
								KccBrew 홈페이지로 이동 ▶
							</button>
						</a>
					</div>
				</div>
			</div>
			<div class="todoAndetc">
				<div class="to-do-list">
					<p> A/S 방문 일정  </p>
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
		                                    <td><fmt:formatDate value="${main.visitDate}" pattern="yyyy-MM-dd" /></td>
		                                    <td>
		                                        <c:choose>
		                                            <c:when test="${main.machineCode == '01'}">커피머신</c:when>
		                                            <c:when test="${main.machineCode == '02'}">냉장고</c:when>
		                                            <c:when test="${main.machineCode == '03'}">제빙기</c:when>
		                                            <c:when test="${main.machineCode == '04'}">에어컨</c:when>
		                                            <c:when test="${main.machineCode == '05'}">온수기</c:when>
		                                        </c:choose>
		                                    </td>
		                                    <td>강남점</td>
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
			                                <td><fmt:formatDate value="${main.visitDate}" pattern="yyyy-MM-dd" /></td>
			                                <td>
			                                    <c:choose>
			                                        <c:when test="${main.machineCode == '01'}">커피머신</c:when>
			                                        <c:when test="${main.machineCode == '02'}">냉장고</c:when>
			                                        <c:when test="${main.machineCode == '03'}">제빙기</c:when>
			                                        <c:when test="${main.machineCode == '04'}">에어컨</c:when>
			                                        <c:when test="${main.machineCode == '05'}">온수기</c:when>
			                                    </c:choose>
			                                </td>
			                                <td>강남점</td>
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
			                                <td><fmt:formatDate value="${main.visitDate}" pattern="yyyy-MM-dd" /></td>
			                                <td>
			                                    <c:choose>
			                                        <c:when test="${main.machineCode == '01'}">커피머신</c:when>
			                                        <c:when test="${main.machineCode == '02'}">냉장고</c:when>
			                                        <c:when test="${main.machineCode == '03'}">제빙기</c:when>
			                                        <c:when test="${main.machineCode == '04'}">에어컨</c:when>
			                                        <c:when test="${main.machineCode == '05'}">온수기</c:when>
			                                    </c:choose>
			                                </td>
			                                <td>강남점</td>
			                                <td><c:out value="${main.mechanicName}" /></td>
			                            </tr>
			                        </c:forEach>
			                    </tbody>
			                </table>
			            </div>
					</div>
				</div>
				<div class="todoetc">
					etc
				</div>
			</div>
			<div class="calAndetc">
				<div id='calendar-container'>
					<p>  나의 일정  </p>
					<div id='calendar'></div>
				</div>
			</div>	
		</div>
	</div>
</body>
</html>
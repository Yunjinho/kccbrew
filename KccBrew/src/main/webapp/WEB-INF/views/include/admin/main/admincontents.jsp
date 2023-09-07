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
<link rel="stylesheet" href="${path}/resources/css/comm/reset.css" >
<link rel="stylesheet" href="${path}/resources/css/comm/header.css" >
<link rel="stylesheet" href="${path}/resources/css/comm/mainpage.css"/>
<link rel="stylesheet" href="${path}/resources/css/comm/footer.css"/>
<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/main.min.css'/>
<script src="http://code.jquery.com/jquery-latest.js"></script> 
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/main.min.js'></script>
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/locales-all.min.js'></script>
<script src="${path}/resources/js/comm/calendar.js"></script>
<script src="${path}/resources/js/comm/comm.js"></script>
<script src="${path}/resources/js/comm/header.js"></script>
<script src="${path}/resources/js/comm/main.js"></script>
</head>
<body>
	<div class="body-wrapper">
	
	<!------------------ 상단 메뉴 ---------------------------->
		<div class="top-menu">
			<div class="new-receipt btm">
				<table class="task-list">
					<caption class="tablecap">접수 대기  리스트</caption>
					<thead>	
						<tr>
							<th>접수번호</th>
							<th>접수 상태</th>
							<th>접수 장비</th>
							<th>점주 이름</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="main" items="${asList}">
                            <c:if test="${main.asStatus == '01'}">
                                <tr>
                                    <td><c:out value="${main.asInfoNum}" /></td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${main.asStatus == '01'}">접수 중</c:when>
                                            <c:when test="${main.asStatus == '02'}">반려</c:when>
                                            <c:when test="${main.asStatus == '03'}">접수 완료</c:when>
                                            <c:when test="${main.asStatus == '04'}">처리 완료</c:when>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${main.machineCode == '01'}">커피머신</c:when>
                                            <c:when test="${main.machineCode == '02'}">냉장고</c:when>
                                            <c:when test="${main.machineCode == '03'}">제빙기</c:when>
                                            <c:when test="${main.machineCode == '04'}">에어컨</c:when>
                                            <c:when test="${main.machineCode == '05'}">온수기</c:when>
                                        </c:choose>
                                    </td>
                                    <td><c:out value="${main.managerName}" /></td>
                                </tr>
                            </c:if>
                        </c:forEach>
					</tbody>
				</table>
			</div>
		
			<div class="assigned-as btm">
				<table class="task-list">
					<caption class="tablecap">접수 중인  A/S</caption>
					<thead>
						<tr>
							<th>배정번호</th>
							<th>방문 예정일</th>
							<th>접수 장비</th>
							<th>지점</th>
							<th>수리 기사 이름</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="main" items="${asAssignList}">
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
					<caption class="tablecap">처리 완료 A/S </caption>
					<thead>
						<tr>
							<th>배정번호</th>
							<th>접수 장비</th>
							<th>결과</th>
							<th>지점</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="main" items="${resultList}">
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
						</c:forEach>
					</tbody>
				</table>
			</div>
			
			<div class="new-member-apply btm">
				<table class="task-list">
					<caption class="tablecap">회원 가입 대기</caption>
					<thead>
						<tr>
							<th>아이디</th>
							<th>이름</th>
							<th>전화번호</th>
							<th>사용자 구분</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="main" items="${waitingList}">
							<c:if test="${main.approveStatus == 'N'}">
								<tr>
									<td><c:out value="${main.userId}"/></td>
									<td><c:out value="${main.userName}"/></td>
									<td><c:out value="${main.userTelNo}"/></td>
									<td>
										<c:choose>
											<c:when test="${main.userType == '01'}">관리자</c:when>
											<c:when test="${main.userType == '02'}">점주</c:when>
											<c:when test="${main.userType == '03'}">수리 기사</c:when>
										</c:choose>
									</td>
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
						<span class="name">###</span>님 <br><br>
						<span class="job">관리자</span> <br><br><br><br><br><br>
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
							<img alt="logo"  src="${path}/resources/img/kcc.png">
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
					<p>  A/S 일정  </p>
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
										<th>수리 기사 이름</th>
									</tr>
								</thead>
								 <tbody>
			                        <c:forEach var="main" items="${dailyData}">
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
			                            <th>수리 기사 이름</th>
			                        </tr>
			                    </thead>
			                    <tbody>
			                        <c:forEach var="main" items="${weeklyData}">
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
			                            <th>수리 기사 이름</th>
			                        </tr>
			                    </thead>
			                    <tbody>
			                    	<c:forEach var="main" items="${monthlyData}">
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
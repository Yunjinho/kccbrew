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
<link rel="stylesheet" href="/resources/css/asMng/content-template.css" />

<!-- javascript -->
<script src="<c:url value="/resources/js/sysMng/log/log.js"/>"></script>
<script src="<c:url value="/resources/js/board/pagination.js"/>"></script>
<script src="<c:url value="/resources/js/comm/date/date.js"/>"></script>

<meta charset="UTF-8">
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
											<h2 class="heading">로그조회</h2>
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
							
								<li class="breadcrumb-home"><a href="">시스템관리</a></li>
								<li>
									<div class="header-icon-background">
										<img
											src="<c:url value='/img/common/free-icon-arrow-right.png' />"
											alt="Check List" class="header-icon" />
									</div>
								</li>
								<li><a href="<c:url value='/log' />">로그조회</a></li>
							</ol>
						</div>
						<!-- ********** 페이지 네비게이션 끝 ********** -->

						<div id="region-main">
							<div role="main">
								<span id="maincontent"></span>
								<div class="user-past">

									<!-- ********** 세은 로그 관련 내용 시작 ********** -->
									<div id="content">
										<!-- 로그 검색 -->
										<form name="srhForm" action="/admin/log" method="post">
											<input type="hidden" name="currentPage" value="1">
											<table id="search-box">
												<!-- 1행 -->
												<c:set var="today" value="<%=new java.util.Date()%>" />
												<fmt:formatDate value="${today}" pattern="yyyy"
													var="nowYear" />

												<tr>
													<th>사용자 유형</th>
													<td><select class="tx2" name="userType"
														onchange="javascript:chg();">
															<option value="">사용자</option>
															<option value="관리자"
																${param.userType == 'admin' ? 'selected' : ''}>관리자</option>
															<option value="점주"
																${param.userType == 'manager' ? 'selected' : ''}>점주</option>
															<option value="기사"
																${param.userType == 'mechanic' ? 'selected' : ''}>기사</option>
													</select></td>

													<th>사용자ID</th>
													<td><input type="text" name="userId"
														placeholder="ID를 입력하세요" value="${param.userId}"></td>

													<th>기간</th>
													<td colspan="3" id="form-period"><span><label
															for="startDate">시작일:</label> <input type="date"
															id="startDate" name="startDate" value=""></span> <span><label
															for="endDate">종료일:</label> <input type="date"
															id="endDate" name="endDate" value=""></span></td>
												</tr>


												<!-- 2행 -->
												<tr>
													<th>경로</th>
													<!-- Input field for URI -->
													<td><input type="text" name="uri"
														placeholder="경로를 입력하세요" value="${param.uri}"></td>

													<th>화면</th>
													<td><input type="text" name="view"
														placeholder="화면을 입력하세요" value="${param.view}"></td>

													<th>IP</th>
													<td><input type="text" name="ip"
														placeholder="IP를 입력하세요" value="${param.ip}"></td>

													<th>상태코드</th>
													<td><select class="tx2" name="statusCode" id="yr"
														onchange="javascript:chg();">
															<option value="">상태코드</option>
															<option value="200 OK"
																${param.statusCode == '200 OK' ? 'selected' : ''}>200
																OK</option>
															<option value="201 Created"
																${param.statusCode == '201 Created' ? 'selected' : ''}>201
																Created</option>
															<option value="400 Bad Request"
																${param.statusCode == '400 Bad Request' ? 'selected' : ''}>400
																Bad Request</option>
															<option value="403 Forbidden"
																${param.statusCode == '403 Forbidden' ? 'selected' : ''}>403
																Forbidden</option>
															<option value="404 Not Found"
																${param.statusCode == '404 Not Found' ? 'selected' : ''}>404
																Not Found</option>
															<option value="500 Internal Server Error"
																${param.statusCode == '500 Internal Server Error' ? 'selected' : ''}>500
																Internal Server Error</option>
													</select></td>

												</tr>

											</table>
											<div class="form-btn-box">
												<fieldset>
													<button type="button" class="form-btn" id="searchButton"
														onclick="performSearch();">검색</button>
													<button type="button" class="form-btn"
														onclick="resetForm();">초기화</button>
												</fieldset>
											</div>
										</form>



										<!-- 로그 리스트 -->
										<div id="logTable">
											<div>
												<span class="data-info"> 전체<b><span
														id="totalDataNumber"><c:out
																value="${totalDataNumber}" /></span></b>건<span id="text-separator">
														| </span> <b><span id="currentPage"><c:out
																value="${currentPage}" /></span></b>/<b><span id="totalPage"><c:out
																value="${totalPage}" /></span></b>쪽
												</span>
											</div>
											<table>
												<thead>
													<tr>
														<th>로그번호</th>
														<th>날짜</th>
														<th>경로</th>
														<th>화면</th>
														<th>사용자ID</th>
														<th>사용자유형</th>
														<th>IP</th>
														<th>상태코드</th>
													</tr>
												</thead>
												<tbody id="logTableBody">
													<c:forEach var="log" items="${logs}">
														<tr>
															<td><c:out value="${log.logSeq}" /></td>
															<td><c:out value="${log.date}" /></td>
															<td><c:out value="${log.uri}" /></td>
															<td><c:out value="${log.view}" /></td>
															<td><c:out value="${log.userId}" /></td>
															<td><c:out value="${log.userType}" /></td>
															<td><c:out value="${log.ip}" /></td>
															<td><c:out value="${log.statusCode}" /></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>

										<%-- 	<!-- 페이징 -->
										<div class="paging pagination">

											<!-- 앞으로 가는 버튼 -->
											<a
												href="/log?currentPage=1&startYr=${searchContent.startYr}&startMn=${searchContent.startMn}&endYr=${searchContent.endYr}&endMn=${searchContent.endMn}&uri=${searchContent.uri}&view=${searchContent.view}&userId=${searchContent.userId}&userType=${searchContent.userType}&ip=${searchContent.ip}&statusCode=${searchContent.statusCode}"><img
												src="/resources/img/log/free-icon-left-chevron-6015759.png"
												alt=" 처" /></a>

											<c:choose>
												<c:when test="${currentPage > 1}">
													<a
														href="/log?currentPage=${currentPage - 1}&startYr=${searchContent.startYr}&startMn=${searchContent.startMn}&endYr=${searchContent.endYr}&endMn=${searchContent.endMn}&uri=${searchContent.uri}&view=${searchContent.view}&userId=${searchContent.userId}&userType=${searchContent.userType}&ip=${searchContent.ip}&statusCode=${searchContent.statusCode}"><img
														src="/resources/img/log/free-icon-left-arrow-271220.png"
														alt="이" /></a>
												</c:when>
												<c:otherwise>
													<a href="#" disabled="disabled"><img
														src="/resources/img/log/free-icon-left-arrow-271220.png"
														alt="이" /></a>
												</c:otherwise>
											</c:choose>

											<!-- 리스트 목록 나열 -->
											<div id="number-list">
												<div class="page-btn">
													<c:forEach var="page" begin="${sharePage * 10 + 1}"
														end="${(sharePage + 1) * 10}" step="1">
														<c:if test="${page <= totalPage}">
															<a
																href="/log?currentPage=${page}&startYr=${searchContent.startYr}&startMn=${searchContent.startMn}&endYr=${searchContent.endYr}&endMn=${searchContent.endMn}&uri=${searchContent.uri}&view=${searchContent.view}&userId=${searchContent.userId}&userType=${searchContent.userType}&ip=${searchContent.ip}&statusCode=${searchContent.statusCode}"
																class="pagination page-btn ${currentPage == page ? 'selected' : ''}">
																${page} </a>
														</c:if>
													</c:forEach>
												</div>
											</div>

											<!-- 뒤로 가는 버튼 -->
											<c:if test="${currentPage < totalPage}">
												<a
													href="/log?currentPage=${currentPage + 1}&startYr=${searchContent.startYr}&startMn=${searchContent.startMn}&endYr=${searchContent.endYr}&endMn=${searchContent.endMn}&uri=${searchContent.uri}&view=${searchContent.view}&userId=${searchContent.userId}&userType=${searchContent.userType}&ip=${searchContent.ip}&statusCode=${searchContent.statusCode}">
													<img
													src="/resources/img/log/free-icon-right-arrow-271228.png"
													alt="다" />
												</a>
											</c:if>
											<c:if test="${currentPage == totalPage}">
												<a href="javascript:void(0);" class="disabled-link"> <img
													src="/resources/img/log/free-icon-right-arrow-271228.png"
													alt="다" />
												</a>
											</c:if>

											<a
												href="/log?currentPage=${totalPage}&startYr=${searchContent.startYr}&startMn=${searchContent.startMn}&endYr=${searchContent.endYr}&endMn=${searchContent.endMn}&uri=${searchContent.uri}&view=${searchContent.view}&userId=${searchContent.userId}&userType=${searchContent.userType}&ip=${searchContent.ip}&statusCode=${searchContent.statusCode}"><img
												src="/resources/img/log/free-icon-fast-forward-double-right-arrows-symbol-54366.png"
												alt="마" /></a>
										</div> --%>

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
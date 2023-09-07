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
<link rel="stylesheet" href="${path}/resources/css/comm/header.css" >

<!-- font -->
<!-- notoSans -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans&display=swap"
	rel="stylesheet">
<!-- notoSans Kr -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans&family=Noto+Sans+KR&display=swap"
	rel="stylesheet">
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
							<li class="breadcrumb-home"><a href="">시스템관리</a></li>
							<li>
								<div class="header-icon-background">
									<img
										src="<c:url value='/img/log/free-icon-right-arrow-271228.png' />"
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
									<h2 class="heading">AS 조회</h2>
									<!-- AS 조회-->
									<form action="/searchAsList" method="get">
										<table id="search-box">
											<!-- 1행 -->
											<c:set var="today" value="<%=new java.util.Date()%>" />
											<fmt:formatDate value="${today}" pattern="yyyy"
												var="nowYear" />
											<tr>
												<th>조회 기간</th>
												<!-- 시작 연도 선택 필드 -->
												<td><select class="tx2" name="startYr" id="yr"
													onchange="javascript:chg();">
														<option value="">연도</option>
														<c:forEach var="i" begin="0" end="9">
															<c:set var="year" value="${nowYear - i}" />
															<option value="${year}"
																${param.startYr == year ? 'selected' : ''}>${year}년</option>
														</c:forEach>
												</select></td>

												<!-- 시작 월 선택 필드 -->
												<td><select class="tx2" name="startMn" id="mn">
														<option value="">월</option>
														<c:forEach var="month" begin="1" end="12">
															<option value="${month}"
																${param.startMn == month ? 'selected' : ''}>${month}월</option>
														</c:forEach>
												</select></td>

												<td>~</td>

												<!-- 종료 연도 선택 필드 -->
												<td>
													<select class="tx2" name="endYr" id="yr" onchange="javascript:chg();">
														<option value="">연도</option>
														<c:forEach var="i" begin="0" end="9">
															<c:set var="year" value="${nowYear - i}" />
															<option value="${year}"
																${param.endYr == year ? 'selected' : ''}>${year}년</option>
														</c:forEach>
													</select>
												</td>

												<!-- 종료 월 선택 필드 -->
												<td>
													<select class="tx2" name="endMn" id="mn">
														<option value="">월</option>
														<c:forEach var="month" begin="1" end="12">
															<option value="${month}"
																${param.endMn == month ? 'selected' : ''}>${month}월</option>
														</c:forEach>
													</select>
												</td>
												<td>
													<button type="submit" onclick="" class="form-btn">이동</button>
												</td>
											</tr>


											<!-- 2행 -->
											<tr>
												<th>AS 번호</th>
												<!-- Input field for URI -->
												<td>
													<input type="search" name="asInfoSeq" placeholder="AS 번호를 입력하세요" value="">
												</td>
												<th>점포 이름</th>
												<td>
													<input type="search" name="storeNm" placeholder="점포명을 입력하세요" value="">
												</td>
												<th>점포 주소</th>
												<td colspan="2">
													<input type="search" name="storeAddr" placeholder="점포 주소를 입력하세요" value="">
												</td>
											</tr>
											<!-- 3행 -->
											<tr>
												<th>사용자 ID</th>
												<td >
													<input type="search" name="userId" placeholder="사용자ID를 입력하세요" value="">
												</td>
												<th>장비 구분</th>
												<td>
													<select class="tx2" name="machineCd" id="" onchange="javascript:chg();">
															<option value="">장비 구분</option>
															<c:forEach var="empCd" items="${machineCd}">
																<option value="${empCd.grpCdDtlId}">
																	${empCd.grpCdDtlNm}
																</option>
															</c:forEach>
													</select>
												</td>
												<th>AS 상태</th>
												<td colspan="2">
													<select class="tx2" name="statusCd" id="" onchange="javascript:chg();">
															<option value="">AS 상태</option>
															<c:forEach var="asCd" items="${asStatusCd}">
																<option value="${asCd.grpCdDtlId}">
																	${asCd.grpCdDtlNm}
																</option>
															</c:forEach>
													</select>
												</td>
											</tr>
											<tr>
												<td colspan="6" style="border-bottom:none;"></td>
												<td style="text-align: center; border-bottom: 0px;">
													<div>
														<button type="submit" class="form-btn">검색</button>
													</div>
												</td>
											</tr>											
										</table>
									</form>



									<!-- 로그 리스트 -->
									<div id="logTable">
										<div>
											<p class="data-info">
												전체<b><span><c:out value="${totalLog}" /></span></b>건<span
													id="text-separator"> | </span><b><span><c:out
															value="${currentPage}" /></span></b>/<b><span><c:out
															value="${totalPage}" /></span></b>쪽
											</p>
										</div>
										<table>
											<thead>
												<tr>
													<th>AS 번호</th>
													<th>신청일</th>
													<th>AS 상태</th>
													<th>점포 명</th>
													<th>점포 주소</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="list" items="${ASList}">
													<tr>
														<td><c:out value="${list.asInfoSeq}" /></td>
														<td><c:out value="${list.regDttm}" /></td>
														<td><c:out value="${list.statusCd}" /></td>
														<td><c:out value="${list.storeNm}" /></td>
														<td><c:out value="${list.storeAddr}" /></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>

									<!-- 페이징 -->
									<div class="paging pagination">

										<!-- 앞으로 가는 버튼 -->
										<a href="/log?currentPage=1&startYr=${searchContent.startYr}&startMn=${searchContent.startMn}&endYr=${searchContent.endYr}&endMn=${searchContent.endMn}&uri=${searchContent.uri}&view=${searchContent.view}&userId=${searchContent.userId}&userType=${searchContent.userType}&ip=${searchContent.ip}&statusCode=${searchContent.statusCode}"><img
											src="/resources/img/log/free-icon-left-chevron-6015759.png"
											alt=" 처" /></a>

										<c:choose>
											<c:when test="${currentPage > 1}">
												<a href="/log?currentPage=${currentPage - 1}&startYr=${searchContent.startYr}&startMn=${searchContent.startMn}&endYr=${searchContent.endYr}&endMn=${searchContent.endMn}&uri=${searchContent.uri}&view=${searchContent.view}&userId=${searchContent.userId}&userType=${searchContent.userType}&ip=${searchContent.ip}&statusCode=${searchContent.statusCode}"><img
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
											<a href="/log?currentPage=${currentPage + 1}&startYr=${searchContent.startYr}&startMn=${searchContent.startMn}&endYr=${searchContent.endYr}&endMn=${searchContent.endMn}&uri=${searchContent.uri}&view=${searchContent.view}&userId=${searchContent.userId}&userType=${searchContent.userType}&ip=${searchContent.ip}&statusCode=${searchContent.statusCode}"> <img
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

										<a href="/log?currentPage=${totalPage}&startYr=${searchContent.startYr}&startMn=${searchContent.startMn}&endYr=${searchContent.endYr}&endMn=${searchContent.endMn}&uri=${searchContent.uri}&view=${searchContent.view}&userId=${searchContent.userId}&userType=${searchContent.userType}&ip=${searchContent.ip}&statusCode=${searchContent.statusCode}"><img
											src="/resources/img/log/free-icon-fast-forward-double-right-arrows-symbol-54366.png"
											alt="마" /></a>
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
<script src="<c:url value='/js/bootstrap.min.js' />"></script>
<script src="<c:url value='/js/jquery.min.js' />"></script>
</body>
</html>
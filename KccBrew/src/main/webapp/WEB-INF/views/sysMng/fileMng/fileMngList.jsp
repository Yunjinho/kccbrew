<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="java.time.LocalDateTime"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/resources/css/store/store.css" />

<meta charset="UTF-8">

<title>파일리스트</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>

</head>
<body>
	<div id="page" class="page-nosubmenu">


		<div id="page-mask">
			<div id="page-container" class="">
				<div id="page-content" class="clearfix">
					<div id="page-content-wrap">


						<!-- ********** 페이지 네비게이션 끝 ********** -->

						<div id="region-main">
							<div role="main">
								<span id="maincontent"></span>
								<div class="user-past">

									<!-- ********** 점포 관련 내용 시작 ********** -->
									<div id="content">
										<h2 class="heading">파일조회</h2>
										<!-- 점포 검색 -->
										<form action="/file" method="get">
											<table id="search-box">
												<!-- 1행 -->
												<c:set var="today" value="<%=new java.util.Date()%>" />
												<fmt:formatDate value="${today}" pattern="yyyy"
													var="nowYear" />
												<tr>

													<th>등록일자</th>
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
													<td><select class="tx2" name="endYr" id="yr"
														onchange="javascript:chg();">
															<option value="">연도</option>
															<c:forEach var="i" begin="0" end="9">
																<c:set var="year" value="${nowYear - i}" />
																<option value="${year}"
																	${param.endYr == year ? 'selected' : ''}>${year}년</option>
															</c:forEach>
													</select></td>

													<!-- 종료 월 선택 필드 -->
													<td><select class="tx2" name="endMn" id="mn">
															<option value="">월</option>
															<c:forEach var="month" begin="1" end="12">
																<option value="${month}"
																	${param.endMn == month ? 'selected' : ''}>${month}월</option>
															</c:forEach>
													</select></td>
												</tr>
												<!-- 2행 -->
												<tr>
												<th>파일 구분</th>
													<td colspan="2"><select name="grpCdDtlId" class="tx2" id="yn"
														onchange="javascript:chg();">
															<option value="">선택</option>
															<c:forEach var="List" items="${typeList}">
														<option value="${List.grpCdDtlId}" ${param.grpCdDtlId == List.grpCdDtlId ? 'selected' : ''}>${List.grpCdDtlNm}</option>
														</c:forEach>
														
													</select></td>
													<th>등록자Id</th>
													<td colspan="2"><input type="search" name="fileRegUser"
														placeholder="등록자ID를 입력하세요" value="${param.fileRegUser}" size="30"></td>
														
												</tr>
												<!--3행  -->
												<tr>
													
													<th>원본파일명</th>
													<td colspan="2"><input type="search" name="fileOriginNm"
														placeholder="원본파일명을 입력하세요" value="${param.fileOriginNm}" size="30"></td>
													<th>서버파일명</th>
													<td colspan="2"><input type="search" name="fileServerNm"
														placeholder="서버파일명을 입력하세요" value="${param.fileServerNm}" size="30"></td>
														</tr>
												<!-- 4행 -->
												<tr>
													<td colspan="8"
														style="text-align: center; border-bottom: 0px;">
														<div class="find-btn" style="text-align: center;">
															<button type="submit" class="form-btn" id="find-btn1">검색</button>
															<button type="button" class="form-btn" id="find-btn1"
																onclick="location.href='/file';">초기화</button>
														</div>
													</td>
												</tr>
											</table>
										</form>
										<div id="logTable">
											<div>
												<p class="data-info">
													전체<b><span><c:out value="${totalLog}" /></span></b>건<span
														id="text-separator"> | </span><b><span><c:out
																value="${currentPage}" /></span></b>/<b><span><c:out
																value="${totalPage}" /></span></b>쪽
												</p>
											</div>

											<table class="text-center">
												<thead>
													<tr>
														<th scope="col">파일번호</th>
														<th scope="col">원본파일명</th>
														<th scope="col">서버이름</th>
														<th scope="col">파일형식</th>
														<th scope="col">등록일자</th>
														<th scope="col">구분</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${list}" var="no" varStatus="loop">
														<tr>
															<td>${no.fileSeq}</td>
															<td>${no.fileOriginNm}</td>
															<td>${no.fileServerNm}</td>
															<td>${no.fileFmt}</td>
															<td><fmt:formatDate value="${no.fileRegDttm}" pattern="yyyy MM/dd" /></td>
															<td>${no.grpCdDtlNm}</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
										
										<!-- 페이징 -->
										<div class="paging pagination">

											<!-- 앞으로 가는 버튼 -->
											<a href="/file?currentPage=1&startYr=${searchContent.startYr}&startMn=${searchContent.startMn}&endYr=${searchContent.endYr}&endMn=${searchContent.endMn}&grpCdDtlId=${searchContent.grpCdDtlId}&fileRegUser=${searchContent.fileRegUser}&fileOriginNm=${searchContent.fileOriginNm}&fileServerNm=${searchContent.fileServerNm}"><img
												src="/resources/img/log/free-icon-left-chevron-6015759.png"
												alt=" 처" /></a>

											<c:choose>
												<c:when test="${currentPage > 1}">
													<a href="/file?currentPage=${currentPage - 1}&startYr=${searchContent.startYr}&startMn=${searchContent.startMn}&endYr=${searchContent.endYr}&endMn=${searchContent.endMn}&grpCdDtlId=${searchContent.grpCdDtlId}&fileRegUser=${searchContent.fileRegUser}&fileOriginNm=${searchContent.fileOriginNm}&fileServerNm=${searchContent.fileServerNm}"><img
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
																href="/file?currentPage=${page}&startYr=${searchContent.startYr}&startMn=${searchContent.startMn}&endYr=${searchContent.endYr}&endMn=${searchContent.endMn}&grpCdDtlId=${searchContent.grpCdDtlId}&fileRegUser=${searchContent.fileRegUser}&fileOriginNm=${searchContent.fileOriginNm}&fileServerNm=${searchContent.fileServerNm}"
																class="pagination page-btn ${currentPage == page ? 'selected' : ''}">
																${page} </a>
														</c:if>
													</c:forEach>
												</div>
											</div>

											<!-- 뒤로 가는 버튼 -->
											<c:if test="${currentPage < totalPage}">
												<a href="/file?currentPage=${currentPage + 1}&startYr=${searchContent.startYr}&startMn=${searchContent.startMn}&endYr=${searchContent.endYr}&endMn=${searchContent.endMn}&grpCdDtlId=${searchContent.grpCdDtlId}&fileRegUser=${searchContent.fileRegUser}&fileOriginNm=${searchContent.fileOriginNm}&fileServerNm=${searchContent.fileServerNm}"> <img
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

											<a href="/file?currentPage=${totalPage}&startYr=${searchContent.startYr}&startMn=${searchContent.startMn}&endYr=${searchContent.endYr}&endMn=${searchContent.endMn}&grpCdDtlId=${searchContent.grpCdDtlId}&fileRegUser=${searchContent.fileRegUser}&fileOriginNm=${searchContent.fileOriginNm}&fileServerNm=${searchContent.fileServerNm}"><img
												src="/resources/img/log/free-icon-fast-forward-double-right-arrows-symbol-54366.png"
												alt="마" /></a>
										</div>

									</div>
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
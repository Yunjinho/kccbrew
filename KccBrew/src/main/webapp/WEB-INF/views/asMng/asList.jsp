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

<link rel="stylesheet" href="/resources/css/asMng/asList.css" />
<link rel="stylesheet" href="/resources/css/asMng/content-template.css" />

<!-- js -->
<script src="<c:url value="resources/js/asMng/asList.js"/>"></script>
<meta charset="UTF-8">
<title>AS 내역 조회</title>
</head>
<body>
	<div id="page-mask">
		<div id="page-container" class="">
			<div id="page-content" class="clearfix">
				<div id="page-content-wrap">
					<!-- ********** 페이지 네비게이션 시작 ********** -->
					<div class="page-content-navigation">
						<h2 class="heading">A/S내역조회</h2>
						<ol class="breadcrumb">
							<li>
								<div class="header-icon-background">
								<a href="/">
									<img
										src="<c:url value='resources/img/common/free-icon-house.png' />"
										alt="Check List" class="header-icon" />
										</a>
								</div>
							</li>
							<li>
								<div class="header-icon-background">
									<img
										src="<c:url value='resources/img/common/free-icon-arrow-right.png' />"
										alt="Check List" class="header-icon" />
								</div>
							</li>
							<li class="breadcrumb-home"><a href="#">AS 관리</a></li>
							<li>
								<div class="header-icon-background">
									<img
										src="<c:url value='resources/img/common/free-icon-arrow-right.png' />"
										alt="Check List" class="header-icon" />
								</div>
							</li>
							<li><a href="<c:url value='/as-list' />">AS 조회</a></li>
						</ol>
					</div>
					<!-- ********** 페이지 네비게이션 끝 ********** -->
					<div id="region-main">
						<div role="main">
							<span id="maincontent"></span>
							<div class="user-past">

								<!-- ********** 관리자 AS 리스트 조회 ********** -->
								<div id="content">

									<!-- 관리자  AS 조건-->
									<c:if test="${sessionScope.user.userTypeCd eq '01'}">
										<form action="/searchAsList" method="get" id="search-form">
											<input type='hidden' name='currentPage' value="1">
											<table id="search-box">
												<tr>
													<th>조회 기간</th>
													<td colspan="2"><input type="date"
														name="wishingStartDate"
														value="${searchContent.wishingStartDate}"
														onchange="changeStartDate()"></td>
													<td>~</td>
													<td colspan="3"><input type="date"
														name="wishingEndDate"
														value="${searchContent.wishingEndDate}"
														onchange="changeEndDate()"></td>

													<!-- 종료 연도 선택 필드 -->
												</tr>


												<!-- 2행 -->
												<tr>
													<th>AS 번호</th>
													<!-- Input field for URI -->
													<td><input type="search" name="asInfoSeq"
														placeholder="AS 번호를 입력하세요"
														value="${searchContent.asInfoSeq}"></td>
													<th>점포 이름</th>
													<td><input type="search" name="storeNm"
														placeholder="점포명을 입력하세요" value="${searchContent.storeNm}">
													</td>
													<th>점포 주소</th>
													<td colspan="2"><input type="search" name="storeAddr"
														placeholder="점포 주소를 입력하세요"
														value="${searchContent.storeAddr}"></td>
												</tr>
												<!-- 3행 -->
												<tr>
													<th>사용자 ID</th>
													<td><input type="search" name="searchId"
														placeholder="사용자ID를 입력하세요"
														value="${searchContent.searchId}"></td>
													<th>장비 구분</th>
													<td><select class="tx2" name="machineCd">
															<option value="">전체</option>
															<c:forEach var="empCd" items="${machineCd}">
																<c:choose>
																	<c:when
																		test="${searchContent.machineCd == empCd.grpCdDtlId}">
																		<option value="${empCd.grpCdDtlId}" selected>
																			${empCd.grpCdDtlNm}</option>
																	</c:when>
																	<c:otherwise>
																		<option value="${empCd.grpCdDtlId}">
																			${empCd.grpCdDtlNm}</option>
																	</c:otherwise>
																</c:choose>
															</c:forEach>
													</select></td>
													<th>AS 상태</th>
													<td colspan="2"><select class="tx2" name="asStatusCd">
															<option value="">전체</option>
															<c:choose>
																<c:when test='${searchContent.asStatusCd eq "Y"}'>
																	<option value="Y" selected>재접수</option>
																</c:when>
																<c:otherwise>
																	<option value="Y">재접수</option>
																</c:otherwise>
															</c:choose>
															<c:forEach var="asCd" items="${asStatusCd}">
																<c:choose>
																	<c:when
																		test="${searchContent.asStatusCd == asCd.grpCdDtlId}">
																		<option value="${asCd.grpCdDtlId}" selected>
																			${asCd.grpCdDtlNm}</option>
																	</c:when>
																	<c:otherwise>
																		<option value="${asCd.grpCdDtlId}">
																			${asCd.grpCdDtlNm}</option>
																	</c:otherwise>
																</c:choose>
															</c:forEach>
													</select></td>
												</tr>
												<tr>
													<td colspan="6" style="border-bottom: none;"></td>
													<td style="border-bottom: none;">
														<div style="text-align: center; border-bottom: 0px; display: flex; float: right;">
															<button style="margin:0px 10px;" type="button" onclick="resetSearch()" class="form-btn">초기화</button>
															<div id="search-submit" class="form-btn">검색</div>
														</div>
													</td>
												</tr>
											</table>
										</form>
									</c:if>
									<!-- 가맹 점주 AS 조건 -->
									<c:if test="${sessionScope.user.userTypeCd eq '02'}">
										<form action="/searchAsList" method="get" id="search-form">
											<input type='hidden' name='currentPage' value="1">
											<table id="search-box">
												<tr>
													<th>조회 기간</th>
													<td colspan="2"><input type="date"
														name="wishingStartDate"
														value="${searchContent.wishingStartDate}"></td>
													<td>~</td>
													<td colspan="3"><input type="date"
														name="wishingEndDate"
														value="${searchContent.wishingEndDate}"></td>
												</tr>
												<!-- 2행 -->
												<tr>
													<th>AS 번호</th>
													<!-- Input field for URI -->
													<td><input type="search" name="asInfoSeq"
														placeholder="AS 번호를 입력하세요"
														value="${searchContent.asInfoSeq}"></td>
													<th>장비 구분</th>
													<td><select class="tx2" name="machineCd">
															<option value="">장비 구분</option>
															<c:forEach var="empCd" items="${machineCd}">
																<c:choose>
																	<c:when
																		test="${searchContent.machineCd == empCd.grpCdDtlId}">
																		<option value="${empCd.grpCdDtlId}" selected>
																			${empCd.grpCdDtlNm}</option>
																	</c:when>
																	<c:otherwise>
																		<option value="${empCd.grpCdDtlId}">
																			${empCd.grpCdDtlNm}</option>
																	</c:otherwise>
																</c:choose>
															</c:forEach>
													</select></td>
													<th>AS 상태</th>
													<td colspan="2"><select class="tx2" name="asStatusCd"
														id="">
															<option value="">AS 상태</option>
															<c:choose>
																<c:when test='${searchContent.asStatusCd eq "Y"}'>
																	<option value="Y" selected>재접수</option>
																</c:when>
																<c:otherwise>
																	<option value="Y">재접수</option>
																</c:otherwise>
															</c:choose>
															<c:forEach var="asCd" items="${asStatusCd}">
																<c:choose>
																	<c:when
																		test="${searchContent.asStatusCd == asCd.grpCdDtlId}">
																		<option value="${asCd.grpCdDtlId}" selected>
																			${asCd.grpCdDtlNm}</option>
																	</c:when>
																	<c:otherwise>
																		<option value="${asCd.grpCdDtlId}">
																			${asCd.grpCdDtlNm}</option>
																	</c:otherwise>
																</c:choose>
															</c:forEach>
													</select></td>
												</tr>
												<tr>
													<td colspan="6" style="border-bottom: none;"></td>
													<td style="text-align: center; border-bottom: 0px;display: flex; float: right;">
														<div style="margin:0px 10px;">
															<button type="button" onclick="resetSearch()" class="form-btn">초기화</button>
														</div>
														<div>
															<div id="search-submit" class="form-btn">검색</div>
														</div>
													</td>
												</tr>
											</table>
										</form>
									</c:if>
									<!-- 수리 기사 AS 조건 -->
									<c:if test="${sessionScope.user.userTypeCd eq '03'}">
										<form action="/searchAsList" method="get" id="search-form">
											<input type='hidden' name='currentPage' value="1">
											<table id="search-box">
												<tr>
													<th>조회 기간</th>
													<td colspan="2"><input type="date"
														name="wishingStartDate"
														value="${searchContent.wishingStartDate}"></td>
													<td>~</td>
													<td colspan="3"><input type="date"
														name="wishingEndDate"
														value="${searchContent.wishingEndDate}"></td>
												</tr>
												<!-- 2행 -->
												<tr>
													<th>AS 번호</th>
													<!-- Input field for URI -->
													<td><input type="search" name="asInfoSeq"
														placeholder="AS 번호를 입력하세요"
														value="${searchContent.asInfoSeq}"></td>
													<th>점포 이름</th>
													<td><input type="search" name="storeNm"
														placeholder="점포명을 입력하세요" value="${searchContent.storeNm}">
													</td>
													<th>점포 주소</th>
													<td colspan="2"><input type="search" name="storeAddr"
														placeholder="점포 주소를 입력하세요"
														value="${searchContent.storeAddr}"></td>
												</tr>
												<!-- 3행 -->
												<tr>
													<th>장비 구분</th>
													<td colspan="2"><select class="tx2" name="machineCd"
														id="">
															<option value="">장비 구분</option>
															<c:forEach var="empCd" items="${machineCd}">
																<c:choose>
																	<c:when
																		test="${searchContent.machineCd == empCd.grpCdDtlId}">
																		<option value="${empCd.grpCdDtlId}" selected>
																			${empCd.grpCdDtlNm}</option>
																	</c:when>
																	<c:otherwise>
																		<option value="${empCd.grpCdDtlId}">
																			${empCd.grpCdDtlNm}</option>
																	</c:otherwise>
																</c:choose>
															</c:forEach>
													</select></td>
													<th>AS 상태</th>
													<td colspan="3"><select class="tx2" name="asStatusCd"
														id="">
															<option value="">AS 상태</option>
															<c:choose>
																<c:when test='${searchContent.asStatusCd eq "Y"}'>
																	<option value="Y" selected>재접수</option>
																</c:when>
																<c:otherwise>
																	<option value="Y">재접수</option>
																</c:otherwise>
															</c:choose>
															<c:forEach var="asCd" items="${asStatusCd}">
																<c:choose>
																	<c:when
																		test="${searchContent.asStatusCd == asCd.grpCdDtlId}">
																		<option value="${asCd.grpCdDtlId}" selected>
																			${asCd.grpCdDtlNm}</option>
																	</c:when>
																	<c:otherwise>
																		<option value="${asCd.grpCdDtlId}">
																			${asCd.grpCdDtlNm}</option>
																	</c:otherwise>
																</c:choose>
															</c:forEach>
													</select></td>
												</tr>
												<tr>
													<td colspan="6" style="border-bottom: none;"></td>
													<td style="border-bottom: none;">
														<div style="text-align: center; border-bottom: 0px; display: flex; float: right;">
															<button style="margin:0px 10px;" type="button" onclick="resetSearch()" class="form-btn">초기화</button>
															<div id="search-submit" class="form-btn">검색</div>
														</div>
													</td>
												</tr>
											</table>
										</form>
									</c:if>

									<div id="logTable">
										<div class="list-info-div">
											<p class="data-info">
												전체<b><span><c:out value="${totalCount}" /></span></b>건<span
													id="text-separator"> | </span><b><span><c:out
															value="${currentPage}" /></span></b>/<b> <span> <c:choose>
															<c:when test="${totalPage eq 0}">1</c:when>
															<c:otherwise>${totalPage}</c:otherwise>
														</c:choose>
												</span></b>쪽
											</p>
											<div>
												<p class="download-excel" onclick="downExcel(1)">현재 페이지
													다운로드</p>
												<p class="download-excel" onclick="downExcel(2)">전체 페이지
													다운로드</p>
											</div>
										</div>
										<table>
											<thead>
												<tr>
													<th>AS 번호</th>
													<th>신청일</th>
													<th>AS 상태</th>
													<th>점포 명</th>
													<th>점포 주소</th>
													<c:if test="${sessionScope.user.userTypeCd != '02'}">
														<th>기사 재배정 신청</th>
													</c:if>
													<c:if test="${sessionScope.user.userTypeCd eq '01' }">
														<th>재접수 여부</th>
													</c:if>
													<th>상세 조회</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="list" items="${ASList}">
													<tr>
														<td><c:out value="${list.asInfoSeq}" /></td>
														<td><c:out value="${list.regDttm}" /></td>
														<td><c:choose>
																<c:when test='${list.resultReapply eq "Y"}'>재접수</c:when>
																<c:otherwise>
																	<c:out value="${list.asStatusNm}" />
																</c:otherwise>
															</c:choose></td>
														<td><c:out value="${list.storeNm}" /></td>
														<td><c:out value="${list.storeAddr}" /></td>
														<c:if test="${sessionScope.user.userTypeCd != '02'}">
															<td><c:choose>
																	<c:when test="${list.reassign =='Y'}">
																		<c:out value="${list.reassign}"></c:out>
																	</c:when>
																	<c:otherwise>-</c:otherwise>
																</c:choose></td>
														</c:if>
														<c:if test="${sessionScope.user.userTypeCd eq '01'}">
															<td><c:choose>
																	<c:when test='${list.resultReapply =="Y"}'>
																		<c:out value="${list.resultReapply}"></c:out>
																	</c:when>
																	<c:otherwise>-</c:otherwise>
																</c:choose></td>
														</c:if>
														<td><c:choose>
																<c:when test="${list.asAssignSeq eq nul}">
																	<a href="#"
																		onclick="selectAsDetail(${list.asInfoSeq},-1,${list.storeSeq})"
																		class="form-btn">조회</a>
																</c:when>
																<c:otherwise>
																	<a href="#"
																		onclick="selectAsDetail(${list.asInfoSeq},${list.asAssignSeq},${list.storeSeq})"
																		class="form-btn">조회</a>
																</c:otherwise>
															</c:choose></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>

									<!-- 페이징 -->
									<div class="paging pagination">

										<!-- 앞으로 가는 버튼 -->
										<a href="#"><img
											src="/resources/img/asMng/free-icon-left-chevron-6015759.png"
											alt=" 처" onclick="movePage(1)" /></a>

										<c:choose>
											<c:when test="${currentPage > 1}">
												<a href="#"><img
													src="/resources/img/asMng/free-icon-left-arrow-271220.png"
													alt="이" onclick="movePage(${currentPage-1})" /></a>
											</c:when>
											<c:otherwise>
												<a href="#" disabled="disabled"><img
													src="/resources/img/asMng/free-icon-left-arrow-271220.png"
													alt="이" /></a>
											</c:otherwise>
										</c:choose>

										<!-- 리스트 목록 나열 -->
										<div id="number-list">
											<div class="page-btn">
												<c:forEach var="page" begin="${startPage}" end="${endPage}"
													step="1">
													<c:if test="${page <= totalPage}">
														<a href="javascript:void(0)" onclick="movePage(${page})"
															class="pagination page-btn ${currentPage == page ? 'selected' : ''}">
															${page} </a>
													</c:if>
												</c:forEach>
											</div>
										</div>

										<!-- 뒤로 가는 버튼 -->
										<c:choose>
											<c:when test="${currentPage < totalPage}">
												<a href="#" onclick="movePage(${currentPage+1})"> <img
													src="/resources/img/asMng/free-icon-right-arrow-271228.png"
													alt="다" />
												</a>
											</c:when>
											<c:otherwise>
												<a href="javascript:void(0);" class="disabled-link"> <img
													src="/resources/img/asMng/free-icon-right-arrow-271228.png"
													alt="다" />
												</a>
											</c:otherwise>
										</c:choose>

										<a href="#" onclick="movePage(${totalPage})"><img
											src="/resources/img/asMng/free-icon-fast-forward-double-right-arrows-symbol-54366.png"
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
</body>
</html>
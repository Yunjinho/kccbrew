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

<title>코드리스트</title>
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
										<h2 class="heading">코드조회</h2>
										<!-- 점포 검색 -->
										<form action="/code" method="get">
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
													<th>그룹코드 사용여부</th>
													<td><select name="cdUseYn" class="tx2" id="yn"
														onchange="javascript:chg();">
															<option value="">선택</option>
															<option value="Y" ${param.cdUseYn == 'Y' ? 'selected' : ''}>Y</option>
															<option value="N" ${param.cdUseYn == 'N' ? 'selected' : ''}>N</option>
													</select></td>
												</tr>
												<!-- 2행 -->
												<tr>

													<th>그룹코드</th>
													<td colspan="3"><select class="tx2" name="cdId"
														onchange="chg()">
															<option value="">그룹코드명</option>
															<c:forEach var="list" items="${List}">
																<option value="${list.cdId}"
																	${param.cdId == list.cdId ? 'selected' : ''}>${list.cdNm}</option>
															</c:forEach>
													</select></td>

													<th>상세코드명</th>
													<!-- Input field for URI -->
													<td colspan="3"><input type="search" name="keyword"
														placeholder="상세코드명 입력하세요" value="${param.keyword}" size="30"> </td>

												</tr>


												<!-- 4행 -->
												<tr>
													<td colspan="8"
														style="text-align: center; border-bottom: 0px;">
														<div class="find-btn" style="text-align: center;">
															<button type="submit" class="form-btn" id="find-btn1">검색</button>
															<button type="button" class="form-btn" id="find-btn1"
																onclick="location.href='/code';">초기화</button>
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
												<th scope="col">코드넘버</th>
												<th scope="col">그룹코드ID</th>
												<th scope="col">그룹코드이름</th>
												<th scope="col">상세코드ID</th>
												<th scope="col">상세코드이름</th>
												<th scope="col">상세코드사용여부</th>
												<th scope="col">상세보기</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${list}" var="no" varStatus="loop">
												<tr>
													<td>${no.cdIdx}</td>
													<td>${no.cdId}</td>
													<td><a href="javascript:void(0);"
														onclick="popup2('${no.cdId}');">${no.cdNm}</a></td>
													<td>${no.cdDtlId}</td>
													<td>${no.cdDtlNm}</td>
													<td>${no.cdDtlUseYn}</td>
													<td><a href="javascript:void(0);"
														data-cdId="${no.cdId}" data-cdDtlId="${no.cdDtlId}"
														onclick="popup1(this.getAttribute('data-cdId'), this.getAttribute('data-cdDtlId'));">
															<input type="button" class="btn_write" value="상세보기"
															<c:if test="${no.cdIdx != 0}"/>>
													</a></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<div id="writebtn">
										<a href="javascript:void(0);" onclick="popup();"> <input
											type="button" class="cdwrite" value="코드등록">
										</a>
									</div>
								</div>
								<div class="paging pagination">

											<!-- 앞으로 가는 버튼 -->
											<a
												href="/code?currentPage=${page}&cdUseYn=${searchContent.cdUseYn}&startYr=${searchContent.startYr}&startMn=${searchContent.startMn}&endYr=${searchContent.endYr}&endMn=${searchContent.endMn}&cdId=${searchContent.cdId}&keyword=${searchContent.keyword}">
												<img
												src="/resources/img/log/free-icon-left-chevron-6015759.png"
												alt=" 처음" />
											</a>

											<c:choose>
												<c:when test="${currentPage > 1}">
													<a
														href="/code?currentPage=${page}&cdUseYn=${searchContent.cdUseYn}&startYr=${searchContent.startYr}&startMn=${searchContent.startMn}&endYr=${searchContent.endYr}&endMn=${searchContent.endMn}&cdId=${searchContent.cdId}&keyword=${searchContent.keyword}"><img
														src="/resources/img/log/free-icon-left-arrow-271220.png"
														alt="이전" /></a>
												</c:when>
												<c:otherwise>
													<a href="#" disabled="disabled"><img
														src="/resources/img/log/free-icon-left-arrow-271220.png"
														alt="이전" /></a>
												</c:otherwise>
											</c:choose>

											<!-- 리스트 목록 나열 -->
											<div id="number-list">
												<div class="page-btn">
													<c:forEach var="page" begin="${sharePage * 10 + 1}"
														end="${(sharePage + 1) * 10}" step="1">
														<c:if test="${page <= totalPage}">
															<a
																href="/code?currentPage=${page}&cdUseYn=${searchContent.cdUseYn}&startYr=${searchContent.startYr}&startMn=${searchContent.startMn}&endYr=${searchContent.endYr}&endMn=${searchContent.endMn}&cdId=${searchContent.cdId}&keyword=${searchContent.keyword}"
																class="pagination page-btn ${currentPage == page ? 'selected' : ''}">
																${page} </a>
														</c:if>
													</c:forEach>
												</div>
											</div>

											<!-- 뒤로 가는 버튼 -->
											<c:if test="${currentPage < totalPage}">
												<a
													href="/code?currentPage=${page}&cdUseYn=${searchContent.cdUseYn}&startYr=${searchContent.startYr}&startMn=${searchContent.startMn}&endYr=${searchContent.endYr}&endMn=${searchContent.endMn}&cdId=${searchContent.cdId}&keyword=${searchContent.keyword}">
													<img
													src="/resources/img/log/free-icon-right-arrow-271228.png"
													alt="다음" />
												</a>
											</c:if>
											<c:if test="${currentPage == totalPage}">
												<a href="javascript:void(0);" class="disabled-link"> <img
													src="/resources/img/log/free-icon-right-arrow-271228.png"
													alt="다음" />
												</a>
											</c:if>

											<a
												href="/code?currentPage=${page}&cdUseYn=${searchContent.cdUseYn}&startYr=${searchContent.startYr}&startMn=${searchContent.startMn}&endYr=${searchContent.endYr}&endMn=${searchContent.endMn}&cdId=${searchContent.cdId}&keyword=${searchContent.keyword}"><img
												src="/resources/img/log/free-icon-fast-forward-double-right-arrows-symbol-54366.png"
												alt="마지막" /></a>
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
							<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
							<script
								src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
							<script>
								window.name = 'viewPage';

								function popup() {
									var url = "<c:url value='/code/insert' />";
									var name = "팝업 테스트";
									var option = "width=800,height=550,top=200,left=450,scrollbars=yes,directories=no,location=no";
									window.open(url, name, option);
									// 팝업 창을 열고 난 후에 window.close(); 함수를 제거하면 팝업 창이 열린 상태로 유지됩니다.
								}

								function popup1(cdId, cdDtlId) {
									var url = "<c:url value='/code/' />" + cdId
											+ "/" + cdDtlId;
									var name = "팝업 테스트";
									var option = "width=800,height=600,top=200,left=450,scrollbars=yes,directories=no,location=no";
									window.open(url, name, option);
									// 팝업 창을 열고 난 후에 window.close(); 함수를 제거하면 팝업 창이 열린 상태로 유지됩니다.
								}

								function popup2(cdId) {
									var url = "<c:url value='/code/' />" + cdId;
									var name = "팝업 테스트";
									var option = "width=800,height=500,top=200,left=450,scrollbars=yes,directories=no,location=no";
									window.open(url, name, option);
									// 팝업 창을 열고 난 후에 window.close(); 함수를 제거하면 팝업 창이 열린 상태로 유지됩니다.
								}
							</script>
</body>
</html>
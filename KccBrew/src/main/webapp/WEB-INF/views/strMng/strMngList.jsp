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
<link rel="stylesheet" href="/resources/css/store/store.css" />

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
<link rel="stylesheet" href="/resources/css/store/store.css" />
<meta charset="UTF-8">

<title>점포리스트</title>
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
										<h2 class="heading">점포조회</h2>
										<!-- 점포 검색 -->
										<form action="/store" method="get">
											<table id="search-box">
												<!-- 1행 -->
												<c:set var="today" value="<%=new java.util.Date()%>" />
												<fmt:formatDate value="${today}" pattern="yyyy"
													var="nowYear" />
												<tr>

													<th>기간</th>
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

													<th>지역</th>
													<td colspan="2"><select class="tx2" name="locationCd"
														onchange="chg()">
															<option value="">지역 대분류</option>
															<option value="02">서울</option>
															<c:forEach var="list" items="${List}">
																<option value="${list.locationCd}"
																	${param.locationCd == list.locationCd ? 'selected' : ''}>${list.locationNm}</option>
															</c:forEach>
													</select></td>
													<td colspan="3"><select class="tx2"
														name="locationCdSeoul" disabled>
															<option value="">지역 소분류</option>
															<c:forEach var="list" items="${seoulList}">
																<option value="${list.locationCd}"
																	${param.locationCdSeoul == list.locationCd ? 'selected'  : ''}>${list.locationNm}</option>
															</c:forEach>
													</select></td>

												</tr>
												<!-- 3행 -->

												<tr>
													<th>점포명</th>
													<!-- Input field for URI -->
													<td colspan="2"><input type="search" name="keyword"
														placeholder="점포명 입력하세요" value="${param.keyword}"></td>
													<th>등록자ID</th>
													<td colspan="2"><input type="search" name="regUser"
														placeholder="등록자를 입력하세요" value="${param.regUser}"></td>
													<th>사용여부</th>
													<td><select name="useYn" class="tx2" id="yn"
														onchange="javascript:chg();">
														<option value="">선택</option>
															<option value="Y" ${param.useYn == 'Y' ? 'selected' : ''}>Y</option>
															<option value="N" ${param.useYn == 'N' ? 'selected' : ''}>N</option>
													</select></td>

												</tr>


												<!-- 4행 -->
												<tr>
													<td colspan="6"
														style="text-align: center; border-bottom: 0px;">
														<div class="find-btn" style="text-align: center;">
															<button type="submit" class="form-btn" id="find-btn1">검색</button>
															<button type="button" class="form-btn" id="find-btn1"
																onclick="location.href='/store';">초기화</button>
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
														<th scope="col">NO</th>
														<th scope="col">스토어이름</th>
														<th scope="col">지역구분</th>
														<th scope="col">점포연락처</th>
														<th scope="col">상세보기</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${list}" var="no" varStatus="stat">
														<tr>
															<td><c:out value="${stat.count}" /></td>
															<td>${no.storeNm}</td>
															<td>${no.locationNm}</td>
															<td>${no.storeTelNo}</td>
															<td><a href="javascript:void(0);"
																onclick="popup(${no.storeSeq});">상세보기</a></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>

										<div id="writebtn">
											<a href="javascript:void(0);" onclick="popup1();"> <input
												type="button" class="strwrite" value="점포등록">
											</a>
										</div>
										<div class="paging pagination">

											<!-- 앞으로 가는 버튼 -->
											<a
												href="/store?currentPage=${page}&useYn=${searchContent.useYn}&startYr=${searchContent.startYr}&startMn=${searchContent.startMn}&endYr=${searchContent.endYr}&endMn=${searchContent.endMn}&locationCdSeoul=${searchContent.locationCdSeoul}&locationCd=${searchContent.locationCd}&regUser=${searchContent.regUser}&storeNm=${searchContent.keyword}">
												<img
												src="/resources/img/log/free-icon-left-chevron-6015759.png"
												alt=" 처음" />
											</a>

											<c:choose>
												<c:when test="${currentPage > 1}">
													<a
														href="/store?currentPage=${page}&useYn=${searchContent.useYn}&startYr=${searchContent.startYr}&startMn=${searchContent.startMn}&endYr=${searchContent.endYr}&endMn=${searchContent.endMn}&locationCdSeoul=${searchContent.locationCdSeoul}&locationCd=${searchContent.locationCd}&regUser=${searchContent.regUser}&storeNm=${searchContent.keyword}"><img
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
																href="/store?currentPage=${page}&useYn=${searchContent.useYn}&startYr=${searchContent.startYr}&startMn=${searchContent.startMn}&endYr=${searchContent.endYr}&endMn=${searchContent.endMn}&locationCdSeoul=${searchContent.locationCdSeoul}&locationCd=${searchContent.locationCd}&regUser=${searchContent.regUser}&storeNm=${searchContent.keyword}"
																class="pagination page-btn ${currentPage == page ? 'selected' : ''}">
																${page} </a>
														</c:if>
													</c:forEach>
												</div>
											</div>

											<!-- 뒤로 가는 버튼 -->
											<c:if test="${currentPage < totalPage}">
												<a
													href="/store?currentPage=${page}&useYn=${searchContent.useYn}&startYr=${searchContent.startYr}&startMn=${searchContent.startMn}&endYr=${searchContent.endYr}&endMn=${searchContent.endMn}&locationCdSeoul=${searchContent.locationCdSeoul}&locationCd=${searchContent.locationCd}&regUser=${searchContent.regUser}&storeNm=${searchContent.keyword}">
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
												href="/store?currentPage=${page}&useYn=${searchContent.useYn}&startYr=${searchContent.startYr}&startMn=${searchContent.startMn}&endYr=${searchContent.endYr}&endMn=${searchContent.endMn}&locationCdSeoul=${searchContent.locationCdSeoul}&locationCd=${searchContent.locationCd}&regUser=${searchContent.regUser}&storeNm=${searchContent.keyword}"><img
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
	<script>
        window.name = 'viewPage';
        function popup(storeSeq) {
            var url = "store/view/" + storeSeq;
            var name = "popup test";
            var option = "width=1300, height=700, top=100, left=200, scrollbars=yes, directories=no, location=no";
            window.open(url, name, option);
            window.close();
        }
        
        function popup1() {
            var url = "store/insert";
            var name = "popup test";
            var option = "width=700, height=600, top=200, left=500, scrollbars=yes, directories=no, location=no";
            window.open(url, name, option);
            window.close();
        }
        function chg() {
            var locationSelect = document
                  .querySelector("select[name='locationCd']");
            var subLocationSelect = document
                  .querySelector("select[name='locationCdSeoul']");

            if (locationSelect.value === '02') {
               subLocationSelect.disabled = false; // 서브 로케이션 활성화
            } else {
               subLocationSelect.disabled = true; // 서브 로케이션 비활성화
            }
         }

        </script>


</body>
</html>

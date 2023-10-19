<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<link rel="stylesheet" href="${path}/resources/css/notice/notice.css"/>
<script src="${path}/resources/js/notice/notice.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<c:set var="pageSize" value="${pageSize}" />
<c:set var="totalPages" value="${totalPages}" />
<c:set var="currentPage" value="${currentPage}" />
<c:set var="notices" value="${viewAllWithCon}" />
</head>

<body>
	<div class="body-wrapper">
		<div class="table-wrapper">
			<!-- ********** 페이지 네비게이션 시작 ********** -->
			<div class="page-content-navigation">
				<h2 class="noti-head">공지사항</h2>
				<ol class="breadcrumb">
				<li>
					<div class="header-icon-background">
						<a href="/">
							<img src="<c:url value='resources/img/common/free-icon-house.png' />" alt="Check List" class="header-icon" />
						</a>
					</div>
				</li>
				<li>
					<div class="header-icon-background">
						<img src="<c:url value='resources/img/common/free-icon-arrow-right.png' />" alt="Check List" class="header-icon" />
					</div>
				</li>
				<li class="breadcrumb-home">마이페이지</li>
				<li>
					<div class="header-icon-background">
						<img src="<c:url value='resources/img/common/free-icon-arrow-right.png' />" alt="Check List" class="header-icon" />
					</div>
				</li>
				<li class="curPage">공지사항</li>
				</ol>
			</div>
			<!-- ********** 페이지 네비게이션 끝 ********** -->
			<div class="content-wrapper">
				<div class="margin-for-searchbox">
					<div class="searchBox">
						<div class="searchForm">
							<form action="/noticelistwithcon" method="GET" id="searchListForm" name="searchListForm">
								<div class="select-list">
									<select id="list-search" name="searchOption">
										<option value="all" ${param.searchOption == 'all' ? 'selected' : ''}>제목 + 내용</option>
										<option value="title" ${param.searchOption == 'title' ? 'selected' : ''}>제목</option>
										<option value="content" ${param.searchOption == 'content' ? 'selected' : ''}>내용</option>
									</select>
								</div>
									<div class="search-input">
										<input type="text" id="search-text-input" class="search-notice-box" name="searchText" value="${param.searchText}" placeholder="검색어를 입력하세요."/>
										<div id="searchText" data-searchtext="${param.searchText}"></div>
										<button type="button" id="resetBtn" onclick=resetInput()><i class="fa-solid fa-xmark"></i></button>
									</div>
								<div class="search-notice-btn">
									<button type="submit" id="searchBtn">검색</button>
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="page-info">
					<p class="pageInfo">전체 <span class="pagenum">${paging.total}</span>개 | <span class="pagenum">${paging.nowPage}</span> / <span class="pagenum">${paging.lastPage}</span> 쪽</p>
					
					<div class="down-excel">
						<button class="nowpage-down" onclick="downExcel(1)">현재 페이지 다운로드</button>
						<button class="allpage-down" onclick="downExcel(2)">전체 페이지 다운로드</button>
					</div>
				</div>
				<table class="notice-table">	
					<thead>
						<tr>
							<th>순번</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>조회수</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${empty viewAll}">
								<tr>
									<td colspan="5">
										검색어 [<strong>${param.searchText}</strong>]에 해당하는 글이 없습니다.
									</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach var="noti" items="${viewAll}">
									<tr>
										<td>
											<c:out value="${noti.noticeSeq}"/>
										</td>
										<td class="noticeTitle">
											<c:url var="toNoticeDetail" value="/noticedetail/${noti.noticeSeq}"/>
											<a href="${toNoticeDetail}">
												<span class="noti-title">
													<span class="highlighted"><c:out value="${noti.noticeTitle}"/></span>
												</span>
											</a>
										</td>
										<td>
											관리자
										</td>
										<td>
											<fmt:formatDate value="${noti.writeDate}" pattern="yyyy-MM-dd"/>
										</td>
										<td>
											<c:out value="${noti.views}"/>
										</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
				<!-- 목록 개수 조절 -->
				<div class="cellPerPage">
					<select id="cntPerPage" name="sel" onchange="selChange()">
						<option value="10"
							<c:if test="${paging.cntPerPage == 10}">selected</c:if>>10줄 보기</option>
						<option value="15"
							<c:if test="${paging.cntPerPage == 15}">selected</c:if>>15줄 보기</option>
						<option value="20"
							<c:if test="${paging.cntPerPage == 20}">selected</c:if>>20줄 보기</option>
					</select>
				</div>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<div class="btn-wrapper">
					<c:url var="toInsertNotice" value="/insertnotice"/>
						<a href="${toInsertNotice}" class="writeBtn">
							글 쓰기
						</a>
					</div>
				</sec:authorize>
				
				<!-- 페이징 -->
				<div class="pagingSection" style="display: block; text-align: center;">	
					<c:if test="${paging.nowPage > 1}">
				        <a href="/noticelistwithcon?nowPage=1&cntPerPage=${paging.cntPerPage}&searchOption=${searchOption}&searchText=${searchText}">
				        	<img src="/resources/img/asMng/free-icon-left-chevron-6015759.png" alt="첫 페이지로"/>
				        </a>
				        <a href="/noticelistwithcon?nowPage=${paging.nowPage - 1}&cntPerPage=${paging.cntPerPage}&searchOption=${searchOption}&searchText=${searchText}"> 
				        	<img src="/resources/img/asMng/free-icon-left-arrow-271220.png"	alt="이전 페이지로" />
				        </a>
				    </c:if>
				    <c:if test="${paging.nowPage == 1 && paging.total != 0}">
				    	<a style="cursor:pointer">
				        	<img src="/resources/img/asMng/free-icon-left-chevron-6015759.png" alt="첫 페이지로"/>
				        </a>
				        <a style="cursor:pointer">
				        	<img src="/resources/img/asMng/free-icon-left-arrow-271220.png"	alt="이전 페이지로" />
				        </a>
				    </c:if>
				    <div class="pageBtns">
						<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="p">
							<c:choose>
								<c:when test="${p == paging.nowPage}">
									<b>${p}</b>
								</c:when>
								<c:when test="${p != paging.nowPage}">
									<a class="otherPage" href="/noticelistwithcon?nowPage=${p}&cntPerPage=${paging.cntPerPage}&searchOption=${searchOption}&searchText=${searchText}">${p}</a>
								</c:when>
							</c:choose>
						</c:forEach>
				    </div>
				    <c:if test="${paging.nowPage < paging.lastPage}">
				        <a href="/noticelistwithcon?nowPage=${paging.nowPage + 1}&cntPerPage=${paging.cntPerPage}&searchOption=${searchOption}&searchText=${searchText}">
				        	<img src="/resources/img/asMng/free-icon-right-arrow-271228.png" alt="다음 페이지로" />
				        </a>
				        <a href="/noticelistwithcon?nowPage=${paging.lastPage}&cntPerPage=${paging.cntPerPage}&searchOption=${searchOption}&searchText=${searchText}">
				        	<img src="/resources/img/asMng/free-icon-fast-forward-double-right-arrows-symbol-54366.png" alt="마지막 페이지로"/>
				        </a>
    				</c:if>
    				<c:if test="${paging.nowPage == paging.lastPage}">
				    	<a style="cursor:pointer">
				        	<img src="/resources/img/asMng/free-icon-right-arrow-271228.png" alt="다음 페이지로" />
				        </a>
				        <a style="cursor:pointer">
				        	<img src="/resources/img/asMng/free-icon-fast-forward-double-right-arrows-symbol-54366.png" alt="마지막 페이지로"/>
				        </a>
				    </c:if>
				</div>
			</div>
		</div>
	</div>
	<div id="nowPage" data-notice-nowpage = "${paging.nowPage}"></div>
	<div id="totalPage" data-notice-totalpage = "${paging.total}"></div>
	<input type="hidden" name="nowPage" value="1">
	<script>
	function selChange() {
		var sel = document.getElementById('cntPerPage').value;
		location.href="/noticelistwithcon?nowPage=${paging.nowPage}&cntPerPage=" + sel + "&searchOption=${searchOption}&searchText=${searchText}";
	}
</script>
</body>
</html>
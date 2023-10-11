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
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<c:set var="pageSize" value="${pageSize}" />
<c:set var="totalPages" value="${totalPages}" />
<c:set var="currentPage" value="${currentPage}" />

</head>

<body>
	<div class="body-wrapper">
		<div class="table-wrapper">
			<!-- ********** 페이지 네비게이션 시작 ********** -->
			<div class="page-content-navigation">
				<ol class="breadcrumb">
					<li class="breadcrumb-home">공지사항</li>
					<li class="right-arrow">
						<div class="header-icon-background2">
							<img
								src="<c:url value='resources/img/asMng/free-icon-right-arrow-271228.png' />"
								alt="Check List" class="header-icon" />
						</div>
					</li>
					<li>공지사항</li>
				</ol>
			</div>
			<!-- ********** 페이지 네비게이션 끝 ********** -->
			<div class="content-wrapper">
				<h2>공지사항</h2>
				<div class="searchBox">
					<div class="searchForm">
						<div class="select-list">
							<select id="list-search">
								<option value="">제목 + 내용</option>
								<option value="">제목</option>
								<option value="">내용</option>
								<option value="">작성자</option>
							</select>
						</div>
						<div class="search-input">
							<input type="text" class="search-notice-box"/>
						</div>
						<div class="search-notice-btn">
							<button type="submit">검색</button>
						</div>
						
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
					<c:forEach var="noti" items="${viewAll}">
					<tbody>
						<tr>
							<td>
								<c:out value="${noti.noticeSeq}"/>
							</td>
							<td class="noticeTitle">
								<c:url var="toNoticeDetail" value="/noticedetail/${noti.noticeSeq}"/>
								<a href="${toNoticeDetail}">
									<c:out value="${noti.noticeTitle}"/>
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
					</tbody>
					</c:forEach>
				</table>
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
					<c:if test="${paging.startPage > 1}">
				        <a href="/noticelist?nowPage=1&cntPerPage=${paging.cntPerPage}">
				        	<img src="/resources/img/asMng/free-icon-left-chevron-6015759.png" alt="첫 페이지로"/>
				        </a>
				        <a href="/noticelist?nowPage=${paging.startPage - 1}&cntPerPage=${paging.cntPerPage}">
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
									<a class="otherPage" href="/noticelist?nowPage=${p}&cntPerPage=${paging.cntPerPage}">${p}</a>
								</c:when>
							</c:choose>
						</c:forEach>
				    </div>
				    <c:if test="${paging.endPage < paging.lastPage}">
				        <a href="/noticelist?nowPage=${paging.endPage + 1}&cntPerPage=${paging.cntPerPage}">
				        	<img src="/resources/img/asMng/free-icon-right-arrow-271228.png" alt="다음 페이지로" />
				        </a>
				        <a href="/noticelist?nowPage=${paging.lastPage}&cntPerPage=${paging.cntPerPage}">
				        	<img src="/resources/img/asMng/free-icon-fast-forward-double-right-arrows-symbol-54366.png" alt="마지막 페이지로"/>
				        </a>
    				</c:if>
				</div>
			</div>
		</div>
	</div>
	<script>
	function selChange() {
		var sel = document.getElementById('cntPerPage').value;
		location.href="noticelist?nowPage=${paging.nowPage}&cntPerPage="+sel;
	}
	
	
</script>
</body>
</html>
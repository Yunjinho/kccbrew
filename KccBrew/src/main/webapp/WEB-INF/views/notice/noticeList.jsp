<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<link rel="stylesheet" href="${path}/resources/css/notice/notice.css"/>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>
<body>
	<div class="body-wrapper">
		<div class="table-wrapper">
			<!-- ********** 페이지 네비게이션 시작 ********** -->
			<div class="page-content-navigation">
				<ol class="breadcrumb">
					<li class="breadcrumb-home">공지사항</li>
					<li class="right-arrow">
						<div class="header-icon-background">
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
					<c:forEach var="noti" items="${noticeList}">
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
				<div class="btn-wrapper">
				<c:url var="toInsertNotice" value="/insertnotice"/>
					<a href="${toInsertNotice}" class="writeBtn">
						글 쓰기
					</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
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
<title>공지 상세</title>
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
					<div id="noticeDtlTitle">
						<c:out value="${noticeVo.noticeTitle}"/>	
					</div>
					<div id="noticeDtlInfo">
						<span class="info-name">작성자</span> &nbsp;<c:out value="${noticeVo.writerName}"/>
						&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
						<span class="info-name">작성일</span> &nbsp; <fmt:formatDate value="${noticeVo.writeDate}"	pattern="yyyy-MM-dd" />
						&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
						<span class="info-name">조회수</span> &nbsp;<c:out value="${noticeVo.views}"/>
					</div>
					<div id="noticeDtlContent">
						<c:out value="${noticeVo.noticeContent}"/>
					</div>
				<div class="btn-wrapper">
					<c:url var="toList" value="/noticelist"/>
					<a href="${toList}" class="toListBtn">
						목록
					</a>
					<c:url var="update" value="/update"/>
					<a href="${update}" class="updateBtn">
						수정
					</a>
					<c:url var="delete" value="/delete/${noticeVo.noticeSeq}"/>
					<a href="${delete}" class="deleteBtn" onclick="return confirm('정말로 삭제하시겠습니까?');">
						삭제
					</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
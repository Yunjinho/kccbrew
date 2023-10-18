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
<title>공지 상세</title>
<link rel="stylesheet" href="${path}/resources/css/notice/notice.css"/>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

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
							<img src="<c:url value='${path}/resources/img/common/free-icon-house.png' />" alt="Check List" class="header-icon" />
						</a>
					</div>
				</li>
				<li>
					<div class="header-icon-background">
						<img src="<c:url value='${path}/resources/img/common/free-icon-arrow-right.png' />" alt="Check List" class="header-icon" />
					</div>
				</li>
				<li class="breadcrumb-home">마이페이지</li>
				<li>
				<div class="header-icon-background">
					<img src="<c:url value='${path}/resources/img/common/free-icon-arrow-right.png' />" alt="Check List" class="header-icon" />
				</div>
				</li>
				<li class="curPage">공지사항</li>
				</ol>
			</div>
			<!-- ********** 페이지 네비게이션 끝 ********** -->
			<div class="content-wrapper">
					<div id="noticeDtlTitle">
						<c:out value="${noticeVo.noticeTitle}"/>	
					</div>
					<div id="noticeDtlInfo">
						<span class="info-name">작성자</span> &nbsp;관리자
						&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
						<span class="info-name">작성일</span> &nbsp; <fmt:formatDate value="${noticeVo.writeDate}"	pattern="yyyy-MM-dd" />
						&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
						<span class="info-name">수정일</span> &nbsp; <fmt:formatDate value="${noticeVo.modDate}"	pattern="yyyy-MM-dd" />
						&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
						<span class="info-name">조회수</span> &nbsp;<c:out value="${noticeVo.views}"/>
					</div>
					<div id="noticeImgContent">
						<c:forEach var="imgList" items="${imgList}">
							<div style="background-image:url('/${imgList.localSavePath}${imgList.fileDetailServerName}')" id="notice-img"></div>
						</c:forEach>
					</div>
					<pre id="noticeDtlContent"><c:out value="${noticeVo.noticeContent}"/></pre>
				<div class="btn-wrapper">
					<c:url var="toList" value="/noticelist"/>
					<a href="${toList}" class="toListBtn">
						목록
					</a>
					<sec:authorize access="hasRole('ROLE_ADMIN')">
						<c:url var="update" value="/notice/update/${noticeVo.noticeSeq}"/>
						<a href="${update}" class="updateBtn">
							수정
						</a>
						
						<c:url var="delete" value="/delete/${noticeVo.noticeSeq}"/>
						<a href="${delete}" class="deleteBtn" onclick="return confirm('정말로 삭제하시겠습니까?');">
							삭제
						</a>
					</sec:authorize>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
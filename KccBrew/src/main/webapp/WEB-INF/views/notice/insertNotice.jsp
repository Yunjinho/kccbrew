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
<style>
	@font-face {
		font-family: 'Noto Sans';
		font-style: normal;
		src: url("${path}/resources/fonts/NotoSansKR-Regular.ttf")
	}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="${path}/resources/js/notice/notice.js"></script>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<link rel="stylesheet" href="${path}/resources/css/notice/notice.css"/>
</head>
<body>
	<div id="page-mask">
		<div id="page-container" class="">
			<div id="page-content" class="clearfix">
				<div id="main-content-wrap">
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
				<form method="POST" id="insertNoticeForm" action="/insertnoticeform" name="insertNoticeForm" enctype="multipart/form-data">
					<table class="insert-notice-table">
						<tbody>
							<tr>
								<th>글 제목</th>
								<td><input type="text" name="noticeTitle" placeholder="제목을 입력하세요" autocomplete="off" required></td>
							</tr>
							<tr>
								<th>글 내용</th>
								<td>
									<textarea id="notice-content" name="noticeContent" maxlength="1000" placeholder="최대 1000자까지 입력 가능합니다." required></textarea>
									<div id="count-text">
										<span id="count">0</span>/1000
									</div>
								</td>
							</tr>
							<tr>
								<th>첨부 파일</th>
								<td>
									<div style="display:flex;">
										<div id="fileCount" style="margin: 0px 5px 0px 0px;">업로드할 파일을 선택해주세요.(최대 3개, 허용 확장자: jpg, jpeg, png)</div>
										<div class="file-box">
											<div class="file-label">
												<label for="fileInput" id="fileUploadBtn">파일 선택 </label>
												<input type="file" id="fileInput" name="noticeImg" onchange="addFile(this);" multiple accept=".jpg, .jpeg, .png">
											</div>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<th>파일 목록</th>
								<td>
									<div class="file-list"></div>
								</td>
							</tr>
							<tr>
								<th>파일 <br>미리보기</th>
								<td>
									<div class="img-list"></div>
								</td>
							</tr>
						</tbody>
					</table>
					<input type="hidden" id="writerName" name="writerName" value="<c:out value="${noticeVo.writerName}"/>">
					<div class="modButtons">
						<button type="submit" id="insertNoticeBtn">등록</button>
						<c:url var="cancel" value="/noticelist"></c:url>
						<a href="${cancel}">
							<button type="button" class="cancel">취소</button>
						</a>
					</div>
				</form>
			</div>
			</div>
			</div>
			</div>
			</div>
			

</body>
</html>

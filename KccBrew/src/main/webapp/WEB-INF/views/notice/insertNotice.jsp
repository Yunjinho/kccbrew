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

<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
<script src="${path}/resources/summerNote/summernote-lite.js"></script>
<script src="${path}/resources/summerNote/lang/summernote-ko-KR.js"></script>
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
				<h2>공지사항 등록</h2>
				<form method="post" id="insertNoticeForm" name="insertNoticeForm" enctype="multipart/form-data">
					<table class="insert-notice-table">
						<tr>
							<th>글 제목</th>
							<td><input type="text" name="noticeTitle" placeholder="공지 제목" required></td>
						</tr>
						<tr>
							<th>글 내용</th>
							<td>
								<textarea id="notice-content" name="noticeContent"  maxlength="1000" placeholder="1000자까지 입력할 수 있습니다." required></textarea>
								<div id="count-text">
									<span id="count">0</span>/1000
								</div>
							</td>
						</tr>
						<tr>
							<th>첨부 파일</th>
							<td><input type="file" name="noticeImg"></td>
						</tr>
					</table>
					<div class="modButtons">
						<button type="button" id="insertNoticeBtn">등록</button>
						<c:url var="cancel" value="/noticelist"></c:url>
						<a href="${cancel}">
							<button type="button" class="cancel">취소</button>
						</a>
					</div>
				</form>
				<div class="test">
					<textarea class="summernote" name="editordata"></textarea>
				</div>
			</div>
		</div>
	</div>
	<script>
	$(document).ready(function() {
		$('#notice-content').on('keyup', function() {
			var textCount = $(this).val().length;
			$('#count').html(textCount);
			$('#count').css('color', 'red');
			
            if(textCount > 1000) {
                $(this).val($(this).val().substring(0, 1000));
                $('#count').html("1000");
            }
		});
	});
	
	$('.summernote').summernote({
		  height: 150,
		  lang: "ko-KR"
		});
	</script>
</body>
</html>
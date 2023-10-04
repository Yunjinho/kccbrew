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

<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
<link rel="stylesheet" href="${path}/resources/css/notice/notice.css"/>

<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.18/lang/summernote-ko-KR.min.js"></script>
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
								<div class="summer">
									<textarea class="summernote" id="notice-content" name="noticeContent" maxlength="1000" required></textarea>
								</div>
								<!-- <div id="count-text">
									<span id="count">0</span>/1000
								</div> -->
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
			</div>
		</div>
	</div>
	<script>
	$(document).ready(function() {
		$('.summernote').summernote({
			  // 에디터 높이
			  height: 300,
			  width: 1112,
			  // 에디터 한글 설정
			  lang: "ko-KR",
			  // 에디터에 커서 이동 (input창의 autofocus라고 생각하시면 됩니다.)
			  focus : true,
			  toolbar: [
				    // 글꼴 설정
				    ['fontname', ['fontname']],
				    // 글자 크기 설정
				    ['fontsize', ['fontsize']],
				    // 굵기, 기울임꼴, 밑줄,취소 선, 서식지우기
				    ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
				    // 글자색
				    ['color', ['forecolor','color']],
				    // 표만들기
				    ['table', ['table']],
				    // 글머리 기호, 번호매기기, 문단정렬
				    ['para', ['ul', 'ol', 'paragraph']],
				    // 줄간격
				    ['height', ['height']],
				    // 그림첨부, 링크만들기, 동영상첨부
				    ['insert',['picture','link','video']],
				    // 코드보기, 확대해서보기, 도움말
				    ['view', ['codeview','fullscreen', 'help']]
				  ],
			// 추가한 글꼴
			fontNames: ['Noto Sans KR', 'Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋음체','바탕체'],
			fontNamesIgnoreCheck: ['Noto Sans KR', 'Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋음체','바탕체'],
			// 추가한 폰트사이즈
			fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72'],
			
			callbacks: {
				onImageUpload : function(files){
					sendFile(files[0], this);
				}
			}
		});
		
		function sendFile(file, editor){
			var data = new FormData();
			data.append("file", file);
			console.log(file);
			$.ajax ({
				data : data,
				type : "POST",
				url : "SummerNoteImageFile",
				contentType : false,
				processData : false,
				success : function(data){
					console.log(data);
					console.log(editor);
					$(editor).summernote("insertImage",data.url);
				}
			});
		}
		
		/* $('.summernote').summernote('code', '');
		
		$('.summernote').on('summernote.keyup', function() {
			var textCount = $(this).summernote('code').length;
			$('#count').html(textCount);
			$('#count').css('color', 'red');
			
            if(textCount > 1000) {
            	$(this).summernote('code', $(this).summernote('code').substring(0, 1000));
                $('#count').html("1000");
            }
		});	 */	
	});
	</script>
</body>
</html>
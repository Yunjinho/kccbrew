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
<link rel="stylesheet" href="/resources/css/asMng/asReceipt.css" />
<link rel="stylesheet" href="/resources/css/asMng/content-template.css" />

<!-- js -->
<script src="https://code.jquery.com/jquery-3.6.0.slim.js"
	integrity="sha256-HwWONEZrpuoh951cQD1ov2HUK5zA5DwJ1DNUXaM6FsY="
	crossorigin="anonymous"></script>
<script src="<c:url value="/resources/js/asMng/asMod.js"/>"></script>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
.preview-container {
	max-width: 100%; /* 원하는 최대 너비 설정 */
	max-height: 150px; /* 원하는 최대 높이 설정 */
	overflow: hidden; /* 넘친 부분 숨김 */
}

.preview {
	max-width: 100%; /* 부모 요소에 맞게 이미지 크기 조정 */
	height: auto; /* 가로세로 비율 유지 */
}
</style>
</head>
<body>
	<div id="page-mask">
		<div id="page-container" class="">
			<div id="page-content" class="clearfix">
				<div id="page-content-wrap">
					<div class="page-content-navigation">
						<ol class="breadcrumb">
							<li class="breadcrumb-home"><a href="#">AS 관리</a></li>
							<li>
								<div class="header-icon-background">
									<img
										src="<c:url value='resources/img/asMng/free-icon-right-arrow-271228.png' />"
										alt="Check List" class="header-icon" />
								</div>
							</li>
							<li><a href="<c:url value='/as-receipt' />">AS 수정</a></li>
						</ol>
					</div>
					<div id="region-main">
						<div role="main">
							<span id="maincontent"></span>
							<div class="user-past">
								<div id="content">
									<h2 class="heading">AS 접수</h2>
									<hr>
									<!-- AS 접수-->
									<form action="/as-mod" method="post" id="receipt-form"
										enctype="multipart/form-data">
										<h6>
											<img src="/resources/img/asMng/check.png" class="tag-image">희망
											신청일
										</h6>
										<div>
											<div>
												<fmt:parseDate value="${asDetailInfo.wishingStartDate}"
													pattern="yyyy/MM/dd" var="wishingStartDate" />
												<fmt:formatDate value="${wishingStartDate}"
													pattern="yyyy-MM-dd" var="formattedStartDate" />
												<input type="date" value="${formattedStartDate}"
													name="wishingStartDate">
											</div>
											<div style="font-size: 2em; text-align: center;">~</div>
											<div>
												<fmt:parseDate value="${asDetailInfo.wishingEndDate}"
													pattern="yyyy/MM/dd" var="wishingEndDate" />
												<fmt:formatDate value="${wishingEndDate}"
													pattern="yyyy-MM-dd" var="formattedEndDate" />
												<input type="date" value="${formattedEndDate}"
													name="wishingEndDate">
											</div>
										</div>

										<h6>
											<img src="/resources/img/asMng/check.png" class="tag-image">점포
											정보
										</h6>
										<div>
											<div style="font-size: 1.5em; flex: 0.5; text-align: center;">
												점포명</div>
											<div style="flex: 0.5;">
												<input type="text" name="storeNm"
													value="${asDetailInfo.storeNm}" readonly>
											</div>
											<div style="font-size: 1.5em; flex: 0.5; text-align: center;">
												점포 주소</div>
											<div style="flex: 2;">
												<input name="storeAddr"
													value="${asDetailInfo.storeAddr},${asDetailInfo.storeAddrDtl}"
													style="max-width: initial; width: 100%;" type="text"
													readonly>
											</div>
										</div>

										<h6>
											<img src="/resources/img/asMng/check.png" class="tag-image">AS
											신청 장비
										</h6>
										<div>
											<div>
												<select name="machineCd" required="required">
													<option value="">장비 코드</option>
													<c:forEach var="list" items="${machineCd}">
														<option value="${list.grpCdDtlId}">
															${list.grpCdDtlNm}</option>
													</c:forEach>
												</select>
											</div>
										</div>

										<h6>
											<img src="/resources/img/asMng/check.png" class="tag-image">AS
											신청 내용
										</h6>
										<div>
											<div>
												<textarea name="asContent" required="required">${asDetailInfo.asContent}</textarea>
											</div>
										</div>

										<div class="flex-label-div">
											<div>
												<h6>
													<img src="/resources/img/asMng/check.png" class="tag-image">사진
													첨부 파일
												</h6>
											</div>
											<div>
												<div class="in-decrease">
													<span onclick="addFile()">파일 추가</span>
												</div>
												<div class="in-decrease">
													<span onclick="removeFile()">파일 제거</span>
												</div>
											</div>
										</div>
										<div class="file-upload-div">
												<div class="image-slider">
													<div class="preview-container">
														<img id="preview0" src="#" class="preview"
													alt="선택된 이미지가 없습니다">
														<!-- 추가 이미지 추가 -->
													</div>
													<button id="prevButton" class="slider-button">이전</button>
													<button id="nextButton" class="slider-button">다음</button>
												</div>
											
											<div class="input-container">
												<input type="file" id="imgFile0" name="imgFile" value=""
													onchange="imgTypeCheck(this, 0)">
											</div>
									</div>
										<div>
											<div>
												<button type="submit" class="form-btn">접수</button>
											</div>
											<div>
												<button onclick="history.back()">취소</button>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
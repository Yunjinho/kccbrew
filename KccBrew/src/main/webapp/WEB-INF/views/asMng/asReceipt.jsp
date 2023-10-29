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
<script src="<c:url value="resources/js/asMng/asReceipt.js"/>"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
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
							<li><a href="<c:url value='/as-receipt' />">AS 접수</a></li>
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
									<form action="/receipt" method="post" id="receipt-form"
										enctype="multipart/form-data">
									
										<h6>
											<img src="/resources/img/asMng/check.png" class="tag-image">희망
											신청일
										</h6>
										<div>
											<div>
												<input type="date" value="" name="wishingStartDate" id="wishingStartDate">
											</div>
											<div style="font-size: 2em; text-align: center;">~</div>
											<div>
												<input type="date" value="" name="wishingEndDate" id="wishingEndDate">
											</div>
										</div>

										<h6>
											<img src="/resources/img/asMng/check.png" class="tag-image">점포
											정보
										</h6>
										<div>
											<div
												style="align-self: center; flex: 0.5; text-align: center;">
												점포명</div>
											<div style="flex: 0.5;">
												<select id="storeNm" onchange="changeStr()" name="storeNm"
													required="required"
													style="max-width: initial; width: 100%;">
													<option value="">점포 명</option>
													<c:forEach var="list" items="${strInfo}">
														<option value="${list.storeSeq}">${list.storeNm}
														</option>
													</c:forEach>
												</select>
												 <input type="hidden" id="selectedStoreSeq" name="storeSeq">
											</div>
											<div
												style="align-self: center; flex: 0.5; text-align: center;">
												점포 주소</div>
											<div style="flex: 2;">
												<select name="storeAddr" required="required"
													style="max-width: initial; width: 100%;" readonly>
													<option value="">점포 주소</option>
													<c:forEach var="list" items="${strInfo}">
														<option>${list.storeAddr},${list.storeAddrDtl}</option>
													</c:forEach>
												</select>
											</div>
										</div>

										<h6>
											<img src="/resources/img/asMng/check.png" class="tag-image">AS
											신청 장비
										</h6>
										<div>
											<div>
												<select id="machineCd" name="machineCd" required="required">
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
												<textarea name="asContent" required="required"></textarea>
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
											<div>
												<input type="file" name="imgFile" value=""
													onchange="imgTypeCheck(this)">
											</div>
											<div>
												<input type="file" name="imgFile" value=""
													onchange="imgTypeCheck(this)">
											</div>
											<div>
												<input type="file" name="imgFile" value=""
													onchange="imgTypeCheck(this)">
											</div>
										</div>
										<div>
											<div>
												<button type="button" class="form-btn"
													onclick="performSubmit();">접수</button>
											</div>
											<div>
												<a href="/as-list" class="form-btn">취소</a>
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
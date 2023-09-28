<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="/resources/css/code/cdMngDtl.css" />
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<meta charset="UTF-8">
<title>파일정보</title>
</head>
<body>
	<section id="notice" class="notice">
		<div class="container2">
			<div class="category">파일정보</div>
			<hr style="border: solid 1.2px; width: 97%;">

			<table id="search-box">
				<tr>
					<th>파일서버명</th>
					<td colspan="3">${vo.fileServerNm}</td>
				</tr>
				<tr>
					<th>파일원본명</th>
					<td colspan="3">${vo.fileOriginNm}</td>
				</tr>
				<tr>
					<th>파일형식</th>
					<td>${vo.fileFmt}</td>
					<th>분류</th>
					<td>${vo.grpCdDtlNm}</td>
				</tr>
				<tr>
					<th>등록자ID</th>
					<td>${vo.regUser}</td>
					<th>등록일자</th>
					<td><fmt:formatDate value="${vo.regDttm}" pattern="yyyy MM/dd" /></td>
				</tr>
				<tr>
					<th>파일위치</th>
					<td colspan="3">${vo.storageLocation}</td>
				</tr>
			</table>
			<div style="text-align: center;">
			<img src="/${vo.storageLocation}${vo.fileServerNm}" border="0"
						style="position: relative; width: 300px; top: calc(50% - 200px); height: 400px;">
			</div>
			<form name="down" action="/file/download" method="post">
				<input type="hidden" name="fileServerNm" value="${vo.fileServerNm}">
				<input type="hidden" name="fileOriginNm" value="${vo.fileOriginNm}">
				<input type="hidden" name="storageLocation"
					value="${vo.storageLocation}">
				<button class="form-btn" id="download" onclick="down.submit()" style="float: right;">다운로드</button>
			</form>
		</div>
	</section>
</body>
</html>
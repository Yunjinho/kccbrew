<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="/resources/css/code/codedetail.css" />
<meta charset="UTF-8">
<title>코드정보</title>
</head>
<body>
	<section id="notice" class="notice">
		<section class="category">
			<span>코드관리 > 그룹코드정보</span>
		</section>
		<div class="container2">
			<div>그룹상세코드</div>
			<hr style="border: solid 1.2px; width: 97%;">
			<table class="table text-center">
				<tr>
					<th>그룹코드ID</th>
					<td>${codeMng.cdId}</td>
					<th>그룹코드이름</th>
					<td>${codeMng.cdNm}</td>
					<th>사용여부</th>
					<td>${codeMng.cdUseYn}</td>
				</tr>
				<tr>
					<th>최초등록일</th>
					<td>${codeMng.cdRegDttm}</td>
					<th>최초등록자ID</th>
					<td>${codeMng.cdRegUser}</td>
				</tr>
				<tr>
					<th>최종수정일</th>
					<td>${codeMng.cdModDttm}</td>
					<th>최종수정자ID</th>
					<td>${codeMng.cdModUser}</td>
				</tr>
			</table>
			<c:url value="/code/update/${codeMng.cdId}" var="updateUrl" />
			<div class="updatecancle">
				<a href="${updateUrl}"> <input type="button" class="update"
					value="수정">
				</a> <input type="button" class="update" value="취소"
					onclick="window.close()">
			</div>
		</div>
	</section>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<link rel="stylesheet" href="/resources/css/userMng/userMngList.css" />
<!-- notoSans -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<head>
<meta charset="UTF-8">
<title>AS 이력</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
</head>
<body>
	<div class="search-store">
		<div class="search-store-content">
			<div id="strTable" style="text-align: -webkit-center; margin-top:20px">
				<div style="text-align: -webkit-center; margin-top:20px">
					<div> <h2><span><strong>AS 처리 이력 </strong></span></h2></div>
				</div>
				<table class="table">
					<thead>
						<tr>
							<th>구분</th>
							<th>AS 단계</th>
							<th>날짜(최신순)</th>
							<th>행위</th>
							<th>행위자</th>
							<th>행위자ID</th>
						</tr>
					</thead> 
					<tbody>
						<c:forEach var="list" items="${historyList}">
							<tr>
								<td>${list.rowNumber}</td>
								<td>${list.asProcessNm}</td>
								<td>${list.asProcessDate}</td>
								<td>${list.asStatus}</td>
								<td>${list.userTypeNm}</td>
								<td>${list.asPerfromer}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>
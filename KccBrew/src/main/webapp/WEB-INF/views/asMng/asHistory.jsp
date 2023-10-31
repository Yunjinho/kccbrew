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
<style>
.table thead tr th {
    border-top: 1px solid #000;
    border-bottom: 1px solid #d6d7d9;
    background: black;
    color: white;
    text-align: center;
    font-weight: unset;
    border-collapse: unset;
}

.table tbody td {
    border-bottom: 1px solid #e6e7e8;
    text-align: center;
}

</style>
<body>
	<div class="search-store">
		<div class="search-store-content">
			<div id="strTable" style="text-align: -webkit-center; margin-top:20px">
				<div style="text-align: -webkit-center; margin-top:20px">
					<div> <h2 style="    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;"><img src="<c:url value="/resources/img/list-icon.png"/>" style="width:50px;"><span><strong>AS 처리 이력 </strong></span></h2></div>
				</div>
				<table class="table">
					<thead>
						<tr>
							<th>구분</th>
							<th>AS업무</th>
							<th>날짜</th>
							<th>AS상태</th>
							<th>처리자구분</th>
							<th>처리자ID</th>
						</tr>
					</thead> 
					<tbody>
						<c:forEach var="list" items="${historyList}">
							<tr>
								<td>${list.rowNumber}</td>
								<td>${list.asProcessNm}</td>
								<fmt:parseDate var="now" value="${list.asProcessDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
								<td><fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm" /></td>
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
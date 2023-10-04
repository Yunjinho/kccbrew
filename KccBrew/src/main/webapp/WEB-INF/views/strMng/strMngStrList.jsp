<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet"
    href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="/resources/css/code/cdMngDtl.css" />
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<meta charset="UTF-8">
<title>점포 조회</title>
</head>
<body>
	<table>
		<thead>
			<tr> 
				<th>AS 번호</th>
				<th>신청일</th>
				<th>AS 상태</th> 
				<th>점포 명</th>
				<th>점포 주소</th>
				<c:if test="${sessionScope.user.userTypeCd != '02'}"><th>기사 재배정 신청</th></c:if>
				<c:if test="${sessionScope.user.userTypeCd eq '01' }"><th>재접수 여부</th></c:if>
				<th>상세 조회</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="list" items="${ASList}">
				<tr>
					<td><c:out value="${list.asInfoSeq}" /></td>
					<td><c:out value="${list.regDttm}" /></td>
					<td>
						<c:choose>
							<c:when test='${list.resultReapply eq "Y"}'>재접수</c:when>
							<c:otherwise>
								<c:out value="${list.asStatusNm}" />
							</c:otherwise>														
						</c:choose>
					</td>
					<td><c:out value="${list.storeNm}" /></td>
					<td><c:out value="${list.storeAddr}" /></td>
					<c:if test="${sessionScope.user.userTypeCd != '02'}">
						<td>
							<c:choose>
								<c:when test="${list.reassign =='Y'}"><c:out value="${list.reassign}"></c:out></c:when>
								<c:otherwise>-</c:otherwise>
							</c:choose>
						</td>
					</c:if>
					<c:if test="${sessionScope.user.userTypeCd eq '01'}">
					<td>
						<c:choose>
							<c:when test='${list.resultReapply =="Y"}'><c:out value="${list.resultReapply}"></c:out></c:when>
							<c:otherwise>-</c:otherwise>
						</c:choose>
					</td>
					</c:if>
					<td><a href="#" onclick="selectAsDetail(${list.asInfoSeq},${list.asAssignSeq})"class="form-btn">조회</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>

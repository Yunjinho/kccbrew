<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.js"></script> 
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
<script>
window.onload=function(){
	$("#mngBtn").click(function(){
		alert("");
		$("#mngSeach").sumbit();
	})
}
</script>
</head>
<body>
	<div class="wrap">
		<!-- 관리자 로그인 후 리스트 -->
		<c:if test="${userTypeCd eq '01'}">
			<div>
				<form action="/searchAsList" method="get" id="mngSeach">
					<input type="text" name="asInfoSeq" placeholder="AS 번호">
					<input type="date" name="startDate" placeholder="시작일">
					<input type="date" name="endDate" placeholder="종료일">
					<input type="text" name="userId" placeholder="사용자 아이디">
					<input type="text" name="storeNm" placeholder="점포 이름">
					<input type="text" name="storeAddr" placeholder="점포 주소">
					<select name="machineCd">
						<option>장비 구분</option>
						<c:forEach var="empCd" items="${machineCd}">
							<option value="${empCd.grpCdDtlId}">
								${empCd.grpCdDtlNm}
							</option>
						</c:forEach>
					</select>
					<select name="statusCd">
						<option value="">AS 상태</option>
						<c:forEach var="asCd" items="${asStatusCd}">
							<option value="${asCd.grpCdDtlId}">
								${asCd.grpCdDtlNm}
							</option>
						</c:forEach>
					</select>
					<select name="locationCd">
						<option value="지역">지역</option>
						<c:forEach var="loCd" items="${locationCd}">
							<option value="${loCd.grpCdDtlId}">
								${loCd.grpCdDtlNm}
							</option>
						</c:forEach>
					</select>
				</form>
				<input type="button" id="mngBtn">검색
			</div>	
		</c:if>
<!-- 점주 로그인 후 리스트 -->
		<c:if test="${userTypeCd eq '02'}">
			<div>
				<form id="strSearch">
					<input type="text" name="asInfoSeq" placeholder="AS 접수 번호">				
					<input type="date" name="startDate">
					<input type="date" name="endDate">
					<select name="machineCd">
						<option>장비 구분</option>
						<c:forEach var="empCd" items="${machineCd}">
							<option value="${empCd.grpCdDtlId}">
								${empCd.grpCdDtlNm}
							</option>
						</c:forEach>
					</select>
					<select name="statusCd">
						<option value="">AS 상태</option>
						<c:forEach var="asCd" items="${asStatusCd}">
							<option value="${asCd.grpCdDtlId}">
								${asCd.grpCdDtlNm}
							</option>
						</c:forEach>
					</select>
					<button type="submit"></button>
				</form>
			</div>	
		</c:if>
		<!-- 수리기사 로그인 후 리스트 -->
		<c:if test="${userTypeCd eq 03}">
			<div>
				<form id="mechaSearch">
					<input type="text" name="asInfoSeq">				
					<input type="date" name="startDate">
					<input type="date" name="endDate">
					<input type="text" name="storeNm">
					<input type="text" name="storeAddr">
					<select name="statusCd">
						<option value="">AS 상태</option>
						<c:forEach var="asCd" items="${asStatusCd}">
							<option value="${asCd.grpCdDtlId}">
								${asCd.grpCdDtlNm}
							</option>
						</c:forEach>
					</select>
					<select name="locationCd">
						<option value="지역">지역</option>
						<c:forEach var="loCd" items="${locationCd}">
							<option value="${loCd.grpCdDtlId}">
								${loCd.grpCdDtlNm}
							</option>
						</c:forEach>
					</select>
				</form>
			</div>	
		</c:if>		
	</div>
</body>
</html>
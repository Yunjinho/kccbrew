<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="java.time.LocalDateTime"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/resources/css/asMng/asList.css" />
<link rel="stylesheet" href="/resources/css/asMng/content-template.css" />


<meta charset="UTF-8">

<title>코드리스트</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>

</head>
<body>
	<div id="page-mask">
		<div id="page-container" class="">
			<div id="page-content" class="clearfix">
				<div id="page-content-wrap">
					<!-- ********** 페이지 네비게이션 시작 ********** -->
					<div class="page-content-navigation">
						<h2 class="heading">코드조회</h2>
						<ol class="breadcrumb">
									<li>
								<div class="header-icon-background">
								<a href="/">
									<img
											src="<c:url value='/img/common/free-icon-house.png' />"
											alt="Check List" class="header-icon" />
										</a>
								</div>
							</li>
							<li>
									<div class="header-icon-background">
										<img
											src="<c:url value='/img/common/free-icon-arrow-right.png' />"
											alt="Check List" class="header-icon" />
									</div>
								</li>
							<li class="breadcrumb-home"><a href="#">코드 관리</a></li>
							<li>
									<div class="header-icon-background">
										<img
											src="<c:url value='/img/common/free-icon-arrow-right.png' />"
											alt="Check List" class="header-icon" />
									</div>
								</li>
							<li><a href="<c:url value='/code' />">코드 조회</a></li>
						</ol>
					</div>
					<!-- ********** 페이지 네비게이션 끝 ********** -->
					<div id="region-main">
						<div role="main">
							<span id="maincontent"></span>
							<div class="user-past">

								<!-- ********** 점포 리스트 조회 ********** -->
								<div id="content">
									<!-- 점포 검색 -->

									<div id="logTable" style="margin-top: 50px;">

										<div style="width: 48%; float: left;">
											<h3>그룹코드</h3>
											<div id="grp">
												<%@ include file="grpCdMngDtl.jsp"%>
											</div>
											<table class="left">
												<thead>
													<tr>
														<th scope="col">그룹코드ID</th>
														<th scope="col">그룹코드이름</th>
														<th scope="col">사용여부</th>

													</tr>
												</thead>
												<tbody>
													<c:forEach items="${list}" var="no" varStatus="loop">
														<tr>
															<td>${no.cdId}</td>
															<td><a href="javascript:void(0);"
																data-cdId="${no.cdId}"
																onclick="cdSearch(this.getAttribute('data-cdId'));">${no.cdNm}</a></td>
															<td>${no.cdUseYn}</td>	

														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
										<hr
											style="display: inline-block; height: 700px; width: .05vw; border-width: 0; color: #D3D3D3; background-color: #D3D3D3; margin-left: 20px;">
										<div style="width: 48%; float: right;">
											<h3>상세코드</h3>
											<div id="cd">
												<%@ include file="cdMngDtl.jsp"%></div>
											<table class="right">
												<thead>
													<tr>
														<th scope="col">상세코드ID</th>
														<th scope="col">상세코드이름</th>
														<th scope="col">사용여부</th>
													
														<!-- <th scope="col">상세보기</th> -->
													</tr>
												</thead>
												<tbody id="ajaxList">
													
												</tbody>

											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		window.name = 'viewPage';
		var id = null;
		function cdSearch(cdId) {
			var url = "<c:url value='/code/dtl/' />" + cdId;
			id=cdId;
			$.ajax({
				type : "get",
				url : "<c:url value='/code/' />" + cdId,
				success : function(data) {
					$("#grp").html(data);
				},
				error : function() {
					// 에러 처리
					console.error("Ajax 요청 실패");
				}
			});

			$.ajax({
						type : "get",
						url : url,
						data : {
							"cdId" : cdId
						},
						contentType : "application/x-www-form-urlencoded; charset=UTF-8",
						dataType : "json",
						success : function(data) {
							// 받아온 데이터를 처리하여 HTML에 추가
							$("#ajaxList").empty(); // 이전 데이터를 지우고 새로운 데이터로 갱신
							ajaxHtml(data);

						},
						error : function() {
							// 에러 처리
							console.error("Ajax 요청 실패");
						}
					});
		}

		function ajaxHtml(data) {

			// 데이터를 순회하면서 원하는 작업을 수행합니다.
			data.forEach(function(item) {
						var cdId = item.cdId;
						var cdDtlId = item.cdDtlId;
						var cdDtlNm = item.cdDtlNm;
						var cdUseYn = item.cdDtlUseYn;

						var newRow = "<tr>";
						newRow += "<td>" + cdDtlId + "</td>";
						newRow += '<td><a href="javascript:void(0);" data-cdId="'
								+ cdId
								+ '" data-cdDtlId="'
								+ cdDtlId
								+ '" onclick="searchDtl(this.getAttribute(\'data-cdId\'), this.getAttribute(\'data-cdDtlId\'));">'
								+ cdDtlNm + '</a></td>';
						newRow += "<td>" + cdUseYn + "</td>";
						newRow += "</tr>";

						$("#ajaxList").append(newRow);
					});
		}

	
		function searchDtl(cdId, cdDtlId) {
			var url = "<c:url value='/code/' />" + cdId + "/" + cdDtlId;
			$.ajax({
				type : "get",
				url : url,
				success : function(data) {
					console.log(data);
					$("#cd").html(data);
				},
				error : function() {
					// 에러 처리
					console.error("Ajax 요청 실패");
				}
			});

		}

	</script>
</body>
</html>
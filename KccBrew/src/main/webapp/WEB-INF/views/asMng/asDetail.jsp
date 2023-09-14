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
<link rel="stylesheet" href="/resources/css/asMng/asDetail.css" />
<link rel="stylesheet" href="/resources/css/asMng/content-template.css" />
<!-- font -->
<!-- notoSans -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans&display=swap" rel="stylesheet">
<!-- notoSans Kr -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans&family=Noto+Sans+KR&display=swap" rel="stylesheet">
<script src="<c:url value="resources/js/asMng/asDetail.js"/>"></script>
<script src="http://code.jquery.com/jquery-latest.js"></script> 
<meta charset="UTF-8">
<!-- 카카오 api -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8ebfc94bdd15e21c9b4d64159a004634"></script>
<script>
window.onload=function(){
	var lat=(${asDetailInfo.latitude})
	var log=(${asDetailInfo.longitude})
	var mapContainer = document.getElementById("map"), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(lat, log), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

	var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

	// 마커가 표시될 위치입니다 
	var markerPosition  = new kakao.maps.LatLng(lat, log); 

	// 마커를 생성합니다
	var marker = new kakao.maps.Marker({
    position: markerPosition
	});

	// 마커가 지도 위에 표시되도록 설정합니다
	marker.setMap(map);
}
</script>
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
								<li><a href="<c:url value='/as-detail' />">AS 조회</a></li>
						</ol>
					</div>
					<div id="region-main">
						<div role="main">
							<div class="user-past">
								<div id="content">
									<div>
										<h2 class="heading">AS 접수 번호 : ${asDetailInfo.asInfoSeq} </h2>
											<hr>
										<table id="search-box">
											<tr>
											<th> 접수 사진</th>
												<td colspan="7">
													<!-- AS 상세 조회-->
													<div id="carouselExampleControlsNoTouching" class="carousel slide" data-bs-touch="false">
											  			<div class="carousel-inner">
															<c:forEach var="asInfoImgList" items="${asInfoImgList}" varStatus="status">
																<c:choose>
																	<c:when test="${status.index == 0}">
																	    <div class="carousel-item active">
																	    	<div style="background-image:url('${asInfoImgList.storageLocation}${asInfoImgList.fileServerNm}')" class="d-block w-100" id="as-receipt-img"></div>
																	    </div>
																	</c:when>
																	<c:otherwise>
																	    <div class="carousel-item">
																	    	<div style="background-image:url('${asInfoImgList.storageLocation}${asInfoImgList.fileServerNm}')" class="d-block w-100" id="as-receipt-img"></div>
																	    </div>
																	</c:otherwise>
																</c:choose>
															</c:forEach>	
														</div>
														<button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControlsNoTouching" data-bs-slide="prev">
														  <span class="carousel-control-prev-icon" aria-hidden="true"></span>
														  <span class="visually-hidden">Previous</span>
														</button>
														<button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControlsNoTouching" data-bs-slide="next">
														  <span class="carousel-control-next-icon" aria-hidden="true"></span>
														  <span class="visually-hidden">Next</span>
														</button>
													</div>
												</td>
										  	</tr>
											<tr>
												<th>희망 기간</th>
												<!-- 시작 연도 선택 필드 -->
												<td>
													<input type="text" value="${asDetailInfo.wishingStartDate}" readonly>
												</td>
												<td>~</td>
												<td>
													<input type="text" value="${asDetailInfo.wishingEndDate}" readonly>
												</td>
												<th>방문 예정일</th>
												<td>
													<input type="text" value="${asDetailInfo.visitDttm}" readonly>
												</td>
												<th>AS 처리일</th>
												<td>
													<input type="text" value="${asDetailInfo.resultDttm}" readonly>
												</td>
											</tr>
		
											<tr>
												<th>점포명</th>
												<td>
													<input type="text" value="${asDetailInfo.storeNm}" readonly>
												</td>
												<th>점포 번호</th>
												<td>
													<input type="text" value="${asDetailInfo.storeTelNo}" readonly>
												</td>
												<th>점포 주소</th>
												<td colspan="5">
													<input type="text" value="${asDetailInfo.storeAddr}" readonly>
												</td>
											</tr>
		
											<tr>
												<th>접수 ID</th>
												<td>
													<input type="text" value="${asDetailInfo.storeMngId}" name="strMngId" readonly>
												</td>
												<th>장비 구분</th>
												<td>
													<input type="text" value="${asDetailInfo.machineCdNm}" readonly>
												</td>
												<th>진행 상태</th>
												<td>
													<input type="text" value="${asDetailInfo.asStatusNm}" readonly>
												</td>
												<th>배정 기사</th>
												<td>
													<input type="text" value="${asDetailInfo.mechanicNm}" readonly>
												</td>
											</tr>
											<tr>
												<th>접수 내용</th>
												<td colspan="7">
													<textarea readonly>${asDetailInfo.asContent}</textarea>
												</td>
											</tr>
											<tr>
												<th>처리 내용</th>
												<td colspan="7">
													<textarea readonly>${asDetailInfo.resultDtl}</textarea>
												</td>
											</tr>
											<tr>
												<th>점포 위치</th>
												<td colspan="7">
													<div id="map" style="width:100%;height:350px;"></div>
												</td>
											</tr>
											<c:if test="${sessionScope.userTypeCd eq '01' and asDetailInfo.asStatusCd eq '01'}">
												<tr>
													<td colspan="7" style=" border-bottom:none;"></td>
													<td style=" border-bottom:none;">
														<div>
															<a href="#" class="form-btn" style=" margin: 0; float: right;">접수 반려</a>
														</div>
													</td>
												</tr>
											</c:if>
											<c:if test="${sessionScope.userTypeCd eq '02' and asDetailInfo.asStatusCd eq '01'}">
												<tr>
													<td colspan="8" style=" border-bottom:none;"></td>
													<td style=" border-bottom:none;">
														<div>
															<a href="#" class="form-btn" style=" margin: 0; float: right;">수정</a>
														</div>
													</td>
												</tr>
											</c:if>
											<c:if test="${sessionScope.userTypeCd eq '03'and asDetailInfo.asStatusCd eq '03' }">
												<tr>
													<td colspan="7" style=" border-bottom:none;"></td>
													<td style=" border-bottom:none;">
														<div>
															<button onclick="rejectAs(${sessionScope.userTypeCd})" class="form-btn" style=" margin: 0; float: right;">배정 반려</button>
														</div>
													</td>
												</tr>
											</c:if>
										</table>
								  	</div>
								  	<c:if test="${asDetailInfo.asStatusCd eq '01' and sessionScope.userTypeCd eq '01'}">
									  	<div>
									  		<h2 class="heading">기사 배정</h2>
											<hr>
											<form action="/as-assign" method="post">
												<table id="search-box">
													<tr>
														<td colspan="9"></td>
														<td > 
															<div>
																<button type="submit" class="form-btn" >기사 배정</button>
															</div>
														</td>
													</tr>
													<tr>
														<th>지역(대분류)</th>
														<td>	
															<select name=location class="selectMcha" onchange="changeLocationCd()">
																<option value="">지역 선택</option>
																<c:forEach var="locationCd" items="${locationCd}">
																	<option value="${locationCd.grpCdDtlId}">${locationCd.grpCdDtlNm}</option>
																</c:forEach>
															</select>
														</td>
														<th>지역(소분류)</th>
														<td colspan="3">
															<select class="selectMcha" name=locationCd onchange="selectLocation()">
																<option value="">지역 선택</option>
															</select>
														</td>
														<th>날짜 선택</th>
														<td>
															<input type="date" name="visitDttm" onchange="selectDate()" required>
														</td>
														<th>수리 기사 목록</th>
														<td>
															<select name="mechanicId" class="selectMcha" required>
																<option value="">수리 기사 선택</option>
															</select>
														</td>
													</tr>
												</table>
												<input type="hidden" name="asInfoSeq" value="${asDetailInfo.asInfoSeq}">
											</form>
								  		</div>
								  	</c:if>
							  		<div>
						  			</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal">
		<div class="modal-content">
		</div>
	</div>	
</body>
</html>
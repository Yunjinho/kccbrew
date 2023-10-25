<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@ page import="java.time.LocalDateTime"%>
<%@ page import="java.time.format.DateTimeFormatter"%>


<!DOCTYPE html>
<html>
<head>
<!-- css -->
<link rel="stylesheet" href="/resources/css/asMng/asDetail.css" />
<link rel="stylesheet" href="/resources/css/asMng/content-template.css" />
<script src="<c:url value="resources/js/asMng/asDetail.js"/>"></script>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<meta charset="UTF-8">
<!-- 카카오 api -->
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8ebfc94bdd15e21c9b4d64159a004634"></script>

<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
	integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js"
	integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa"
	crossorigin="anonymous"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">

<script>
window.onload=function(){
	var lat=${asDetailInfo.latitude}; 
	var log=${asDetailInfo.longitude}; 

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

<!-- 스프링 시큐리티사용 -->
<sec:authentication property="principal.username" var="userId" />
<sec:authentication property="authorities" var="roles" />

<script>
	var userId = '${userId}';
	var userType = '${roles}';
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
									<!-- 탭메뉴 -->
									<div class="tabNav">
										<ul class="tab-ul">
											<li class="active" id="view-receipt"><div
													class="tab-div">
													<span onclick="selectReceiptInfo()">AS 접수 정보</span>
												</div></li>
											<li class="last" id="view-result"><div class="tab-div">
													<span
														onclick="selectResultInfo(${sessionScope.user.userTypeCd},${asDetailInfo.asResultSeq})">AS
														결과</span>
												</div></li>
										</ul>
									</div>
									<div>
										<div class="form-btn" onclick="viewHistory()" style="width:150px;margin-top:20px">해당 AS건 이력 조회</div>
									</div>
									<div class="as-receipt-info">
										<!-- 기사 배정 및 반려 -->
										<input type="hidden" name="storeSeq" id="storeSeq"
											value="${asDetailInfo.storeSeq}">



										<!-- 기사 배정 및 반려 -->
										<c:if
											test="${(asDetailInfo.asStatusCd eq '01' and sessionScope.user.userTypeCd eq '01' and empty asDetailInfo.asAssignSeq) or (sessionScope.user.userTypeCd eq '01' and asDetailInfo.reassign eq'Y' and (asDetailInfo.resultReapply eq 'N' or empty asDetailInfo.resultReapply ))}">
											<div>
												<div class="heading-div">
													<h1 class="heading">기사 배정</h1>
												</div>
												<form action="/as-assign" method="post" id="as-assign-form">
													<table id="search-box">
														<c:choose>
															<c:when
																test="${asDetailInfo.reassign eq'N' or asDetailInfo.reassign eq ''}">
																<tr>
																	<td colspan="9"></td>
																	<td>
																		<div>
																			<input type="hidden" name="storeSeq"
																				value="${asDetailInfo.storeSeq}">
																			<button type="button" class="form-btn"
																				onclick="performSubmit(1);">기사 배정</button>

																		</div>
																	</td>
																</tr>
															</c:when>
															<c:otherwise>
																<tr>
																	<td colspan="8"></td>
																	<td>
																		<div>
																			<a type="submit" class="form-btn"
																				onclick="rejectConfirm('Y')">승인</a>
																		</div>
																	</td>
																	<td>
																		<div>
																			<a type="submit" class="form-btn"
																				onclick="rejectConfirm('N')">거절</a>
																		</div>
																	</td>
																</tr>
																<tr>
																	<th>반려 사유</th>
																	<td colspan="9">
																		<div>
																			<textarea class="content-textarea" readonly>${asDetailInfo.rejectContentMcha}</textarea>
																			<input type="hidden" name="asAssignSeq"
																				id="asAssignSeq" value="${asDetailInfo.asAssignSeq}">
																			<input type="hidden" name="asInfoSeq" id="asInfoSeq"
																				value="${asDetailInfo.asInfoSeq}">
																		</div>
																	</td>
																</tr>

															</c:otherwise>
														</c:choose>
														<tr>
															<th>지역(대분류)</th>
															<td><select name=location class="selectMcha"
																onchange="changeLocationCd()">
																	<option value="">지역 선택</option>
																	<c:forEach var="locationCd" items="${locationCd}">
																		<option value="${locationCd.grpCdDtlId}">${locationCd.grpCdDtlNm}</option>
																	</c:forEach>
															</select></td>
															<th>지역(소분류)</th>
															<td colspan="3"><select class="selectMcha"
																name=locationCd onchange="selectLocation()">
																	<option value="">지역 선택</option>
															</select></td>
															<th>날짜 선택</th>

															<td><input type="date" name="visitDttm"
																id="visitDate" onchange="selectDate()" required></td>
															<th>수리 기사 목록</th>
															<td><select name="mechanicId" class="selectMcha"
																id="mechanicId" required>
																	<option value="">수리 기사 선택</option>
															</select></td>
														</tr>
													</table>
													<input type="hidden" name="asInfoSeq"
														value="${asDetailInfo.asInfoSeq}"> <input
														type="hidden" name="machineCd"
														value="${asDetailInfo.machineCd}">
												</form>
											</div>
										</c:if>

										<!-- AS 재접수 처리 -->
										<c:if
											test="${sessionScope.user.userTypeCd eq '01' and asDetailInfo.resultReapply eq 'Y'  and asDetailInfo.reapplyConfirm eq 'N'}">
											<div>
												<div class="heading-div">
													<h1 class="heading">기사 재배정</h1>
												</div>
												<form action="/as-assign" method="post" id="as-assign-form"">
													<table id="search-box">
														<tr>
															<td colspan="9"></td>
															<td>
																<div>
																	<button type="button" class="form-btn" onclick="performSubmit(2);">기사 배정</button>
																</div>
															</td>
														</tr>
														<tr>
															<th>지역(대분류)</th>
															<td><select name=location class="selectMcha"
																onchange="changeLocationCd()">
																	<option value="">지역 선택</option>
																	<c:forEach var="locationCd" items="${locationCd}">
																		<option value="${locationCd.grpCdDtlId}">${locationCd.grpCdDtlNm}</option>
																	</c:forEach>
															</select></td>
															<th>지역(소분류)</th>
															<td colspan="3"><select class="selectMcha"
																name=locationCd onchange="selectLocation()">
																	<option value="">지역 선택</option>
															</select></td>
															<th>날짜 선택</th>
															<td><input type="date" name="visitDttm"
																id="visitDate" onchange="selectDate()" required></td>
															<th>수리 기사 목록</th>
															<td><select name="mechanicId" class="selectMcha"
																required id="mechanicId">
																	<option value="">수리 기사 선택</option>
															</select></td>
														</tr>
													</table>
													<input type="hidden" name="asInfoSeq"
														value="${asDetailInfo.asInfoSeq}"> <input
														type="hidden" name="machineCd"
														value="${asDetailInfo.machineCd}"> <input
														type="hidden" name="asResultSeq"
														value="${asDetailInfo.asResultSeq}"> <input
														type="hidden" name="storeSeq"
														value="${asDetailInfo.storeSeq}"><input
														type="hidden" name="asAssignSeq"
														value="${asDetailInfo.asAssignSeq}">
												</form>
											</div>
										</c:if>

										<div class="heading-div">
											<h1 class="heading">접수 정보</h1>
										</div>
										<table id="search-box">
											<c:if
												test="${sessionScope.user.userTypeCd eq '01' and asDetailInfo.asStatusCd eq '01'}">
												<tr>
													<td colspan="7"></td>
													<td>
														<div>
															<a href="#"
																onclick="rejectAs(${sessionScope.user.userTypeCd})"
																class="form-btn" style="margin: 0; float: right;">접수
																반려</a>
														</div>
													</td>
												</tr>
											</c:if>
											<c:if
												test="${sessionScope.user.userTypeCd eq '02' and (asDetailInfo.asStatusCd eq '02' or asDetailInfo.asStatusCd eq '01') and sessionScope.user.userId eq asDetailInfo.storeMngId}">
												<tr>
													<td colspan="7"></td>
													<td style="display:flex;">
														<div>
															<a
																href="/as-mod?asInfoSeq=${asDetailInfo.asInfoSeq}&asAssignSeq=${asDetailInfo.asAssignSeq}&storeSeq=${asDetailInfo.storeSeq}"
																class="form-btn" style="margin: 0px 5px;">수정</a>
														</div>
														<div>
															<a onclick="deleteAs()" class="form-btn"
																style="margin: 0px 5px;">취소</a>
														</div>
													</td>
												</tr>
											</c:if>
											
											<c:if
												test="${sessionScope.user.userTypeCd eq '03'and asDetailInfo.asStatusCd eq '03' and asDetailInfo.reassign eq 'N' and ( asDetailInfo.resultReapply eq 'N' or empty asDetailInfo.resultReapply)}">
												<tr>
													<td colspan="7"></td>
													<td>
														<div>
															<button
																onclick="rejectAs(${sessionScope.user.userTypeCd})"
																class="form-btn" style="margin: 0; float: right;">배정
																반려</button>
														</div>
													</td>
												</tr>
											</c:if>
											<tr>
												<th>접수 사진</th>
												<td colspan="7">
													<!-- AS 상세 조회-->
													<div id="carouselExampleControlsNoTouching"
														class="carousel slide" data-bs-touch="false">
														<div class="carousel-inner">
															<c:forEach var="asInfoImgList" items="${asInfoImgList}"
																varStatus="status">
																<c:choose>
																	<c:when test="${status.index == 0}">
																		<div class="carousel-item active">
																			<div
																				style="background-image:url('${asInfoImgList.storageLocation}${asInfoImgList.fileServerNm}')"
																				class="d-block w-100" id="as-receipt-img"></div>
																		</div>
																	</c:when>
																	<c:otherwise>
																		<div class="carousel-item">
																			<div
																				style="background-image:url('${asInfoImgList.storageLocation}${asInfoImgList.fileServerNm}')"
																				class="d-block w-100" id="as-receipt-img"></div>
																		</div>
																	</c:otherwise>
																</c:choose>
															</c:forEach>
														</div>
														<button class="carousel-control-prev" type="button"
															data-bs-target="#carouselExampleControlsNoTouching"
															data-bs-slide="prev">
															<span class="carousel-control-prev-icon"
																aria-hidden="true"></span> <span class="visually-hidden">Previous</span>
														</button>
														<button class="carousel-control-next" type="button"
															data-bs-target="#carouselExampleControlsNoTouching"
															data-bs-slide="next">
															<span class="carousel-control-next-icon"
																aria-hidden="true"></span> <span class="visually-hidden">Next</span>
														</button>
													</div>
												</td>
											</tr>
											<tr>
												<th>희망 기간</th>
												<!-- 시작 연도 선택 필드 -->
												<td><input type="text"
													value="${asDetailInfo.wishingStartDate}" readonly>
												</td>
												<td>~</td>
												<td><input type="text"
													value="${asDetailInfo.wishingEndDate}" readonly></td>
												<th>방문 예정일</th>
												<td><input type="text"
													value="${asDetailInfo.visitDttm}" readonly id="visitDate"></td>
												<th>AS 처리일</th>
												<td><input type="text"
													value="${asDetailInfo.resultDttm}" readonly></td>
											</tr>

											<tr>
												<th>점포명</th>
												<td><input type="text" value="${asDetailInfo.storeNm}" 
													id="storeNm" readonly></td>
												<th>점포 번호</th>
												<td><input type="text"
													value="${asDetailInfo.storeTelNo}" readonly></td>
												<th>점포 주소</th>
												<td colspan="3"><input type="text"
													value="${asDetailInfo.storeAddr}" readonly></td>
											</tr>

											<tr>
												<th>접수 ID</th>
												<td><input type="text"
													value="${asDetailInfo.storeMngId}" name="strMngId" readonly>
												</td>
												<th>장비 구분</th>
												<td><input type="text"
													value="${asDetailInfo.machineCdNm}" readonly></td>
												<th>진행 상태</th>
												<td><c:choose>
														<c:when test="${asDetailInfo.resultReapply eq 'Y'}">
															<input type="text" value="재접수" readonly>
														</c:when>
														<c:otherwise>
															<input type="text" value="${asDetailInfo.asStatusNm}"
																readonly>
														</c:otherwise>
													</c:choose></td>
												<th>배정 기사</th>
												<td><input type="text"
													value="${asDetailInfo.mechanicNm}" readonly></td>
											</tr>
											<tr>
												<th>접수 내용</th>
												<td colspan="7"><textarea class="content-textarea"
														readonly>${asDetailInfo.asContent}</textarea></td>
											</tr>
											<tr>
												<th>점포 위치</th>
												<td colspan="7">
													<div id="map" style="width: 100%; height: 350px;"></div>
												</td>
											</tr>
											<c:if test="${asDetailInfo.resubmission eq 'Y'}">
												<tr>
													<th>접수 반려 내용</th>
													<td colspan="7"><textarea class="content-textarea"
															readonly>${asDetailInfo.rejectContentStr}</textarea></td>
												</tr>
											</c:if>
										</table>
									</div>



									<!-- 결과 처리 입력 form-->
									<div class="as-result-info">
										<c:if
											test="${asDetailInfo.asStatusCd eq '03' and sessionScope.user.userTypeCd eq '03' and (asDetailInfo.resultReapply eq 'N' or empty asDetailInfo.resultReapply)}">
											<div>
												<div class="heading-div">
													<h1 class="heading">AS 결과</h1>
												</div>
												<form action="/insertResult" method="post"
													enctype="multipart/form-data">
													<input type="hidden" name="asInfoSeq"
														value="${asDetailInfo.asInfoSeq}"> <input
														type="hidden" name="asAssignSeq"
														value="${asDetailInfo.asAssignSeq}"> <input
														type="hidden" name="storeSeq"
														value="${asDetailInfo.storeSeq}">
													<table id="search-box">
														<tr>
															<th>처리 일자</th>
															<td><input type="date" name="resultDttm" readonly required>
															</td>
															<th>청구 비용 입력</th>
															<td><input type="number" name="asPrice" required>
															</td>
														</tr>
														<tr>
															<th>처리 내용</th>
															<td colspan="7	"><textarea class="content-textarea"
																	name="resultDtl"></textarea></td>
														</tr>
													</table>
													<div class="flex-label-div">
														<div>
															<h6>
																<img src="/resources/img/asMng/check.png"
																	class="tag-image">사진 첨부 파일
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
													</div>
													<div>
														<input style="margin-right: 5px; float: right;"
															type="submit" class="form-btn" value="결과 입력">
													</div>
												</form>
											</div>
										</c:if>
										<!-- 처리 완료된 AS -->
										<c:if
											test="${asDetailInfo.asStatusCd eq '04' or asDetailInfo.resultReapply eq 'Y'}">
											<div class="heading-div">
												<h1 class="heading">AS 결과</h1>
											</div>
											<table id="search-box">
												<tr>
													<th style="width: 10%;">결과 사진</th>
													<td colspan="7">
														<!-- AS 상세 조회-->
														<div id="carouselExampleControlsNoTouching"
															class="carousel slide" data-bs-touch="false">
															<div class="carousel-inner">
																<c:forEach var="asResultImgList"
																	items="${asResultImgList}" varStatus="status">
																	<c:choose>
																		<c:when test="${status.index == 0}">
																			<div class="carousel-item active">
																				<div
																					style="background-image:url('${asResultImgList.storageLocation}${asResultImgList.fileServerNm}')"
																					class="d-block w-100" id="as-receipt-img"></div>
																			</div>
																		</c:when>
																		<c:otherwise>
																			<div class="carousel-item">
																				<div
																					style="background-image:url('${asResultImgList.storageLocation}${asResultImgList.fileServerNm}')"
																					class="d-block w-100" id="as-receipt-img"></div>
																			</div>
																		</c:otherwise>
																	</c:choose>
																</c:forEach>
															</div>
															<button class="carousel-control-prev" type="button"
																data-bs-target="#carouselExampleControlsNoTouching"
																data-bs-slide="prev">
																<span class="carousel-control-prev-icon"
																	aria-hidden="true"></span> <span
																	class="visually-hidden">Previous</span>
															</button>
															<button class="carousel-control-next" type="button"
																data-bs-target="#carouselExampleControlsNoTouching"
																data-bs-slide="next">
																<span class="carousel-control-next-icon"
																	aria-hidden="true"></span> <span
																	class="visually-hidden">Next</span>
															</button>
														</div>
													</td>
												</tr>
												<tr>
													<th>AS 처리 내용</th>
													<td colspan="7"><textarea class="content-textarea"
															readonly>${asDetailInfo.resultDtl}</textarea></td>
												</tr>
												<tr>
													<th>AS 처리일</th>
													<td><input type="text"
														value="${asDetailInfo.resultDttm}" readonly></td>
													<th>AS 비용</th>
													<td><input type="text" value="${asDetailInfo.asPrice}"
														readonly></td>
													<th>만족도</th>
													<td><c:choose>
															<c:when test='${asDetailInfo.storeMngFb != ""}'>
																<input type="text" value="${asDetailInfo.storeMngFb}"
																	readonly>
															</c:when>
															<c:otherwise>
																<input type="text" value="-" readonly>
															</c:otherwise>
														</c:choose></td>
												</tr>
												<c:if
													test="${sessionScope.user.userTypeCd eq '02' and asDetailInfo.resultReapply eq 'N' and asDetailInfo.asStatusCd eq '04' and asDetailInfo.reapplyConfirm eq 'N'}">
													<tr>
														<td colspan="5" style='border-bottom: none'></td>
														<td style='border-bottom: none; float: right'>
															<button onclick="resultAs('Y')" class="form-btn">재신청</button>
														</td>
														<td style='border-bottom: none; float: right'>
															<button onclick="resultAs('N')" class="form-btn">AS
																확인</button>
														</td>
													</tr>
												</c:if>
											</table>
										</c:if>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 수리기사 AS배정반려 등록 모달창 -->
	<div class="modal-reject">
		<div class="reject-content">
			<form class="reject-form" method="post" action="/reject"  name="as-assign-reject-form">
				<h1 class="modal-heading">반려 내용 작성</h1>
				<hr>
				<input type="hidden" name="asInfoSeq" id="as-assign-reject-asInfoSeq" value="${asDetailInfo.asInfoSeq}"> 
				<input type="hidden"  name="storeSeq" value="${asDetailInfo.storeSeq}">
				<textarea class="content-textarea" name="rejectRs"></textarea>
				<c:if test="${sessionScope.user.userTypeCd =='03'}">
					<input type="hidden" name="asAssignSeq"  value="${asDetailInfo.asAssignSeq}" id="as-assign-reject-asAssignSeq">
				</c:if>
				<c:if test="${sessionScope.user.userTypeCd =='01'}">
					<input type="hidden" name="asAssignSeq"  value="" id="as-assign-reject-asAssignSeq">
				</c:if>
				<div>
					<button type="button" class="form-btn" onclick="RejectAsAssign();"
						style="margin: 0; float: right;">반려</button>
					<div class="form-btn" onclick="cancelModal()"
						style="margin: 0; float: right;">취소</div>
				</div>
			</form>
		</div>
	</div>
	<!-- 수리기사 AS배정반려 등록 모달창 -->
	
	
	<!-- 접수반려내용 모달창 -->
	<div class="modal-rejectContent">
		<div class="reject-content">
			<h1 class="modal-heading">반려 내용</h1>
			<hr>
			<textarea class="content-textarea">${asDetailInfo.rejectContentStr}</textarea>

			<div>
				<div class="form-btn" style="margin: 0; float: right;"
					onclick="cancelModal()">취소</div>
			</div>
		</div>
	</div>

	<!-- 결과 모달 -->
	<div class="modal-result">
		<div class="result-content">
			<form class="result-form" method="post" action="/as-result-mng">
				<input type="hidden" name="resultReapply"> <input
					type="hidden" name="asResultSeq"
					value="${asDetailInfo.asResultSeq}"> <input type="hidden"
					name="asInfoSeq" value="${asDetailInfo.asInfoSeq}">
				<fieldset>
					<h1 class="result-heading">!서비스 만족도를 입력해주세요</h1>
					<input type="radio" name="storeMngFb" value="5" id="rate1"><label
						for="rate1">★</label> <input type="radio" name="storeMngFb"
						value="4" id="rate2"><label for="rate2">★</label> <input
						type="radio" name="storeMngFb" value="3" id="rate3"><label
						for="rate3">★</label> <input type="radio" name="storeMngFb"
						value="2" id="rate4"><label for="rate4">★</label> <input
						type="radio" name="storeMngFb" value="1" id="rate5"><label
						for="rate5">★</label>
				</fieldset>
				<div>
					<button type="submit" class="form-btn">확인</button>
					<div onclick="cancelModal()" class="form-btn">취소</div>
				</div>
			</form>
		</div>
	</div>

</body>
</html>
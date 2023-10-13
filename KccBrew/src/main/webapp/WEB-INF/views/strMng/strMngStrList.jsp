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
<link rel="stylesheet" href="/resources/css/store/strMngStrList.css" />
<link rel="stylesheet" href="/resources/css/asMng/content-template.css" />
<!-- js -->
<script src="<c:url value="resources/js/strMng/strMngStrList.js"/>"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<meta charset="UTF-8">
</head>
<body>
	<div id="page-mask">
		<div id="page-container" class="">
			<div id="page-content" class="clearfix">
				<div id="page-content-wrap">
					<!-- ********** 페이지 네비게이션 시작 ********** -->
					<div class="page-content-navigation">
						<ol class="breadcrumb">
							<li class="breadcrumb-home"><a href="#">점포 관리</a></li>
							<li>
								<div class="header-icon-background">
									<img
										src="<c:url value='resources/img/asMng/free-icon-right-arrow-271228.png' />"
										alt="Check List" class="header-icon" />
								</div>
							</li>
							<li><a href="<c:url value='/as-list' />">점포 조회</a></li>
						</ol>
					</div>
					<!-- ********** 페이지 네비게이션 끝 ********** -->
					<div id="region-main">
						<div role="main">
							<span id="maincontent"></span>
							<div class="user-past">
								<div id="content">
									<h2 class="heading" style="margin: 10px 0px; ">내 점포 목록</h2>
									<div class="form-btn" id="add-store"><span>점포 추가</span></div>
									<div id="logTable">
										<table>
											<thead>
												<tr> 
													<th>번호</th>
													<th>점포 이름</th>
													<th>점포 번호</th> 
													<th>점포 주소</th>
													<th>비고</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="list" items="${myStrList}">
													<tr>
														<td><c:out value="${list.rowNumber}" /></td>
														<td><c:out value="${list.storeNm}" /></td>
														<td><c:out value="${list.storeTelNo}" /></td>
														<c:choose>
															<c:when test="${ list.storeAddrDtl eq null }">
																<td><c:out value="${list.storeAddr}"/></td>
															</c:when>
															<c:otherwise>
																<td><c:out value="${list.storeAddr}, ${list.storeAddrDtl}"/></td>
															</c:otherwise>
														</c:choose>
														<td>
															<div style="display:flex;">
																<input type="hidden" class="storeList" value="${list.storeSeq}">
																<div onclick="selectStrDetail(${list.storeSeq})"class="form-btn">조회</div>
																<div onclick="deleteStrDetail(${list.storeSeq})"class="form-btn">삭제</div>
															</div>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</div>
								<section id="notice" class="notice">
								 <div class="container2">
						            <div class="category">점포 상세 정보</div>
						            <hr style="border: solid 1.2px; width: 100%;">
						            
								<form action="/store/update" method="post" id="storeForm"
									onsubmit="return submitForm()" style="margin-top:20px">
									<table id="search-box">
										<tr>
											<th>지점명</th>
											<td><input type="text" id="storeNm" name="storeNm"
												value="${store.storeNm}" readonly></td>
											<th>점포연락처</th>
											<td><input type="text" id="storeTelNo" name="storeTelNo"
												oninput="hypenTel(this)" maxlength="13" readonly
												value="${store.storeTelNo}" placeholder="하이픈(-)은 자동입력 됩니다.."></td>
										</tr>
										<tr>
											<th>좌표</th>
											<td><input class="field" id="lat" name="latitude" readonly
												value="${store.latitude}" size="12" />, <input class="field" id="lng"
												name="longitude" value="${store.longitude}" size="12" readonly></td>
						
											<th>사용여부</th>
											<td><input type="text" value="${store.useYn}"readonly></td>
										</tr>
										<tr>
											<th colspan="1">주소</th>
											<td colspan="3"><input type="text" id="address_kakao" name="storeAddr"
												placeholder="클릭해주세요" value="${store.storeAddr}"  size="50" readonly />, <input
												type="text" name="storeAddrDtl" id="storeAddrDtl"
												value="${store.storeAddrDtl}" readonly /></td>
										</tr>
									</table>
									
								  <div id="staticMap" style="width: 550px; height: 300px; margin: auto;"></div>
									 <div class="updatecancle" style="text-align: center;">
										<input type="hidden" name="storeSeq" value="${store.storeSeq}" style="">
									</div>
								</form>
								</div>
							</section>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- modal -->
	<div class="search-store">
		<div class="search-store-content">
			<table id="search-box">
				<tr>
					<td>
						<input type="text" class="search-input" placeholder="점포명 또는 주소를 입력해주세요."> 
						<input type="hidden" class="hidden-keyword">
					</td>
				</tr>
			</table>
			<div id="strTable">
				<table class="table">
					<thead>
						<tr>
							<th>선택</th>
							<th>점포명</th>
							<th>주소</th>
						</tr>
					</thead> 
					<tbody>
						<c:forEach var="list" items="${storeList}">
							<tr>
								<td scope="row"><input type="radio" name="select-store" onclick="selectStore(${list.storeSeq})">
									<input type="hidden" name="storeSeq" value="${list.storeSeq}"></td>
								<td>${list.storeNm}</td>
								<td>${list.storeAddr}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
			
			<!-- 페이징 -->
			<div class="paging pagination">

				<!-- 앞으로 가는 버튼 -->
				<a href="#"><img
					src="/resources/img/asMng/free-icon-left-chevron-6015759.png"
					alt=" 처" onclick="movePage(1)"/></a>

				<c:choose>
					<c:when test="${currentPage > 1}">
						<a href="#"><img class="left-btn"
							src="/resources/img/asMng/free-icon-left-arrow-271220.png"
							alt="이" onclick="movePage(${currentPage-1})"/></a>
					</c:when>
					<c:otherwise>
						<a href="#" class="left-btn"><img
							src="/resources/img/asMng/free-icon-left-arrow-271220.png"
							alt="이" /></a>
					</c:otherwise>
				</c:choose>
				<!-- 리스트 목록 나열 -->
				<div id="number-list">
					<div class="page-btn">
						<c:forEach var="page" begin="${startPage}" end="${endPage}" step="1">
							<c:if test="${page <= totalPage}">
								<a href="javascript:void(0)" onclick="movePage(${page})"
									class="pagination page-btn ${currentPage == page ? 'selected' : ''}">
									${page} </a>
							</c:if>
						</c:forEach>
					</div>
				</div>

				<!-- 뒤로 가는 버튼 -->
				<c:if test="${currentPage < totalPage}">
					<a href="#" class="right-btn" onclick="movePage(${currentPage+1})"> 
					<img src="/resources/img/asMng/free-icon-right-arrow-271228.png" alt="다" />
					</a>
				</c:if>
				<c:if test="${currentPage == totalPage}">
					<a href="javascript:void(0);" class="right-btn" class="disabled-link"> 
					<img src="/resources/img/asMng/free-icon-right-arrow-271228.png" alt="다" />
					</a>
				</c:if>

				<a href="#" class="end-btn" onclick="movePage(${totalPage})" >
				<img src="/resources/img/asMng/free-icon-fast-forward-double-right-arrows-symbol-54366.png" alt="마" />
				</a>
			</div>
		</div>
	</div>
	
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAY5-GHYNs9VdzbExAcRYn5NPHIsu727XI&libraries=places&callback=initAutocomplete" async defer></script>
<!-- Daum Postcode API -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8ebfc94bdd15e21c9b4d64159a004634"></script>
<script type="text/javascript">
function initializeKakaoMap() {
    kakao.maps.load(function () {
        // Kakao 지도 초기화 코드
        var markerPosition = new kakao.maps.LatLng(${store.latitude}, ${store.longitude});
        var marker = {
            position: markerPosition
        };
        var staticMapContainer = document.getElementById('staticMap');
        var staticMapOption = {
            center: new kakao.maps.LatLng(${store.latitude}, ${store.longitude}),
            level: 3,
            marker: marker
        };
        var staticMap = new kakao.maps.StaticMap(staticMapContainer, staticMapOption);
    });
}

// Kakao 지도 스크립트 로드 후 호출되도록 이벤트 핸들러 추가
window.addEventListener('load', initializeKakaoMap);
</script>
</body>
</html>
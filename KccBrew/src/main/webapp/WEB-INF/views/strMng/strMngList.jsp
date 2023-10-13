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
<link rel="stylesheet" href="/resources/css/asMng/asList.css" />
<link rel="stylesheet" href="/resources/css/asMng/content-template.css" />
<meta charset="UTF-8">

<title>점포리스트</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<style>
input {
	width: unset;
}

#startDate {
	width: 70%
}

#endDate {
	width: 70%;
}
</style>
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
										src="<c:url value='/resources/img/asMng/free-icon-right-arrow-271228.png' />"
										alt="Check List" class="header-icon" />
								</div>
							</li>
							<li><a href="<c:url value='/store' />">점포 조회</a></li>
						</ol>
					</div>
					<!-- ********** 페이지 네비게이션 끝 ********** -->
					<div id="region-main">
						<div role="main">
							<span id="maincontent"></span>
							<div class="user-past">

								<!-- ********** 점포 리스트 조회 ********** -->
								<div id="content">
									<h2 class="heading">점포조회</h2>
									<!-- 점포 검색 -->
									<form action="/store/search" method="get" id="search-form">
										<input type='hidden' name='currentPage' value="1">
										<table id="search-box">
											<!-- 1행 -->
											<c:set var="today" value="<%=new java.util.Date()%>" />
											<tr>

												<th>등록일</th>
												<td colspan="2" id="form-holiday-period"><span><label
														for="startDate">시작일 : &nbsp;</label><input type="date"
														id="startDate" name="startDate"
														value="${searchContent.startDate}"></span></td>
												<td colspan="2"><span><label for="endDate">종료일
															: &nbsp;</label> <input type="date" id="endDate" name="endDate"
														value="${searchContent.endDate}"></span></td>

												<th>등록자ID</th>
												<td colspan="1"><input type="search" name="regUser"
													placeholder="등록자를 입력하세요" value="${searchContent.regUser}"
													size="15"></td>
												<th>사용여부</th>
												<td><select name="useYn" class="tx2" id="yn"
													onchange="javascript:chg();">
														<option value="">선택</option>
														<option value="Y"
															${searchContent.useYn == 'Y' ? 'selected' : ''}>Y</option>
														<option value="N"
															${searchContent.useYn == 'N' ? 'selected' : ''}>N</option>
												</select></td>
											</tr>

											<tr>
												<th>지역</th>
												<td colspan="2"><select class="tx2" name="locationCd"
													onchange="chg()">
														<option value="">지역 대분류</option>
														<c:forEach var="list" items="${List}">
															<option value="${list.locationCd}"
																${searchContent.locationCd == list.locationCd ? 'selected' : ''}>${list.locationNm}</option>
														</c:forEach>
												</select></td>
												<td colspan="2"><select class="tx2"
													name="locationCdSeoul" disabled>
														<option value="">지역 소분류</option>
														<c:forEach var="list" items="${seoulList}">
															<option value="${list.locationCd}"
																${searchContent.locationCdSeoul == list.locationCd ? 'selected'  : ''}>${list.locationNm}</option>
														</c:forEach>
												</select></td>
												<th>점포명</th>
												<!-- Input field for URI -->
												<td colspan="3"><input type="search" name="keyword"
													placeholder="점포명 입력하세요" value="${searchContent.keyword}"
													size="30" style="width: 95%;"></td>


											</tr>

											<!-- 4행 -->
											<tr>
												<td colspan="7" style="border-bottom: none;"></td>
												<td colspan="2"
													style="text-align: center; border-bottom: 0px;">
													<div style="display: flex;">
														<button type="button" onclick="resetSearch()"
															class="form-btn" style="margin-right: 0px;">초기화</button>

														<button type="submit" class="form-btn"
															style="margin-left: 10px;">검색</button>
													</div>
												</td>
											</tr>
										</table>
									</form>
									<div id="logTable">
										<div class="list-info-div">
											<p class="data-info">
												전체<b><span><c:out value="${totalCount}" /></span></b>건<span
													id="text-separator"> | </span><b><span><c:out
															value="${currentPage}" /></span></b>/<b><span><c:out
															value="${totalPage}" /></span></b>쪽
											</p>
										
										</div>
										<table>
											<thead>
												<tr>
													<th scope="col">NO</th>
													<th scope="col">스토어이름</th>
													<th scope="col">지역구분</th>
													<th scope="col">점포연락처</th>
													<th scope="col">상세보기</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${list}" var="no" varStatus="stat">
													<tr>
														<td><c:out value="${no.storeSeq}" /></td>
														<td>${no.storeNm}</td>
														<td>${no.locationNm}</td>
														<td>${no.storeTelNo}</td>
														<td><a href="javascript:void(0);"
															onclick="popup(${no.storeSeq});">상세보기</a></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>

									<div id="writebtn"
										style="text-align: center; border-bottom: 0; float: right;">
										<a href="javascript:void(0);" onclick="popup1();">
											<button type="button" class="form-btn"
												style="margin: 10px; margin-right: 30px;">점포등록</button>
										</a>
									</div>
									<!-- 페이징 -->
									<div class="paging pagination">

										<!-- 앞으로 가는 버튼 -->
										<a href="#"><img
											src="/resources/img/asMng/free-icon-left-chevron-6015759.png"
											alt=" 처" onclick="movePage(1)" /></a>

										<c:choose>
											<c:when test="${currentPage > 1}">
												<a href="#"><img
													src="/resources/img/asMng/free-icon-left-arrow-271220.png"
													alt="이" onclick="movePage(${currentPage-1})" /></a>
											</c:when>
											<c:otherwise>
												<a href="#" disabled="disabled"><img
													src="/resources/img/asMng/free-icon-left-arrow-271220.png"
													alt="이" /></a>
											</c:otherwise>
										</c:choose>

										<!-- 리스트 목록 나열 -->
										<div id="number-list">
											<div class="page-btn">
												<c:forEach var="page" begin="${startPage}" end="${endPage}"
													step="1">
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
											<a href="#" onclick="movePage(${currentPage+1})"> <img
												src="/resources/img/asMng/free-icon-right-arrow-271228.png"
												alt="다" />
											</a>
										</c:if>
										<c:if test="${currentPage == totalPage}">
											<a href="javascript:void(0);" class="disabled-link"> <img
												src="/resources/img/asMng/free-icon-right-arrow-271228.png"
												alt="다" />
											</a>
										</c:if>

										<a href="#" onclick="movePage(${totalPage})"><img
											src="/resources/img/asMng/free-icon-fast-forward-double-right-arrows-symbol-54366.png"
											alt="마" /></a>
									</div>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		$(document).ready(function(){
			 var locationSelect = document.querySelector("select[name='locationCd']");
      	 	 var subLocationSelect = document.querySelector("select[name='locationCdSeoul']");
			 if (locationSelect.value === '02') {
				 
	               subLocationSelect.disabled = false; // 서브 로케이션 활성화
	            } else {
	               subLocationSelect.disabled = true; // 서브 로케이션 비활성화
	            }
			});
        window.name = 'viewPage';
        function popup(storeSeq) {
            var url = "/store/view/" + storeSeq;
            var name = "popup test";
            var option = "width=900, height=880, top=60, left=400, scrollbars=yes, directories=no, location=no";
            window.open(url, name, option);
            window.close();
        }
        
        function popup1() {
            var url = "/store/insert";
            var name = "popup test";
            var option = "width=800, height=600, top=130, left=450, scrollbars=yes, directories=no, location=no";
            window.open(url, name, option);
            window.close();
        }
        function chg() {
            var locationSelect = document
                  .querySelector("select[name='locationCd']");
            var subLocationSelect = document
                  .querySelector("select[name='locationCdSeoul']");

            if (locationSelect.value === '02') {
               subLocationSelect.disabled = false; // 서브 로케이션 활성화
            } else {
               subLocationSelect.disabled = true; // 서브 로케이션 비활성화
            }
         } 
        function resetSearch(){
        	$("input[name=startDate]").val("");
        	$("input[name=endDate]").val("");
        	$("input[name=regUser]").val("");
        	$("input[name=keyword]").val("");
        	$("select[name=useYn] option:eq(0)").prop("selected", true);
        	$("select[name=locationCd] option:eq(0)").prop("selected", true);
        	$("select[name=locationCdSeoul] option:eq(0)").prop("selected", true);
        }
        function movePage(pageNumber){
        		
        	$("input[name=currentPage]").val(pageNumber);
        	
        	$("#search-form").submit();
        }
        window.onload=function(){
        	history.replaceState({}, null, location.pathname);
        }
        </script>
</body>
</html>

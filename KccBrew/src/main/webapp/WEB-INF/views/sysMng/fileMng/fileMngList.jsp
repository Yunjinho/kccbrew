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

<title>파일리스트</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>

</head>
<style>

#startDate{
width: 70%
}
#endDate{
width: 70%;
}
</style>
<body>
	<div id="page-mask">
		<div id="page-container" class="">
			<div id="page-content" class="clearfix">
				<div id="page-content-wrap">
					<!-- ********** 페이지 네비게이션 시작 ********** -->
					<div class="page-content-navigation">
						<h2 class="heading">파일조회</h2>
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
								
							<li class="breadcrumb-home"><a href="#">파일 관리</a></li>
							<li>
									<div class="header-icon-background">
										<img
											src="<c:url value='/img/common/free-icon-arrow-right.png' />"
											alt="Check List" class="header-icon" />
									</div>
								</li>
							<li><a href="<c:url value='/file' />">파일 조회</a></li>
						</ol>
					</div>
					<!-- ********** 페이지 네비게이션 끝 ********** -->
					<div id="region-main">
						<div role="main">
							<span id="maincontent"></span>
							<div class="user-past">

								<!-- ********** 파일 리스트 조회 ********** -->
								<div id="content">
									<!-- 점포 검색 -->
									<form action="/file/search" method="get" id="search-form">
										<input type='hidden' name='currentPage' value="1">
										<table id="search-box">
											<!-- 1행 -->
											<c:set var="today" value="<%=new java.util.Date()%>" />
											<tr>

												<th>등록일자</th>
												<!-- 시작 연도 선택 필드 -->
												<td colspan="2" id="form-holiday-period"><span><label
														for="startDate">시작일 : &nbsp;</label><input type="date"
														id="startDate" name="startDate"
														value="${searchContent.startDate}"></span></td>
												<td colspan="2"><span><label for="endDate">종료일
															: &nbsp;</label> <input type="date" id="endDate" name="endDate"
														value="${searchContent.endDate}"></span></td>
											<th>파일 구분</th>
												<td colspan="2"><select name="grpCdDtlId" class="tx2"
													id="yn" onchange="javascript:chg();">
														<option value="">선택</option>
														<c:forEach var="List" items="${typeList}">
															<option value="${List.grpCdDtlId}"
																${searchContent.grpCdDtlId == List.grpCdDtlId ? 'selected' : ''}>${List.grpCdDtlNm}</option>
														</c:forEach>

												</select></td>
											</tr>
											<!-- 2행 -->
											<tr>

												<th>서버파일명</th>
												<td colspan="2"><input type="search"
													name="fileServerNm" placeholder="서버파일명을 입력하세요"
													value="${searchContent.fileServerNm}" size="30"></td>
											<th>원본파일명</th>
												<td colspan="1"><input type="search"
													name="fileOriginNm" placeholder="원본파일명을 입력하세요"
													value="${searchContent.fileOriginNm}" size="30"></td>
												
											<th>등록자Id</th>
												<td colspan="1"><input type="search" name="fileRegUser"
													placeholder="등록자ID를 입력하세요"
													value="${searchContent.fileRegUser}" size="30"></td>
											
											</tr>
											<!-- 3행 -->
											<tr>
												<td colspan="6" style="border-bottom: none;"></td>
												<td style="border-bottom: none;">
												<div style="display: flex;">
														<button type="button" onclick="resetSearch()"
															class="form-btn" style="margin-right: 0px;">초기화</button>
														<button type="submit" class="form-btn" id="find-btn1" style="margin-left: 10px;">검색</button>
													</div></td>
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

										<table class="text-center">
											<thead>
												<tr>
													<th scope="col">파일번호</th>
													<th scope="col">원본파일명</th>
													<th scope="col">위치</th>
													<th scope="col">분류</th>
													<th scope="col">등록일자</th>
													<th scope="col">다운로드</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${list}" var="no" varStatus="loop">
													<tr>
														<td>${no.fileSeq}</td>
														<td>${no.fileOriginNm}</td>
														<td>${no.storageLocation}</td>
														<td>${no.grpCdDtlNm}</td>
														<td><fmt:formatDate value="${no.fileRegDttm}"
																pattern="yyyy MM/dd" /></td>
														<td><a href="javascript:void(0);"
															onclick="popup(${no.fileDtlSeq});">상세보기</a></td>
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
function popup(fileDtlSeq) {
    var url = "/file/" + fileDtlSeq;
    var name = "popup test";
    var option = "width=900, height=800, top=60, left=400, scrollbars=yes, directories=no, location=no";
    window.open(url, name, option);
    window.close();
}

function resetSearch(){
	$("input[name=startDate]").val("");
	$("input[name=endDate]").val("");
	$("input[name=fileRegUser]").val("");
	$("input[name=fileOriginNm]").val("");
	$("input[name=fileServerNm]").val("");
	$("select[name=grpCdDtlId] option:eq(0)").prop("selected", true);
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
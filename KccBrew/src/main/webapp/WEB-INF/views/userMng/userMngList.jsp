<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="java.time.LocalDateTime"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/resources/css/store/store.css" />

<!-- font -->
<!-- notoSans -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans&family=Noto+Sans+KR&display=swap"
	rel="stylesheet">
<meta charset="UTF-8">

<title>회원리스트</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<style>
div.Modal2 {
	position: relative;
	z-index: 1;
	display: none;
}

div.Modal1 {
	position: relative;
	z-index: 1;
	display: none;
}

div.modalBackground {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background: rgba(0, 0, 0, 0.8);
	z-index: -1;
}

.modal-dialog2 {
	position: fixed;
	top: 20%;
	left: calc(50% - 250px);
	height: 250px;
	padding: 20px 10px;
	background: #fff;
	border: #333 solid 1px;
	border-radius: 5px;
	z-index: 11;
	width: 360px;
}

.modal-dialog1 {
	position: fixed;
	top: calc(50% -400px);
	left: calc(50% - 500px);
	height: 800px;
	padding: 20px 10px;
	background: #fff;
	border: #333 solid 1px;
	border-radius: 5px;
	z-index: 11;
	width: 1000px;
}

.modal-body {
	padding: 20px;
}

.modal-header, .modal-footer {
	padding: 10px 20px;
}

.modal-header {
	border-bottom: #eee solid 1px;
}

.modal-header h2 {
	font-size: 20px;
}

.modal-footer {
	border-top: #eee solid 1px;
	text-align: right;
}

.modal-footer button {
	background-color: navy;
	color: white;
	border: none;
	padding: 5px 10px;
	border-radius: 4px;
}
</style>
</head>
<body>
	<div id="page" class="page-nosubmenu">


		<div id="page-mask">
			<div id="page-container" class="">
				<div id="page-content" class="clearfix">
					<div id="page-content-wrap">


						<!-- ********** 페이지 네비게이션 끝 ********** -->

						<div id="region-main">
							<div role="main">
								<span id="maincontent"></span>
								<div class="user-past">

									<!-- ********** 점포 관련 내용 시작 ********** -->
									<div id="content">
										<h2 class="heading">회원조회</h2>
										<!-- 점포 검색 -->
										<form action="/user" method="get" style="margin-bottom: 10px;">
											<table id="search-box">
												<!-- 1행 -->
												<c:set var="today" value="<%=new java.util.Date()%>" />
												<fmt:formatDate value="${today}" pattern="yyyy"
													var="nowYear" />
												<tr>

													<th>가입일자</th>
													<!-- 시작 연도 선택 필드 -->
													<td><select class="tx2" name="startYr" id="yr"
														onchange="javascript:chg();">
															<option value="">연도</option>
															<c:forEach var="i" begin="0" end="9">
																<c:set var="year" value="${nowYear - i}" />
																<option value="${year}"
																	${param.startYr == year ? 'selected' : ''}>${year}년</option>
															</c:forEach>
													</select></td>

													<!-- 시작 월 선택 필드 -->
													<td><select class="tx2" name="startMn" id="mn">
															<option value="">월</option>
															<c:forEach var="month" begin="1" end="12">
																<option value="${month}"
																	${param.startMn == month ? 'selected' : ''}>${month}월</option>
															</c:forEach>
													</select></td>

													<td>~</td>

													<!-- 종료 연도 선택 필드 -->
													<td><select class="tx2" name="endYr" id="yr"
														onchange="javascript:chg();">
															<option value="">연도</option>
															<c:forEach var="i" begin="0" end="9">
																<c:set var="year" value="${nowYear - i}" />
																<option value="${year}"
																	${param.endYr == year ? 'selected' : ''}>${year}년</option>
															</c:forEach>
													</select></td>

													<!-- 종료 월 선택 필드 -->
													<td><select class="tx2" name="endMn" id="mn">
															<option value="">월</option>
															<c:forEach var="month" begin="1" end="12">
																<option value="${month}"
																	${param.endMn == month ? 'selected' : ''}>${month}월</option>
															</c:forEach>
													</select></td>
													<th>ID 사용여부</th>
													<td><select name="useYn" class="tx2" id="yn"
														onchange="javascript:chg();">
															<option value="">선택</option>
															<option value="Y" ${param.useYn == 'Y' ? 'selected' : ''}>Y</option>
															<option value="N" ${param.useYn == 'N' ? 'selected' : ''}>N</option>
													</select></td>

												</tr>
												<!-- 2행 -->
												<tr>


													<th>사용자Id</th>
													<td colspan="2"><input type="search" name="userId"
														placeholder="사용자ID를 입력하세요" value="${param.userId}"
														size="30"></td>
													<th>사용자이름</th>
													<td colspan="2"><input type="search" name="userNm"
														placeholder="사용자ID를 입력하세요" value="${param.userNm}"
														size="30"></td>
													<th>사용자 구분</th>
													<td colspan="1"><select name="userTypeCd" class="tx2"
														onchange="javascript:chg();">
															<option value="">선택</option>
															<option value="01"
																${param.userTypeCd == '01' ? 'selected' : ''}>관리자</option>
															<option value="02"
																${param.userTypeCd == '02' ? 'selected' : ''}>점주</option>
															<option value="03"
																${param.userTypeCd == '03' ? 'selected' : ''}>기사</option>

													</select></td>
												</tr>
												<!-- 4행 -->
												<tr>
													<td colspan="8"
														style="text-align: center; border-bottom: 0px;">
														<div class="find-btn" style="text-align: center;">
															<button type="submit" class="form-btn" id="find-btn1">검색</button>
															<button type="button" class="form-btn" id="find-btn1"
																onclick="location.href='/user';">초기화</button>
														</div>
													</td>
												</tr>
											</table>
										</form>
										<div id="logTable" style="margin-bottom: 30px;">
											<p class="data-info">
												새로운 가입 정보<b><span><c:out value="${newTotal}" /></span></b>건
											</p>
											<table class="text-center"
												style="border: 1.5px solid #444444;">
												<thead>
													<tr>
														<th scope="col">사용자ID</th>
														<th scope="col">사용자이름</th>
														<th scope="col">사용자구분</th>
														<th scope="col">전화번호</th>
														<th scope="col">사용여부</th>
														<th scope="col">가입일자</th>
														<th scope="col">승인여부</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${newList}" var="newL" varStatus="loop">
														<tr>
															<td>${newL.userId}</td>
															<td>${newL.userNm}</td>
															<td>${newL.userType}</td>
															<td>${newL.userTelNo}</td>
															<td>${newL.useYn}</td>
															<td><fmt:formatDate value="${newL.regDttm}"
																	pattern="yyyy MM/dd" /></td>
															<td><c:choose>
																	<c:when test="${not empty newL.approveYn}">
                            											${newL.approveYn}</c:when>
																	<c:otherwise>
																		<!-- 확인 버튼 출력 -->
																		<a href="javascript:void(0);"
																			onclick="openModal('${newL.userId}');">확인필요</a>
																	</c:otherwise>
																</c:choose></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
										<div id="logTable">
											<div>
												<p class="data-info">
													전체<b><span><c:out value="${total}" /></span></b>건<span
														id="text-separator"> | </span><b><span><c:out
																value="${currentPage}" /></span></b>/<b><span><c:out
																value="${totalPage}" /></span></b>쪽
												</p>
											</div>



											<table class="text-center">
												<thead>
													<tr>
														<th scope="col">NO</th>
														<th scope="col">사용자ID</th>
														<th scope="col">사용자이름</th>
														<th scope="col">사용자구분</th>
														<th scope="col">전화번호</th>
														<th scope="col">사용여부</th>
														<th scope="col">가입일자</th>
														<th scope="col">승인여부</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${list}" var="no" varStatus="loop">
														<tr>
															<td>${no.rowNum}</td>
															<td><form id="dtl" method="get" style="all:unset;">
																	<input type="hidden" value="${param.userId}"> <a
																		href="javascript:void(0);"
																		onclick="openModal1('${no.userId}');">${no.userId}</a>
																</form></td>
															<td  style="height: 36px;">${no.userNm}</td>
															<td>${no.userType}</td>
															<td>${no.userTelNo}</td>
															<td>${no.useYn}</td>
															<td><fmt:formatDate value="${no.regDttm}"
																	pattern="yyyy MM/dd" /></td>
															<td><c:choose>
																	<c:when test="${not empty no.approveYn}">
                            											${no.approveYn}</c:when>
																	<c:otherwise>
																		<!-- 확인 버튼 출력 -->
																		<a href="javascript:void(0);"
																			onclick="openModal('${no.userId}');">확인필요</a>
																	</c:otherwise>
																</c:choose></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>

										<!-- 페이징 -->
										<div class="paging pagination">

											<!-- 앞으로 가는 버튼 -->
											<a
												href="/user?currentPage=${page}&startYr=${searchContent.startYr}&startMn=${searchContent.startMn}&endYr=${searchContent.endYr}&endMn=${searchContent.endMn}&userTypeCd=${searchContent.userTypeCd}&useYn=${searchContent.useYn}&userId=${searchContent.userId}&userNm=${searchContent.userNm}">
												<img
												src="/resources/img/log/free-icon-left-chevron-6015759.png"
												alt=" 처음" />
											</a>

											<c:choose>
												<c:when test="${currentPage > 1}">
													<a
														href="/user?currentPage=${page}&startYr=${searchContent.startYr}&startMn=${searchContent.startMn}&endYr=${searchContent.endYr}&endMn=${searchContent.endMn}&userTypeCd=${searchContent.userTypeCd}&useYn=${searchContent.useYn}&userId=${searchContent.userId}&userNm=${searchContent.userNm}"><img
														src="/resources/img/log/free-icon-left-arrow-271220.png"
														alt="이전" /></a>
												</c:when>
												<c:otherwise>
													<a href="#" disabled="disabled"><img
														src="/resources/img/log/free-icon-left-arrow-271220.png"
														alt="이전" /></a>
												</c:otherwise>
											</c:choose>

											<!-- 리스트 목록 나열 -->
											<div id="number-list">
												<div class="page-btn">
													<c:forEach var="page" begin="${sharePage * 10 + 1}"
														end="${(sharePage + 1) * 10}" step="1">
														<c:if test="${page <= totalPage}">
															<a
																href="/user?currentPage=${page}&startYr=${searchContent.startYr}&startMn=${searchContent.startMn}&endYr=${searchContent.endYr}&endMn=${searchContent.endMn}&userTypeCd=${searchContent.userTypeCd}&useYn=${searchContent.useYn}&userId=${searchContent.userId}&userNm=${searchContent.userNm}"
																class="pagination page-btn ${currentPage == page ? 'selected' : ''}">
																${page} </a>
														</c:if>
													</c:forEach>
												</div>
											</div>

											<!-- 뒤로 가는 버튼 -->
											<c:if test="${currentPage < totalPage}">
												<a
													href="/user?currentPage=${page}&startYr=${searchContent.startYr}&startMn=${searchContent.startMn}&endYr=${searchContent.endYr}&endMn=${searchContent.endMn}&userTypeCd=${searchContent.userTypeCd}&useYn=${searchContent.useYn}&userId=${searchContent.userId}&userNm=${searchContent.userNm}">
													<img
													src="/resources/img/log/free-icon-right-arrow-271228.png"
													alt="다음" />
												</a>
											</c:if>
											<c:if test="${currentPage == totalPage}">
												<a href="javascript:void(0);" class="disabled-link"> <img
													src="/resources/img/log/free-icon-right-arrow-271228.png"
													alt="다음" />
												</a>
											</c:if>

											<a
												href="/user?currentPage=${page}&startYr=${searchContent.startYr}&startMn=${searchContent.startMn}&endYr=${searchContent.endYr}&endMn=${searchContent.endMn}&userTypeCd=${searchContent.userTypeCd}&useYn=${searchContent.useYn}&userId=${searchContent.userId}&userNm=${searchContent.userNm}"><img
												src="/resources/img/log/free-icon-fast-forward-double-right-arrows-symbol-54366.png"
												alt="마지막" /></a>
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
	<div class="Modal2" id="modal-one">
		<div class="modal-dialog2">
			<div class="modal-header">
				<h2>승인요청</h2>
			</div>
			<div class="modal-body">
				<p>
					<b><span id="userIdPlaceholder"></span></b>님을 승인하시겠습니까?
				</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="modal_approve">수락</button>
				<button type="button" class="modal_cancel">거절</button>
				<button type="button" class="cancel">연기</button>
			</div>
		</div>
		<div class="modalBackground"></div>
	</div>
	<div class="Modal1" id="modal-one1">
		<div class="modal-dialog1">
			<div class="modal-body1">
				<%@ include file="userMngDtl.jsp"%>
			</div>
			<div class="modal-footer1" style="width: 80%; margin:auto;">
				<button type="button" class="cancel1">닫기</button>
			</div>
		</div>
		<div class="modalBackground"></div>
	</div>


	<script>
		var selectedUserId = null;

		function openModal1(userId) {
			selectedUserId = userId;
			loadUserDetails(userId); // 모달 내용을 동적으로 불러옴
			$(".Modal1").css("display", "block");
			$(".cancel1").css("display", "block");
		}
		$(".cancel1").click(function() {
			$(".Modal1").css("display", "none");
			selectedUserId = null;
		});

		function openModal(userId) {
			selectedUserId = userId;
			$("#userIdPlaceholder").text(userId);
			$(".Modal2").css("display", "block");
		}
		$(".cancel").click(function() {
			$(".Modal2").css("display", "none");
			selectedUserId = null;
		});
		// 모달 거절 버튼 이벤트 처리
		$(".modal_cancel").click(function() {
			if (selectedUserId) {
				// AJAX 요청을 통해 서버로 사용자 ID를 전달하고 업데이트 수행
				$.ajax({
					url : '/updateUserApproval', // 사용자 승인 업데이트를 수행하는 서버 엔드포인트
					type : 'POST',
					dataType : 'text',
					data : {
						userId : selectedUserId,
						approveYn : 'N'
					}, // 사용자 ID와 승인 상태 전달
					success : function(response) {
						if (response.trim() === "ok") {
							alert('사용자를 거절하였습니다.');
						} else {
							alert('사용자 거절에 실패하였습니다.');
						}
						// 모달 창 닫기 및 페이지 새로고침
						$(".Modal2").css("display", "none");
						location.reload();
					},
					error : function() {
						alert('서버 오류로 거절에 실패하였습니다.');
						$(".Modal2").css("display", "none");
					}
				});
			}
		});

		// 수락 버튼 이벤트 처리
		$(".modal_approve").click(function() {
			if (selectedUserId) {
				// AJAX 요청을 통해 서버로 사용자 ID를 전달하고 업데이트 수행
				$.ajax({
					url : '/updateUserApproval',
					type : 'POST',
					dataType : 'text',
					data : {
						userId : selectedUserId,
						approveYn : 'Y'
					},
					success : function(response) {
						if (response.trim() === "ok") {
							alert('사용자를 승인하였습니다.');
						} else {
							alert('사용자 승인에 실패하였습니다.');
						}
						// 모달 창 닫기 및 페이지 새로고침
						$(".Modal2").css("display", "none");
						location.reload();
					},
					error : function() {
						alert('서버 오류로 승인에 실패하였습니다.');
						$(".Modal2").css("display", "none");
					}
				});
			}
		});
		function loadUserDetails(userId) {
			$.ajax({
				url : '/user/info/' + userId,
				type : 'GET',
				success : function(data) {
					// 받아온 데이터를 모달 내부에 삽입
					$(".modal-body1").html(data);
				},
				error : function() {
					alert('사용자 정보를 불러오는데 실패하였습니다.');
				}
			});
		}
	</script>
</body>
</html>


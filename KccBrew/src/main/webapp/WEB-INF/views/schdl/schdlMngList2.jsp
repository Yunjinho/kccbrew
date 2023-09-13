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
<link rel="stylesheet" href="/resources/css/log/mylogtest.css" />
<link rel="stylesheet" href="/resources/css/log/content-template.css" />

<!-- font -->
<!-- notoSans -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans&display=swap"
	rel="stylesheet">
<!-- notoSans Kr -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans&family=Noto+Sans+KR&display=swap"
	rel="stylesheet">
<meta charset="UTF-8">
<title>Insert title here</title>

</head>

<body>
	<div id="page" class="page-nosubmenu">
		<!-- ********** header영역 시작********** -->
		<div id="page-header">
			<nav
				class="navbar navbar-default navbar-fixed-top headroom animated slideDown">
				<div class="container-fluid">

					<!-- 브랜드 로고 -->
					<div class="navbar-brand">
						<a href="" class="logo"> <img src="/resources/img/kcc.png"
							style="height: 100%; background: white;" />
						</a>
					</div>

					<!-- ********** header메뉴 시작********** -->
					<div class="usermenu">
						<ul class="nav nav-pills" role="tablist">
							<!-- 사용자 이름 -->
							<li class="user_department hidden-xs">이세은</li>

							<!-- 사용자 프로필 사진 -->
							<li class="user-info-menu"><a class="user-info-popover"
								tabindex="0" role="button" data-toggle="popover"
								data-placement="bottom" data-original-title="" title=""> <img
									src="/resources/img/log/f2.png" />
							</a>
								<div class="user-info-box popover-box">
									<div class="user-info-picture">
										<img src="./게시판_files/f1.png" alt="이세은 사진" class="userpicture">
										<h4>이세은</h4>
										<p class="department">화학과</p>
									</div>
									<ul class="user-info-submenu clearfix">
										<li class="items"><a
											href="https://cyber.inu.ac.kr/user/edit.jsp?id=86992">개인정보
												수정</a></li>
										<li class="items"><a
											href="https://cyber.inu.ac.kr/login/logout.jsp?sesskey=RWLqOGp4Jq">로그아웃</a></li>
									</ul>
									<ul class="user-info-shortcut">
										<li><a href="https://cyber.inu.ac.kr/user/files.jsp">파일
												관리</a></li>
									</ul>
								</div></li>

							<!-- 사용자 할일 -->
							<li class="user-courses"><a
								class="user-courses-popover icon-a" tabindex="0" role="button"
								data-toggle="popover" data-placement="bottom"
								data-original-title="" title="">
									<div class="header-icon-background">
										<img src="/resources/img/log/free-icon-checklist-3478402.png"
											alt="Check List" class="header-icon" />
									</div>
							</a>
								<div class="user-courses-box popover-box">
									<div class="popover-header">진행중인 강좌 (0)</div>
									<ul class="my-course-lists">
										<li class="nocourses">Loading...</li>
									</ul>
									<div class="popover-footer">
										<a href="https://cyber.inu.ac.kr/">MY COURSES</a>
									</div>
								</div></li>

							<!-- 알림 -->
							<li class="user-notification"><a
								class="icon-a user-noti-popover" tabindex="0" role="button"
								data-toggle="popover" data-placement="bottom"
								data-original-title="" title="">
									<div class="header-icon-background">
										<img
											src="/resources/img/log/free-icon-notification-bell-3913239.png"
											alt="Check List" class="header-icon" />
									</div>
							</a>
								<div class="user-noti-box popover-box">
									<div class="popover-header">
										<span>전체 알림</span> <img
											src="/resources/img/log/popover-icon.png" alt="" class="gear" />
									</div>
									<div class="user-noti-lists">
										<div class="nomessage">Loading...</div>
									</div>
									<div class="popover-footer">
										<a href="https://cyber.inu.ac.kr/local/ubnotification/">모두
											보기</a>
									</div>
								</div></li>

							<!-- 마이 메뉴 -->
							<li class="user-product"><a class="user-menu-product icon-a"
								tabindex="0" role="button" data-toggle="popover"
								data-original-title="" title=""><div
										class="header-icon-background">
										<img src="/resources/img/log/free-icon-menu-7421181.png"
											alt="Check List" class="header-icon" />
									</div></a>
								<div class="hide menu-product">
									<div class="menu-product-itl">
										<h5>
											교육혁신원 홈페이지 <a href="http://ctl.inu.ac.kr/" target="_blank"><img
												src="./게시판_files/itl_home.png" alt=""></a>
										</h5>
										<ul>
											<li><a href="http://www.inu.ac.kr/" target="_blank">인천대학교
													홈페이지</a></li>
											<li><a href="http://www.kocw.net/home/index.do"
												target="_blank">KOCW 강의공개 서비스</a></li>
											<li><a href="http://starinu.inu.ac.kr/" target="_blank">학생역량관리시스템(STARinU)</a></li>
											<li><a href="http://lib.inu.ac.kr/" target="_blank">도서관</a></li>
											<li><a href="http://job.inu.ac.kr/" target="_blank">취업경력개발원</a></li>
											<li><a
												href="http://rnd.inu.ac.kr/user/indexMain.do?siteId=rnd"
												target="_blank">산학협력단</a></li>
											<li><a href="http://scc.inu.ac.kr/" target="_blank">학생생활상담소</a></li>
										</ul>
									</div>
								</div></li>

							<!-- 로그아웃 -->
							<li class="active user-login user-logout hidden-xs"><a
								href="" class="btn btn-person">로그아웃</a></li>
						</ul>
					</div>
					<!-- ********** header메뉴 끝********** -->

					<div class="coursename">
						<h1>&nbsp;</h1>
					</div>
				</div>
			</nav>
		</div>
		<!-- ********** header영역 끝********** -->

		<!-- ********** 왼쪽 메뉴 시작 ********** -->
		<div id="page-lnb">
			<ul class="left-menus">

				<!-- 관리자 메뉴 -->

				<!-- 메뉴1 -->
				<li class="" data-original-title="" title=""><a href=""
					class="left-menu-link left-menu-link-mypage" title="My Page"
					data-toggle="tooltip" data-placement="right">
						<h5 class="">A/S관리</h5>
				</a>
					<ul>
						<li class=""><a href="https://cyber.inu.ac.kr/" title="">Dashboard</a></li>
						<li class=""><a href="https://cyber.inu.ac.kr/user/files.jsp"
							title="">파일 관리</a></li>
						<li class=""><a
							href="https://cyber.inu.ac.kr/mod/ubboard/my.jsp" title="">진행강좌
								공지</a></li>
						<li class=""><a
							href="https://cyber.inu.ac.kr/user/edit.jsp?id=86992" title="">개인정보
								수정</a></li>
					</ul></li>

				<!-- 메뉴2 -->
				<li class="active active-fix" data-original-title="" title=""><a
					href="" class="left-menu-link left-menu-link-mycourses"
					title="교과 과정" data-toggle="tooltip" data-placement="right">
						<h5 class="">시스템관리</h5>
				</a>
					<ul>
						<li class="active"><a href="" title="active">로그조회</a></li>
						<li class=""><a href="" title="">파일조회</a></li>
					</ul></li>

				<!-- 메뉴3 -->
				<li class="" data-original-title="" title=""><a href=""
					class="left-menu-link left-menu-link-irrcourse" title="비교과 과정"
					data-toggle="tooltip" data-placement="right">
						<h5 class="nosubmenu">코드관리</h5>
				</a></li>

				<!-- 메뉴4 -->
				<li class="" data-original-title="" title=""><a href=""
					class="left-menu-link left-menu-link-eclass" title="자율강좌"
					data-toggle="tooltip" data-placement="right">
						<h5 class="nosubmenu">수리기사관리</h5>
				</a></li>

				<!-- 메뉴5 -->
				<li class="" data-original-title="" title=""><a href=""
					class="left-menu-link left-menu-link-onlinep" title="교수지원"
					data-toggle="tooltip" data-placement="right">
						<h5 class="">점포관리</h5>
				</a>
					<ul>
						<li class=""><a href="" title="">수강 신청</a></li>
						<li class=""><a href="" title="">수강 현황</a></li>
						<li class=""><a href="" title="">수료 확인</a></li>
					</ul></li>

				<!-- 메뉴6 -->
				<li t class="" data-original-title="" title=""><a href=""
					class="left-menu-link left-menu-link-online" title="학습지원"
					data-toggle="tooltip" data-placement="right">
						<h5 class="">회원관리</h5>
				</a>
					<ul>
						<li class=""><a href="" title="">수강 신청</a></li>
						<li class=""><a href="" title="">수강 현황</a></li>
						<li class=""><a href="" title="">수료 확인</a></li>
					</ul></li>
			</ul>
		</div>
		<!-- ********** 왼쪽 메뉴 끝 ********** -->

		<div id="page-mask">
			<div id="page-container" class="">
				<div id="page-content" class="clearfix">
					<div id="page-content-wrap">

						<!-- ********** 페이지 네비게이션 시작 ********** -->
						<div class="page-content-navigation">
							<ol class="breadcrumb">
								<li class="breadcrumb-home"><a href="">스케줄관리</a></li>
								<li>
									<div class="header-icon-background">
										<img
											src="<c:url value='/img/log/free-icon-right-arrow-271228.png' />"
											alt="Check List" class="header-icon" />
									</div>
								</li>
								<li><a href="<c:url value='/schedule2' />">휴가사용현황</a></li>
							</ol>
						</div>
						<!-- ********** 페이지 네비게이션 끝 ********** -->

						<div id="region-main">
							<div role="main">
								<span id="maincontent"></span>
								<div class="user-past">

									<!-- ********** 세은 로그 관련 내용 시작 ********** -->
									<div id="content">
										<h2 class="heading">휴가사용현황</h2>
										<!-- 로그 검색 -->

										<form action="/schedule2" method="get">
											<table id="search-box">
												<!-- 1행 -->
												<c:set var="today" value="<%=new java.util.Date()%>" />
												<fmt:formatDate value="${today}" pattern="yyyy"
													var="nowYear" />
												<tr>

													<th>기간</th>
													<!-- 시작 연도 선택 필드 -->
													<td><select class="tx2" name="startYr" id="startYr">
															<option value="">연도</option>
															<c:forEach var="i" begin="0" end="9">
																<c:set var="year" value="${nowYear - i}" />
																<option value="${year}"
																	${param.startYr == year ? 'selected' : ''}>${year}년</option>
															</c:forEach>
													</select></td>
													<!-- 시작 월 선택 필드 -->
													<td><select class="tx2" name="startMn" id="startMn">
															<option value="">월</option>
															<c:forEach var="month" begin="1" end="12">
																<option value="${month}"
																	${param.startMn == month ? 'selected' : ''}>${month}월</option>
															</c:forEach>
													</select></td>
													<td>~</td>
													<!-- 종료 연도 선택 필드 -->
													<td><select class="tx2" name="endYr" id="endYr">
															<option value="">연도</option>
															<c:forEach var="i" begin="0" end="9">
																<c:set var="year" value="${nowYear - i}" />
																<option value="${year}"
																	${param.endYr == year ? 'selected' : ''}>${year}년</option>
															</c:forEach>
													</select></td>
													<!-- 종료 월 선택 필드 -->
													<td><select class="tx2" name="endMn" id="endMn">
															<option value="">월</option>
															<c:forEach var="month" begin="1" end="12">
																<option value="${month}"
																	${param.endMn == month ? 'selected' : ''}>${month}월</option>
															</c:forEach>
													</select></td>
												</tr>

												<tr>
													<th>위치</th>

													<td><select class="tx2" name="storeLocation"
														onchange="chg()">
															<option>지역 대분류</option>
															<option value="2"
																${param.storeLocation == '2' ? 'selected' : ''}>서울</option>
															<option value="31"
																${param.storeLocation == '31' ? 'selected' : ''}>경기도</option>
															<option value="32"
																${param.storeLocation == '32' ? 'selected' : ''}>인천</option>
													</select></td>

													<td><select class="tx2" name="storeSubLocation" disabled>
															<option>지역 소분류</option>
													</select></td>

													<th>유형</th>
													<td><select class="tx2" name="userType"
														onchange="updateUserType()">
															<option value="">사용자 유형</option>
															<option value="기사"
																${param.userType == '기사' ? 'selected' : ''}>기사</option>
															<option value="점주"
																${param.userType == '점주' ? 'selected' : ''}>점주</option>
													</select></td>

													<th>검색어</th>
													<td><select class="tx2" name="searchKeword"
														onchange="chgName(this)">
															<option value="">검색어</option>
															<option value="회원ID"
																${param.searchKeword == '회원ID' ? 'selected' : ''}>회원ID</option>
															<option value="회원이름"
																${param.searchKeword == '회원이름' ? 'selected' : ''}>회원이름</option>
															<option value="지점ID"
																${param.searchKeword == '지점ID' ? 'selected' : ''}>지점ID</option>
															<option value="지점명"
																${param.searchKeword == '지점명' ? 'selected' : ''}>지점명</option>
													</select></td>
													<td><input type="text" id="search-word"
														name="searchKeword" placeholder="키워드 선택 후 입력해주세요" required
														disabled></td>

												</tr>

											</table>
											<button type="submit" class="form-btn">검색</button>
											<button type="reset" class="form-btn">초기화</button>
										</form>




										<!-- 검색어 선택한 것이 input요소의 name으로 설정 -->
										<script>
											function chgName(selectElement) {
												const selectedValue = selectElement.value; // 선택한 값
												const inputElement = document
														.getElementById('search-word'); // 검색어 입력 필드

												if (selectedValue === '') {
													// 선택한 값이 빈 문자열인 경우, input 요소를 비활성화
													inputElement.disabled = true;
												} else {
													// 선택한 값이 빈 문자열이 아닌 경우, input 요소를 활성화
													inputElement.disabled = false;
												}
											}
										</script>


										<!-- 사용자 유형 선택에 따른 검색어 추출 -->
										<script>
											function updateUserType() {

												const userTypeSelect = document
														.getElementsByName('userType')[0]; // 사용자 유형 선택 요소
												const searchSelect = document
														.getElementsByName('searchKeword')[0]; // 검색어 선택 요소

												if (userTypeSelect.value === '기사') {
													// '기사'가 선택된 경우, '회원ID'와 '회원이름' 옵션만 남기고 나머지는 삭제
													removeAllOptions(searchSelect);
													addOption(searchSelect,
															'검색어', '');
													addOption(searchSelect,
															'회원ID', 'userId');
													addOption(searchSelect,
															'회원이름', 'userName');
												} else {
													removeAllOptions(searchSelect);
													addOption(searchSelect,
															'검색어', '');
													addOption(searchSelect,
															'회원ID', 'userId');
													addOption(searchSelect,
															'회원이름', 'userName');
													addOption(searchSelect,
															'지점ID', 'storeId');
													addOption(searchSelect,
															'지점명', 'storeName');
												}

												if (typeof searchSelect.onchange === 'function') {
													searchSelect.onchange();
												}
											}

											function removeAllOptions(
													selectElement) {
												while (selectElement.options.length > 0) {
													selectElement.remove(0);
												}
											}

											function addOption(selectElement,
													text, value) {
												const option = new Option(text,
														value);
												selectElement.add(option);
											}
										</script>


										<!-- 로케이션을 서울로 지정 시, 서브로케이션 활성화 -->
										<script>
function chg() {
  const storeLocationSelect = document.getElementsByName('storeLocation')[0];
  const storeSubLocationSelect = document.getElementsByName('storeSubLocation')[0];

  // 대분류 선택값 가져오기
  const selectedStoreLocation = storeLocationSelect.value;

  // 소분류 옵션 초기화
  storeSubLocationSelect.innerHTML = '<option>지역 소분류</option>';

  // 대분류에 따라 소분류 옵션 추가
  if (selectedStoreLocation === '2') {
    storeSubLocationSelect.innerHTML += `
      <option value="02-200" ${param.storeSubLocation == '02-200' ? 'selected' : ''}>양천</option>
      <option value="02-300" ${param.storeSubLocation == '02-300' ? 'selected' : ''}>은평,마포,서대문</option>
  	<option value="02-400"
		${param.storeSubLocation == '02-400' ? 'selected' : ''}>송파,강동,중량,광진,선동</option>
	<option value="02-500"
		${param.storeSubLocation == '02-500' ? 'selected' : ''}>서초,강남,과천시</option>
	<option value="02-700"
		${param.storeSubLocation == '02-700' ? 'selected' : ''}>마포,용산,종로</option>
	<option value="031-200"
		${param.storeSubLocation == '031-200' ? 'selected' : ''}>수원시</option>
    `;
    storeSubLocationSelect.disabled = false;
  } else {
	    storeSubLocationSelect.disabled = true;
	  }
}
</script>




										<!-- 로그 리스트 -->
										<div id="logTable">
											<div>
												<p class="data-info">
													전체<b><span><c:out value="${totalDataNumber}" /></span></b>건<span
														id="text-separator"> | </span><b><span><c:out
																value="${currentPage}" /></span></b>/<b><span><c:out
																value="${totalPage}" /></span></b>쪽
												</p>
											</div>
											<table>
												<thead>
													<tr>
														<th>순번</th>
														<th>회원구분</th>
														<th>회원ID</th>
														<th>회원이름</th>
														<th>지점ID</th>
														<th>지점명</th>
														<th>위치코드</th>
														<th>신청일</th>
														<th>시작일</th>
														<th>종료일</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="schedule2" items="${schedules}">
														<tr>
															<td><c:out value="${schedule2.rowNumber}" /></td>
															<td><c:out value="${schedule2.userType}" /></td>
															<td><c:out value="${schedule2.userId}" /></td>
															<td><c:out value="${schedule2.userName}" /></td>
															<td><c:choose>
																	<c:when test="${schedule2.storeId != 0}">
																		<c:out value="${schedule2.storeId}" />
																	</c:when>
																	<c:otherwise>
																		<!-- 0인 경우 아무것도 표시하지 않음 -->
																	</c:otherwise>
																</c:choose></td>
															<td><c:out value="${schedule2.storeName}" /></td>
															<td><c:out value="${schedule2.locationCd}" /></td>
															<td><c:out value="${schedule2.appDate}" /></td>
															<td><c:out value="${schedule2.startDate}" /></td>
															<td><c:out value="${schedule2.endDate}" /></td>
														</tr>
													</c:forEach>
											</table>
										</div>

										<!-- 페이징 -->
										<%-- 									<div class="paging pagination">

											<!-- 맨 앞으로 가는 버튼 -->
											<a
												href="/schedule2?currentPage=1
&startYr=${searchContent.startYr}&startMn=${searchContent.startMn}&endYr=${searchContent.endYr}&endMn=${searchContent.endMn}
&userType=${searchContent.userType}
&userId=${searchContent.userId}
&userName=${searchContent.userName}
&storeId=${searchContent.storeId}
&storeName=${searchContent.storeName}
&storeLocation=${searchContent.storeLocation}
&scheduleType=${searchContent.scheduleType}"><img
												src="/resources/img/log/free-icon-left-chevron-6015759.png"
												alt=" 처음" /></a>

											<c:choose>
												<c:when test="${currentPage > 1}">
													<a
														href="/schedule2?currentPage=${currentPage - 1}&startYr=${searchContent.startYr}&startMn=${searchContent.startMn}&endYr=${searchContent.endYr}&endMn=${searchContent.endMn}&userType=${searchContent.userType}&userId=${searchContent.userId}&userName=${searchContent.userName}&storeId=${searchContent.storeId}&storeName=${searchContent.storeName}&storeLocation=${searchContent.storeLocation}&scheduleType=${searchContent.scheduleType}">
														<img
														src="/resources/img/log/free-icon-left-arrow-271220.png"
														alt="이전" />
													</a>
												</c:when>
												<c:otherwise>
													<a href="#" class="disabled-link"> <img
														src="/resources/img/log/free-icon-left-arrow-271220.png"
														alt="이전" />
													</a>
												</c:otherwise>
											</c:choose>

											<!-- 리스트 목록 나열 -->
											<div id="number-list">
												<div class="page-btn">
													<c:forEach var="page" begin="${sharePage * 10 + 1}"
														end="${(sharePage + 1) * 10}" step="1">
														<c:if test="${page <= totalPage}">
															<a
																href="/schedule2?currentPage=${page}
&startYr=${searchContent.startYr}&startMn=${searchContent.startMn}&endYr=${searchContent.endYr}&endMn=${searchContent.endMn}
&userType=${searchContent.userType}
&userId=${searchContent.userId}
&userName=${searchContent.userName}
&storeId=${searchContent.storeId}
&storeName=${searchContent.storeName}
&storeLocation=${searchContent.storeLocation}
&scheduleType=${searchContent.scheduleType}"
																class="pagination page-btn ${currentPage == page ? 'selected' : ''}">
																${page} </a>
														</c:if>
													</c:forEach>
												</div>
											</div>

											<!-- 뒤로 가는 버튼 -->
											<c:if test="${currentPage < totalPage}">
												<a
													href="/schedule2?currentPage=${currentPage + 1}
&startYr=${searchContent.startYr}&startMn=${searchContent.startMn}&endYr=${searchContent.endYr}&endMn=${searchContent.endMn}
&userType=${searchContent.userType}
&userId=${searchContent.userId}
&userName=${searchContent.userName}
&storeId=${searchContent.storeId}
&storeName=${searchContent.storeName}
&storeLocation=${searchContent.storeLocation}
&scheduleType=${searchContent.scheduleType}">
													<img
													src="/resources/img/log/free-icon-right-arrow-271228.png"
													alt="다음" />
												</a>
											</c:if>
											<c:if test="${currentPage == totalPage}">
												<a href="" class="disabled-link"> <img
													src="/resources/img/log/free-icon-right-arrow-271228.png"
													alt="다음" />
												</a>
											</c:if>

											<a
												href="/schedule2?currentPage=${totalPage}
&startYr=${searchContent.startYr}&startMn=${searchContent.startMn}&endYr=${searchContent.endYr}&endMn=${searchContent.endMn}
&userType=${searchContent.userType}
&userId=${searchContent.userId}
&userName=${searchContent.userName}
&storeId=${searchContent.storeId}
&storeName=${searchContent.storeName}
&storeLocation=${searchContent.storeLocation}
&scheduleType=${searchContent.scheduleType}"><img
												src="/resources/img/log/free-icon-fast-forward-double-right-arrows-symbol-54366.png"
												alt="마지막" /></a>
										</div> --%>
									</div>
									<!-- ********** 세은 로그 관련 내용 끝 ********** -->
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="<c:url value='/js/bootstrap.min.js' />"></script>
	<script src="<c:url value='/js/jquery.min.js' />"></script>
</body>
</html>
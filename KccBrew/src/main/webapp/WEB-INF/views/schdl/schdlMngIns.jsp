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
<link rel="stylesheet" href="/resources/css/schdl/myinsertform.css" />

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

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>

<body>
	<%-- Import the necessary Java classes --%>
	<%@ page import="java.util.Date"%>

	<%-- Get the current date and time --%>
	<%
		Date now = new Date();
	%>

	<%-- Set the "now" variable using JSTL --%>
	<c:set var="now" value="<%=now%>" />


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
								<li><a href="<c:url value='/schedule2' />">휴가등록</a></li>
							</ol>
						</div>
						<!-- ********** 페이지 네비게이션 끝 ********** -->

						<div id="region-main">
							<div role="main">
								<span id="maincontent"></span>
								<div class="user-past">

									<!-- ********** 세은 로그 관련 내용 시작 ********** -->
									<div id="content">
										<h2 class="heading">휴가등록</h2>
										<!-- 로그 검색 -->

										<div class="subtitle">수리기사</div>
										<table id="search-box">
											<tr>
												<th>유형</th>
												<td><c:out value="" /></td>

												<th>ID</th>
												<td><c:out value="" /></td>

												<th>이름</th>
												<td><c:out value="" /></td>

												<th>연락처</th>
												<td><c:out value="" /></td>

											</tr>

											<tr>
												<th>사용장비코드</th>
												<td><c:out value="" /></td>

												<th>장비명</th>
												<td><c:out value="" /></td>

												<th>지역코드</th>
												<td><c:out value="" /></td>

												<th>지역명</th>
												<td><c:out value="" /></td>

											</tr>
										</table>


										<!-- 점포일 경우 -->
										<div class="subtitle">점포</div>
										<table id="search-box">
											<!-- 점주정보 -->
											<tr>
												<th>유형</th>
												<td><c:out value="점주" /></td>

												<th>점주ID</th>
												<td><c:out value="${user.userId}" /></td>

												<th>점주이름</th>
												<td><c:out value="" /></td>

												<th>점주연락처</th>
												<td><c:out value="" /></td>

											</tr>

											<!-- 점포정보 -->
											<tr>
												<th>점포ID</th>
												<td><c:out value="${store.storeSeq}" /></td>

												<th>점포명</th>
												<td><c:out value="${store.storeNm}" /></td>

												<th>점포주소</th>
												<td><c:out value="" /></td>

												<th>점포연락처</th>
												<td><c:out value="" /></td>

											</tr>
										</table>



										<!-- 로케이션을 서울로 지정 시, 서브로케이션 활성화 -->
										<script>
											function chg() {
												var locationSelect = document
														.querySelector("select[name='storeLocation']");
												var subLocationSelect = document
														.querySelector("select[name='storeSubLocation']");

												if (locationSelect.value === '2') {
													subLocationSelect.disabled = false; // 서브 로케이션 활성화
												} else {
													subLocationSelect.disabled = true; // 서브 로케이션 비활성화
												}
											}
										</script>



										<!-- 로그 리스트 -->
										<div id="logTable">
											<div class="subtitle">
												휴가신청내역(전체<b><span><c:out
															value="${totalDataNumber}" /></span></b>건)
											</div>
											<!-- 휴가신청 조회 및 삭제 -->
											<form id="deleteForm" action="/holiday/delete" method="post">
												<table>
													<thead>
														<tr>
															<th>순번</th>
															<!-- <th>휴가아이디</th> -->
															<th>휴가신청일</th>
															<th>휴가시작일</th>
															<th>휴가종료일</th>
															<th>일수</th>
															<th>실제사용</th>
															<th>선택</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="holiday" items="${holidays}"
															varStatus="loop">

															<tr>
																<td><c:out value="${loop.index + 1}" /></td>
																<%-- <td><c:out value="${holiday.holidaySeq}" /></td> --%>
																<td><c:out value="${holiday.appDate}" /></td>
																<td><c:out value="${holiday.startDate}" /></td>
																<td><c:out value="${holiday.endDate}" /></td>
																<td><c:set var="startDate"
																		value="${holiday.startDate.time}" /> <c:set
																		var="endDate" value="${holiday.endDate.time}" /> <c:set
																		var="dateDifference"
																		value="${(endDate - startDate) / (1000 * 3600 * 24) + 1}" />
																	<fmt:formatNumber var="formattedDateDifference"
																		value="${dateDifference}" /> <c:out
																		value="${formattedDateDifference}" /></td>
																<td><c:out value="${holiday.actualUse}" /></td>



																<td><c:choose>
																		<c:when
																			test="${holiday.actualUse == 'N' || holiday.startDate <= now}">
																			<input type="checkbox" name="holidaySeq" disabled />
																		</c:when>
																		<c:otherwise>
																			<input type="checkbox" name="holidaySeq"
																				value="${holiday.holidaySeq}" />
																		</c:otherwise>
																	</c:choose></td>

															</tr>


														</c:forEach>
													</tbody>
												</table>
												<button type="button" id="cancelButton">휴가 취소</button>
											</form>
											
  <div id="myModal" class="modal">
        <div class="modal-content">
            <h2>휴가 취소 확인</h2>
            <p>휴가를 취소하시겠습니까?</p>
            <button id="confirmYes">예</button>
            <button id="confirmNo">아니오</button>
        </div>
    </div>
    
    <script>
 // 휴가 취소 버튼 클릭 시 모달 창 열기
    document.getElementById("cancelButton").addEventListener("click", function() {

        var modal = document.getElementById("myModal");
        modal.classList.add("active");
    });

    // 예 버튼 클릭 시
    document.getElementById("confirmYes").addEventListener("click", function() {
        // 여기에서 취소 로직을 수행하거나, 폼을 서버로 제출할 수 있습니다.
        // 폼을 서버로 제출하려면 JavaScript를 사용하여 폼을 선택하고 submit() 메서드를 호출하면 됩니다.
        var form = document.getElementById("deleteForm");
        form.submit();
        
        // 모달 창 닫기
        var modal = document.getElementById("myModal");
        modal.classList.remove("active");
    });

    // 아니오 버튼 클릭 시 모달 창 닫기
    document.getElementById("confirmNo").addEventListener("click", function() {
        var modal = document.getElementById("myModal");
        modal.classList.remove("active");
    });
    </script>




										</div>

										<!-- 휴가신청 -->
										<div class="subtitle">휴가신청</div>
										
										<div id="applyModal" class="modal">
    <div class="modal-content">
        <h2>휴가 신청 확인</h2>
        <p>휴가를 신청하시겠습니까?</p>
        <button id="applyYes">예</button>
        <button id="applyNo">아니오</button>
    </div>
</div>
										
										<form action="/holiday/add" method="post">
											<table id="search-box">
												<tr>
													<th>휴가신청일</th>
													<td><c:out
															value="<%=new java.text.SimpleDateFormat(\"yyyy-MM-dd\").format(new java.util.Date())%>" /></td>

													<th>휴가일</th>
													<td><input type="date" name="startDate" id="startDate" />
														~ <input type="date" name="endDate" id="endDate" />
														(사용일수: <span id="usedDays"></span> / 잔여일수: <span
														id="remainingDays"></span>)</td>
												</tr>
											</table>

											<button type="button" id="applyButton">휴가신청</button>
											<button type="reset" id="resetButton">초기화</button>
										</form>


										<script>
    // 모델에서 잔여일수 데이터 가져와서 표시
    var remainingDays = ${remainingDays};
    document.getElementById("remainingDays").textContent = remainingDays;

    function calculateDays() {
        var startDate = new Date(document.getElementById("startDate").value);
        var endDate = new Date(document.getElementById("endDate").value);

        if (!isNaN(startDate) && !isNaN(endDate)) {
            var timeDiff = endDate - startDate + 1;
            var daysDiff = Math.ceil(timeDiff / (1000 * 3600 * 24)); 
            document.getElementById("usedDays").textContent = daysDiff;
            document.getElementById("remainingDays").textContent = remainingDays - daysDiff;
        } else {
            document.getElementById("usedDays").textContent = "";
        }
    }

    // input 필드 값이 변경될 때마다 calculateDays 함수 호출
    document.getElementById("startDate").addEventListener("change", calculateDays);
    document.getElementById("endDate").addEventListener("change", calculateDays);

    // 초기화 버튼을 눌렀을 때
    document.getElementById("resetButton").addEventListener("click", function () {
        // 입력 필드를 초기화
        document.getElementById("startDate").value = "";
        document.getElementById("endDate").value = "";

        // 일수 및 잔여일수를 초기 상태로 되돌림
        document.getElementById("usedDays").textContent = "";
        document.getElementById("remainingDays").textContent = remainingDays;
    });
</script>

<script>
//휴가신청 버튼 클릭 시 모달 창 열기
document.getElementById("applyButton").addEventListener("click", function() {
 var modal = document.getElementById("applyModal");
 modal.classList.add("active");
});

//예 버튼 클릭 시
document.getElementById("applyYes").addEventListener("click", function() {
 // 여기에서 휴가 신청 로직을 수행하거나, 폼을 서버로 제출할 수 있습니다.
 // 폼을 서버로 제출하려면 JavaScript를 사용하여 폼을 선택하고 submit() 메서드를 호출하면 됩니다.
 var form = document.querySelector("form");
 form.submit();
 
 // 모달 창 닫기
 var modal = document.getElementById("applyModal");
 modal.classList.remove("active");
});

//아니오 버튼 클릭 시 모달 창 닫기
document.getElementById("applyNo").addEventListener("click", function() {
 var modal = document.getElementById("applyModal");
 modal.classList.remove("active");
});
</script>
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
</body>
</html>
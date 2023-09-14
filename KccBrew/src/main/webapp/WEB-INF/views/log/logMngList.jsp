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
								<li class="breadcrumb-home"><a href="">시스템관리</a></li>
								<li>
									<div class="header-icon-background">
										<img
											src="<c:url value='/img/log/free-icon-right-arrow-271228.png' />"
											alt="Check List" class="header-icon" />
									</div>
								</li>
								<li><a href="<c:url value='/log' />">로그조회</a></li>
							</ol>
						</div>
						<!-- ********** 페이지 네비게이션 끝 ********** -->

						<div id="region-main">
							<div role="main">
								<span id="maincontent"></span>
								<div class="user-past">

									<!-- ********** 세은 로그 관련 내용 시작 ********** -->
									<div id="content">
										<h2 class="heading">로그조회</h2>
										<!-- 로그 검색 -->
										<form action="/log" method="get">
											<table id="search-box">
												<!-- 1행 -->
												<c:set var="today" value="<%=new java.util.Date()%>" />
												<fmt:formatDate value="${today}" pattern="yyyy"
													var="nowYear" />
												<tr>
													<th>기간</th>
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

													<td>
														<button type="submit" onclick="" class="form-btn">이동</button>
													</td>
												</tr>


												<!-- 2행 -->
												<tr>
													<th>경로</th>
													<!-- Input field for URI -->
													<td><input type="search" name="uri"
														placeholder="경로를 입력하세요" value="${param.uri}"></td>
													<th>화면</th>
													<td><input type="search" name="view"
														placeholder="화면을 입력하세요" value="${param.view}"></td>
													<th>사용자ID</th>
													<td colspan="2"><input type="search" name="userId"
														placeholder="사용자ID를 입력하세요" value="${param.userId}"></td>

												</tr>
												<!-- 3행 -->
												<tr>
													<th>사용자 유형</th>
													<td><select class="tx2" name="userType" id=""
														onchange="javascript:chg();">
															<option value="">사용자</option>
															<option value="admin"
																${param.userType == 'admin' ? 'selected' : ''}>관리자</option>
															<option value="store-owner"
																${param.userType == 'store-owner' ? 'selected' : ''}>점주</option>
															<option value="mechanic"
																${param.userType == 'mechanic' ? 'selected' : ''}>기사</option>
													</select></td>
													<th>IP</th>
													<td><input type="search" name="ip"
														placeholder="IP를 입력하세요" value="${param.ip}"></td>
													<th>상태코드</th>
													<td colspan="2"><select class="tx2" name="statusCode"
														id="yr" onchange="javascript:chg();">
															<option value="">상태코드</option>
															<option value="200 OK"
																${param.statusCode == '200 OK' ? 'selected' : ''}>200
																OK</option>
															<option value="201 Created"
																${param.statusCode == '201 Created' ? 'selected' : ''}>201
																Created</option>
															<option value="400 Bad Request"
																${param.statusCode == '400 Bad Request' ? 'selected' : ''}>400
																Bad Request</option>
															<option value="403 Forbidden"
																${param.statusCode == '403 Forbidden' ? 'selected' : ''}>403
																Forbidden</option>
															<option value="404 Not Found"
																${param.statusCode == '404 Not Found' ? 'selected' : ''}>404
																Not Found</option>
															<option value="500 Internal Server Error"
																${param.statusCode == '500 Internal Server Error' ? 'selected' : ''}>500
																Internal Server Error</option>
													</select></td>
												</tr>
												<!-- 4행 -->
												<tr>
													<td colspan="7"
														style="text-align: center; border-bottom: 0px;">
														<div>
															<button type="submit" class="form-btn">검색</button>
														</div>
													</td>
												</tr>
											</table>
										</form>



										<!-- 로그 리스트 -->
										<div id="logTable">
											<div>
												<p class="data-info">
													전체<b><span><c:out value="${totalLog}" /></span></b>건<span
														id="text-separator"> | </span><b><span><c:out
																value="${currentPage}" /></span></b>/<b><span><c:out
																value="${totalPage}" /></span></b>쪽
												</p>
											</div>
											<table>
												<thead>
													<tr>
														<th>Log Seq</th>
														<th>Date</th>
														<th>URI</th>
														<th>View</th>
														<th>User ID</th>
														<th>User Type</th>
														<th>IP</th>
														<th>Status Code</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="log" items="${logs}">
														<tr>
															<td><c:out value="${log.logSeq}" /></td>
															<td><c:out value="${log.date}" /></td>
															<td><c:out value="${log.uri}" /></td>
															<td><c:out value="${log.view}" /></td>
															<td><c:out value="${log.userId}" /></td>
															<td><c:out value="${log.userType}" /></td>
															<td><c:out value="${log.ip}" /></td>
															<td><c:out value="${log.statusCode}" /></td>
														</tr>
													</c:forEach>






												</tbody>
											</table>
										</div>

										<!-- 페이징 -->
										<div class="paging pagination">

											<!-- 앞으로 가는 버튼 -->
											<a href="/log?currentPage=1&startYr=${searchContent.startYr}&startMn=${searchContent.startMn}&endYr=${searchContent.endYr}&endMn=${searchContent.endMn}&uri=${searchContent.uri}&view=${searchContent.view}&userId=${searchContent.userId}&userType=${searchContent.userType}&ip=${searchContent.ip}&statusCode=${searchContent.statusCode}"><img
												src="/resources/img/log/free-icon-left-chevron-6015759.png"
												alt=" 처" /></a>

											<c:choose>
												<c:when test="${currentPage > 1}">
													<a href="/log?currentPage=${currentPage - 1}&startYr=${searchContent.startYr}&startMn=${searchContent.startMn}&endYr=${searchContent.endYr}&endMn=${searchContent.endMn}&uri=${searchContent.uri}&view=${searchContent.view}&userId=${searchContent.userId}&userType=${searchContent.userType}&ip=${searchContent.ip}&statusCode=${searchContent.statusCode}"><img
														src="/resources/img/log/free-icon-left-arrow-271220.png"
														alt="이" /></a>
												</c:when>
												<c:otherwise>
													<a href="#" disabled="disabled"><img
														src="/resources/img/log/free-icon-left-arrow-271220.png"
														alt="이" /></a>
												</c:otherwise>
											</c:choose>

											<!-- 리스트 목록 나열 -->
											<div id="number-list">
												<div class="page-btn">
													<c:forEach var="page" begin="${sharePage * 10 + 1}"
														end="${(sharePage + 1) * 10}" step="1">
														<c:if test="${page <= totalPage}">
															<a
																href="/log?currentPage=${page}&startYr=${searchContent.startYr}&startMn=${searchContent.startMn}&endYr=${searchContent.endYr}&endMn=${searchContent.endMn}&uri=${searchContent.uri}&view=${searchContent.view}&userId=${searchContent.userId}&userType=${searchContent.userType}&ip=${searchContent.ip}&statusCode=${searchContent.statusCode}"
																class="pagination page-btn ${currentPage == page ? 'selected' : ''}">
																${page} </a>
														</c:if>
													</c:forEach>
												</div>
											</div>

											<!-- 뒤로 가는 버튼 -->
											<c:if test="${currentPage < totalPage}">
												<a href="/log?currentPage=${currentPage + 1}&startYr=${searchContent.startYr}&startMn=${searchContent.startMn}&endYr=${searchContent.endYr}&endMn=${searchContent.endMn}&uri=${searchContent.uri}&view=${searchContent.view}&userId=${searchContent.userId}&userType=${searchContent.userType}&ip=${searchContent.ip}&statusCode=${searchContent.statusCode}"> <img
													src="/resources/img/log/free-icon-right-arrow-271228.png"
													alt="다" />
												</a>
											</c:if>
											<c:if test="${currentPage == totalPage}">
												<a href="javascript:void(0);" class="disabled-link"> <img
													src="/resources/img/log/free-icon-right-arrow-271228.png"
													alt="다" />
												</a>
											</c:if>

											<a href="/log?currentPage=${totalPage}&startYr=${searchContent.startYr}&startMn=${searchContent.startMn}&endYr=${searchContent.endYr}&endMn=${searchContent.endMn}&uri=${searchContent.uri}&view=${searchContent.view}&userId=${searchContent.userId}&userType=${searchContent.userType}&ip=${searchContent.ip}&statusCode=${searchContent.statusCode}"><img
												src="/resources/img/log/free-icon-fast-forward-double-right-arrows-symbol-54366.png"
												alt="마" /></a>
										</div>

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
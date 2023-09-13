<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/comm/register.css">
<link rel="stylesheet" href="resources/css/comm/common.css">
<link rel="stylesheet" href="${path}/resources/css/comm/reset.css"/>
<link rel="stylesheet" href="${path}/resources/css/comm/footer.css"/>
<script src="http://code.jquery.com/jquery-latest.js"></script> 
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
<script src="<c:url value="resources/js/comm/register.js"/>"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>

<body class="register"onpageshow="if(event.persisted) noBack();" onunload="" marginwidth="0" marginheight="0">
	<!-- wrap -->
	<div class="wrap">
		<a href="/"><img src="<c:url value="resources/img/logo.png"/>" class="logo"></a>
		<div class="registerbox">
			<!-- 로그인 입력 -->
			<div class="register_input">
				<h2>
					<img src="<c:url value="resources/img/register/register_text.png"/>" alt="로그인 텍스트 이미지">
				</h2>
				<div class="register_01">
					<!-- 로그인 입력 -->
					<fieldset>
							<form id="registerForm" name="registerForm" method="post" style="display: inline" action="/register" enctype="multipart/form-data">
								<div>
									<div class="register-info">
										<img src="<c:url value="resources/img/register/register_id.png"/>">
										<input type="text" id="id" name="userId" title="아이디" placeholder="아이디">
										<input type="hidden" name="userTypeCd">
									</div>
									<div class="register-info">
										<img src="<c:url value="resources/img/register/register_pwd.png"/>">
								    	<input type="password" id="pw" name="userPwd" title="비밀번호" placeholder="비밀번호">
									</div>
									<div class="register-info">
										<img src="<c:url value="resources/img/register/register_pwd.png"/>">
										<input type="password" name="userPwdConfirm"  placeholder="비밀번호 확인">
									</div>
								</div>
								
								<ul class="register-msg">
									<li id="userIdMsg">아이디: 필수 정보입니다.</li>
									<li id="userPwdMsg">비밀번호: 필수 정보입니다.</li>
									<li id="userPwdConfirmMsg">비밀번호가 일치하지 않습니다.</li>
								</ul>
								<br>
								
								<div>
									<div class="register-info">
										<img src="<c:url value="resources/img/register/register_id.png"/>" class="register-icons">
										<input type="text" name="userNm"  placeholder="이름">
									</div>
									<div class="register-info">
										<img src="<c:url value="resources/img/register/register_tel.png"/>" class="register-icons">
										<input type="text" name="userTelNo"  placeholder="연락처">
									</div>
									<div class="register-info">
										<img src="<c:url value="resources/img/register/register_email.png"/>" class="register-icons">
										<input type="email" name="userEmail"  placeholder="이메일">
									</div>
									<div class="register-info">
										<img src="<c:url value="resources/img/register/register_addr.png"/>" class="register-icons">
										<input type="text" name="userAddr" id="address_kakao" placeholder="주소" readonly>
									</div>
									<div class="register-info">
										<img src="<c:url value="resources/img/register/register_addr.png"/>" class="register-icons">
										<input type="text" name="userAddressDetail" placeholder="상세 주소">
									</div>
									<div class="register-info" id="insert-img-box">
										<img src="<c:url value="resources/img/register/register_id.png"/>" class="register-icons">
										<input type="file" value="이미지 등록(click)" name="imgFile" onchange="imgTypeCheck(this)">
									</div>
								</div>
								<ul class="register-msg">
									<li id="userNmMsg">사용자 이름: 필수 정보입니다.</li>
									<li id="userTelNoMsg">사용자 전화번호: 필수 정보입니다.</li>
									<li id="userEmailMsg">사용자 이메일: 필수 정보입니다.</li>
									<li id="userAddrMsg">사용자 주소: 필수 정보입니다.</li>
									<li id="imgFileMsg">사용자 사진: 필수 정보입니다.</li>
								</ul>
								<br>
								
								<div id="storemng-register-form">
									<input type="hidden" name="storeId" value="0">
									<div class="register-info">
										<img src="<c:url value="resources/img/register/register_addr.png"/>" class="register-icons">
										<input type="text" name="storeAddr" id="store-addr" placeholder="주소" readonly>
									</div>
									<div class="register-info">
										<img src="<c:url value="resources/img/register/register_store.png"/>" class="register-icons">
										<input type="text" name="storeNm"  placeholder="점포명" readonly>
									</div>
								</div>
								<ul class="register-msg">	
									<li id="storeAddMsg">점포: 필수 정보입니다.</li>
								</ul>
								<br>
								
								<div id="engmng-register-form">
									<div class="register-info">
										<img src="<c:url value="resources/img/register/register_mechine.png"/>" class="register-icons">
										<select name="eqpmnCd" >
											<option value="">장비 선택</option>
											<c:forEach var="mechineList" items="${mechineList}">
								    			<option value="${mechineList.grpCdDtlId}" >
							    					${mechineList.grpCdDtlNm}
								    			</option>
											</c:forEach>
										</select>
									</div>
									<div class="register-info">
										<img src="<c:url value="resources/img/register/register_location.png"/>" class="register-icons">
										<select name="location" onchange="changeLocationCd()">
											<option value="">지역 선택</option>
											<c:forEach var="locationList" items="${locationList}">
								    			<option value="${locationList.grpCdDtlId}">
							    					${locationList.grpCdDtlNm}
								    			</option> 
											</c:forEach>
										</select>
									</div>
									<div class="register-info">
										<img src="<c:url value="resources/img/register/register_location.png"/>" class="register-icons">
										<select name="locationCd">
											<option value="">지역 상세 선택</option>
										</select>
									</div>
								</div>
								<ul class="register-msg">
									<li id="mechaAddMsg">기사: 필수 정보입니다.</li>
								</ul>
								<br>
								<div class="register-button">
									<button type="button" id="signup" >회원가입</button>
								</div>
							</form>
					</fieldset>
					<div class="photo">
						<img>
						<p>사진 등록</p>
					</div>
				</div>
			</div>
		</div>
		<div class="servicezone">
			<h4>
				<p>KccBrew </p>
				<p class="title">
					<span>가입 유형</span>
				</p>
			</h4>
			<div class="user-type">
				<div>
					<img src="<c:url value="resources/img/register/register_manager.png"/>" onclick="changeType(01)">
				</div>
				<div class="user-type">
					<p id="manger" onclick="changeType(01)">관리자</p>
				</div>
			</div>
			<div class="user-type">
				<div>
					<img src="<c:url value="resources/img/register/register_store_manager.png"/>" onclick="changeType(02)">
				</div>
				<div class="user-type">
					<p id="store_manager" onclick="changeType(02)">가맹 점주</p>
				</div>
			</div>
			<div class="user-type">
				<div>
					<img src="<c:url value="resources/img/register/register_mecha.png"/>" onclick="changeType(03)">
				</div>
				<div class="user-type">
					<p id="mechanic" onclick="changeType(03)">수리 기사</p>
				</div>
			</div>
		</div>
	</div>
	
	<!-- modal -->
	<div class="search-store">
		<div class="search-store-content">
			<input type="text" class="search-input" placeholder="점포명 또는 주소를 입력해주세요.">
			<input type="hidden" class="hidden-keyword">
			<table id="store-list" class="table">
				<thead>
					<tr>
						<td scope="col">선택</td>
						<td scope="col">점포명</td>
						<td scope="col">주소</td>
					</tr>
				</thead>
				<tbody class="table-group-divider">
					<c:forEach var="list" items="${storeList}">
						<tr>
							<td scope="row">
								<input type="radio" name="select-store">
								<input type="hidden" value="${list.storeSeq}">
							</td>
							<td>${list.storeNm}</td>
							<td>${list.storeAddr}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<div>
				<ul class="paging">
					<c:forEach var ="i" begin='${startPage}' end='${endPage}'>
						<li class="page-number">
							<p onclick="movePage('${i}')">${i}</p>
						</li>
					</c:forEach>
					<c:if test="${nowPageBlock lt totalPageBlock}">
						<li class="page-number">
							<p onclick="movePage('${endPage+1}')">▶</p>
						</li>
					</c:if>
				</ul>
			</div>
			<input type="button" id="select-store" value="선택">
		</div>
	</div>
</body>
</html>
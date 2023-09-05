<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="/resources/css/code/insert.css" />
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<section id="notice" class="notice">
		<section class="category">
			<span>코드관리 > 코드정보 > 코드수정</span>
		</section>
		<div>
			<div class="div">상세코드 수정</div>
			<hr style="border: solid 1.2px; width: 97%;">
			<form action="/code/update" method="post" id="update" onsubmit="return submitForm()">

				<input type="hidden" name="cdIdx"
					value="${codeMng.cdIdx}" />
				<div class="container2">
					<table class="text-center">
						<tr>
							<th>그룹코드선택</th>
							<td><select name="cdId" size="3">
                                    <c:forEach items="${List}" var="list">
                                        <option value="${list.cdId}" <c:if test="${codeMng.cdId==list.cdId}">selected</c:if>>${list.cdNm}</option>
                                    </c:forEach>
                                </select></td>
						</tr>
						<tr>
							<th>상세코드ID</th>
							<td><input type="text" name="cdDtlId" id="cdDtlId"
								value="${codeMng.cdDtlId}" required></td>
						</tr>
						<tr>
							<th>상세코드이름</th>
							<td><input type="text" name="cdDtlNm" id="cdDtlNm"
								value="${codeMng.cdDtlNm}" required></td>
						</tr>
						<tr>
							<th>사용여부</th>
							<td><select name="cdDtlUseYn">
									<option value="Y">Y</option>
									<option value="N">N</option>
							</select></td>
						</tr>
						<tr>
							<th>최초등록일</th>
							<td>${codeMng.cdDtlRegDttm}</td>
						</tr>
						<tr>
							<th>최초등록자ID</th>
							<td>${codeMng.cdDtlRegUser}</td>
						</tr>
						<tr>
							<th>최종수정일</th>
                    <td>${codeMng.cdDtlModDttm}</td>
                    </tr>
                    <tr>
                    <th>최종수정자ID</th>
                    <td>${codeMng.cdDtlModUser}</td>
						</tr>
					</table>
				</div>

				<div class="savecancle">
					<input type="button" name="save" class="button"
						onClick="submitForm()" value="저장"> <input type="button"
						class="update" value="취소" onclick="history.back()">
				</div>
			</form>
		</div>
	</section>
	<script>
	function submitForm() {
	    var form = document.getElementById("update");

	    // AJAX 요청 보내기
	    $.ajax({
	        type: "POST",
	        url: form.getAttribute("action"),
	        data: $(form).serialize(),
	        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	        success: function(response) {
	            var Close = confirm("저장이 완료되었습니다. 창을 닫으시겠습니까?");
	            if (Close) {
	                window.opener.location.reload();
	                window.close();
	            }
	        },
	        error: function(error) {
	            console.error("AJAX 요청 실패:", error);
	            alert("저장에 실패하였습니다.");
	        }
	    });
	    return false;}
	</script>
</body>
</html>

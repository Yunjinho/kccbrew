<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<link rel="stylesheet" href="/resources/css/code/cdMndMod.css" />
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans&family=Noto+Sans+KR&display=swap"
	rel="stylesheet">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
</head>
 <style>
        /* 드롭다운 리스트 스타일 */
        .custom-select {
            display: inline-block;
            position: relative;
            width: 100%;
            max-width: 300px;
            height: 34px;
            margin: 0 0 5px 0;
        }
    </style>
<body>
	<section id="notice" class="notice">
		<div>
			<div class="category">상세코드 수정</div>
			<hr style="border: solid 1.2px; width: 97%;">
			<form action="/code/update" method="post" id="update"
				onsubmit="return submitForm()">

				<input type="hidden" name="cdIdx" value="${codeMng.cdIdx}" />
				<div class="container2">
					<table id="search-box">
						<tr>
							  <th>그룹코드선택</th>
                            <td>
                                <select name="cdId" size="3" class="tx2" id="cdId">
                                    <option value="">그룹코드명</option>
                                    <c:forEach items="${List}" var="list">
                                        <option value="${list.cdId}" ${codeMng.cdId==list.cdId ? 'selected' : ''}>${list.cdNm}</option>
                                    </c:forEach>
                                </select>
                            </td>
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

				<div class="savecancle" style="text-align: center;">
					<button type="button" name="save" class="form-btn"
						onClick="submitForm()" value="저장" id="find-btn1">저장</button>
					<button type="button" class="form-btn" value="취소"
						onclick="history.back()" id="find-btn1">취소</button>
				</div>
			</form>
		</div>
	</section>
	<!-- JavaScript 부분 -->
<script>
var selectElement = document.getElementById("cdId"); // 드롭다운 요소를 가져옴
var selectedValue = "${codeMng.cdId}";

if (selectedValue) {
    selectElement.value = selectedValue;
}

function toggleDropdown() {
    if (selectElement.style.display === "none" || selectElement.style.display === "") {
        selectElement.style.display = "block";
    } else {
        selectElement.style.display = "none";
    }
}
    // 드롭다운을 직접 열고 닫을 필요 없이 사용자가 선택하면 펼쳐지도록 변경
    selectElement.addEventListener("change", function() {
        this.blur(); // 드롭다운 선택 후 포커스를 해제하여 드롭다운이 닫히도록 함
    });

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
        return false;
    }
</script>
</body>
</html>
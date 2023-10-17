<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page import="java.time.LocalDateTime"%>
<%@ page import="java.time.format.DateTimeFormatter"%>


<!DOCTYPE html>
<html>
<head>
<!-- css -->
<link rel="stylesheet" href="/resources/css/asMng/asReceipt.css" />
<link rel="stylesheet" href="/resources/css/asMng/content-template.css" />

<!-- js -->

<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<div id="page-mask">
		<div id="page-container" class="">
			<div id="page-content" class="clearfix">
				<div id="page-content-wrap">
					<div class="page-content-navigation">
						<ol class="breadcrumb">
							<li class="breadcrumb-home"><a href="#">AS 관리</a></li>
							<li>
								<div class="header-icon-background">
									<img
										src="<c:url value='resources/img/asMng/free-icon-right-arrow-271228.png' />"
										alt="Check List" class="header-icon" />
								</div>
							</li>
							<li><a href="<c:url value='/as-receipt' />">AS 수정</a></li>
						</ol>
					</div>
					<div id="region-main">
						<div role="main">
							<span id="maincontent"></span>
							<div class="user-past">
								<div id="content">
									<h2 class="heading">AS 수정</h2>
									<hr>

									<!-- AS 접수-->
									<form action="/as-mod" method="post" id="receipt-form"
										enctype="multipart/form-data">
										<h6>
											<img src="/resources/img/asMng/check.png" class="tag-image">희망
											신청일
										</h6>
										<div>
											<div>
												<fmt:parseDate value="${asDetailInfo.wishingStartDate}"
													pattern="yyyy/MM/dd" var="wishingStartDate" />
												<fmt:formatDate value="${wishingStartDate}"
													pattern="yyyy-MM-dd" var="formattedStartDate" />
												<input type="date" value="${formattedStartDate}"
													name="wishingStartDate">
											</div>
											<div style="font-size: 2em; text-align: center;">~</div>
											<div>
												<fmt:parseDate value="${asDetailInfo.wishingEndDate}"
													pattern="yyyy/MM/dd" var="wishingEndDate" />
												<fmt:formatDate value="${wishingEndDate}"
													pattern="yyyy-MM-dd" var="formattedEndDate" />
												<input type="date" value="${formattedEndDate}"
													name="wishingEndDate">
											</div>
										</div>

										<h6>
											<img src="/resources/img/asMng/check.png" class="tag-image">점포
											정보
										</h6>
										<div>
											<div style="font-size: 1.5em; flex: 0.5; text-align: center;">
												점포명</div>
											<div style="flex: 0.5;">
												<input type="text" name="storeNm"
													value="${asDetailInfo.storeNm}" readonly>
											</div>
											<div style="font-size: 1.5em; flex: 0.5; text-align: center;">
												점포 주소</div>
											<div style="flex: 2;">
												<input name="storeAddr"
													value="${asDetailInfo.storeAddr},${asDetailInfo.storeAddrDtl}"
													style="max-width: initial; width: 100%;" type="text"
													readonly>
											</div>
										</div>

										<h6>
											<img src="/resources/img/asMng/check.png" class="tag-image">AS
											신청 장비
										</h6>
										<div>
											<div>
												<select name="machineCd" required="required">
													<option value="">장비 코드</option>
													<c:forEach var="list" items="${machineCd}">
														<c:choose>
															<c:when test="${list.grpCdDtlNm eq asDetailInfo.machineCdNm}">
																<option value="${list.grpCdDtlId}" selected> ${list.grpCdDtlNm}</option>
															</c:when>
															<c:otherwise>
																<option value="${list.grpCdDtlId}"> ${list.grpCdDtlNm}</option>
															</c:otherwise>
														</c:choose>
														
													</c:forEach>
												</select>
											</div>
										</div>

										<h6>
											<img src="/resources/img/asMng/check.png" class="tag-image">AS
											신청 내용
										</h6>
										<div>
											<div>
												<textarea name="asContent" required="required">${asDetailInfo.asContent}</textarea>
											</div>
										</div>

										<div class="flex-label-div">
											<div>
												<h6>
													<img src="/resources/img/asMng/check.png" class="tag-image">사진
													첨부 파일
												</h6>
											</div>
											<div>
												<div class="in-decrease">
													<span onclick="addFile()">파일 추가</span>
												</div>
												<div class="in-decrease">
													<span onclick="removeFile()">파일 제거</span>
												</div>
											</div>
										</div>
										<div class="file-upload-div">
											
										</div>

										<div>
											<div>
												<div id="receipt-submit" class="form-btn">접수</div>
											</div>
											<div>
												<a href="/as-list" class="form-btn">취소</a>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script> 
<%-- 	<script src="<c:url value="/resources/js/asMng/asReceipt.js"/>"></script> --%>
<script type="text/javascript">
function checkDate(){
	var start=$("input[name=wishingStartDate]").val();
	var end=$("input[name=wishingEndDate]").val();
	if(start==''||end=='') return true;
	if(start>end){
		alert("마지막일은 시작일보다 이후이여야합니다.");
		$("input[name=wishingEndDate]").val("");
		$("input[name=wishingStartDate]").val("");
		return false;
	}
	return true;
}
$(document).ready(function() {
	$("#receipt-submit").click(function(){
		if(checkDate()){
			$("#receipt-form").submit();
		}
	})
    var asInfoSeq = "${asInfoSeq}"; // 수정 필요한 부분
    var asAssignSeq = "${asAssignSeq}"; // 수정 필요한 부분
    var storeSeq = "${storeSeq}"; // 수정 필요한 부분
    var localUrl = "${asDetailInfo.localSavePath}"
    $.ajax({
        url: "/getAsInfoImages?asInfoSeq=" + asInfoSeq + "&asAssignSeq="+asAssignSeq+"&storeSeq="+storeSeq,
        method: "GET",
        dataType: "json",
        success: function(asInfoImgList) {
            if (asInfoImgList.length > 0) {
                asInfoImgList.forEach(function(imgInfo) {
                    // 이미지 파일의 경로를 가져옵니다.
                    var imageUrl = "<c:url value='/resources/img/asMng/receipt/' />" + imgInfo.fileServerNm;

                    // 이미지 파일을 다운로드하고 File 객체를 생성합니다.
                    fetch(imageUrl)
                        .then(function(response) {
                            return response.blob();
                        })
                        .then(function(blob) {
                            var fileName = imgInfo.fileServerNm;
                            var file = new File([blob], fileName);

                            // FileList 객체를 생성하고 File 객체를 추가합니다.
                            var fileList = new DataTransfer();
                            fileList.items.add(file);

                            var inputElement = document.createElement("input");
                            inputElement.type = "file";
                            inputElement.name = "imgFile";
                            inputElement.value = "";
                            inputElement.setAttribute("onchange", "imgTypeCheck(this)");

                            inputElement.files = fileList.files;

                            inputElement.onchange = function() {
                                imgTypeCheck(this);
                            };

                            var divElement = document.createElement("div");
                            divElement.appendChild(inputElement);

                            // 파일 입력란을 파일 업로드 DIV에 추가
                            document.querySelector(".file-upload-div").appendChild(divElement);
                        })
                        .catch(function(error) {
                            console.error("Error downloading image: " + error);
                        });
                });
            }
        },
        error: function(error) {
            console.error("Error fetching image data: " + error);
        }
    });
});

// 이미지 확장자 체크 함수
function imgTypeCheck(fileName) {
    var imgFile = fileName.files[0];
    if (imgFile) {
        var fileLen = imgFile.name.length;
        var lastDot = imgFile.name.lastIndexOf('.');
        var fileType = imgFile.name.substring(lastDot, fileLen).toLowerCase();
        if (fileType == ".jpeg" || fileType == ".jpg" || fileType == ".png") {
        } else {
            alert("사진 : jpeg, jpg, png 확장자를 가진 파일만 사용하실 수 있습니다.");
            $(fileName).val("");
        }
    }
}
function addFile(){
	var str="<div>\n<input type='file' name='imgFile' value='' onchange='imgTypeCheck(this)'>\n</div>";
	$(".file-upload-div").append(str);
}
function removeFile(){
	var str=$(".file-upload-div>div:last-child").remove();
}
</script>

</body>
</html>
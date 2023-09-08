<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
   <link rel="stylesheet" href="/resources/css/code/cdMngDtl.css" />
    <meta charset="UTF-8">
    <title>코드정보</title>
</head>
<body>
    <section id="notice" class="notice">

        <div class="container2">
            <div class="category">그룹코드</div>
            <hr style="border: solid 1.2px; width: 97%;">
            <table id="search-box">
                <thead>
                    <tr>
                        <th scope="col">그룹코드ID</th>
                        <th scope="col">그룹코드이름</th>
                    </tr>
                </thead>
                <tbody>
                        <tr>
                            <td>${codeMng.cdId}</td>
                            <td>${codeMng.cdNm}</td>
                        </tr>
                </tbody>
            </table>
        </div>

        <div class="container2">
            <div class="category">그룹상세코드</div>
            <hr style="border: solid 1.2px; width: 97%;">
            <table id="search-box">
                <tr>
                    <th>그룹상세코드ID</th>
                    <td>${codeMng.cdDtlId}</td>
                    <th>그룹상세코드이름</th>
                    <td>${codeMng.cdDtlNm}</td>
                    <th>사용여부</th>
                    <td>${codeMng.cdDtlUseYn}</td>
                </tr>
                <tr>
                    <th>최초등록일</th>
                    <td>${codeMng.cdDtlRegDttm}</td>
                    <th>최초등록자ID</th>
                    <td>${codeMng.cdDtlRegUser}</td>
                </tr>
                <tr>
                    <th>최종수정일</th>
                    <td>${codeMng.cdDtlModDttm}</td>
                    <th>최종수정자ID</th>
                    <td>${codeMng.cdDtlModUser}</td>
                </tr>
            </table>
            <c:url value="/code/update/${codeMng.cdId}/${codeMng.cdDtlId}" var="updateUrl" />
			<div class="updatecancle" style="text-align: center;">
				<a href="${updateUrl}"> <button type="button" class="form-btn" id="find-btn1"
					value="수정">수정</button>
				</a> <button type="button" class="form-btn" value="취소"  id="find-btn1"
					onclick="window.close()">취소</button>
			</div> 
        </div>
    </section>
</body>
</html>

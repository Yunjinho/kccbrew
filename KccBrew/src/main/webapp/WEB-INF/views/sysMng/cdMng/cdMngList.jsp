<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/css/code/code.css" />
    <meta charset="UTF-8">
    <title>코드리스트</title>
</head>
<body>
    <section id="notice" class="notice">
        <div class="container">
            <section class="category">
                <span>코드관리 > 코드조회</span>
            </section>
            <section class="filter">
                <form action="<c:url value='/code' />" method="get" >
                    <div class="YN">
                        <div class="YN4">
                            <span class="fw">그룹코드 검색</span>
                            <span class="inputbar">
                                <input type="text" name="keyword" size="40" placeholder="코드명을 입력해주세요">
                            </span>
                        </div>
                        <div class="YN5">
                            <label class="fw">그룹코드 사용여부</label>
                            <span class="YN1">
                                <input type="radio" class="radio-value" name="cdUseYn" value="Y" />
                                사용
                            </span>
                            <span class="YN2">
                                <input type="radio" class="radio-value" name="cdUseYn" value="N" />
                                미사용
                            </span>
                            <span class="YN3">
                                <input type="submit" value="검색" class="resetbtn">
                                <a href="<c:url value='/code' />"><input type="button" value="초기화" class="bt"></a>
                            </span>
                        </div>
                    </div>
                </form>
            </section>
            <div id="grcd">
                <div id="writebtn">
                    <a href="javascript:void(0);" onclick="popup();">
                        <input type="button" class="cdwrite" value="코드등록">
                    </a>
                </div>
                <table class="text-center">
                    <thead>
                        <tr>
                            <th scope="col">순번</th>
                            <th scope="col">그룹코드ID</th>
                            <th scope="col">그룹코드이름</th>
                            <th scope="col">코드상세ID</th>
                            <th scope="col">코드상세이름</th>
                            <th scope="col">사용여부</th>
                            <th scope="col">상세보기</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${list}" var="no" varStatus="loop">
                            <tr>
                                <td>${no.cdIdx}</td>
                                <td>${no.cdId}</td>
                                <td>
                                    <a href="javascript:void(0);" onclick="popup2('${no.cdId}');">${no.cdNm}</a>
                                </td>
                                <td>${no.cdDtlId}</td>
                                <td>${no.cdDtlNm}</td>
                                <td>${no.cdDtlUseYn}</td>
                                <td>
                                    <a href="javascript:void(0);" data-cdId="${no.cdId}" data-cdDtlId="${no.cdDtlId}" onclick="popup1(this.getAttribute('data-cdId'), this.getAttribute('data-cdDtlId'));">
                                        <input type="button" class="btn_write" value="상세보기" <c:if test="${no.cdIdx != 0}"/>>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </section>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
    window.name = 'viewPage';

    function popup() {
        var url = "<c:url value='/code/insert' />";
        var name = "팝업 테스트";
        var option = "width=700,height=600,top=200,left=500,scrollbars=yes,directories=no,location=no";
        window.open(url, name, option);
        // 팝업 창을 열고 난 후에 window.close(); 함수를 제거하면 팝업 창이 열린 상태로 유지됩니다.
    }

    function popup1(cdId, cdDtlId) {
        var url = "<c:url value='/code/' />" + cdId + "/" + cdDtlId;
        var name = "팝업 테스트";
        var option = "width=1200,height=600,top=200,left=250,scrollbars=yes,directories=no,location=no";
        window.open(url, name, option);
        // 팝업 창을 열고 난 후에 window.close(); 함수를 제거하면 팝업 창이 열린 상태로 유지됩니다.
    }

    function popup2(cdId) {
        var url = "<c:url value='/code/' />" + cdId;
        var name = "팝업 테스트";
        var option = "width=1200,height=600,top=100,left=200,scrollbars=yes,directories=no,location=no";
        window.open(url, name, option);
        // 팝업 창을 열고 난 후에 window.close(); 함수를 제거하면 팝업 창이 열린 상태로 유지됩니다.
    }
</script>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/code/search.css" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <title>코드리스트</title>
</head>
<body>
    <section id="notice" class="notice">
        <div class="container">
            <section class="category">
                <span>코드관리 > 코드검색</span>
            </section>
            <section class="filter">
                <div class="YN4">
                    <form action="/code/search" method="get">
                        <span class="fw">그룹코드 검색</span>
                        <span class="inputbar">
                            <input type="text" name="keyword" size="40">
                        </span>
                        <input type="submit" value="검색" class="bt">
                    </form>
                </div>
            </section>
        </div>

        <div id="grcd">
            <div class="bt1">
                <a href="javascript:void(0);" onclick="popup();">
                    <input type="button" class="button" value="코드등록">
                </a>
                <a href="/code">
                    <input type="button" class="button" value="전체목록">
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
                    <c:forEach items="${list}" var="no">
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
    </section>

    <script>
        window.name = 'viewPage';

        function popup() {
            var url = "/code/insert";
            var name = "popup test";
            var option = "width=700,height=600,top=200,left=500,scrollbars=yes,directories=no,location=no";
            window.open(url, name, option);
            window.close();
        }

        function popup1(cdId, cdDtlId) {
            var url = "/code/" + cdId + "/" + cdDtlId;
            var name = "popup test";
            var option = "width=1200,height=600,top=200,left=250,scrollbars=yes,directories=no,location=no";
            window.open(url, name, option);
            window.close();
        }

        function popup2(cdId) {
            var url = "/code/" + cdId;
            var name = "popup test";
            var option = "width=1200,height=600,top=100,left=200,scrollbars=yes,directories=no,location=no";
            window.open(url, name, option);
            window.close();
        }
    </script>
</body>
</html>

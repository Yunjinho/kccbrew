<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet"  href="/resources/css/store/store.css" />
<head>
    <meta charset="UTF-8">
    <style>
        body {
            background-color: #EBF2FC;
        }
    </style>
    <title>점포리스트</title>
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
</head>
<body>
    <section id="notice" class="notice">
        <div class="container">
            <section class="category">
                <span>점포관리 > 점포조회</span>
            </section>
            <section class="filter">
                <div class="YN4">
                    <form action="<c:url value='/store/search' />" method="get">
                        <span class="fw">점포 검색</span>
                        <span class="inputbar">
                            <input type="text" name="keyword" size="40" placeholder="점포명을 입력해주세요">
                        </span>
                        <input type="submit" value="검색" class="bt">
                    </form>
                </div>
            </section>
            <table class="text-center">
                <thead>
                    <tr>
                        <th scope="col">NO</th>
                        <th scope="col">스토어이름</th>
                        <th scope="col">지역구분</th>
                        <th scope="col">점포연락처</th>
                        <th scope="col">상세보기</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${list}" var="no" varStatus="stat">
                        <tr>
                            <td><c:out value="${stat.count}" /></td>
                            <td>${no.storeNm}</td>
                            <td>${no.locationNm}</td>
                            <td>${no.storeTelNo}</td>
                            <td><a href="javascript:void(0);" onclick="popup(${no.storeSeq});">상세보기</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div id="writebtn">
                <a href="javascript:void(0);" onclick="popup1();">
                    <input type="button" class="strwrite" value="점포등록">
                </a>
            </div>
        </div>
    </section>
    <script>
        window.name = 'viewPage';
        function popup(storeSeq) {
            var url = "store/view/" + storeSeq;
            var name = "popup test";
            var option = "width=1300, height=700, top=100, left=200, scrollbars=yes, directories=no, location=no";
            window.open(url, name, option);
            window.close();
        }
        
        function popup1() {
            var url = "store/insert";
            var name = "popup test";
            var option = "width=700, height=600, top=200, left=500, scrollbars=yes, directories=no, location=no";
            window.open(url, name, option);
            window.close();
        }
    </script>
</body>
</html>

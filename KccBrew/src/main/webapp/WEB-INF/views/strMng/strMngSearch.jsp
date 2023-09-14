
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>점포리스트</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="<c:url value='/css/store/store.css' />" />
    <!-- Header Include -->
    <style>
        body {
            background-color: #EBF2FC;
        }
    </style>
</head>
<body style="overflow:scroll;">
    <section id="notice" class="notice">
        <div class="container">
            <section class="category">
                <span>점포관리 &gt; 점포조회</span>
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
                            <td><c:out value="${no.storeNm}" /></td>
                            <td><c:out value="${no.locationNm}" /></td>
                            <td><c:out value="${no.storeTelNo}" /></td>
                            <td><a href="javascript:void(0);" onclick="popup(${no.storeSeq});">상세보기</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div id="writebtn">
                <a href="javascript:void(0);" onclick="popup1();"><input type="button" class="strwrite" value="점포등록"></a>
                <a href="<c:url value='/store' />"><input type="button" class="button" value="전체목록"></a>
            </div>
        </div>
    </section>
    <script>
        window.name = 'viewPage';
        function popup(storeSeq) {
            var url = "view/" + storeSeq;
            var name = "popup test";
            var option = "width=1300,height=700,top=100,left=200,scrollbars=yes,directories=no,location=no";
            window.open(url, name, option);
            window.close();
        }

        function popup1() {
            var url = "insert";
            var name = "popup test";
            var option = "width=700,height=600,top=200,left=500,scrollbars=yes,directories=no,location=no";
            window.open(url, name, option);
            window.close();
        }
    </script>
</body>
</html>
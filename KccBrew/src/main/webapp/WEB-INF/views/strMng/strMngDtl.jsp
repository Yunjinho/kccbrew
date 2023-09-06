<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>점포상세</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="${path}/resources/css/store/view.css" type="text/css">
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
</head>
<body>
    <section id="notice" class="notice">
        <div class="container">
            <section class="category">
                <span>점포관리 > 점포정보</span>
            </section>
            <table class="table text-center">
                <tr>
                    <th>지점명</th>
                    <td>${store.storeNm}</td>
                    <th>점포연락처</th>
                    <td>${store.storeTelNo}</td>
                </tr>
                <tr>
                    <th>지역구분</th>
                    <td>${store.locationNm}</td>
                    <th>사용여부</th>
                    <td>${store.useYn}</td>
                </tr>

                <tr>
                    <th>점포주소</th>
                    <td colspan="3">${store.storeAddr}, ${store.storeAddrDtl}</td>
                </tr>
            </table>

            <!-- 이미지 지도를 표시할 div 입니다 -->
            <div id="staticMap" style="width: 780px; height: 455px;"></div>
            <table class="table text-center">
                <thead>
                    <tr>
                        <th scope="col">NO</th>
                        <th scope="col">점주이름</th>
                        <th scope="col">점주연락처</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${list}" var="item" varStatus="stat">
                        <tr>
                            <td>${stat.count}</td>
                            <td>${item.userNm}</td>
                            <td>${item.userTelNo}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div class="updatecancle">
                <a href="/store/update/${store.storeSeq}">
                    <input type="button" class="update" value="수정">
                </a>
                <input type="button" class="update" value="취소" onclick="window.close()">
            </div>
        </div>
    </section>
  <script type="text/javascript">
    function loadKakaoMap() {
        var script = document.createElement("script");
        script.type = "text/javascript";
        script.src = "//dapi.kakao.com/v2/maps/sdk.js?appkey=8ebfc94bdd15e21c9b4d64159a004634";
        script.onload = initializeKakaoMap; // Kakao 지도 초기화 함수 호출
        document.head.appendChild(script);
    }

    function initializeKakaoMap() {
        kakao.maps.load(function () {
            // Kakao 지도 초기화 코드
            var markerPosition = new kakao.maps.LatLng(${store.latitude}, ${store.longitude});
            var marker = {
                position: markerPosition
            };
            var staticMapContainer = document.getElementById('staticMap');
            var staticMapOption = {
                center: new kakao.maps.LatLng(${store.latitude}, ${store.longitude}),
                level: 3,
                marker: marker
            };
            var staticMap = new kakao.maps.StaticMap(staticMapContainer, staticMapOption);
        });
    }

    // 페이지 로드 시 Kakao 지도 스크립트 로드 시작
    loadKakaoMap();
</script>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8ebfc94bdd15e21c9b4d64159a004634"></script>
</body>
</html>

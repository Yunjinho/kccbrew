<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet"
    href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="/resources/css/userMng/userMngList.css" />
<!-- notoSans -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans&family=Noto+Sans+KR&display=swap"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<meta charset="UTF-8">
<title>점포정보</title>
</head>
<style>
    #notice{
    margin: 40px;}
    
   
    .form-btn {
     display: unset;}
     
     .category{
     font-size: 1.2em;
     }
</style>
<body>
    <section id="notice" class="notice">
        <div class="container2">
            <div class="category">점포정보</div>
            <hr style="border: solid 1.2px; width: 97%;">
            <table id="search-box">
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
                    <td colspan="3">${store.storeAddr},${store.storeAddrDtl}</td>
                </tr>
            </table>
        </div>
            <!-- 이미지 지도를 표시할 div 입니다 -->
            <div id="staticMap" style="width: 700px; height: 400px; margin: 20px auto;"></div>
            <div class="container2">
                <div class="category">점주리스트</div>
                <hr style="border: solid 1.2px; width: 97%;">
                <table id="search-box">
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
                <div class="updatecancle" style="text-align: center; margin-top: 30px;">
                    <a href="/store/update/${store.storeSeq}"> <button type="button"
                         class="form-btn" id="find-btn1"
                    value="수정">수정</button>
                    </a> <button type="button" class="form-btn" value="취소"  id="find-btn1"
                    onclick="window.close()">취소</button>
                </div>
            </div>
    </section>
    <script type="text/javascript"
        src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8ebfc94bdd15e21c9b4d64159a004634"></script>
    <script type="text/javascript">
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

        // Kakao 지도 스크립트 로드 후 호출되도록 이벤트 핸들러 추가
        window.addEventListener('load', initializeKakaoMap);
    </script>
</body>
</html>
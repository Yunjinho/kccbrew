<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8">
<title>점포등록</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="${path}/resources/css/store/update.css"
	type="text/css">
</head>
<body>
	<section id="notice" class="notice">
		<section class="category">
			<span>점포관리 &gt; 점포정보 &gt; 점포수정</span>
		</section>
		<form action="/store/update" method="post" id="storeForm"
			onsubmit="return submitForm()">
			<table class="table text-center">
				<tr>
					<th>지점명</th>
					<td><input type="text" id="storeNm" name="storeNm"
						value="${store.storeNm}" required> <input type="hidden"
						id="storeNmHidden" name="storeNm"></input> <!-- 숨겨진 필드 추가 --> <input
						type="button" name="strnm" value="중복확인" class="check"
						id="name-check-btn" required></td>
					<th>점포연락처</th>
					<td><input type="text" id="storeTelNo" name="storeTelNo"
						oninput="hypenTel(this)" maxlength="13"
						value="${store.storeTelNo}" placeholder="하이픈(-)은 자동입력 됩니다.."></td>
				</tr>
				<tr>
					<th>좌표</th>
					<td><input class="field" id="lat" name="latitude"
						value="${store.latitude}" />, <input class="field" id="lng"
						name="longitude" value="${store.longitude}"></td>

					<th>사용여부</th>
					<td><select name="useYn">
							<option value="Y">Y</option>
							<option value="N">N</option>
					</select></td>
				</tr>
				<tr>
					<th>주소</th>
					<td><input type="text" id="address_kakao" name="storeAddr"
						placeholder="클릭해주세요" value="${store.storeAddr}" readonly /> <input
						type="text" name="storeAddrDtl" id="storeAddrDtl"
						value="${store.storeAddrDtl}" required /></td>
				</tr>
				<tr>
					<th>지역분류</th>
					<td><select id="location" name="locationCd" size="3">
							<option value="">지역선택</option>
							<c:forEach var="list" items="${list}">
								<option value="${list.locationCd}"
									${store.locationCd == list.locationCd ? 'selected' : ''}>${list.locationNm}</option>
							</c:forEach>
					</select> <select id="locationSeoul" name="locationCdSeoul" size="3"
						disabled>
							<option value="">지역선택 상세</option>
							<c:forEach var="list" items="${seoullist}">
								<option value="${list.locationCd}"
									${store.locationCd == list.locationCd ? 'selected' : ''}>${list.locationNm}</option>
							</c:forEach>
					</select></td>
				</tr>
			</table>
			<div id="staticMap" style="width: 780px; height: 455px;"></div>
			<div class="updatecancle">
				<input type="hidden" name="storeSeq" value="${store.storeSeq}">
				<input type="submit" name="save" class="button" value="저장"
					id="submitBtn"> <input type="button" class="update"
					value="취소" onclick="history.back()">
			</div>
		</form>
	</section>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<!-- Google Maps API -->
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAY5-GHYNs9VdzbExAcRYn5NPHIsu727XI&libraries=places&callback=initAutocomplete"
		async defer></script>
	<!-- Daum Postcode API -->
	<script
		src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

	<script>
    window.onload = function() {
        document.getElementById("address_kakao").addEventListener("click", function() {
            new daum.Postcode({
                oncomplete: function(data) {
                    document.getElementById("address_kakao").value = data.address;
                    document.querySelector("input[name=storeAddrDtl]").focus();
                    updateLatLng();
                }
            }).open();
        });
    }

    var mallsEnabled = false;

    function update_selected() {
        var selectedValue = $("#location").val();
        mallsEnabled = (selectedValue === "02");
    }

    $(function() {
        update_selected();

        $("#location").change(function() {
            update_selected();

            if (mallsEnabled) {
                $("#locationSeoul").prop('disabled', false);
            } else {
                $("#locationSeoul").prop('disabled', true);
            }
        });
    });

    function updateLatLng() {
        var address = document.getElementById("address_kakao").value;
        var geocoder = new google.maps.Geocoder();
        geocoder.geocode({
            'address': address
        }, function(results, status) {
            if (status === google.maps.GeocoderStatus.OK) {
                var lat = results[0].geometry.location.lat();
                var lng = results[0].geometry.location.lng();
                document.getElementById("lat").value = lat;
                document.getElementById("lng").value = lng;
            } else {
                console.log('Geocode was not successful for the following reason: ' + status);
            }
        });
    }

    const hypenTel = function(target){
        target.value = target.value.replace(/[^0-9]/g, '').replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);
    }

    const storeNmHidden = document.querySelector("#storeNmHidden"); // 숨겨진 필드
    const submitBtn = document.querySelector("#submitBtn"); // 폼 제출 버튼

    storeNm.addEventListener("input", function() {
        if (storeNm.value === storeNmHidden.value) {
            submitBtn.disabled = false; // 저장 버튼 활성화
        } else {
            submitBtn.disabled = true; // 저장 버튼 비활성화
        }
    });

    const nameCheckBtn = document.querySelector("#name-check-btn");

    nameCheckBtn.addEventListener("click", function() {
        const storeNm = document.getElementById("storeNm").value;
console.log(storeNm);
        if (!storeNm) {
            alert("점포명을 입력해주세요.");
            return;
        }

       
		
        $.ajax({
            async: true,
            type: 'POST',
            url: "${pageContext.request.contextPath}/api/namecheck", // 경로 수정
            data: JSON.stringify(storeNm), // JSON으로 데이터 전송
            dataType: "json", // 서버가 JSON 응답을 반환하는 것으로 예상
            contentType: "application/json; charset=UTF-8",
            success: function (check) {
                if (check == "1") {
                    alert("사용 가능한 점포명입니다.");
                    // 이후 원하는 동작 수행
                    document.getElementById("submitBtn").disabled = false;
                    document.getElementById("storeNm").disabled = true;
                    document.getElementById("storeNmHidden").value = storeNm;
                } else {
                    alert("이미 존재하는 점포명입니다.");
                    // 이후 원하는 동작 수행
                }
            },
            error: function (error) {
                console.error("에러 발생:", error);
                alert("서버와의 통신 중 오류가 발생했습니다.");
            }
        });
    });
    
    function submitForm() {
        var form = document.getElementById("storeForm");
        console.log(storeNm);
        $.ajax({
            type: "POST",
            url: form.getAttribute("action"),
            data: $(form).serialize(),
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",  success: function (response) {
                var Close = confirm("저장이 완료되었습니다. 창을 닫으시겠습니까?");
                if (Close) {
                    window.opener.location.reload();
                    window.close();
                }
            },
            error: function (error) {
                console.error("AJAX 요청 실패:", error);
                alert("저장에 실패하였습니다.");
            }
        });

        return false;
    }
</script>
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
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8ebfc94bdd15e21c9b4d64159a004634"></script>
</body>
</html>


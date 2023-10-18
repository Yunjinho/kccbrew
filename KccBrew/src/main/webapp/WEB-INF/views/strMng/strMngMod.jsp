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
<title>점포수정</title>
</head>
<style>
	input{
	width: auto;
	}
   #notice{
    margin: 40px;}
    
   
    .form-btn {
     display: unset;}
     
     .category{
     font-size: 1.2em;
     }
     .register-msg>li {
	height: 10px;
	width: 283px;
	    margin: auto;
}

.register-msg {
	margin: 0px;
	height: 8px;
	padding: 0;
	  list-style:none;
	      color: red;
}
</style>

<body>
	<section id="notice" class="notice">
		 <div class="container2">
            <div class="category">점포수정</div>
            <hr style="border: solid 1.2px; width: 97%;">
            
		<form action="/store/update" method="post" id="storeForm"
			onsubmit="return submitForm()" style="margin-top:20px">
			<table id="search-box">
				<tr>
					<th>지점명</th>
					<td><input type="text" id="storeNm" name="storeNm"
						value="${store.storeNm}" required> 
										<input type="hidden" value="${store.storeNm}"
						id="storeNmHidden"></input> <!-- 숨겨진 필드 추가 --> <input
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
						value="${store.latitude}" size="12" />, <input class="field" id="lng"
						name="longitude" value="${store.longitude}" size="12"></td>

					<th>사용여부</th>
					<td><select name="useYn">
							<option value="Y">Y</option>
							<option value="N">N</option>
					</select></td>
				</tr>
				<tr>
					<th colspan="1">주소</th>
					<td colspan="3"><input type="text" id="address_kakao" name="storeAddr"
						placeholder="클릭해주세요" value="${store.storeAddr}"  size="40" readonly />, <input
						type="text" name="storeAddrDtl" id="storeAddrDtl"
						value="${store.storeAddrDtl}" required /></td>
				</tr>
				<tr>
					<th colspan="1">지역분류</th>
					<td colspan="3">
					<input type="hidden" id="dtl" value="${store.locationCd}"><select id="location" name="locationCd" size="1" onchange="changeLocationCd()">
							<option value="">지역선택</option>
							<c:forEach var="list" items="${list}">
								<option value="${list.locationCd}"
									${store.locationCd == list.locationCd ? 'selected' : ''}>${list.locationNm}</option>
							</c:forEach>
					</select> <select id="locationSeoul" name="locationCdSeoul" size="1" >
							<option value="">지역선택 상세</option>
						
					</select></td>
				</tr>
			</table>
			
		  <div id="staticMap" style="width: 700px; height: 400px; margin: 20px auto;"></div>
			 <div class="updatecancle" style="text-align: center;">
			 <ul class="register-msg" id="msg" style="display:none;">
											<li id="userPwdConfirmMsg">중복확인이 필요합니다.</li>
										</ul>
				<input type="hidden" name="storeSeq" value="${store.storeSeq}" style="">
				<button type="submit" name="save"  class="form-btn" value="저장"
					id="submitBtn" style="display: inline-block;  margin-left: 5px;  margin-right: 5px; margin-top:20px">저장</button> 
					<button type="button"  class="form-btn"
					value="취소" onclick="history.back()" style="display: inline-block;  margin-left: 5px;  margin-right: 5px; margin-top:20px">취소</button>
			</div>
		</form>
		</div>
	</section>
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
    	var dtlCd = $("#dtl").val();
		console.log(dtlCd);
    	// 정규 표현식 패턴
    	var pattern = /\d+/;

    	// 정규 표현식과 일치하는 부분 찾기
    	var matches = dtlCd.match(pattern);

    	if (matches) {
    	    // 정규 표현식과 일치하는 첫 번째 숫자 얻기
    	    var locCd = matches[0];
    	   /*  $("#dtl").val()=locCd; */
    	    console.log(locCd);
    	
    	    var selectElement = document.getElementById('location');

    	    // select 요소의 option 요소를 모두 가져옵니다.
    	    var options = selectElement.getElementsByTagName('option');

    	    // 각 option 요소를 확인하며 선택 여부를 결정합니다.
    	    for (var i = 0; i < options.length; i++) {
    	        var option = options[i];
    	        var locationCd = option.value;

    	        // store.locationCd와 hiddenValue를 비교하여 선택 여부를 결정합니다.
    	        if (locationCd == locCd) {
    	            option.selected = true;
    	        } else {
    	            option.selected = false;
    	        }
    	    
    	    
    	    
    	    
    	    }} else{
    	    	
    	    	var locCd = $("#location").val(); 
    	    }
    	$.ajax({
      		type : "GET",           // 타입 (get, post, put 등등)
      		url : "/search-location-code",           // 요청할 서버url
      		dataType : "json",       // 데이터 타입 (html, xml, json, text 등등)
      		data : {
      			'locCd' : locCd,
      		},
      		success : function(data) { // 결과 성공 콜백함수
      			changeLosctionDtlCd(data);
      		}
      	});
    }

    function changeLosctionDtlCd(data){
    	var dtlLocation= $("#dtl").val();
      	var locOption=$("select[name=locationCdSeoul]");
      	var content='<option value="">지역 상세 선택</option>';

      	for(var i=0;i<data.length;i++){
      		content +='<option value="' + data[i].grpCdDtlId + '" ' + (dtlLocation == data[i].grpCdDtlId ? 'selected' : '') + '>' + data[i].grpCdDtlNm + '</option>';
      	}
      	locOption.html(content);
      }

      //대분류 지역 선택한 값에 맞춰서 소분류 지역 값 조회
      function changeLocationCd(){
      	var locCd = $("#location").val(); 
      	console.log(locCd);
      	$.ajax({
      		type : "GET",           // 타입 (get, post, put 등등)
      		url : "/search-location-code",           // 요청할 서버url
      		dataType : "json",       // 데이터 타입 (html, xml, json, text 등등)
      		data : {
      			'locCd' : locCd,
      		},
      		success : function(data) { // 결과 성공 콜백함수
      			changeLosctionDtlCd(data);
      		}
      	});
      }

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

	const hypenTel = function(target) {
	    if (!target) {
	        return "";
	    }
	    const value = target.value.replace(/[^0-9]/g, ""); // 숫자 이외의 모든 문자를 제거
	    let result = [];
	    let restNumber = "";

	    // 지역번호와 나머지 번호로 나누기
	    if (value.startsWith("02")) {
	        // 서울 02 지역번호
	        result.push(value.substr(0, 2));
	        restNumber = value.substring(2);
	        if (restNumber.length == 8) {
		        // 4자리 이상인 경우
		        result.push(restNumber.substring(0, 4));
		        result.push(restNumber.substring(4));
		    } else if (7 >= restNumber.length) {
		        // 4자리 이상인 경우
		        result.push(restNumber.substring(0, 3));
		        result.push(restNumber.substring(3));
		    } else {
		        // 4자리 미만인 경우
		    	 result.push(restNumber.substring(0, 4));
			        result.push(restNumber.substring(4));
		    }

	        
	    } else if (value.startsWith("1")) {
	        // 지역 번호가 없는 경우
	        // 1xxx-yyyy
	        restNumber = value;
	        if (restNumber.length >= 4) {
		        // 4자리 이상인 경우
		        result.push(restNumber.substring(0, 4));
		        result.push(restNumber.substring(4));
		    } else {
		        // 4자리 미만인 경우
		        result.push(restNumber);
		    }
	    } else {
	        // 나머지 3자리 지역번호
	        // 0xx-yyyy-zzzz
	        result.push(value.substr(0, 3));
	        restNumber = value.substring(3);
	        if (restNumber.length == 8) {
		        // 4자리 이상인 경우
		        result.push(restNumber.substring(0, 4));
		        result.push(restNumber.substring(4));
		    } else if (7 >= restNumber.length) {
		        // 4자리 이상인 경우
		        result.push(restNumber.substring(0, 3));
		        result.push(restNumber.substring(3));
		    } else {
		        // 4자리 미만인 경우
		    	 result.push(restNumber.substring(0, 4));
			        result.push(restNumber.substring(4));
		    }
	    }

	  

	    const hyphenatedValue = result.filter(function(val) {
	        return val;
	    }).join("-");

	    // 입력 필드에 결과 설정
	    target.value = hyphenatedValue;
	}

    var con = document.getElementById("msg"); 
    const storeNmHidden = document.querySelector("#storeNmHidden");
    const storeNm = document.querySelector("#storeNm");
    const submitBtn = document.querySelector("#submitBtn");

    storeNm.addEventListener("input", function() {
        if (storeNm.value === storeNmHidden.value) {
            submitBtn.disabled = false; // Enable the submit button
            msg.style.display="none";
        } else {
            submitBtn.disabled = true; // Disable the submit button
            msg.style.display="block";
        }
    });

    const nameCheckBtn = document.querySelector("#name-check-btn");

    nameCheckBtn.addEventListener("click", function() {
        const storeNm = document.getElementById("storeNm").value;
        if (!storeNm) {
            alert("점포명을 입력해주세요.");
            return;
        }
        $.ajax({
            async: true,
            type: 'POST',
            url: "${pageContext.request.contextPath}/user/namecheck",
            data: JSON.stringify(storeNm),
            dataType: "json",
            contentType: "application/json; charset=UTF-8",
            success: function (check) {
                if (check == "1") {
                    alert("사용 가능한 점포명입니다.");
                    // Update the hidden field value
                    document.getElementById("storeNmHidden").value = storeNm;
                    // Check if storeNm and storeNmHidden are equal
                    if (storeNm === storeNmHidden.value) {
                        submitBtn.disabled = false; 
                        msg.style.display="none";// Enable the submit button
                    } else {
                        submitBtn.disabled = true; // Disable the submit button
                        msg.style.display="block";
                    }
                } else {
                    alert("이미 존재하는 점포명입니다.");
                    document.getElementById("storeNmHidden").value = storeNm;
                    // Check if storeNm and storeNmHidden are equal
                    if (storeNm === storeNmHidden.value) {
                        submitBtn.disabled = false; 
                        msg.style.display="none";// Enable the submit button
                    } else {
                        submitBtn.disabled = true; // Disable the submit button
                        msg.style.display="block";
                    }
                }
            },
            error: function (error) {
                console.error("에러 발생:", error);
                alert("서버와의 통신 중 오류가 발생했습니다.");
                msg.style.display="block";
            }
        });
    });
    
    function submitForm() {
        var form = document.getElementById("storeForm");
        storeNm.value = storeNm.value.replace(/,/g, ''); 
        console.log(storeNm);
        $.ajax({
            type: "POST",
            url: form.getAttribute("action"),
            data: $(form).serialize(),
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            success: function (response) {
                var Close = confirm("저장이 완료되었습니다. 창을 닫으시겠습니까?");
                console.log(storeNm);
                if (Close) {
                    window.opener.location.href = "/store";
                    window.close();
                }
            },
            error: function (error) {
                console.error("AJAX 요청 실패:", error);
                alert("빈칸을 모두 입력해주세요.");
            }
        });

        return false;
    }
</script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8ebfc94bdd15e21c9b4d64159a004634"></script>
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

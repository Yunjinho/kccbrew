document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("address_kakao").addEventListener("click", function () {
        new daum.Postcode({
            oncomplete: function (data) {
                document.getElementById("address_kakao").value = data.address;
                document.querySelector("input[name=storeAddrDtl]").focus();
                updateLatLng();
            }
        }).open();
    });


    
    
    function updateLatLng() {
        var address = document.getElementById("address_kakao").value;
        var geocoder = new google.maps.Geocoder();
        geocoder.geocode({
            'address': address
        }, function (results, status) {
            if (status === google.maps.GeocoderStatus.OK) {
                var lat = results[0].geometry.location.lat();
                var lng = results[0].geometry.location.lng();
                document.getElementById("lat").value = lat;
                document.getElementById("lng").value = lng;
            } else {
                console.error('Geocode was not successful for the following reason: ' + status);
            }
        });
    }



});



//대분류 지역 조건 변경 시 소분류 지역값들 변경 함수
  function changeLosctionDtlCd(data){
  	
  	var locOption=$("select[name=locationCdSeoul]");
  	var content='<option value="">지역 상세 선택</option>';

  	for(var i=0;i<data.length;i++){
  		content +='<option value="'+data[i].grpCdDtlId+'" >'+data[i].grpCdDtlNm+'</option>'
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
function deleteStrDetail(storeSeq){
	 if (!confirm("해당 계정으로 활동한 모든 내역이 삭제됩니다.\n내 점포에서 삭제 하시겠습니까?")) {
	 } else {
	    	$.ajax({
	    		type : "POST",           // 타입 (get, post, put 등등)
	    		url : "/delete-my-store",           // 요청할 서버url
	    		dataType : "text",       // 데이터 타입 (html, xml, json, text 등등)
	    		data : {
	    			'storeSeq' : storeSeq
	    		},
	    		success : function(data) { // 결과 성공 콜백함수
	    			 window.location.href = "/store-list";
	    		}
	    	});
	  }
}
function selectStore(storeSeq){
	var flag=false;
	 $('.storeList').each(function(i){
		 if(storeSeq==$(this).val()){
			 alert("등록된 점포 입니다.");
			 flag=true;
		 }
	  });
	 if(flag){
		 return;
	 }
	 if (!confirm("해당 점포를 내 점포에 등록 하시겠습니까?")) {
	 } else {
	    	$.ajax({
	    		type : "POST",           // 타입 (get, post, put 등등)
	    		url : "/insert-my-store",           // 요청할 서버url
	    		dataType : "text",       // 데이터 타입 (html, xml, json, text 등등)
	    		data : {
	    			'storeSeq' : storeSeq
	    		},
	    		success : function(data) { // 결과 성공 콜백함수
	    			 window.location.href = "/store-list";
	    		}
	    	});
	  }
}

//점포 목록 테이블 데이터 변경
function insertTableContent(data){
	var content = "";
	for (var i = 0; i < data[0].length; i++) {
		content += '<tr><td><input type="radio" name="select-store" onclick="selectStore('+data[0][i].storeSeq+')">'
			+ '<input type="hidden" value="'+data[0][i].storeSeq+'"></td>\n'
			+ '<td>'+data[0][i].storeNm+'</td>\n' 
			+ '<td>'+data[0][i].storeAddr+'</td>\n</tr>'; 
	}
	$(".table>tbody").html(content);
	$(".hidden-keyword").val(data[2].keyword);
}

//점포 목록 조회에서 페이지 이동
function movePage(page){
	var keyword=$(".hidden-keyword").val();
	$.ajax({
		type : "GET",           // 타입 (get, post, put 등등)
		url : "/search-store-list",           // 요청할 서버url
		dataType : "json",       // 데이터 타입 (html, xml, json, text 등등)
		data : {
			'keyword' : keyword,
			'page':page
		},
		success : function(data) { // 결과 성공 콜백함수
			insertTableContent(data);
			insertPageContent(data);					
		}
	});
}

//점포 목록 조회 페이지에서 페이지 번호 데이터 입력
function insertPageContent(data){
	inputPagingString="";
	for(var i=data[1].startPage;i<=data[1].endPage;i++){
		var selected=data[1].currentPage == i ? 'selected' : '';
		if(i<=data[1].totalPage){
			inputPagingString+="<a href='javascript:void(0)' onclick='movePage("+i+")' class='pagination page-btn "+selected+"'>"+i+"</a>\n"
		}
	}
	$(".page-btn").html(inputPagingString)
	
	if(data[1].currentPage>1){
		$(".left-btn").removeAttr("onclick");
		$(".left-btn").attr("onclick","movePage("+(data[1].currentPage-1)+")");
	}else{
		$(".left-btn").removeAttr("onclick");
	}

	if(data[1].currentPage<data[1].totalPage){
		$(".right-btn").removeAttr("onclick");
		$(".right-btn").attr("onclick","movePage("+(data[1].currentPage+1)+")");
	}else{
		$(".right-btn").removeAttr("onclick");
	}
	
	$(".end-btn").removeAttr("onclick");
	$(".end-btn").attr("onclick","movePage("+(data[1].totalPage)+")");
	
	
	
	applyPagingCss();
}
//페이징 CSS 적용
function applyPagingCss(){
	$(".page-btn").css('font-weight','normal');
	$(".page-btn").css('display','flex');
	$(".page-btn").css('width','auto');
	$(".page-btn").css('background-color','white');
	$(".page-btn").css('color','black');
	$(".pagination .selected").css("font-weight","bold");
	$(".pagination .selected").css("border-radius","50%");
	$(".pagination .selected").css("display","inline-block");
	$(".pagination .selected").css("width","35px");
	$(".pagination .selected").css("color","white");
	$(".pagination .selected").css("background-color","navy");
}
function selectStrDetail(strSeq){
	$.ajax({
		type : "GET",           // 타입 (get, post, put 등등)
		url : "/select-str-dtl",           // 요청할 서버url
		dataType : "json",       // 데이터 타입 (html, xml, json, text 등등)
		data : {
			'storeSeq':strSeq
		},
		success : function(data) { // 결과 성공 콜백함수
			updateStrInfo(data);
		}
	});
}
function updateStrInfo(data){
	$("input[name=storeNm]").val(data.strObj.storeNm);
	$("input[name=storeTelNo]").val(data.strObj.storeTelNo);
	$("input[name=latitude]").val(data.strObj.latitude);
	$("input[name=longitude]").val(data.strObj.longitude);
	$("input[name=useYn]").val(data.strObj.useYn);
	$("input[name=storeAddr]").val(data.strObj.storeAddr);
	$("input[name=storeNm]").val(data.strObj.storeNm);
	$("input[name=storeAddrDtl]").val(data.strObj.storeAddrDtl);
	$("input[name=storeSeq]").val(data.strObj.storeSeq);
	changeMap(data.strObj.latitude,data.strObj.longitude)
}
function changeMap(lat,log){
	var mapContainer = document.getElementById("staticMap"), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(lat, log), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

	var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

	// 마커가 표시될 위치입니다 
	var markerPosition  = new kakao.maps.LatLng(lat, log); 

	// 마커를 생성합니다
	var marker = new kakao.maps.Marker({
    position: markerPosition
	});

	// 마커가 지도 위에 표시되도록 설정합니다
	marker.setMap(map);
		
}
window.onload = function(){
	 var mallsEnabled = false;

	    function update_selected() {
	        var selectedValue = $("#location").val();
	        mallsEnabled = (selectedValue === "02");
	        if (mallsEnabled) {
	            $("#locationSeoul").prop('disabled', false);
	        } else {
	            $("#locationSeoul").prop('disabled', true);
	        }
	  
	    }

	    $(function() {
	        update_selected();

	        $("#location").change(function() {
	            update_selected();

	          
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

	    /*storeNm.addEventListener("input", function() {
	        if (storeNm.value === storeNmHidden.value) {
	            submitBtn.disabled = false; // 저장 버튼 활성화
	        } else {
	            submitBtn.disabled = true; // 저장 버튼 비활성화
	        }
	    });*/

	    
	document.getElementById("address_kakao").addEventListener("click", function() {
        new daum.Postcode({
            oncomplete: function(data) {
                document.getElementById("address_kakao").value = data.address;
                document.querySelector("input[name=storeAddrDtl]").focus();
                updateLatLng();
            }
        }).open();
	});
	
	$("#add-store").click(function(){
		$(".search-store").css("display","block");
	})
	// 모달창 밖에 영역 클릭시 삭제
	window.addEventListener("click", function(e) {
		if ($(e.target).hasClass('search-store')) {
			$(".search-store").css("display","none");
		}
	});
	
	//점포 검색한에서 엔터키 입력
	$(".search-input").keyup(function(){
		if(window.event.keyCode==13){
			var keyword=$(".search-input").val();
			$.ajax({
				type : "GET",           // 타입 (get, post, put 등등)
				url : "/search-store-list",           // 요청할 서버url
				dataType : "json",       // 데이터 타입 (html, xml, json, text 등등)
				data : {
					'keyword' : keyword,
					'page':1
				},
				success : function(data) { // 결과 성공 콜백함수
					insertTableContent(data);
					insertPageContent(data);					
				}
			});
		}
	})
}
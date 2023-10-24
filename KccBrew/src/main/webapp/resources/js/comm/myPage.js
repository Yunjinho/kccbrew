$(document).ready(function(){
	// 파일 업로드 input 변경 시 미리보기 처리
	$('#file').on('change', function (e) {
		var fileInput = e.target;
		var profileImg = $('#profileImg');

		if (fileInput.files && fileInput.files[0]) {
			var reader = new FileReader();

			reader.onload = function (e) {
				profileImg.attr('src', e.target.result);
			};
			reader.readAsDataURL(fileInput.files[0]);
		}
		if(fileInput.files.length > 0){
			$("#confirmModBtn").show();
			$("#confirmModExceptImgBtn").hide();
		};
	});

	// 이미지 변경 버튼 클릭 시 파일 업로드 input 클릭
	$('#changeImageBtn').click(function () {
		console.log('이미지 변경 버튼 클릭');
		$('#file').click();
	});

	//수정 확인 버튼
	$("#confirmModBtn").click(function(){
		var selectedMachineCode = $("#chooseMachineCode").val();
		var selectedLocationCode = $("#chooseDetailLocationCode").val();
		var formData = new FormData();
		formData.append('userImg', $('#file')[0].files[0]);

		$("#machineCodeHidden").val(selectedMachineCode);
		$("#locationCodeHidden").val(selectedLocationCode);

		alert("정보를 수정했습니다");
		$("#updateProfileForm").attr("action","/confirmmod");
		$("#updateProfileForm").submit();
	});

	//이미지를 제외한 수정 확인 버튼
	$("#confirmModExceptImgBtn").click(function(){
		var selectedMachineCode = $("#chooseMachineCode").val();
		var selectedLocationCode = $("#chooseDetailLocationCode").val();
		var formData = new FormData();

		$("#machineCodeHidden").val(selectedMachineCode);
		$("#locationCodeHidden").val(selectedLocationCode);

		alert("정보를 수정했습니다");
		$("#updateProfileForm").attr("action","/confirmmodexceptimg");
		$("#updateProfileForm").submit();
	});
	
	//카카오 주소 API
	$("#address_kakao").click(function(){
		new daum.Postcode({
			oncomplete: function(data) { //선택시 입력값 세팅
				document.getElementById("address_kakao").value = data.address; // 주소 넣기
				document.querySelector("input[name=userAddressDtl]").focus(); //상세입력 포커싱
			}
		}).open();
	})
	
	//각 조건 확인
	$("input[name=userName]").focusout(function(){
		checkName();
	})
	
	$("input[name=userEmail]").focusout(function(){
		checkUserEmail();
	})
	
	$("input[name=userTelNo]").focusout(function(){
		checkCallNum();
	})
	
	$("input[name=userAddress]").focusout(function(){
		checkUserAddr();
	})
	
	$("input[name=userImg]").focusout(function(){
		checkImg();
	})
	
	$("select[name=chooseMachineCode]").focusout(function(){
		checkMachineCd();
	})
	
	$("select[name=mechaLocation]").focusout(function(){
		checkLocation();
	})
	
	$("select[name=mechaLocationCode]").focusout(function(){
		checkLocationCode();
	})
});

//사진 확장자 체크-> 이미지 사진만 올리게 
function imgTypeCheck(fileName){
	var userImg=fileName.files[0];
	//파일 확장자 추출	
	var fileLen = userImg.name.length;
	var lastDot = userImg.name.lastIndexOf('.');
	var fileType = userImg.name.substring(lastDot, fileLen).toLowerCase();

	//확장자 비교
	if(fileType == ".jpeg" || fileType == ".jpg" || fileType == ".png"){
		$("#imgFileMsg").css("display","none")
		var reader = new FileReader();
		reader.onload = (e) => {
			var image = new Image();
			image.src=e.target.result;
			$("#file").attr("src",e.target.result);
		};
		$(".insert-img").css("border","none");
		reader.readAsDataURL(userImg);
	}else{
		$("#imgFileMsg").html("사용자 사진은 jpeg, jpg, png 확장자를 가진 파일만 사용할 수 있습니다.");
		$("#imgFileMsg").css("display","block");
		$("#file").css("display","none");
		$("input[name=userImg]").val("");
	}
}

var locationCd = document.getElementById("locationCd").textContent;
getLocationName2(locationCd);

//대분류 지역 코드 변경 시 소분류 지역 코드 변경
function changeLocationDtlCd(data){
	var locOption=$("select[name=mechaLocationCode]");
	var content = '<option>== 선택 ==</option>';

	for(var i=0;i<data.length;i++){
		content +='<option value="'+data[i].grpCdDtlId+'" >'+data[i].grpCdDtlNm+'</option>'
	}
	locOption.html(content);
}

//대분류 지역 코드 값 맞춰서 소분류 지역 값 조회
function changeLocationCd(){
	var locCd = $("select[name=mechaLocation] option:selected").val();
	$.ajax({
		type : "GET",           // 타입 (get, post, put 등등)
		url : "/searchlocationcode",           // 요청할 서버url
		dataType : "json",       // 데이터 타입 (html, xml, json, text 등등)
		data : {
			'locCd' : locCd,
		},
		success : function(data) { // 결과 성공 콜백함수
			changeLocationDtlCd(data);
		}
	});
}

//주소 조건 체크
function checkUserAddr() {
	var userAddress = $("input[name=userAddress]");
	if(userAddress.val() == ""){
		$("#userAddrMsg").css("display","block");
		return false;
	}else {
		$("#userAddrMsg").css("display","block");
		return true;
	}
}

//이메일 조건 체크
function checkUserEmail() {
	var userEmail = $("input[name=userEmail");
	if(userEmail.val() == ""){
		$("#emailMsg").css("display","block");
		return false;
	}else {
		var regex=/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/
			if(!(regex).test(userEmail.val())){
				$("#emailMsg").html("이메일을 다시 한 번 확인해주세요.")
				$("#emailMsg").css("display","block");
				return false;
			}
		$("#userAddreMsg").css("display","none");
		return true;
	}
}
//이름 조건 확인
function checkName() {
	var userName = $("input[name=userName");
	if(userName.val() == ""){
		$("#userNameMsg").css("display","block");
		return false;
	}else {
		$("#userNameMsg").css("display","none");
		return true;
	}
}

//전화번호 조건 확인
function checkCallNum() {
	var userCallNum = $("input[name=userTelNo]");
	if(userCallNum.val() == ""){
		$("#userCallNumberMsg").css("display", "block");
		return false;
	}else {
		var regex=/^\d{10,11}$/;
			if(!(regex).test(userCallNum.val())){
				$("#userCallNumberMsg").html("전화번호를 다시 한 번 확인해주세요.");
				$("#userCallNumberMsg").css("display", "block");
				return false;
			}
		$("#userCallNumberMsg").css("display", "none");
		return true;
	}
}

//활동 지역 조건 확인
function checkLocation() {
	if($("select[name=mechaLocation").val() == "== 선택 =="){
		$("#locationCdMsg").css("display","block");
		return false;
	}else {
		$("#locationCdMsg").css("display","none");
		return true;
	}
}
//활동 지역 상세 조건 확인
function checkLocationCode() {
	if($("select[name=mechaLocationCode").val() == "== 선택 =="){
		$("#locationDtlCdMsg").css("display","block");
		return false;
	}else {
		$("#locationDtlCdMsg").css("display","none");
		return true;
	}
}

//장비 코드 조건 확인
function checkMachineCd() {
	if($("select[name=chooseMachineCode").val == "== 선택 =="){
		$("#machineCdMsg").css("display","block");
		return false;
	}else {
		$("#machineCdMsg").css("display","none");
		return true;
	}
}

//사진 등록 여부 확인
function checkImg() {
	if($("input[name=userImg").val() == ""){
		$("#imgFileMsg").css("display","block");
		return false;
	}else {
		$("#imgFileMsg").css("display","none");
		return true;
	}
}





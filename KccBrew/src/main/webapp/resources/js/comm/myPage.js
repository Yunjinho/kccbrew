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

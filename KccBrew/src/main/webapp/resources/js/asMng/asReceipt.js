function addFile(){
	var	str="<div>\n<input type='file' name='imgFile' value='' onchange='imgTypeCheck(this)'>\n</div>";
	$(".file-upload-div").append(str);
}
function removeFile(){
	var str=$(".file-upload-div>div:last-child").remove();
}
function imgTypeCheck(fileName){
	var imgFile=fileName.files[0];
	//파일 확장자 추출	
	var fileLen = imgFile.name.length;
	var lastDot = imgFile.name.lastIndexOf('.');
	var fileType = imgFile.name.substring(lastDot, fileLen).toLowerCase();
	//확장자 비교
	if(fileType == ".jpeg" || fileType == ".jpg" || fileType == ".png"){
	}else{
		alert("사진 : jpeg, jpg, png 확장자를 가진 파일만 사용하실 수 있습니다.");
		$("input[name=imgFile]").val("")
	}
}
function changeStr(){
	var selected=$("select[name=storeNm] option:selected");
	var index=selected.index();

	var addr=$("select[name=storeAddr]");
	addr.prop("selectedIndex", index);
};
function changeStartDate(){
	var start=$("input[name=wishingStartDate]").val();
	var end=$("input[name=wishingEndDate]").val();
	if(end=='') return;
	if(start>end){
		alert("시작일은 마지막일보다 이전이여야합니다.");
		$("input[name=wishingStartDate]").val("");
	}
}
function changeEndDate(){
	var start=$("input[name=wishingStartDate]").val();
	var end=$("input[name=wishingEndDate]").val();
	if(start=='') return;
	if(start>end){
		alert("마지막일은 시작일보다 이후이여야합니다.");
		$("input[name=wishingEndDate]").val("");
	}
}
window.onload=function(){

	history.replaceState({}, null, location.pathname);
};

/*ajax로 AS접수*/
function performSubmit() {
	var form = document.getElementById("receipt-form");
	var formData = new FormData(form);

	$.ajax({
		url: form.action,
		type: form.method,
		data: formData,
		processData: false, 
		contentType: false, 
		success: function(data) {
			sendAsReceiptAlarm();
			
			window.location.href = '/as-list';
			alert('AS접수가 등록되었습니다!');
		},
		error: function(error) {
			console.log('performSubmit()함수의 ajax 요청 실패:', error);
		}
	});
}



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
	
}
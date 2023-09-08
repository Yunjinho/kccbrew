function addFile(){
	var	str="<div>\n<input type='file' name='imgFile'>\n</div>";
	$(".file-upload-div").append(str);
}

function removeFile(){
	var str=$(".file-upload-div>div:last-child").remove();
}
window.onload=function(){
	history.replaceState({}, null, location.pathname);
	
}
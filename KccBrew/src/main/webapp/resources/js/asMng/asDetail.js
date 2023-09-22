function selectReceiptInfo(){
	$("#view-receipt>div").css("background-color","#043763");
	$("#view-receipt>div>span").css("color","white");
	$("#view-result>div").css("background-color","white");
	$("#view-result>div>span").css("color","#767676");

	$(".as-receipt-info").css("display","block");
	$(".as-result-info").css("display","none");
}
function selectResultInfo(userTypeCd,asResultSeq){
	
	if(asResultSeq==null && ( userTypeCd=='01'|| userTypeCd=='02')){
		alert("AS가 완료되지 않은 신청건입니다.");
		return;
	}
	$("#view-receipt>div").css("background-color","white");
	$("#view-receipt>div>span").css("color","#767676");
	$("#view-result>div").css("background-color","#043763");
	$("#view-result>div>span").css("color","white");
	
	$(".as-receipt-info").css("display","none");
	$(".as-result-info").css("display","block");
}
//대분류 지역 조건 변경 시 소분류 지역값들 변경 함수
function changeLosctionDtlCd(data){
	var locOption=$("select[name=locationCd]");
	var content='<option value="">지역 상세 선택</option>';
	
	for(var i=0;i<data.length;i++){
		content +='<option value="'+data[i].grpCdDtlId+'" >'+data[i].grpCdDtlNm+'</option>'
	}
	locOption.html(content);
}
//대분류 지역 선택한 값에 맞춰서 소분류 지역 값 조회
function changeLocationCd(){
	var locCd = $("select[name=location] option:selected").val();
	$.ajax({
	    type : "POST",           // 타입 (get, post, put 등등)
	    url : "/search-location-cd",           // 요청할 서버url
	    dataType : "json",       // 데이터 타입 (html, xml, json, text 등등)
	    data : { 
			'locCd' : locCd,
		},
	    success : function(data) { // 결과 성공 콜백함수
			changeLosctionDtlCd(data);
	    }
	});
}
//해당 일정에 근무 할 수 있는 수리기사 조회
function changeMach(){
	var locationCd=$("select[name=locationCd] option:selected").val();
	var visitDttm=$("input[name=visitDttm]").val();
	var machineCd=$("input[name=machineCd]").val();
	$.ajax({
		type : "POST",           // 타입 (get, post, put 등등)
	    url : "/search-mecha",           // 요청할 서버url
	    dataType : "json",       // 데이터 타입 (html, xml, json, text 등등)
	    data : {
			'locationCd' : locationCd,
			'visitDttm' : visitDttm,
			'machineCd' : machineCd
		},
	    success : function(data) { // 결과 성공 콜백함수
	    	insertMechaList(data)
	    }
	})
}

function insertMechaList(data){
	var html="<select name='mechanicId' class='selectMcha' required>"+"<option value=''>수리 기사 선택</option>";
	for(var i=0;i<data.mechaList.length;i++){
		html+="<option value="+data.mechaList[i].userId+">"+data.mechaList[i].userNm+"</option>";
	}
	html+="</select>"
	$("select[name=mechanicId]").html(html);
}
function selectLocation(){changeMach()}
function selectDate(){
	var strMngId=$("input[name=strMngId]").val();
	var visitDttm=$("input[name=visitDttm]").val();
	$.ajax({
		type : "POST",           // 타입 (get, post, put 등등)
	    url : "/check-str-schedule",           // 요청할 서버url
	    dataType : "json",       // 데이터 타입 (html, xml, json, text 등등)
	    data : {
	    	'strMngId'	: strMngId,
			'visitDttm' : visitDttm
		},
	    success : function(data) { // 결과 성공 콜백함수
	    	if(data.count>0){
	    		$("input[name=visitDttm]").val("");
	    		alert("해당 날짜는 점포 휴무일입니다.");
	    		return;
	    	}
	    }
	})
	changeMach()
	
}

function rejectAs(userTypeCd){
	$("html").css("overflow","hidden");
	$(".modal-reject").css("display","block");
}
function resultAs(reapply){
	$("html").css("overflow","hidden");
	$("input[name=resultReapply]").val(reapply);
	$(".modal-result").css("display","block");
}

function resubmission(reapply){
	$("html").css("overflow","hidden");
	$(".modal-rejectContent").css("display","block");
}

function cancelModal(){
	$("html").css("overflow","auto");
	$(".modal-reject").css("display","none");
	$(".modal-result").css("display","none");
	$(".modal-rejectContent").css("display","none");
}

function rejectConfirm(flag){
	var visitDttm=$("input[name=visitDttm]").val();
	var mechanicId=$("select[name=mechanicId] option:selected").val();
	var asAssignSeq=$("input[name=asAssignSeq]").val();
	var asInfoSeq=$("input[name=asInfoSeq]").val();
	
	$.ajax({
		type : "POST",           // 타입 (get, post, put 등등)
	    url : "/reject-confirm",           // 요청할 서버url
	    dataType : "text",       // 데이터 타입 (html, xml, json, text 등등)
	    data : {
	    	'mechanicId': mechanicId,
			'visitDttm' : visitDttm,
			'asAssignSeq' : asAssignSeq,
			'asInfoSeq'	: asInfoSeq,
			'flag'		: flag
		},
	    success : function(data) { // 결과 성공 콜백함수
	    	location.replace("/as-list");
	    }
	})
}
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

$(document).ready(function(){
	$("input[name=resultDttm]").val(new Date().toISOString().substring(0, 10))
})
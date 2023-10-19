function resetSearch(){
	$("input[name=wishingStartDate]").val("");
	$("input[name=wishingEndDate]").val("");
	$("input[name=asInfoSeq]").val("");
	$("input[name=storeNm]").val("");
	$("input[name=storeAddr]").val("");
	$("input[name=searchId]").val("");
	$("select[name=machineCd] option:eq(0)").prop("selected", true);
	$("select[name=asStatusCd] option:eq(0)").prop("selected", true);
	
}
function movePage(pageNumber){
		
	$("input[name=currentPage]").val(pageNumber);
	
	$("#search-form").submit();
}
function selectAsDetail(asInfoSeq,asAssignSeq,storeSeq){
	if(asAssignSeq==-1){
		asAssignSeq=""
	}
	location.href="/as-detail?asInfoSeq="+asInfoSeq+"&asAssignSeq="+asAssignSeq+"&storeSeq="+storeSeq;
}
function checkDate(){
	var start=$("input[name=wishingStartDate]").val();
	var end=$("input[name=wishingEndDate]").val();
	if(end==''||start==''){ return true;}
	if(start>end){
		alert("시작일은 마지막일보다 이전이여야합니다.");
		$("input[name=wishingStartDate]").val("");
		$("input[name=wishingEndDate]").val("");
		return false;
	}
	return true;
}
function downExcel(flag){
	
	var currentPage=$("input[name=currentPage]").val();
	var wishingStartDate=$("input[name=wishingStartDate]").val();
	var wishingEndDate=$("input[name=wishingEndDate]").val();
	var asInfoSeq=$("input[name=asInfoSeq]").val(); 
	var storeNm=$("input[name=storeNm]").val();
	var storeAddr=$("input[name=storeAddr]").val();
	var searchId=$("input[name=searchId]").val();
	var machineCd=$("select[name=machineCd] option:selected").val();
	var asStatusCd=$("select[name=asStatusCd] option:selected").val();
	console.log(currentPage+","+wishingEndDate+","+wishingStartDate+","+asInfoSeq+","+storeNm+","+storeAddr
			+","+searchId+","+machineCd+","+asStatusCd)
	console.log("상태는 " + asStatusCd);
	$.ajax({
		type : "POST",           // 타입 (get, post, put 등등)
	    url : "/download-list",           // 요청할 서버url
	    dataType : "text",       // 데이터 타입 (html, xml, json, text 등등)
	    data : {
	    	'flag': flag,
	    	'currentPage':currentPage,
	    	'wishingStartDate': wishingStartDate,
	    	'wishingEndDate' : wishingEndDate,
	    	'asInfoSeq': asInfoSeq,
	    	'storeNm': storeNm,
	    	'storeAddr': storeAddr,
	    	'searchId': searchId,
	    	'machineCd': machineCd,
	    	'asStatusCd': asStatusCd
		},
	    success : function(data) { // 결과 성공 콜백함수
	    	alert("다운로드가 완료되었습니다.")
	    }
	})
}
window.onload=function(){
	history.replaceState({}, null, location.pathname);
	
	$("#search-submit").click(function(){
		if(($("input[name=asInfoSeq]").val()=="")){
			if(isNaN($("input[name=asInfoSeq]").val())){
				alert("AS 번호는 숫자만 입력 가능합니다.")
				return;
			}
		}
		if(checkDate()){
			$("#search-form").submit();
		}
	})
		
	
	$("#receipt-as").click(function(){
		location.href="/as-receipt"
	})
}
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
function selectAsDetail(asInfoSeq,asAssignSeq){
	if(asAssignSeq==null){
		asAssignSeq=""
	}
	location.href="/as-detail?asInfoSeq="+asInfoSeq+"&asAssignSeq="+asAssignSeq;
}
function downExcel(flag){
	
	var currentPage=$("#search-form>input[name=currentPage]").val();
	var startYr=$("#search-form>input[name=startYr]").val();
	var startMn=$("#search-form>input[name=startMn]").val();
	var endYr=$("#search-form>input[name=endYr]").val();
	var endMn=$("#search-form>input[name=endMn]").val();
	var asInfoSeq=$("#search-form>input[name=asInfoSeq]").val(); 
	var storeNm=$("#search-form>input[name=storeNm]").val();
	var storeAddr=$("#search-form>input[name=storeAddr]").val();
	var searchId=$("#search-form>input[name=searchId]").val();
	var machineCd=$("#search-form>input[name=machineCd]").val();
	var asStatusCd=$("#search-form>input[name=asStatusCd]").val();
	
	$.ajax({
		type : "POST",           // 타입 (get, post, put 등등)
	    url : "/download-list",           // 요청할 서버url
	    dataType : "text",       // 데이터 타입 (html, xml, json, text 등등)
	    data : {
	    	'flag': flag,
	    	'currentPage':currentPage,
	    	'startYr': startYr,
	    	'startMn': startMn,
	    	'endYr': endYr,
	    	'endMn': endMn,
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
function checkEndDate(){
	var startDate=$("input[name=wishingStartDate]").val();
	var endDate=$("input[name=wishingEndDate]").val();
	
	if(startDate=="") return;
	if(startDate>endDate) {
		alert("검색 마지막 일은 검색 시작일보다 이후이어야 합니다.");
		$("input[name=wishingEndDate]").val("");
	}
	
}
function checkStartDate(){
	var startDate=$("input[name=wishingStartDate]").val();
	var endDate=$("input[name=wishingEndDate]").val();
	if(endDate=="") return;
	if(startDate>endDate) {
		alert("검색 시작일은 검색 마지막 일보다 이전이어야 합니다.");
		$("input[name=wishingStartDate]").val("");
	}
}
window.onload=function(){
	history.replaceState({}, null, location.pathname);
	
	$("#receipt-as").click(function(){
		location.href="/as-receipt"
	})
}
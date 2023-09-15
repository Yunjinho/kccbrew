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
window.onload=function(){
	history.replaceState({}, null, location.pathname);
	
	$("#receipt-as").click(function(){
		location.href="/as-receipt"
	})
}
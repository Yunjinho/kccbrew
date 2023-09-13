function movePage(pageNumber){
	var url='/searchAsList?page='+pageNumber;
	$("#search-form").attr("action",url).submit();
}
function selectAsDetail(asInfoSeq){
	location.href="/as-detail?asInfoSeq="+asInfoSeq;
}
window.onload=function(){
	history.replaceState({}, null, location.pathname);
	
	$("#receipt-as").click(function(){
		location.href="/as-receipt"
	})
}
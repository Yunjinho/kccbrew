function movePage(pageNumber){
	var url='/searchAsList?page='+pageNumber;
	$("#search-form").attr("action",url).submit();
}
window.onload=function(){
	history.replaceState({}, null, location.pathname);
	
	$("#receipt-as").click(function(){
		
	})
}
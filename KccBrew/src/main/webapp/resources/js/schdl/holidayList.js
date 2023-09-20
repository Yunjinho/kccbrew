document.addEventListener("DOMContentLoaded", function() {


	/*날짜 구간 선택 시 날짜 기본값 설정*/
	setInputElementValue('selectedStartDate', getFirstDayOfYear());
	setInputElementValue('selectedEndDate', getCurrentDate());
});


/*검색조건 ajax로 전송*/
function performSearch() {
	// 폼 요소 가져오기
	var form = document.forms["srhForm"];

	// Ajax 요청 보내기
	$.ajax({
		url: form.action, 
		type: form.method,
		data: $(form).serialize(), 
		success: function(data) {
			console.log('Ajax 요청 성공:', data);
		},
		error: function(error) {
			// Ajax 요청이 실패했을 때 실행할 코드
			console.log('Ajax 요청 실패:', error);
		}
	});
}


/*구간으로 입력 시 바로 날짜 이동*/
function goDate(){
	var selectedStartDate = document.getElementById('selectedStartDate').value;
	var selectedEndDate = document.getElementById('selectedEndDate').value;
	var fm = document.srhForm;
	fm.startDate.value = selectedStartDate;
	fm.endDate.value = selectedEndDate;
	performSearch();
	return true;
}


function postPopup() {
    var url = "/holiday/registration";
    var name = "팝업 테스트";
    var width = 800; // 원하는 너비로 설정
    var height = 500; // 원하는 높이로 설정
    var option = "width=" + width + ",height=" + height + ",top=200,left=450,directories=no,location=no";
    window.open(url, name, option);
}

//페이지 로드 이벤트 핸들러 등록
window.onload = onPageLoad;

//페이지가 로드되면 실행될 함수
function onPageLoad() {

	/*폼에 날짜 기본값 설정*/
/*	setInputElementValue('startDate', getFirstDayOfYear());
	setInputElementValue('endDate', getCurrentDate());*/


}



/*페이징*/
function goPage(arg){
	var fm = document.srhForm;
	fm.currentPage.value = arg;
	performSearch();
	return true;

}


/*날짜검색*/
function goDate(startDate, endDate){
	var fm = document.srhForm;
	fm.startDate.value = startDate;
	fm.endDate.value = endDate;
	return true;
}


/*캘린더 테이블*/
function getCurrentDate() {
	var today = new Date();
	var year = today.getFullYear();
	var month = String(today.getMonth() + 1).padStart(2, '0'); // 월은 0부터 시작하므로 +1을 해주고 2자리로 포맷팅
	var day = String(today.getDate()).padStart(2, '0'); // 일도 2자리로 포맷팅
	return year + '-' + month + '-' + day;
}

//올해의 첫날을 가져오는 함수
function getFirstDayOfYear() {
	const today = new Date();
	const year = today.getFullYear();
	return year + '-01-01';
}

//input 요소에 값을 설정하는 함수
function setInputElementValue(inputName, value) {
	const inputElement = document.querySelector('input[name="' + inputName + '"]');
	if (inputElement) {
		inputElement.value = value;
	}
}

/*현재 년월을 가져오는 함수*/
function getCurrentYearAndMonth() {
	var today = new Date();
	var year = today.getFullYear();
	var month = today.getMonth() + 1; // 월은 0부터 시작하므로 1을 더해줍니다.
	return { year: year, month: month };
}


//이동 버튼 클릭 시 이벤트 처리
function getPeriod() {
	var selectedYear = document.getElementById('yearSelect').value;
	var selectedMonth = document.getElementById('monthSelect').value;
	var startDate = selectedYear + '-' + selectedMonth + '-01';
	var endDate = selectedYear + '-' + selectedMonth + '-' + new Date(selectedYear, selectedMonth, 0).getDate();
	goDate(startDate, endDate);
}

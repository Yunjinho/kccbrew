
//페이지 로드 이벤트 핸들러 등록
window.onload = onPageLoad;

//페이지가 로드되면 실행될 함수
function onPageLoad() {

	/*폼에 날짜 기본값 설정*/
	setInputElementValue('startDate', getFirstDayOfYear());
	setInputElementValue('endDate', getCurrentDate());


}



/*페이징*/
//페이지 이동
function goPage(arg){
	console.log("goPage()함수 실행!");
	var fm = document.srhForm;
	fm.currentPage.value = arg;
	performSearch();
	return true;

}


/*날짜검색*/
//날짜 이동
function goDate(startDate, endDate){
	var fm = document.srhForm;
	fm.startDate.value = startDate;
	fm.endDate.value = endDate;
	performSearch();
	return true;
}


/*캘린더 테이블*/
//현재 날짜를 가져오는 함수
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


function getLastDayAndPopulateTable() {
	var selectedYear = document.getElementById('yearSelect').value;
	var selectedMonth = document.getElementById('monthSelect').value;

	// JavaScript의 Date 객체를 사용하여 마지막 일(day) 계산
	var lastDay = new Date(selectedYear, selectedMonth, 0).getDate();

	// 테이블 요소를 가져옵니다.
	var table = document.getElementById('cal-table');

	// thead의 첫 번째 행을 가져옵니다.
	var firstRow = table.querySelector('thead tr:first-child');

	// thead의 두 번째 행을 가져옵니다.
	var secondRow = table.querySelector('thead tr:nth-child(2)');

	// 1행 5열부터 20열까지의 자식 요소를 삭제
	while (firstRow.children[5]) {
		firstRow.removeChild(firstRow.children[5]);
	}

	// 2행 5열부터 20열까지의 자식 요소를 삭제
	while (secondRow.children[0]) {
		secondRow.removeChild(secondRow.children[0]);
	}

	// 1행 5열부터 20열까지는 1부터 16까지 표시
	for (var i = 1; i <= 16; i++) {
		var td = document.createElement('td');
		td.textContent = i;
		firstRow.appendChild(td);
	}

	// 2행 5열부터 20열까지는 17부터 마지막 일수까지 표시
	var startDay = 17;
	for (var i = 5; i <= 20; i++) {
		var td = document.createElement('td');
		if (startDay <= lastDay) {
			td.textContent = startDay;
			startDay++;
		} else {
			td.textContent = "";
		}
		secondRow.appendChild(td);
	} 
}

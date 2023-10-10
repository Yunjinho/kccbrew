document.addEventListener("DOMContentLoaded", function() {

	/*날짜 구간 선택 시 날짜 기본값 설정*/
	setInputElementValue('startDate', getFirstDayOfYear());
	setInputElementValue('endDate', getLastDayOfYear());

});

/*검색조건 ajax로 전송*/
function performSearch() {
	var form = document.forms["srhForm"];

	// Ajax 요청 보내기
	$.ajax({
		url: form.action, 
		type: form.method,
		data: $(form).serialize(), 
		success: function(data) {
			console.log('Ajax 요청 성공:', data);

			/*휴가리스트 테이블에 표시*/
			var logs = data.logs;
			displayLogs(logs);

			/*전체 데이터 수 표시*/
			var totalDataNumber = data.totalDataNumber;
			document.getElementById("totalDataNumber").textContent = totalDataNumber;

			/*페이징처리*/
			var currentPage = data.currentPage;
			var totalPage = data.totalPage;
			var sharePage = data.sharePage;
			updatePageButtons(currentPage, totalPage, sharePage);


		},
		error: function(error) {
			console.log('Ajax 요청 실패:', error);
		}
	});
}



function displayLogs(logs) {
	var tbody = $('#logTableBody');
	tbody.empty(); 

	$.each(logs, function(index, log) {
		var row = $('<tr>');
		row.append($('<td>').text(log.logSeq));
		row.append($('<td>').text(formatDate(new Date(log.date))));
		row.append($('<td>').text(log.uri));
		row.append($('<td>').text(log.view));
		row.append($('<td>').text(log.userId));
		row.append($('<td>').text(log.userType));
		row.append($('<td>').text(log.ip));
		row.append($('<td>').text(log.statusCode));
		tbody.append(row);
	});
}


//input 요소에 값을 설정하는 함수
function setInputElementValue(inputName, value) {
	const inputElement = document.querySelector('input[name="' + inputName + '"]');
	if (inputElement) {
		inputElement.value = value;
	}
}

function resetForm() {
	console.log("resetForm함수실행!");
	const form = document.querySelector('form[name="srhForm"]');
	if (form) {
		form.reset(); 
		setInputElementValue('startDate', getFirstDayOfYear());
		setInputElementValue('endDate', getLastDayOfYear());
	}
}

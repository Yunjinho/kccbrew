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
        row.append($('<td>').text(log.date));
        row.append($('<td>').text(log.uri));
        row.append($('<td>').text(log.view));
        row.append($('<td>').text(log.userId));
        row.append($('<td>').text(log.userType));
        row.append($('<td>').text(log.ip));
        row.append($('<td>').text(log.statusCode));
        tbody.append(row);
    });
}

/*ajax로 받은 휴가리스트 테이블에 추가
function displayLogs(logs) {
	console.log("displayLogs함수실행!");
	var tableBody = document.getElementById("holiday-list-tbody");

	tableBody.innerHTML = "";

	schedules.forEach(function(schedule) {
		var row = tableBody.insertRow(-1); 

		if (userTypeCd === "01") {
			사용자가 관리자인 경우
			var cell1 = row.insertCell(0);
			var cell2 = row.insertCell(1);
			var cell3 = row.insertCell(2);
			var cell4 = row.insertCell(3);
			var cell5 = row.insertCell(4);
			var cell6 = row.insertCell(5);
			var cell7 = row.insertCell(6);
			var cell8 = row.insertCell(7);
			var cell9 = row.insertCell(8);

			cell1.innerHTML = schedule.rowNumber;
			cell2.innerHTML = schedule.userType;
			cell3.innerHTML = schedule.userId;
			cell4.innerHTML = schedule.scheduleId;
			cell5.innerHTML = formatDate(new Date(schedule.appDate));
			cell6.innerHTML = formatDate(new Date(schedule.startDate));
			cell7.innerHTML = formatDate(new Date(schedule.endDate));

			if(schedule.actualUse === "N") {
				cell8.innerHTML = "취소완료";
			}
			else if (new Date(schedule.startDate) <= new Date()) {
				cell8.innerHTML = "이미 사용된 휴가";
			} else  {
				var cancelButton = document.createElement("button");
				cancelButton.textContent = "취소";
				cancelButton.className = "form-btn";
				cancelButton.onclick = function () {
					openCancelModal(schedule.scheduleId, schedule.startDate, schedule.endDate);
				};
				cell8.appendChild(cancelButton);
			} 
			cell9.innerHTML = schedule.actualUse;
		} 
		사용자가 점주 또는 수리기사인 경우
		else {
			var cell1 = row.insertCell(0);
			var cell2 = row.insertCell(1);
			var cell2 = row.insertCell(1);
			var cell3 = row.insertCell(2);
			var cell4 = row.insertCell(3);
			var cell5 = row.insertCell(4);
			var cell6 = row.insertCell(5);

			cell1.innerHTML = schedule.rowNumber;
			cell2.innerHTML = schedule.scheduleId;
			cell2.innerHTML = formatDate(new Date(schedule.appDate));
			cell3.innerHTML = formatDate(new Date(schedule.startDate));
			cell4.innerHTML = formatDate(new Date(schedule.endDate));

			if(schedule.actualUse === "N") {
				cell5.innerHTML = "취소완료";
			}
			else if (new Date(schedule.startDate) <= new Date()) {
				cell5.innerHTML = "이미 사용된 휴가";
			} else  {
				var cancelButton = document.createElement("button");
				cancelButton.textContent = "취소";
				cancelButton.className = "form-btn";
				cancelButton.onclick = function () {
					openCancelModal(schedule.scheduleId, schedule.startDate, schedule.endDate);
				};
				cell5.appendChild(cancelButton);
			} 
			cell6.innerHTML = schedule.actualUse;
		}


	});
}*/
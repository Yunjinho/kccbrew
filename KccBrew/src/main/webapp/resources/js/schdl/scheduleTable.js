document.addEventListener("DOMContentLoaded", function() {
	console.log("해당 페이지 전용 자바스크립트");

	/*년월 선택 시 날짜 기본값 설정*/
	var currentYearAndMonth = getCurrentYearAndMonth();
	document.getElementById('yearSelect').value = currentYearAndMonth.year;
	document.getElementById('monthSelect').value = currentYearAndMonth.month;

	var selectedYear = document.getElementById('yearSelect').value;
	var selectedMonth = document.getElementById('monthSelect').value;

	var lastDay = new Date(selectedYear, selectedMonth, 0).getDate();

	getLastDayAndPopulateTable();

	// 이번 달의 1일과 마지막 날짜를 계산합니다.
	var firstDayOfMonth = new Date(selectedYear, selectedMonth - 1, 1);
	var lastDayOfMonth = new Date(selectedYear, selectedMonth, 0);

	// 입력 필드에 값을 설정합니다.
	$("input[name='startDate']").val(firstDayOfMonth.getFullYear() + "-" + (firstDayOfMonth.getMonth() + 1) + "-01");
	$("input[name='endDate']").val(lastDayOfMonth.getFullYear() + "-" + (lastDayOfMonth.getMonth() + 1) + "-" + lastDayOfMonth.getDate());
});


//AJAX 요청을 보내는 함수
function performSearch() {
	
	var formData = $("form[name='srhForm']").serialize();

	$.ajax({
		type: "POST",
		url: "/admin/schedule", 
		data: formData,
		success: function (response) {
			console.log(response);

			var dataCount = response.length;

			$("#dataCount").text(dataCount);

			var tbody = $("#cal-table tbody");
			tbody.empty();

			for (var i = 0; i < response.length; i++) {
				var schedule = response[i];
				var userId = schedule.userId; // userId 키에 해당하는 값

				/*휴무일 리스트*/
				var holidayDates = schedule.holidayDates;
				var holidayCount = holidayDates.length;

				/*배정일 리스트*/
				var assignDates = schedule.assignDates;
				var assignCount = assignDates.length;
				/*근무일 리스트*/
				var resultDates = schedule.resultDates;
				var resultCount = resultDates.length;

				// <tr> 요소 생성 및 클래스 속성 추가
				var row = $("<tr>").addClass("row-" + (i+1));
				var row2 = $("<tr>").addClass("row-" + (i+1) + " second-row");



				// 첫 번째 행에 rowspan이 2인 셀 5개 추가
				for (var j = 0; j < 5; j++) {
					if (j === 0) {
						$("<td>", { rowspan: 2 }).text(userId).appendTo(row);
						$("<td>", { rowspan: 2 }).text(schedule.userNm).appendTo(row);
						$("<td>", { rowspan: 2 }).text(schedule.locationCd).appendTo(row);
						$("<td>", { rowspan: 2 }).text(schedule.eqpmnCd).appendTo(row);
						$("<td>", { rowspan: 2 }).text("휴무: " + holidayCount + "건, 배정: " + assignCount + "건, 처리: " + resultCount + "건").appendTo(row);
					}
				}


				// <td> 요소 크기 설정
				var tdWidth = "2.5em"; // 가로 크기
				var tdHeight = "2.5em"; // 세로 크기

				for (var k = 0; k < 16; k++) {
					// <td> 요소 생성 및 스타일 설정
					$("<td>")
					.addClass("day-" + (k+1))
					.css({
						"width": tdWidth,
						"height": tdHeight
					})
					.text(" ")
					.appendTo(row);

					$("<td>")
					.addClass("day-" + (k + 17))
					.css({
						"width": tdWidth,
						"height": tdHeight
					})
					.text(" ")
					.appendTo(row2);
				}

				// 테이블 행을 tbody에 추가
				row.appendTo(tbody);
				row2.appendTo(tbody);


				/*휴무일 리스트*/
				for (var j = 0; j < holidayDates.length; j++) {
					var holiday = holidayDates[j];
					var startDay = new Date(holiday.HLDY_STAR); // 시작 날짜
					var endDay = new Date(holiday.HLDY_END); // 종료 날짜

					// 날짜를 밀리초로 변환
					var startTime = startDay.getTime();
					var endTime = endDay.getTime();

					// 시작일부터 종료일까지 반복
					for (var currentDate = new Date(startDay); currentDate <= endDay; currentDate.setDate(currentDate.getDate() + 1)) {

						var day = currentDate.getDate();

						var rowClassName = "row-" + (i + 1);
						var cellClassName = "day-" + day; // 클래스 이름 생성

						var $divElement = $("<div class='schedule-index'></div>")
						.attr("data-user-id", userId)
						.attr("data-schedule-type", "holiday")
						.attr("data-schedule-date", currentDate);

						$divElement.css({
							"background-color": "#b8cff5", // 배경색 설정
							"width": "1.2em", // 가로 크기 설정
							"height": "1.2em", // 세로 크기 설정
							"margin": "1px"
						});

						// 마우스 오버 이벤트 핸들러 추가
						$divElement.on("click", function() {
							var $this = $(this);
							var userId = $this.attr("data-user-id");
							var scheduleType = $this.attr("data-schedule-type");
							var scheduleDate = $this.attr("data-schedule-date");

							// 데이터를 컨트롤러로 보내는 함수 호출
							scheduleIndexData(userId, scheduleType, scheduleDate);
							openModal();
						});


						/*$divElement.hover(
								function() {
									console.log("mouse hover!");
									// 마우스 커서가 요소 위에 있을 때
									var $this = $(this);
									$this.addClass('scale-element');
									$this.parents().addClass('scale-element');
									var userId = $this.attr("data-user-id");
									console.log("userId: " + userId);
									var scheduleType = $this.attr("data-schedule-type");
									console.log("scheduleType: " + scheduleType);
									var scheduleDate = $this.attr("data-schedule-date");
									console.log("scheduleDate: " + scheduleDate);


									var $divContent = $(".div-content");
									$divContent.appendTo($this);

								},
								function() {
									console.log("마우스 벗어남!");
									$this.find('.div-content').remove();
								}
						);*/

						$("." + rowClassName + " ." + cellClassName)
						.append($divElement);
					}
				}


				/*배정일 리스트*/
				for (var k = 0; k < assignDates.length; k++) {
					var assignDate = assignDates[k];
					var currentDate = new Date(assignDate);
					var day = currentDate.getDate(); // 현재 날짜의 일(day) 추출

					// 선택한 행과 열의 <td> 요소를 찾아서 값 대입
					var rowClassName = "row-" + (i + 1);
					var cellClassName = "day-" + day; // 클래스 이름 생성

					var $divElement = $("<div class='schedule-index'></div>");

					// 데이터를 HTML 속성으로 저장
					$divElement.attr("data-user-id", userId);
					$divElement.attr("data-schedule-type", "assign");
					$divElement.attr("data-schedule-date", currentDate);

					$divElement.css({
						"background-color": "rgb(247, 212, 116)", // 배경색 설정
						"width": "1.2em", // 가로 크기 설정
						"height": "1.2em", // 세로 크기 설정
						"margin": "1px" // 가운데 정렬 설정
					});

					// 마우스 오버 이벤트 핸들러 추가
					$divElement.on("click", function() {
						var $this = $(this);
						var userId = $this.attr("data-user-id");
						var scheduleType = $this.attr("data-schedule-type");
						var scheduleDate = $this.attr("data-schedule-date");

						// 데이터를 컨트롤러로 보내는 함수 호출
						scheduleIndexData(userId, scheduleType, scheduleDate);
						openModal();
					});

					// 해당 클래스 이름을 가진 <td> 요소에 <div> 추가
					/*		$("." + rowClassName + " ." + cellClassName)
					.empty() // 기존 내용을 비우기
					.html($divElement);*/

					$("." + rowClassName + " ." + cellClassName)
					.append($divElement); 
				}


				/*근무일 리스트*/
				for (var l = 0; l < resultDates.length; l++) {
					var resultDate = resultDates[l];
					var currentDate = new Date(resultDate);
					var day = currentDate.getDate(); // 현재 날짜의 일(day) 추출

					// 선택한 행과 열의 <td> 요소를 찾아서 값 대입
					var rowClassName = "row-" + (i + 1);
					var cellClassName = "day-" + day; // 클래스 이름 생성

					var $divElement = $("<div class='schedule-index'></div>");

					// 데이터를 HTML 속성으로 저장
					$divElement.attr("data-user-id", userId);
					$divElement.attr("data-schedule-type", "result");
					$divElement.attr("data-schedule-date", currentDate);

					$divElement.css({
						"background-color": "#e8a9ac", // 배경색 설정
						"width": "1.2em", // 가로 크기 설정
						"height": "1.2em", // 세로 크기 설정
						"margin": "1px" // 가운데 정렬 설정
					});

					/*클릭 이벤트핸들러*/
					$divElement.on("click", function() {
						var $this = $(this);
						var userId = $this.attr("data-user-id");
						var scheduleType = $this.attr("data-schedule-type");
						var scheduleDate = $this.attr("data-schedule-date");

						// 데이터를 컨트롤러로 보내는 함수 호출
						scheduleIndexData(userId, scheduleType, scheduleDate);
						openModal();
					});

					// 해당 클래스 이름을 가진 <td> 요소에 <div> 추가
					/*	$("." + rowClassName + " ." + cellClassName)
					.empty() // 기존 내용을 비우기
					.html($divElement);*/

					$("." + rowClassName + " ." + cellClassName)
					.append($divElement); 
				}


			}

		},
		error: function (error) {
			// 에러 처리
			console.error("AJAX 요청 실패:", error);
		}
	});
}

/*회원아이디, 스케줄타입에 따른 스케줄정보 조회*/
function scheduleIndexData(userId, scheduleType, scheduleDate) {

	var date = new Date(scheduleDate);
	var year = date.getFullYear();
	var month = String(date.getMonth() + 1).padStart(2, '0'); 
	var day = String(date.getDate()).padStart(2, '0'); 
	var formattedDate = year + '-' + month + '-' + day;

	$.ajax({
		type: "POST",
		url: "/schedule-Info", 
		data: {
			userId: userId,
			scheduleType: scheduleType,
			scheduleDate: formattedDate
		},
		success: function(response) {
			console.log("Response from server:"+ response);

			// scheduleType 값에 따라 다른 함수 호출
			if (scheduleType === "holiday") {
				displayHolidayData(response);
			} else if (scheduleType === "assign") {
				displayAssignData(response);
			} else if (scheduleType === "result") {
				displayResultData(response);
			} 
		},
		error: function(error) {
			console.error("Error:", error);
		}
	});
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

/*모달창에 휴가정보 표시*/
function displayHolidayData(data) {
	console.log("data: " + data);

	var contentTitle = document.getElementById("contentTitle");

	contentTitle.textContent = "휴가상세";

	var listContainer = document.getElementById("listContainer");


	var holidaySeq = data.holidaySeq;
	var  startDate = formatDate(new Date(data.startDate));
	var  endDate = formatDate(new Date(data.endDate));
	var  appDate = formatDate(new Date(data.appDate));

	// 기존 자식 요소 모두 제거
	while (listContainer.firstChild) {
		listContainer.removeChild(listContainer.firstChild);
	}

	for (var i = 0; i < data.length; i++) {
		var holidaySeq = data[i].holidaySeq; // 휴일 아이디 데이터 가져오기
		var appDate = formatDate(new Date(data[i].appDate)); // 신청일 데이터 가져오기
		var startDate = formatDate(new Date(data[i].startDate)); // 시작일 데이터 가져오기
		var endDate = formatDate(new Date(data[i].endDate)); // 종료일 데이터 가져오기

		var listItem1 = document.createElement("li");
		listItem1.textContent = "휴일 아이디: " + holidaySeq;
		var listItem4 = document.createElement("li");
		listItem4.textContent = "신청일: " + appDate;
		var listItem2 = document.createElement("li");
		listItem2.textContent = "시작일: " + startDate;
		var listItem3 = document.createElement("li");
		listItem3.textContent = "종료일: " + endDate;

		listContainer.appendChild(listItem1);
		listContainer.appendChild(listItem2);
		listContainer.appendChild(listItem3);
		listContainer.appendChild(listItem4);
	}
}


/*모달창에 배정정보 표시*/
function displayAssignData(data) {
	console.log("displayAssignData함수실행!");

	var contentTitle = document.getElementById("contentTitle");

	contentTitle.textContent = "AS배정상세";

	var listContainer = document.getElementById("listContainer");

	// 기존 자식 요소 모두 제거
	while (listContainer.firstChild) {
		listContainer.removeChild(listContainer.firstChild);
	}

	for (var i = 0; i < data.length; i++) {
		var asAssignSeq = data[i].asAssignSeq;
		var visitDate = formatDate(new Date(data[i].visitDate));
		var confirmDate = formatDate(new Date(data[i].confirmDate));
		var regDate = formatDate(new Date(data[i].regDate));
		var regUser = data[i].regUser;
		var modDate = formatDate(new Date(data[i].modDate));
		var modUser = data[i].modUser;
		var reAssign = data[i].reAssign;
		var rejectContentMecha = data[i].rejectContentMecha;

		var listItem1 = document.createElement("li");
		listItem1.textContent = "접수배정번호: " + asAssignSeq;
		var listItem2 = document.createElement("li");
		listItem2.textContent = "수리예정일: " + visitDate;
		var listItem3 = document.createElement("li");
		listItem3.textContent = "배정확인일: " + confirmDate;
		var listItem4 = document.createElement("li");
		listItem4.textContent = "등록일: " + regDate;
		var listItem5 = document.createElement("li");
		listItem5.textContent = "등록자: " + regUser;
		var listItem6 = document.createElement("li");
		listItem6.textContent = "수정일: " + modDate;
		var listItem7 = document.createElement("li");
		listItem7.textContent = "수정자: " + modUser;
		var listItem8 = document.createElement("li");
		listItem8.textContent = "재배정: " + reAssign;
		var listItem9 = document.createElement("li");
		listItem9.textContent = "반려내용: " + rejectContentMecha;

		listContainer.appendChild(listItem1);
		listContainer.appendChild(listItem2);
		listContainer.appendChild(listItem3);
		listContainer.appendChild(listItem4);
		listContainer.appendChild(listItem5);
		listContainer.appendChild(listItem6);
		listContainer.appendChild(listItem7);
		listContainer.appendChild(listItem8);
		if (rejectContentMecha !== null) {
			listContainer.appendChild(listItem9);
		}
	}

}


/*모달창에 결과정보 표시*/
function displayResultData(data) {
	console.log("displayResultData 함수 실행!");

	var contentTitle = document.getElementById("contentTitle");
	contentTitle.textContent = "AS 결과 상세";

	var listContainer = document.getElementById("listContainer");

	// 기존 자식 요소 모두 제거
	while (listContainer.firstChild) {
		listContainer.removeChild(listContainer.firstChild);
	}

	for (var i = 0; i < data.length; i++) {
		var listItem = document.createElement("li");
		var asResultSeq = data[i].asResultSeq;
		var resultDetail = data[i].resultDetail;
		var storeManagerFeedBack = data[i].storeManagerFeedBack;
		var resultDate = formatDate(new Date(data[i].resultDate));
		var fileSeq = data[i].fileSeq;
		var asPrice = data[i].asPrice;
		var modUser = data[i].modUser;
		var modDate = formatDate(new Date(data[i].modDate));
		var reApply = data[i].reApply;

		// 각 데이터 항목을 별도의 <li> 요소로 만들어 추가
		listItem.innerHTML = `
			<span class="bullet">•</span> 접수결과번호: ${asResultSeq}<br>
			<span class="bullet">•</span> 처리일: ${resultDate}<br>
			<span class="bullet">•</span> 처리내용: ${resultDetail}<br>
			<span class="bullet">•</span> 점주평가: ${storeManagerFeedBack}<br>
			<span class="bullet">•</span> 청구비용: ${asPrice}<br>
			<span class="bullet">•</span> 파일ID: ${fileSeq}<br>
			<span class="bullet">•</span> 재접수여부: ${reApply}<br>
			<span class="bullet">•</span> 수정일: ${modDate}<br>
			<span class="bullet">•</span> 수정자: ${modUser}<br>
			<span>===================</span>
			`;

		listContainer.appendChild(listItem);
	}
}




//모달 열기
function openModal() {
	var modal = document.getElementById("schedule-index-modal");
	modal.style.display = "block";
}

//모달 닫기
function closeModal() {
	var modal = document.getElementById("schedule-index-modal");
	modal.style.display = "none";
}

/*날짜 형식 포맷*/
function formatDate(date) {
	const year = date.getFullYear();
	const month = String(date.getMonth() + 1).padStart(2, '0'); // 월을 두 자리로 맞추고 0으로 채웁니다.
	const day = String(date.getDate()).padStart(2, '0'); // 일을 두 자리로 맞추고 0으로 채웁니다.
	return `${year}-${month}-${day}`;
}

/*폼 초기화(날짜는 해당년월로 초기화)*/
function resetForm() {
	console.log("resetForm함수실행!");
	const form = document.querySelector('form[name="srhForm"]');
	if (form) {
		form.reset(); 
		var currentDate = getCurrentYearAndMonth();
		var yearSelect = document.getElementById('yearSelect');
		var monthSelect = document.getElementById('monthSelect');
	}
}



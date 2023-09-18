//AJAX 요청을 보내는 함수
function performSearch() {
	console.log("performSearch()함수 실행!");
	// 폼 데이터 가져오기
	var formData = $("form[name='srhForm']").serialize();

	// AJAX POST 요청 보내기
	$.ajax({
		type: "POST",
		url: "/schedule", // 데이터를 처리할 URL을 여기에 지정
		data: formData,
		success: function (response) {
			// 성공적인 응답 처리
			console.log(response);

			// 데이터 개수 계산
			var dataCount = response.length;

			// 데이터 개수를 #dataCount 요소에 업데이트
			$("#dataCount").text(dataCount);

			// HTML 테이블의 tbody를 선택
			var tbody = $("#cal-table tbody");
			tbody.empty();

			// 각 데이터를 반복하면서 행 추가
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
						$("<td>", { rowspan: 2 }).text("휴무일: " + holidayCount + ", 배정일: " + assignCount + ", 근무일: " + resultCount).appendTo(row);
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

					// 시작일부터 종료일까지 반복
					for (var currentDate = new Date(startDay); currentDate <= endDay; currentDate.setDate(currentDate.getDate() + 1)) {
						var day = currentDate.getDate(); // 현재 날짜의 일(day) 추출
						// 선택한 행과 열의 <td> 요소를 찾아서 값 대입
						var rowClassName = "row-" + (i + 1);
						var cellClassName = "day-" + day; // 클래스 이름 생성

						var $divElement = $("<div class='schedule-index'></div>")
						.attr("data-user-id", userId)
						.attr("data-schedule-type", "holiday")
						.attr("data-schedule-date", startDay);

						$divElement.css({
							"background-color": "pink", // 배경색 설정
							"width": "1.2em", // 가로 크기 설정
							"height": "1.2em", // 세로 크기 설정
							"margin": "0 auto"
						});

						// 마우스 오버 이벤트 핸들러 추가
						$divElement.on("mouseover", function() {
							var $this = $(this);
							var userId = $this.attr("data-user-id");
							console.log("userId: " + userId);
							var scheduleType = $this.attr("data-schedule-type");
							console.log("scheduleType: " + scheduleType);
							var scheduleDate = $this.attr("data-schedule-date");
							console.log("scheduleDate: " + scheduleDate);

							// 데이터를 컨트롤러로 보내는 함수 호출
							scheduleIndexData(userId, scheduleType, scheduleDate);
						});

						// 해당 클래스 이름을 가진 <td> 요소에 <div> 추가
						$("." + rowClassName + " ." + cellClassName).html($divElement);
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
						"background-color": "#f7d474", // 배경색 설정
						"width": "1.2em", // 가로 크기 설정
						"height": "1.2em", // 세로 크기 설정
						"margin": "0 auto" // 가운데 정렬 설정
					});

					// 해당 클래스 이름을 가진 <td> 요소에 <div> 추가
					$("." + rowClassName + " ." + cellClassName)
					.empty() // 기존 내용을 비우기
					.html($divElement);
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
						"background-color": "#5b8554", // 배경색 설정
						"width": "1.2em", // 가로 크기 설정
						"height": "1.2em", // 세로 크기 설정
						"margin": "0 auto" // 가운데 정렬 설정
					});

					// 해당 클래스 이름을 가진 <td> 요소에 <div> 추가
					$("." + rowClassName + " ." + cellClassName)
					.empty() // 기존 내용을 비우기
					.html($divElement);
				}


			}

		},
		error: function (error) {
			// 에러 처리
			console.error("AJAX 요청 실패:", error);
		}
	});
}


function scheduleIndexData(userId, scheduleType, scheduleDate) {

	var date = new Date(scheduleDate);
	// 날짜를 yyyy-mm-dd 형식으로 포맷팅
    var year = date.getFullYear();
    var month = String(date.getMonth() + 1).padStart(2, '0'); // 월은 0부터 시작하므로 1을 더하고 두 자리로 포맷팅
    var day = String(date.getDate()).padStart(2, '0'); // 일도 두 자리로 포맷팅
    var formattedDate = year + '-' + month + '-' + day;

	$.ajax({
		type: "POST",
		url: "/schedule-Info", // 컨트롤러의 URL을 여기에 입력하세요.
		data: {
			userId: userId,
			scheduleType: scheduleType,
			scheduleDate: formattedDate
		},
		success: function(response) {
			console.log("Response from server:", response);
			
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


function displayHolidayData(data) {
	
	console.log("data: " + data);
    // 모달 요소를 가져옴 (모달의 ID를 사용하여 조정)
    var modal = document.getElementById("schedule-index-modal");

    var modalContent = document.getElementById("modalContent");
    // 필드명을 사용하여 필드값을 구함
    var holidaySeq = data.holidaySeq;

    // 필드값을 모달 내용에 추가
    modalContent.innerHTML = "휴일 번호: " + holidaySeq;
    openModal(); // 모달 열기
}


function displayAssignData(data) {
    // 모달 요소를 가져옴 (모달의 ID를 사용하여 조정)
    var modal = document.getElementById("schedule-index-modal");

    var modalContent = document.getElementById("modalContent");
    modalContent.innerHTML = "받은 데이터: " + data;
    openModal(); // 모달 열기
}

function displayResultData(data) {
    // 모달 요소를 가져옴 (모달의 ID를 사용하여 조정)
    var modal = document.getElementById("schedule-index-modal");

    var modalContent = document.getElementById("modalContent");
    modalContent.innerHTML = "받은 데이터: " + data;
    openModal(); // 모달 열기
}


// 모달 열기
function openModal() {
    var modal = document.getElementById("schedule-index-modal");
    modal.style.display = "block";
}

// 모달 닫기
function closeModal() {
    var modal = document.getElementById("schedule-index-modal");
    modal.style.display = "none";
}


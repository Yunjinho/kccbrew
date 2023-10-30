document.addEventListener("DOMContentLoaded", function() {

	/*날짜 구간 선택 시 날짜 기본값 설정*/
	setInputElementValue('selectedStartDate', getFirstDayOfYear());
	setInputElementValue('selectedEndDate', getLastDayOfCurrentYear());

	/*날짜 구간 선택 시 날짜 기본값 설정*/
	setInputElementValue('startDate', getFirstDayOfYear());
	setInputElementValue('endDate', getLastDayOfCurrentYear());

});
function downExcel(flag){
	
	var location=$("select[name=location] option:selected").val();
	var locationCd=$("select[name=locationCd] option:selected").val();
	var startDate=$("input[name=startDate]").val();
	var endDate=$("input[name=endDate]").val();
	var userType=$("select[name=userType] option:selected").val();
	var userId=$("input[name=userId]").val();
	var userName=$("input[name=userName]").val();
	var storeId=$("input[name=storeId]").val();
	var storeName=$("input[name=storeName]").val();
	var selectedEndDate=$("input[name=selectedEndDate]").val();
	var selectedStartDate=$("input[name=selectedStartDate]").val();
	var page=$("input[name=page]").val();
	$.ajax({
		type : "POST",           // 타입 (get, post, put 등등)
	    url : "/download-holiday-list",           // 요청할 서버url
	    dataType : "text",       // 데이터 타입 (html, xml, json, text 등등)
	    data : {
	    	'flag': flag,
	    	'location':location,
	    	'locationCd': locationCd,
	    	'startDate' : startDate,
	    	'endDate': endDate,
	    	'userType': userType,
	    	'userId': userId,
	    	'userName': userName,
	    	'storeId': storeId,
	    	'storeName': storeName,
	    	'selectedEndDate': selectedEndDate,
	    	'selectedStartDate': selectedStartDate,
	    	'page': page
		},
	    success : function(data) { // 결과 성공 콜백함수
	    	alert("다운로드가 완료되었습니다.")
	    }
	})
}
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

			/*휴가리스트 테이블에 표시*/
			var schedules = data.schedules;
			displaySchedules(schedules);

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

/*페이징처리*/
function updatePageButtons(currentPage, totalPage, sharePage) {
	$("input[name=page]").val(currentPage);
	/*페이지 표시*/
	document.getElementById("currentPage").textContent = currentPage;
	document.getElementById("totalPage").textContent = totalPage;

	var numberList = document.getElementById("number-list");
	var pageButtonsHTML = '';

	// 페이지 버튼들을 생성
	for (var page = sharePage * 10 + 1; page <= (sharePage + 1) * 10 && page <= totalPage; page++) {
		pageButtonsHTML += '<a href="" onclick="goPage(' + page + '); return false;" class="pageNow pagination page-btn ' + (currentPage == page ? 'selected' : '') + '">' + page + '</a>';
	}

	// 페이지 버튼들을 업데이트
	numberList.innerHTML = pageButtonsHTML;

	document.querySelector('.pageFirst').href = "javascript:void(0);";
	document.querySelector('.pageFirst img').alt = "처음";
	document.querySelector('.pageFirst').onclick = function() {
		goPage(1);
		return false;
	};

	document.querySelector('.pagePrev').href = "javascript:void(0);";
	document.querySelector('.pagePrev img').alt = "이전";
	document.querySelector('.pagePrev').onclick = function() {
		if (currentPage === 1) {
		} else {
			goPage(currentPage - 1);
		}
	};

	document.querySelector('.pageNext').href = "javascript:void(0);";
	document.querySelector('.pageNext img').alt = "다음";
	document.querySelector('.pageNext').onclick = function() {
		if (currentPage === totalPage) {
		} else {
			goPage(currentPage + 1);
		}
	};

	document.querySelector('.pageLast').href = "javascript:void(0);";
	document.querySelector('.pageLast img').alt = "마지막";
	document.querySelector('.pageLast').onclick = function() {
		goPage(totalPage);
		return false;
	};

	var pageButtons = document.querySelectorAll('.page-btn a');
	pageButtons.forEach(function(pageButton, index) {
		var page = currentPage + index;
		pageButton.href = "javascript:void(0);";
		pageButton.textContent = page;
		pageButton.className = "pageNow pagination page-btn" + (currentPage == page ? ' selected' : '');
		pageButton.onclick = function() {
			goPage(page);
			return false;
		};
	});
}

/*ajax로 받은 휴가리스트 테이블에 추가*/
function displaySchedules(schedules) {
	console.log("displaySchedules함수실행!");
	var tableBody = document.getElementById("holiday-list-tbody");

	tableBody.innerHTML = "";

	schedules.forEach(function(schedule) {
		var row = tableBody.insertRow(-1); 

		if (userTypeCd === "01") {
			/*사용자가 관리자인 경우*/
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

		/*사용자가 점주인 경우*/
		else if (userTypeCd === "02") {
			var cell1 = row.insertCell(0);
			/*var cell2 = row.insertCell(1);*/
			var cell2 = row.insertCell(1);
			var cell3 = row.insertCell(2);
			var cell4 = row.insertCell(3);
			var cell5 = row.insertCell(4);
			var cell6 = row.insertCell(5);
			var cell7 = row.insertCell(6);

			cell1.innerHTML = schedule.rowNumber;
			/*cell2.innerHTML = schedule.scheduleId;*/
			cell2.innerHTML = schedule.storeName;
			cell2.setAttribute('data-store-id', schedule.storeId);
			cell3.innerHTML = formatDate(new Date(schedule.appDate));
			cell4.innerHTML = formatDate(new Date(schedule.startDate));
			cell5.innerHTML = formatDate(new Date(schedule.endDate));

			if(schedule.actualUse === "N") {
				cell6.innerHTML = "취소완료";
			}
			else if (new Date(schedule.startDate) <= new Date()) {
				cell6.innerHTML = "이미 사용된 휴가";
			} else  {
				var cancelButton = document.createElement("button");
				cancelButton.textContent = "취소";
				cancelButton.className = "form-btn";
				cancelButton.onclick = function () {
					openCancelModal(schedule.scheduleId, schedule.startDate, schedule.endDate);
				};
				cell6.appendChild(cancelButton);
			} 
			cell7.innerHTML = schedule.actualUse;
		} 

		/*사용자가 수리기사인 경우*/
		else {
			var cell1 = row.insertCell(0);
			/*var cell2 = row.insertCell(1);*/
			var cell2 = row.insertCell(1);
			var cell3 = row.insertCell(2);
			var cell4 = row.insertCell(3);
			var cell5 = row.insertCell(4);
			var cell6 = row.insertCell(5);

			cell1.innerHTML = schedule.rowNumber;
			/*cell2.innerHTML = schedule.scheduleId;*/
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
}


/*구간으로 입력 시 바로 날짜 이동*/
function goDate(){
	var selectedStartDate = document.getElementById('selectedStartDate').value;
	var selectedEndDate = document.getElementById('selectedEndDate').value;
	var fm = document.srhForm;
	fm.startDate.value = selectedStartDate;
	console.log("selectedStartDate: " + selectedStartDate);
	fm.endDate.value = selectedEndDate;
	console.log("selectedEndDate: " + selectedEndDate);
	performSearch();
	return true;
}

/*팝업창*/ 
function postPopup() {
	var url = "/holiday/add";
	var name = "팝업 테스트";
	var width = 800; // 원하는 너비로 설정
	var height = 500; // 원하는 높이로 설정
	var option = "width=" + width + ",height=" + height + ",top=200,left=450,directories=no,location=no";
	window.open(url, name, option);
}

/*해당년도의 마지막 날 조회*/
function getLastDayOfCurrentYear() {
	var today = new Date();
	var currentYear = today.getFullYear(); 

	var nextYear = currentYear + 1;
	var firstDayOfNextYear = new Date(nextYear, 0, 1);

	var lastDayOfCurrentYear = new Date(firstDayOfNextYear - 1);

	return formatDate(lastDayOfCurrentYear);
}

/*날짜 형식 포맷*/
function formatDate(date) {
	var year = date.getFullYear();
	var month = (date.getMonth() + 1).toString().padStart(2, '0'); // 월을 2자리 숫자로 변환
	var day = date.getDate().toString().padStart(2, '0'); // 일을 2자리 숫자로 변환
	return `${year}-${month}-${day}`;
}

/*취소확인 모달창 오픈*/
function openCancelModal(scheduleId, startDate, endDate) {

	var modal = document.getElementById("cancelModal");
	modal.style.display = "block";

	var cancelYesButton = document.getElementById("cancelYes");
	cancelYesButton.onclick = function () {
		cancelSchedule(scheduleId, startDate, endDate);

		modal.style.display = "none";
	};

	var cancelNoButton = document.getElementById("cancelNo");
	cancelNoButton.onclick = function () {
		modal.style.display = "none";
	};
}

/*휴가취소*/
function cancelSchedule(scheduleId, startDate, endDate) {
	console.log("cancelSchedule함수실행!");

	$.ajax({
		url: "/holiday/delete",
		type: "get",
		data : {
			'scheduleId' : scheduleId
		},
		success: function(message) {
			console.log('Ajax 요청 성공:', message);

			var resultModal = document
			.getElementById("resultModal");
			var modalMessage = document
			.getElementById("modalMessage");
			modalMessage.innerText = message;
			resultModal.style.display = "block";

			var confirmButton = document
			.getElementById("modal-result-confirmButton");
			confirmButton.onclick = function() {
				reoload();
			};
			sendHolidayCancel(startDate, endDate);

		},
		error: function(error) {
			console.log('Ajax 요청 실패:', error);
		}
	});
}


/* 팝업창 리로드 함수 */
function reoload() {
	window.location.reload();
}


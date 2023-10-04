/*전역변수*/
var ping;
var sockjs;

/*기본적으로 로그인한 회원의 ID, 권한을 전송*/
document.addEventListener("DOMContentLoaded", function() {

	sockjs = new SockJS("http://localhost:8080/echoHandler");

	sockjs.onopen = function(evt) {
		console.log("socket연결");

		if (typeof sockjs != 'undefined') {
			var data = {
					userId: userId,
					userType: userType
			};
			var jsonStr = JSON.stringify(data);
			sockjs.send(jsonStr);
		} else {
			console.log("연결되지 않음.");
		}
	};

	sockjs.onclose = function(evt) {
		console.log("socket연결해제");
	};

	// 메세지 수신 시
	sockjs.onmessage = function(evt) {

		var jsonMessage = JSON.parse(evt.data);
		var category = jsonMessage.category;

		if (category === "alarm") {
			executeAlarmFunction(jsonMessage);
		}
	};

	sockjs.onerror = function(evt) {
		alert('에러:' + evt.data);
	};

});

/*휴가 신청 시 알람 발신*/
function sendHolidayAdd() {
	console.log(" sendHolidayAdd함수실행!");
	console.log("sockjs: " + sockjs);

	var selectedStartDate = document.getElementById('selectedStartDate').value;
	var selectedEndDate = document.getElementById('selectedEndDate').value;

	if (typeof sockjs != 'undefined') {
		var data = {
				title: "holiday-add",
				userId: userId,
				userType: userType,
				startDate: selectedStartDate,
				endDate: selectedEndDate
		};
		var jsonStr = JSON.stringify(data);
		sockjs.send(jsonStr);
	} else {
		console.log("연결되지 않음.");
	}
}

/*휴가 취소 시 알람 발신*/
function sendHolidayCancel() {
	console.log(" sendHolidayCancel 함수실행!");
	console.log("sockjs: " + sockjs);

	var selectedStartDate = document.getElementById('selectedStartDate').value;
	var selectedEndDate = document.getElementById('selectedEndDate').value;

	if (typeof sockjs != 'undefined') {
		var data = {
				title: "holiday-cancel",
				userId: userId,
				userType: userType,
				startDate: selectedStartDate,
				endDate: selectedEndDate
		};
		var jsonStr = JSON.stringify(data);
		sockjs.send(jsonStr);
	} else {
		console.log("연결되지 않음.");
	}
}


/*실시간알람 수신*/
function executeAlarmFunction(jsonMessage) {
	var title = jsonMessage.title;
	var content = jsonMessage.content;

	var alertElement = document.querySelector('.alert');
	alertElement.removeAttribute('hidden');

	var titleElement = document.getElementById('alert-title');
	titleElement.textContent = title;
	var contentElement = document.getElementById('alert-content');
	contentElement.textContent = content;

	// 3초후 알림 삭제
	setTimeout(function () {
		alertElement.style.opacity = '0';
		setTimeout(function () {
			alertElement.setAttribute('hidden', 'true');
			alertElement.style.opacity = '1'; 
		}, 500); 
	}, 10000); 
}
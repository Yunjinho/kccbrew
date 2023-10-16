/*전역변수*/
var ping;
var sockjs;

var storeSeq;
var storeNm;

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
		console.log("onmessage실행!");

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

function getStoreData(storeId, storeName) {
	storeSeq = storeId;
	storeNm = storeName;
}

/*휴가 신청 시 알람 발신(기사,점주->관리자)*/
function sendHolidayAdd() {
	console.log(" sendHolidayAdd함수실행!");

	var selectedStoreSeq = null;
	var selectedStoreNm = null;

	var storeSeqElement = document.getElementById("storeSeq");
	if (storeSeqElement) {
		selectedStoreSeq = storeSeqElement.value;
		var selectedIndex = storeSeqElement.selectedIndex;
		if (selectedIndex !== -1) {
			selectedStoreNm = storeSeqElement.options[selectedIndex].text;
		}
	}

	var selectedStartDate = document.getElementById('selectedStartDate').value;
	var selectedEndDate = document.getElementById('selectedEndDate').value;

	if (typeof sockjs != 'undefined') {
		var data = {
				title: "holiday-add",
				userId: userId,
				userType: userType,
				startDate: selectedStartDate,
				endDate: selectedEndDate,
				storeNm: selectedStoreNm,
				storeSeq: selectedStoreSeq
		};
		var jsonStr = JSON.stringify(data);
		sockjs.send(jsonStr);
	} else {
		console.log("연결되지 않음.");
	}
}

/*휴가 취소 시 알람 발신(기사,점주->관리자)*/
function sendHolidayCancel(startDate, endDate) {
	console.log(" sendHolidayCancel 함수실행!");

	if (typeof sockjs != 'undefined') {
		var data = {
				title: "holiday-cancel",
				userId: userId,
				userType: userType,
				startDate: startDate,
				endDate: endDate,
				storeNm: storeNm,
				storeSeq: storeSeq
		};
		var jsonStr = JSON.stringify(data);
		sockjs.send(jsonStr);
	} else {
		console.log("연결되지 않음.");
	}
}

/*AS신청 시 알람 발신(점주->관리자)*/
function sendAsReceiptAlarm() {
	console.log(" sendAsReceiptAlarm함수실행!");

	var wishingStartDate = document.getElementById('wishingStartDate').value;
	var wishingEndDate = document.getElementById('wishingEndDate').value;

	var storeElement = document.getElementById("storeNm"); 
	var storeOption = storeElement.options[storeElement.selectedIndex]; 
	var storeNm = storeOption.textContent; 
	var storeSeq = storeOption.value;

	var machineElement = document.getElementById("machineCd"); 
	var machineOption = machineElement.options[machineElement.selectedIndex];
	var machine = machineOption.textContent; 


	if (typeof sockjs != 'undefined') {
		var data = {
				title: "as-receipt",
				userId: userId,
				userType: userType,
				storeId: storeSeq,
				storeName: storeNm,
				machine: machine,
				startDate: wishingStartDate,
				endDate: wishingEndDate
		};
		var jsonStr = JSON.stringify(data);
		sockjs.send(jsonStr);
	} else {
		console.log("연결되지 않음.");
	}
}


/*AS배정 시 알람 발신(관리자->수리기사)*/
function sendAsAssignAlarm() {
	console.log(" sendAsAssignAlarm함수실행!");

	var storeNm = document.getElementById("storeNm").value;
	var visitDate = document.getElementById("visitDate").value;
	var mechanicId = document.getElementById("mechanicId").value;


	if (typeof sockjs != 'undefined') {
		var data = {
				title: "as-assign",
				adminId: userId,
				mechanicId: mechanicId,
				storeName: storeNm,
				visitDate: visitDate
		};
		var jsonStr = JSON.stringify(data);
		sockjs.send(jsonStr);
	} else {
		console.log("연결되지 않음.");
	}
}

/*수리기사가 AS배정반려 시 알람 발신(수리기사->관리자)*/
function sendAsAssignRejectAlarm() {
	console.log(" sendAsAssignRejectAlarm함수실행!");

	var asInfoSeq = document.getElementById("as-assign-reject-asInfoSeq").value;
	var asAssignSeq = document.getElementById("as-assign-reject-asAssignSeq").value;
	var storeSeq = document.getElementById("storeSeq").value;
	var storeNm = document.getElementById("storeNm").value;

	if (typeof sockjs != 'undefined') {
		var data = {
				title: "as-assign-reject",
				userId: userId,
				userType: userType,
				storeNm: storeNm,
				storeSeq: storeSeq,
				asInfoSeq: asInfoSeq,
				asAssignSeq: asAssignSeq
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

/*비실시간 알림 받기*/
function getAlarmData() {
	$.ajax({
		type: "GET",
		url: "/getAlarmData",
		data: {
			userId: userId,
			userType: getUserType(userType)
		},
		success: function(data) {
		    var notificationList = document.getElementById("notification-list");

		    notificationList.innerHTML = "";

		    if (data !== null && !data === '') {
		        for (var i = 0; i < data.length; i++) {
		            var item = data[i];
		            var listItem = document.createElement("li");
		            listItem.className =
		                "list-group-item d-flex justify-content-between align-items-start";

		            listItem.innerHTML = `
		                <div class="ms-2 me-auto">
		                <div class="fw-bold non-real-time alarm-title">${item.alarmTitle}</div>
		                <div class="non-real-time alarm-content">${item.alarmContent}</div>
		                </div>
		                `;

		            notificationList.appendChild(listItem);
		        }
		    } else {
		    	var listItem = document.createElement("li");
	            listItem.className =
	                "list-group-item d-flex justify-content-between align-items-start";
	            
	            listItem.innerHTML = `
	                <div class="ms-2 me-auto">
	                <div class="fw-bold non-real-time alarm-title"></div>
	                <div class="non-real-time alarm-content">최근3일간의 알림이 없습니다.</div>
	                </div>
	                `;
	            
	            notificationList.appendChild(listItem);
		    }
		},

		error: function() {
			alert("데이터를 가져오는 동안 오류가 발생했습니다.");
		}
	});
}

function getUserType(userType) {
	console.log("getUserType함수실행!");
	switch(userType) {
	case "[ROLE_ADMIN]":
		return "관리자";
	case "[ROLE_MANAGER]":
		return "점주";
	default:
		return "수리기사";
	}
}

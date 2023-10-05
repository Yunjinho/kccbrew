/*관리자용 달력 데이터 뿌리기*/

document.addEventListener("DOMContentLoaded", function() {
	var calendar;
	var year;
	var month;

	var currentDate = new Date();
	year = currentDate.getFullYear();
	month = currentDate.getMonth() + 1; 

	sendAjaxRequest(year, month);

});


function sendAjaxRequest(year, month) {
	$.ajax({
		url: "/schedule/calendar", 
		type: "POST", 
		data : {
			"year" : year,
			"month" : month
		},
		success: function (data) {
			var events = [];

			for (var i = 0; i < data.length; i++) {
				var map = data[i];
			}

			/*AS신청일*/
			var asRegDates = map.asRegDates;
			var asRegDateCountByDate = {}; // 날짜별 AS 신청 건수를 추적하는 객체

			for (var j = 0; j < asRegDates.length; j++) {
				var asRegDate = new Date(asRegDates[j]);
				var formatAsRegDate = formatDateToYYYYMMDD(asRegDate);

				// 해당 날짜의 건수를 1 증가시킵니다.
				if (asRegDateCountByDate[formatAsRegDate]) {
					asRegDateCountByDate[formatAsRegDate]++;
				} else {
					asRegDateCountByDate[formatAsRegDate] = 1;
				}
			}

			// asRegDateCountByDate 객체를 기반으로 캘린더 이벤트를 생성합니다.
			for (var date in asRegDateCountByDate) {
				events.push({
					title: 'AS신청: ' + asRegDateCountByDate[date] + '건',
					date: date,
					backgroundColor: 'rgb(247, 212, 116)',
					borderColor: 'transparent',
				});
			}



			/*AS결과일*/
			var resultDates = map.resultDates;
			var resultDateCountByDate = {}; // 날짜별 AS 신청 건수를 추적하는 객체

			for (var j = 0; j < resultDates.length; j++) {
				var resultDate = new Date(resultDates[j]);
				var formatResultDate = formatDateToYYYYMMDD(resultDate);

				// 해당 날짜의 건수를 1 증가시킵니다.
				if (resultDateCountByDate[formatResultDate]) {
					resultDateCountByDate[formatResultDate]++;
				} else {
					resultDateCountByDate[formatResultDate] = 1;
				}
			}

			// asRegDateCountByDate 객체를 기반으로 캘린더 이벤트를 생성합니다.
			for (var date in resultDateCountByDate) {
				events.push({
					title: 'AS처리: ' + resultDateCountByDate[date] + '건',
					date: date,
					backgroundColor: '#e8a9ac',
					borderColor: 'transparent',
				});
			}



			calendar.setOption(
					'events', events);
		},
		error: function (xhr, status, error) {
			console.error("AJAX 요청 실패: " + error);
		}
	});
}

/*날짜형식을 'yyyy-MM-dd'으로 변환*/
function formatDateToYYYYMMDD(date) {
	var year = date.getFullYear();
	var month = (date.getMonth() + 1).toString().padStart(2, '0'); // 월은 0부터 시작하므로 +1 필요
	var day = date.getDate().toString().padStart(2, '0');
	return year + '-' + month + '-' + day;
}

/*'0000년 0월' 문자열을 날짜 형식으로 변환*/
function parseDateStr(dateStr) {
	dateStr = dateStr.replace('년', '').replace('월', '');
	var dateParts = dateStr.split(' ');
	var year = dateParts[0];
	var month = dateParts[1];

	return {
		year: year,
		month: month
	};
}
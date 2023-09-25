document.addEventListener("DOMContentLoaded", function() {
	var calendar;
	var year;
	var month ;

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

				/*휴가일*/
				console.log("**********holidayDates**********");
				var holidayDates = map.holidayDates;
				console.log("holidayDates.length: " + holidayDates.length);
				for (var j = 0; j < holidayDates.length; j++) {
					var HLDY_STAR = holidayDates[j].HLDY_STAR;
					var HLDY_END = holidayDates[j].HLDY_END;

					var startDate = new Date(HLDY_STAR);
					var endDate = new Date(HLDY_END);
					endDate.setDate(endDate.getDate() + 1);

					var formatStartDate = formatDateToYYYYMMDD(startDate);
					var formatEndDate = formatDateToYYYYMMDD(endDate);

					console.log("formatStartDate: " + formatStartDate);
					console.log("formatEndDate: " + formatEndDate);


					events.push({
						title: '휴가' + (j+1),
						start: formatStartDate, 
						end: formatEndDate,    
						backgroundColor: '#b8cff5', 
						borderColor: 'transparent', 
					});
				}
			}

			/*배정일*/
			console.log("**********assignDates**********");
			var assignDates = map.assignDates;
			console.log("assignDates: " + assignDates);
			for (var j = 0; j < assignDates.length; j++) {
				
				var assignDate = new Date(assignDates[j]);
				var formatAssignDate = formatDateToYYYYMMDD(assignDate);

				console.log("formatAssignDate: " + formatAssignDate);
				
				events.push({
					title: '배정' + (j+1),
					date: formatAssignDate, 
					backgroundColor: 'rgb(247, 212, 116)',
					borderColor: 'transparent', 
				});

			}

			
			/*결과일*/
			console.log("**********resultDates**********");
			var resultDates = map.resultDates;
			console.log("resultDates: " + resultDates);
			for (var j = 0; j < resultDates.length; j++) {
				
				var resultDate = new Date(resultDates[j]);
				var formatResultDate = formatDateToYYYYMMDD(resultDate);

				console.log("formatResultDate: " + formatResultDate);
				
				events.push({
					title: '결과' + (j+1),
					date: formatResultDate, 
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

function formatDateToYYYYMMDD(date) {
	var year = date.getFullYear();
	var month = (date.getMonth() + 1).toString().padStart(2, '0'); // 월은 0부터 시작하므로 +1 필요
	var day = date.getDate().toString().padStart(2, '0');
	return year + '-' + month + '-' + day;
}
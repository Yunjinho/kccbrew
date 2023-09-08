<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>JSP 캘린더 예제</title>
<link
	href="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.0/main.min.css"
	rel="stylesheet">

<!-- jQuery 및 jQuery UI를 로드-->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
	<div id="calendar"></div>
	<script
		src="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.0/main.min.js"></script>
	<script>
		document.addEventListener('DOMContentLoaded', function() {
			var calendarEl = document.getElementById('calendar');

			var calendar = new FullCalendar.Calendar(calendarEl, {
				initialView : 'dayGridMonth',
				events : [
				// JSP를 사용하여 동적으로 생성된 이벤트 데이터를 가져옵니다.
				{
					title : '예제 이벤트',
					start : '2023-09-07',
				},
				// 추가 이벤트 데이터
				],

				headerToolbar : {
					left : 'prev,next',
					center : 'title',
					right : 'today',
				/* right : 'dayGridMonth,timeGridWeek,timeGridDay,listMonth', */
				},

				// dateClick 이벤트 핸들러 추가
				dateClick : function(info) {
					// 날짜를 클릭하면 datepicker를 열어서 날짜를 선택하게 합니다.
					var selectedDate = prompt('날짜를 선택하세요 (YYYY-MM-DD)',
							info.dateStr);
					if (selectedDate) {
						calendar.gotoDate(selectedDate);
					}
				},
			});

			/* 	$('#fc-dom-1').click(function() {
					$('#datepicker').datepicker({
						onSelect : function(dateText, inst) {
							calendar.gotoDate(dateText);
						},
					}).datepicker('show');
				}); */

			calendar.render();
		});
	</script>
</body>
</html>
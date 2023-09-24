document.addEventListener("DOMContentLoaded", function() {

});

/*두 날짜 차이 계산*/
/*두 날짜 차이 계산*/
function calculateDays(startDate, endDate) {
	console.log("calculateDays함수실행!");
	document.getElementById('usedDays').textContent = ''; 

	if (!isNaN(startDate.getTime()) && !isNaN(endDate.getTime())) {
		if (startDate.getTime() <= endDate.getTime()) {
			document.getElementById('input-information').style.display = 'none';
			const timeDifference = endDate - startDate;
			const daysDifference = timeDifference / (1000 * 60 * 60 * 24) + 1;
			document.getElementById('usedDays').textContent = `${daysDifference}일`;

			calculateNumbers(daysDifference, usedHolidays);
		} else {
			document.getElementById('input-information').removeAttribute('hidden');
			document.getElementById('input-information').style.display = 'table-cell';
			document.getElementById('input-information').textContent = '시작일은 종료일보다 같거나 이후로 설정해야 합니다.';
		}
	} else {
		document.getElementById('input-information').removeAttribute('hidden');
		document.getElementById('input-information').style.display = 'table-cell';
		document.getElementById('input-information').textContent = '시작일과 종료일 둘 다 선택해주세요!';
	}
}


/*유효한 휴가일수 계산*/
function calculateNumbers(number1, number2) {
	console.log("calculateNumbers함수실행!");
	  const difference = number2 - number1;
	  
	  console.log("difference: " + difference);
	  
	  const remainingDaysElement = document.getElementById('remainingDays');
	  const remainingDaysInfoElement = document.getElementById('remainingDays-info');
	  const holidayDaysInfoElement = document.getElementById('holiday-days-info');
	  const holidayDaysNoticeElement = document.getElementById('holiday-days-notice');
	  
	  remainingDaysElement.textContent = difference;
	  remainingDaysElement.value = difference;

	  if (difference < 0) {
	    holidayDaysInfoElement.hidden = true;
	    holidayDaysNoticeElement.hidden = false;
	    holidayDaysNoticeElement.textContent = "휴가일수는 15일을 초과할 수 없습니다."
	  } else {
	    remainingDaysInfoElement.textContent = difference;
	    holidayDaysInfoElement.hidden = false;
	    holidayDaysNoticeElement.hidden = true;
	  }
	}

//input 요소에 값을 설정하는 함수
function setInputElementValue(inputName, value) {
	const inputElement = document.querySelector('input[name="' + inputName + '"]');
	if (inputElement) {
		inputElement.value = value;
	}
}
document.addEventListener("DOMContentLoaded", function() {

});

/*두 날짜 차이 계산*/
function calculateDays(startDate, endDate) {
	if (!isNaN(startDate.getTime()) && !isNaN(endDate.getTime())) {
		const timeDifference = endDate - startDate;
		const daysDifference = timeDifference / (1000 * 60 * 60 * 24) + 1;
		document.getElementById('usedDays').textContent = `${daysDifference}일`;
		
		calculateNumbers(daysDifference, usedHolidays);
	} else {
		document.getElementById('usedDays').textContent = '올바른 날짜를 입력하세요.';
	}
}


/*두 숫자 차이 계산*/
function calculateNumbers(number1, number2) {

	const difference = number2 - number1;
	document.getElementById('remainingDays').textContent = `${difference}`;

}
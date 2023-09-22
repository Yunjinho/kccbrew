document.addEventListener("DOMContentLoaded", function() {

});

/*두 날짜 차이 계산*/
/*두 날짜 차이 계산*/
function calculateDays(startDate, endDate) {
    document.getElementById('usedDays').textContent = ''; 
    
    if (!isNaN(startDate.getTime()) && !isNaN(endDate.getTime())) {
    	console.log("두 날짜 모두 선택함!");
        if (startDate.getTime() <= endDate.getTime()) {
            document.getElementById('input-information').style.display = 'none';
            const timeDifference = endDate - startDate;
            const daysDifference = timeDifference / (1000 * 60 * 60 * 24) + 1;
            document.getElementById('usedDays').textContent = `${daysDifference}일`;
            
            calculateNumbers(daysDifference, usedHolidays);
        } else {
        	console.log("시작일이 종료일보다 나중임!");
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


/*두 숫자 차이 계산*/
function calculateNumbers(number1, number2) {

	const difference = number2 - number1;
	document.getElementById('remainingDays').textContent = `${difference}`;

}
//올해의 첫날을 가져오는 함수
function getFirstDayOfYear() {
	const today = new Date();
	const year = today.getFullYear();
	return year + '-01-01';
}

//올해의 마지막날을 가져오는 함수
function getLastDayOfYear() {
	const today = new Date();
	const year = today.getFullYear();
	const lastDay = new Date(year, 11, 31); // 11은 12월을 나타냄
	return lastDay.toISOString().substr(0, 10); // "YYYY-MM-DD" 형식으로 반환
}

/*날짜 형식(yyyy-mm-dd) 포맷*/
function formatDate(date) {
	const year = date.getFullYear();
	const month = String(date.getMonth() + 1).padStart(2, '0'); // 월을 두 자리로 맞추고 0으로 채웁니다.
	const day = String(date.getDate()).padStart(2, '0'); // 일을 두 자리로 맞추고 0으로 채웁니다.
	return `${year}-${month}-${day}`;
}
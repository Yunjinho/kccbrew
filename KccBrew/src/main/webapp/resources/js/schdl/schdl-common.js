$(document).ready(function() {
	getEquipmentName(equipmentCodeValue);
	getLocationName(locationCodeValue);
});

/*장비코드로 장비명 조회*/
function getEquipmentName(equipmentCodeValue) {
	$.ajax({
		url: '/getEquipmentName', // Ajax 요청을 처리할 URL
		type: 'GET',
		dataType: 'text', // 반환되는 데이터 형식을 text로 설정
		data: { "equipmentCode": equipmentCodeValue },
		success: function(data) {
			$('#equipmentName').text(data);
		},
		error: function(error) {
			console.log('Ajax 요청 실패:', error);
		}
	});
}

/*지역코드로 지역명 조회*/
function getLocationName(locationCodeValue) {
	$.ajax({
		url: '/getLocationName', // Ajax 요청을 처리할 URL
		type: 'GET',
		dataType: 'text', // 반환되는 데이터 형식을 text로 설정
		data: { "locationCode": locationCodeValue },
		success: function(data) {
			$('#locationName').text(data);
		},
		error: function(error) {
			console.log('Ajax 요청 실패:', error);
		}
	});
}

/*input 요소 비활성화 또는 활성화*/
function inputDisableSetting(selectElementId, targetedElementId) {
	console.log("inputDisableSetting함수실행!");
	const selectedValue = document.getElementById(selectElementId).value; // 선택한 값
	const inputElement = document.getElementById(targetedElementId); // 검색어 입력 필드

	if (selectedValue === '') {
		// 선택한 값이 빈 문자열인 경우, input 요소를 비활성화
		inputElement.disabled = true;
	} else {
		// 선택한 값이 빈 문자열이 아닌 경우, input 요소를 활성화
		inputElement.disabled = false;
	}
}
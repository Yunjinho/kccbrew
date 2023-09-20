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
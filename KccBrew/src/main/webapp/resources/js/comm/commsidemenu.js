$(document).ready(function() {
	var locationCd = document.getElementById("locationCd").textContent;
	getLocationName2(locationCd);
	
	var mechaEqpmnCdElement = document.getElementById("mecha-eqpmnCd");
	if (mechaEqpmnCdElement) {
	  var mechaEqpmnCd = mechaEqpmnCdElement.textContent;
	  getEquipmentName2(mechaEqpmnCd);
	} 
});

/*장비코드로 장비명 조회*/
function getEquipmentName2(equipmentCodeValue) {
	$.ajax({
		url: '/getEquipmentName', // Ajax 요청을 처리할 URL
		type: 'GET',
		dataType: 'text', // 반환되는 데이터 형식을 text로 설정
		data: { "equipmentCode": equipmentCodeValue },
		success: function(data) {
			$('#mecha-eqpmnCd').removeAttr('hidden');
			$('#mecha-eqpmnCd').text(data);
		},
		error: function(error) {
			console.log('Ajax 요청 실패:', error);
		}
	});
}

/*지역코드로 지역명 조회*/
function getLocationName2(locationCodeValue) {
	$.ajax({
		url: '/getLocationName', // Ajax 요청을 처리할 URL
		type: 'GET',
		dataType: 'text', // 반환되는 데이터 형식을 text로 설정
		data: { "locationCode": locationCodeValue },
		success: function(data) {
			$('#locationCd').removeAttr('hidden');
			$('#locationCd').text(data);
		},
		error: function(error) {
			console.log('Ajax 요청 실패:', error);
		}
	});
}


/**********관리자 TODO**********/
/*function getAsReception waiting list(locationCodeValue) {
	$.ajax({
		url: '/getLocationName', // Ajax 요청을 처리할 URL
		type: 'GET',
		dataType: 'text', // 반환되는 데이터 형식을 text로 설정
		data: { "locationCode": locationCodeValue },
		success: function(data) {
			$('#locationCd').removeAttr('hidden');
			$('#locationCd').text(data);
		},
		error: function(error) {
			console.log('Ajax 요청 실패:', error);
		}
	});
}*/

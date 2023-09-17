function updateSecondSelect() {
    var firstSelect = document.querySelector('select[name="superGrpCdDtlId"]');
    var secondSelect = document.querySelector('select[name="grpCdDtlId"]');
    var selectedValue = firstSelect.value;

    // 두 번째 select 초기화
    secondSelect.innerHTML = '<option value="">지역 소분류</option>';

    // selectedValue에 따라 두 번째 select에 옵션 추가
    for (var i = 0; i < locations.length; i++) {
        var location = locations[i];
        if (location.grpCdDtlId === selectedValue) {
            var option = document.createElement('option');
            option.value = location.grpCdDtlId;
            option.text = location.grpCdDtlNm;
            secondSelect.appendChild(option);
        }
    }
}
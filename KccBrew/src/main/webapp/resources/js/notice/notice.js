$(document).ready(function() {
	$('#notice-content').on('keyup', function() {
		var textCount = $(this).val().length;
		$('#count').html(textCount);
		$('#count').css('color', 'red');
		
        if(textCount > 1000) {
            $(this).val($(this).val().substring(0, 1000));
            $('#count').html("1000");
        }
	});
});
var fileNo = 0;
var filesArr = new Array();

/* 첨부 파일 추가 */
function addFile(obj){
	var maxFileCnt = 5;  //파일 최대 개수
	var attFileCnt = document.querySelectorAll('.filebox').length; //기존 첨부 파일 개수
	var remainFileCnt = maxFileCnt - attFileCnt;  //추가로 첨부 가능한 파일의 수
	var curFileCnt = obj.files.length; //input 요소에서 바로 지금 몇개의 파일을 선택했는지에 대한 값
	var totalFileCnt = 0;	//현재까지 첨부한 이미지 개수

	//첨부 파일 개수 확인
	if(curFileCnt > remainFileCnt){
		alert("첨부 파일은 최대 " + maxFileCnt + "개 까지 첨부 가능합니다.");
		return;
	}
	
	for(var i=0; i<Math.min(curFileCnt, remainFileCnt); i++){
		const file = obj.files[i];
		
		// 첨부파일 검증
        if (validation(file)) {
            // 파일 배열에 담기
            var reader = new FileReader();
            reader.onload = function () {
                filesArr.push(file);
                updateFileCount();  //현재 파일 개수 업데이트
            };
            reader.readAsDataURL(file)

            // 목록 추가
            let htmlData = '';
            htmlData += '<div id="file' + fileNo + '" class="filebox">';
            htmlData += '   <p class="name">' + file.name + '</p>';
            htmlData += '   <a class="delete" onclick="deleteFile(' + fileNo + ');"><i class="far fa-minus-square"></i></a>';
            htmlData += '</div>';
            $('.file-list').append(htmlData);
            fileNo++;
        } else {
            continue;
        }
	}
	//초기화
	document.querySelector("input[type=file]").value = "";
}

//파일 개수 업데이트
function updateFileCount() {
    var curFileCnt = filesArr.filter(file => !file.is_delete).length; // 현재 선택된 첨부 파일 개수
    $('#fileCount').html(curFileCnt + " 파일이 선택되었습니다.");
}

/* 첨부파일 삭제 */
function deleteFile(num) {
    document.querySelector("#file" + num).remove();
    filesArr[num].is_delete = true;	
    updateFileCount();
    
    curFileCnt--;
    updateFileCount();
}

/* 첨부파일 검증 */
function validation(obj){
    const fileTypes = ['application/pdf', 'image/gif', 'image/jpeg', 'image/png', 'image/bmp', 'image/tif', 'application/haansofthwp', 'application/x-hwp'];
    if (obj.name.length > 100) {
        alert("파일명이 100자 이상인 파일은 제외되었습니다.");
        return false;
    } else if (obj.size > (100 * 1024 * 1024)) {
        alert("최대 파일 용량인 100MB를 초과한 파일은 제외되었습니다.");
        return false;
    } else if (obj.name.lastIndexOf('.') == -1) {
        alert("확장자가 없는 파일은 제외되었습니다.");
        return false;
    } else if (!fileTypes.includes(obj.type)) {
        alert("첨부가 불가능한 파일은 제외되었습니다.");
        return false;
    } else {
        return true;
    }
}


/* 폼 전송 */
function submitForm() {
    // 폼데이터 담기
    var form = document.querySelector("form");
    var formData = new FormData(form);
    for (var i = 0; i < filesArr.length; i++) {
        // 삭제되지 않은 파일만 폼데이터에 담기
        if (!filesArr[i].is_delete) {
            formData.append("attach_file", filesArr[i]);
        }
    }

    $.ajax({
        method: 'POST',
        url: '/register',
        dataType: 'json',
        data: formData,
        async: true,
        timeout: 30000,
        cache: false,
        headers: {'cache-control': 'no-cache', 'pragma': 'no-cache'},
        success: function () {
            alert("파일업로드 성공");
        },
        error: function (xhr, desc, err) {
            alert('에러가 발생 하였습니다.');
            return;
        }
    });
}

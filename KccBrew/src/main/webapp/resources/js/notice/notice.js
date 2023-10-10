$(document).ready(function() {
	
	//입력한 텍스트 수 계산
	$('#notice-content').on('keyup', function() {
		var textCount = $(this).val().length;
		$('#count').html(textCount);
		$('#count').css('color', 'red');
		
        if(textCount > 1000) {
            $(this).val($(this).val().substring(0, 1000));
            $('#count').html("1000");
        }
	});

	//공지 수정
	$('#updateNoticeBtn').on('click', function(){
		updateForm();
	});
	
	/* 파일 미리보기 */
	$('#fileInput').on('change', function (e) {
        var fileInput = e.target;
        var noticeImg = $('#noticeImg');

        if (fileInput.files && fileInput.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                noticeImg.attr('src', e.target.result);
            };
            reader.readAsDataURL(fileInput.files[0]);
        }
    });
	
//	//공지 등록 버튼 클릭
//	$('#insertNoticeBtn').click(function(){
////		var formData = new FormData();
////		var fileInput = document.querySelector('input[name="noticeFile"]');
////		// 여러 개의 파일을 선택한 경우 모든 파일을 FormData에 추가
////	    for (var i = 0; i < fileInput.files.length; i++) {
////	        var file = fileInput.files[i];
////	        formData.append('noticeFile_', + i, file);
////	    }
////	    
////	    formData.append('noticeTitle', $('input[name="noticeTitle"]').val());
////	    formData.append('noticeContent', $('#notice-content').val());
////	    
////	    // FormData 내용을 콘솔에 출력
////	    formData.forEach(function(value, key) {
////	        console.log(key, value);
////	    });
//	    
//        $("#insertNoticeForm").submit();
//	    alert("공지 등록 완료");
//		
////	    $.ajax({
////	        url: '/insertnoticeform',
////	        type: 'POST',
////	        data: formData,
////	        processData: false,
////	        contentType: false,
////	        success: function(response){
////	            alert("공지가 등록되었습니다.")
////	        },
////	        error: function(error){
////	            alert("공지 등록을 실패했습니다.")
////	        }
////	    });
//	});
	});

var fileNo = 0;
var filesArr = new Array();

/* 첨부 파일 추가 */
function addFile(obj){
	var maxFileCnt = 3;  //파일 최대 개수
	var attFileCnt = document.querySelectorAll('.filebox').length; //기존 첨부 파일 개수
	var remainFileCnt = maxFileCnt - attFileCnt;  //추가로 첨부 가능한 파일의 수
	var curFileCnt = obj.files.length; //input 요소에서 지금 몇개의 파일을 선택했는지에 대한 값
	var totalFileCnt = 0;	//현재까지 첨부한 이미지 개수

	//첨부 파일 개수 확인
	if(curFileCnt > remainFileCnt){
		alert("첨부 파일은 최대 " + maxFileCnt + "개 까지 첨부 가능합니다.");
		return;
	}
	
	for(var i=0; i<Math.min(curFileCnt, remainFileCnt); i++){
		const file = obj.files[i];
		console.log(file);
		// 첨부파일 검증
        if (validation(file)) {
            // 파일 배열에 담기
            var reader = new FileReader();
            reader.onload = function (e) {
            	var imgElement = document.createElement('img');
            	var fileId = "file" + fileNo;
				imgElement.src = e.target.result;
				imgElement.id = fileId;
				imgElement.className = "preview-img";
                filesArr.push(file);
				$('.img-list').append(imgElement);
                updateFileCount();  

            // 목록 추가
            let htmlData = '';
            htmlData += '<div id=' + fileId + ' class="filebox">';
            htmlData += '   <p class="name">' + file.name + '</p>';
            htmlData += '   <a class="delete" onclick="deleteFile(' + fileNo + ');"><i class="far fa-minus-square"></i></a>';
            htmlData += '</div>';
            $('.file-list').append(htmlData);
            fileNo++;
            };
            reader.readAsDataURL(file)
        } else {
            continue;
        }
	}
	//초기화
}

//파일 개수 업데이트
function updateFileCount() {
    var curFileCnt = filesArr.filter(file => !file.is_delete).length; // 현재 선택된 첨부 파일 개수
    $('#fileCount').html(curFileCnt + " 파일이 선택되었습니다.");
}

/* 첨부파일 삭제 */
function deleteFile(num) {
    var filebox = document.querySelector("#file" + num);
    filebox.remove();
    filesArr[num].is_delete = true;
    
    // 미리보기 이미지 삭제
    var previewImage = document.querySelector("#file" + num );
    if (previewImage) {
        previewImage.remove();
    }
    curFileCnt = filesArr.filter(file => !file.is_delete).length;
    updateFileCount();
}

/* 첨부파일 검증 */
function validation(obj){
    const fileTypes = ['image/jpeg', 'image/jpg', 'image/png'];
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
        alert(" 첨부가 불가능한 파일은 제외되었습니다.\n 첨부 가능한 파일: jpeg, jpg, png");
        return false;
    } else {
        return true;
    }
}

//공지 등록하기
//function insertForm(){
//	
//	var formData = new FormData();
//	var noticeTitle = $('input[name="noticeTitle"]').val();
//	var noticeContent = $('#notice-content').val();
//	formData.append('noticeTitle', noticeTitle);
//	formData.append('noticeContent', noticeContent);
//
//	var fileInput = document.querySelector('input[name="noticeFile"]');
//	for(var i=0; i < fileInput.files.length; i++){
//		formData.append('noticeFile', fileInput.files[i]);
//	}
//	
//	$.ajax({
//		url: '/insertnoticeform',
//		type: 'POST',
//		data: formData,
//		processData: false,
//		contentType: false,
//		success:function(response){
//			alert("공지가 등록되었습니다.")
//		},
//		error: function(error){
//			alert("공지 등록을 실패했습니다.")
//		}
//	});
//}

//공지 등록
//function insertForm(){
//    var formData = new FormData();
//    formData.append('noticeFile', $('#file')[0].files[0]);
//  
//    alert("공지 등록 완료");
//    $("#insertNoticeForm").attr("action","/insertnoticeform");
//    $("#insertNoticeForm").submit();
//}

//공지 수정하기
function updateForm(){
	var formData = new FormData();
	var noticeTitle = $('input[name="noticeTitle"]').val();
	var noticeContent = $('#notice-content').val();
	var noticeSeq = $('input[name="noticeSeq"]').val();
	formData.append('noticeTitle', noticeTitle);
	formData.append('noticeContent', noticeContent);

	var fileInput = document.querySelector('input[name="noticeFile"]');
	for(var i=0; i < fileInput.files.length; i++){
		formData.append('noticeFile', fileInput.files[i]);
	}
	
	$.ajax({
		url: '/notice/update/' + noticeSeq,
		type: 'POST',
		data: formData,
		processData: false,
		contentType: false,
		success:function(response){
			alert("공지가 수정되었습니다.")
		},
		error: function(error){
			alert("공지 수정을 실패했습니다.")
		}
	});
}
	
/* 폼 전송 */
//function submitForm() {
//    // 폼데이터 담기
//    var form = document.querySelector("#insertNoticeForm");
//    var formData = new FormData(form);
//    for (var i = 0; i < filesArr.length; i++) {
//        // 삭제되지 않은 파일만 폼데이터에 담기
//        if (!filesArr[i].is_delete) {
//            formData.append("attach_file", filesArr[i]);
//        }
//    }
//
//    $.ajax({
//        method: 'POST',
//        url: '/insertnoticeform',
//        dataType: 'json',
//        data: formData,
//        async: true,
//        timeout: 30000,
//        cache: false,
//        headers: {'cache-control': 'no-cache', 'pragma': 'no-cache'},
//        success: function () {
//            alert("파일업로드 성공");
//        },
//        error: function (xhr, desc, err) {
//            alert('에러가 발생 하였습니다.');
//            return;
//        }
//    });
//}

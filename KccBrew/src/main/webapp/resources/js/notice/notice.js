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
});

var fileNo = 1;
var filesArr = new Array();

/* 첨부 파일 추가 */
function addFile(obj){
	var maxFileCnt = 3;  	//파일 최대 개수
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
		file.is_delete = true;
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
            
            };
            reader.readAsDataURL(file)
        } else {
            continue;
        }
	}
	$(".file-label").css("display","none");
	var test=
		'<div class="file-label"><label for="fileInput'+fileNo+'" id="fileUploadBtn">파일 선택 </label>'
		+ '<input style="display:none;"type="file" id="fileInput'+fileNo+'" name="noticeImg" onchange="addFile(this);" multiple accept=".jpg, .jpeg, .png"></div>'
	$(".file-box").append(test);
	fileNo++;
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
    
    var fileInput = document.querySelector("#fileInput" + num);
    fileInput.value("");
    
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

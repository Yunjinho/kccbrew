
var fileIndex = 1; // 파일 인덱스를 추적하기 위한 변수

    function addFile() {
        
        var container1 = document.querySelector(".preview-container"); // CSS 스타일을 적용하기 위한 클래스
        var container2 = document.querySelector(".input-container");
        
        var inputField = document.createElement("input");
        inputField.type = "file";
        inputField.id = "imgFile" + fileIndex; // 파일 인덱스를 이름에 추가
        inputField.name = "imgFile"; // 파일 인덱스를 이름에 추가
        inputField.setAttribute("onchange", "imgTypeCheck(this, " + fileIndex + ")");

        var previewImage = document.createElement("img");
        previewImage.id = "preview" + fileIndex; // 이미지에 고유한 ID 추가
        previewImage.className = "preview"; // 이미지에 m래래 추가
        previewImage.src = "#"; // 초기 미리보기 이미지 설정

        // 생성한 요소들을 컨테이너에 추가
        container1.appendChild(previewImage);
        container2.appendChild(inputField);
 
        fileIndex++; // 다음 파일을 위한 인덱스 증가

        // 개행 요소 추가 (예: <br>)
        /*var lineBreak = document.createElement("br");
        container2.appendChild(lineBreak);*/
    }
window.onload = function () {
    // 이미지 정보를 서버에서 가져오는 요청
    fetch('/as-mod/images?asInfoSeq=' + asInfoSeq + '&asAssignSeq=' + asAssignSeq)
        .then(function (response) {
            return response.json();
        })
        .then(function (imagePathsOrData) {
            // 이미지 정보를 사용하여 이미지를 미리보기에 표시
            for (var i = 0; i < imagePathsOrData.length; i++) {
                var imageSrc = imagePathsOrData[i].storageLocation + imagePathsOrData[i].fileServerNm;
                if (imageSrc) {
                    var previewImage = document.getElementById("preview" + i);
                    if (previewImage) {
                        previewImage.src = imageSrc;
                    }
                }
            }

            // 이미지 미리보기 엘리먼트와 파일 입력 필드 추가
            fileIndex = imagePathsOrData.length; // 이미지 수만큼 fileIndex 설정
            for (var i = 0; i < fileIndex; i++) {
                addFile(); // 이미지 미리보기 엘리먼트와 파일 입력 필드 추가
            }
        });
};

var fileIndex = 0; // 파일 인덱스를 추적하기 위한 변수
var currentIndex = 0; // 현재 표시 중인 이미지의 인덱스

function addFile() {
    var container1 = document.querySelector(".preview-container");
    var container2 = document.querySelector(".input-container");

    var inputField = document.createElement("input");
    inputField.type = "file";
    inputField.accept = "image/*"; // 이미지 파일만 업로드 가능하도록 설정
    inputField.id = "imgFile" + fileIndex;
    inputField.name = "imgFile";
    inputField.setAttribute("onchange", "imgTypeCheck(this, " + fileIndex + ")");

    var previewImage = document.createElement("img");
    previewImage.id = "preview" + fileIndex;
    previewImage.className = "preview";
    previewImage.src = "data:image/png;base64,"; // 빈 이미지로 초기화

    container1.appendChild(previewImage);
    container2.appendChild(inputField);

    fileIndex++;
}

function removeFile() {
    var container1 = document.querySelector(".preview-container");
    var container2 = document.querySelector(".input-container");

    if (fileIndex > 0) {
        var lastPreview = container1.querySelector(".preview:last-child");
        var lastInput = container2.querySelector("input[type='file']:last-child");

        container1.removeChild(lastPreview);
        container2.removeChild(lastInput);

        fileIndex--;
        currentIndex = Math.min(currentIndex, fileIndex - 1);

    }
}

function imgTypeCheck(fileName, index) {
    var imgFile = fileName.files[0];

    if (imgFile != null && imgFile.type.startsWith("image/")) {
        var reader = new FileReader();
        reader.onload = function (e) {
            var previewImage = document.getElementById("preview" + index);

            if (previewImage && e.target.result) {
                previewImage.src = e.target.result;
            } else {
                alert("이미지를 업로드할 수 없습니다. 올바른 이미지 파일을 선택하세요.");
                fileName.value = "";
            }
        };

        reader.readAsDataURL(imgFile);
    } else {
        alert("이미지 파일만 업로드 가능합니다.");
        fileName.value = "";
    }
}

function updateSlider() {
    var slider = document.querySelector('.preview-container');
    slider.style.transform = `translateX(-${currentIndex * 300}px)`; // 이미지 너비에 맞게 설정
}

const prevButton = document.getElementById('prev');
const nextButton = document.getElementById('next');

prevButton.addEventListener('click', () => {
    if (currentIndex > 0) {
        currentIndex--;
        updateSlider();
    }
});
function changeStartDate(){
	var start=$("input[name=wishingStartDate]").val();
	var end=$("input[name=wishingEndDate]").val();
	if(end=='') return;
	if(start>end){
		alert("시작일은 마지막일보다 이전이여야합니다.");
		$("input[name=wishingStartDate]").val("");
	}
}
function changeEndDate(){
	var start=$("input[name=wishingStartDate]").val();
	var end=$("input[name=wishingEndDate]").val();
	if(start=='') return;
	if(start>end){
		alert("마지막일은 시작일보다 이후이여야합니다.");
		$("input[name=wishingEndDate]").val("");
	}
}
nextButton.addEventListener('click', () => {
    if (currentIndex < fileIndex - 1) {
        currentIndex++;
        updateSlider();
    }
});
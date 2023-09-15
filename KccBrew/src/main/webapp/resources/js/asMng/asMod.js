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

    function removeFile() {
        var container1 = document.querySelector(".preview-container");
        var container2 = document.querySelector(".input-container");
        console.log(container2);
        if (fileIndex > 1) {
            var lastPreview = container1.querySelector(".preview:last-child");
            var lastInput = container2.querySelector("input[type='file']:last-child");
  console.log(lastInput);
            container1.removeChild(lastPreview);
            container2.removeChild(lastInput);

            fileIndex--; // 파일을 제거할 때 인덱스 감소
            console.log(fileIndex);
        }
    }
 
    function imgTypeCheck(fileName, index) {
        var imgFile = fileName.files[0];
        //var imgFile = document.getElementById("imgFile" + index);

        var fileLen = imgFile.name.length;
        var lastDot = imgFile.name.lastIndexOf('.');
        var fileType = imgFile.name.substring(lastDot, fileLen).toLowerCase();
        
        console.log(fileType);
        if (fileType != ".jpeg" && fileType != ".jpg" && fileType != ".png") {
            alert("사진 : jpeg, jpg, png 확장자를 가진 파일만 사용하실 수 있습니다.");
            fileName.value = ""; // 파일 입력 필드 초기화
        }
        
        if (imgFile != null) {
            var reader = new FileReader();
            reader.onload = function (e) {
                var previewImage = document.getElementById("preview" + index);
                console.log(previewImage);
                console.log(e.target.result);
                // 파일 읽기에 성공한 경우에만 미리보기 이미지 업데이트
                if (previewImage && e.target.result) {
                    previewImage.src = e.target.result;
                } else {
                    alert("이미지를 업로드할 수 없습니다. 올바른 이미지 파일을 선택하세요.");
                    fileName.value = ""; // 파일 입력 필드 초기화
                }
            };

            reader.readAsDataURL(imgFile);

        }
    }

window.onload = function() {
    history.replaceState({}, null, location.pathname);
}
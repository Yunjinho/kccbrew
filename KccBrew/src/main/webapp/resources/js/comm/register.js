//사용자 유형 바꿀 때 기존에 입력했던 추가 정보들 삭제
function clearAddInfo(){
	//입력된 주가 정보 삭제
	//점포 추가정보
	$("input[name=storeAddr]").val("");
	$("input[name=storeNm]").val("");
	//수리 기사 추가 정보
	$("select[name=eqpmnCd] option:eq(0)").prop("selected", true);
	$("select[name=location] option:eq(0)").prop("selected", true);
	$("select[name=locationCd] option:eq(0)").prop("selected", true);
	//기사, 관리자 이미지 정보
	$("input[name=imgFile]").val("");
	$(".photo>img").css("display","none");

	// 경고문 삭제
	$("#storeAddMsg").css("display","none");

}
function changeType(typeNum){
	if(typeNum=='01'){
		console.log("changeType: admin");
		$("#storemng-register-form").css("display","none");
		$("#engmng-register-form").css("display","none");

		$("#mechanic").css("text-decoration","none");
		$("#store_manager").css("text-decoration","none");
		$("#manger").css("text-decoration","underline");

		$("#insert-img-box").css("display","flex");
		clearAddInfo();
	}else if(typeNum=='02'){
		$("#storemng-register-form").css("display","block");
		$("#engmng-register-form").css("display","none");

		$("#mechanic").css("text-decoration","none");
		$("#store_manager").css("text-decoration","underline");
		$("#manger").css("text-decoration","none");

		$("#insert-img-box").css("display","none");

		$("input[name=userTypeCd]").val("02");
		clearAddInfo();
	}else{
		$("#storemng-register-form").css("display","none");
		$("#engmng-register-form").css("display","block");

		$("#mechanic").css("text-decoration","underline");
		$("#store_manager").css("text-decoration","none");
		$("#manger").css("text-decoration","none");

		$("#insert-img-box").css("display","flex");
		$("input[name=userTypeCd]").val("03");
		clearAddInfo();
	}

}
//점포 목록 테이블 데이터 변경
function insertTableContent(data){
	var content = "";
	for (var i = 0; i < data[0].length; i++) {
		content += '<tr><td><input type="radio" name="select-store" onclick="selectStore()">'
			+ '<input type="hidden" value="'+data[0][i].storeSeq+'"></td>\n'
			+ '<td>'+data[0][i].storeNm+'</td>\n' 
			+ '<td>'+data[0][i].storeAddr+'</td>\n</tr>'; 
	}
	$(".table>tbody").html(content);
	$(".hidden-keyword").val(data[2].keyword);
}

//점포 목록 조회에서 페이지 이동
function movePage(page){
	console.log("movePage함수 실행!");
	var keyword=$(".hidden-keyword").val();
	$.ajax({
		type : "GET",           // 타입 (get, post, put 등등)
		url : "/search-store-list",           // 요청할 서버url
		dataType : "json",       // 데이터 타입 (html, xml, json, text 등등)
		data : {
			'keyword' : keyword,
			'page':page
		},
		success : function(data) { // 결과 성공 콜백함수
			console.log(data)
			insertTableContent(data);
			insertPageContent(data);					
		}
	});
}

//점포 목록 조회 페이지에서 페이지 번호 데이터 입력
function insertPageContent(data){
	inputPagingString="";
	for(var i=data[1].startPage;i<=data[1].endPage;i++){
		var selected=data[1].currentPage == i ? 'selected' : '';
		if(i<=data[1].totalPage){
			inputPagingString+="<a href='javascript:void(0)' onclick='movePage("+i+")' class='pagination page-btn "+selected+"'>"+i+"</a>\n"
		}
	}
	$(".page-btn").html(inputPagingString)
	
	if(data[1].currentPage>1){
		$(".left-btn").removeAttr("onclick");
		$(".left-btn").attr("onclick","movePage("+(data[1].currentPage-1)+")");
	}else{
		$(".left-btn").removeAttr("onclick");
	}

	if(data[1].currentPage<data[1].totalPage){
		$(".right-btn").removeAttr("onclick");
		$(".right-btn").attr("onclick","movePage("+(data[1].currentPage+1)+")");
	}else{
		$(".right-btn").removeAttr("onclick");
	}
	
	$(".end-btn").removeAttr("onclick");
	$(".end-btn").attr("onclick","movePage("+(data[1].totalPage)+")");
	
	
	
	applyPagingCss();
}
//페이징 CSS 적용
function applyPagingCss(){
	$(".page-btn").css('font-weight','normal');
	$(".page-btn").css('display','flex');
	$(".page-btn").css('width','auto');
	$(".page-btn").css('background-color','white');
	$(".page-btn").css('color','black');
	$(".pagination .selected").css("font-weight","bold");
	$(".pagination .selected").css("border-radius","50%");
	$(".pagination .selected").css("display","inline-block");
	$(".pagination .selected").css("width","35px");
	$(".pagination .selected").css("color","white");
	$(".pagination .selected").css("background-color","navy");
}
//사용자 아이디 조건 확인
function checkUserId(){
	var userId=$("input[name=userId]");

	if(userId.val()==""){
		$("#userIdMsg").css("display","block");
		userId.parent().css("border","1px solid red");
		return false;
	}else{
		var id=userId.val();
		//아이디 정규식 체크
		var regex=/^[A-Za-z\d]{5,20}$/
			if(!(regex).test(id)){
				$("#userIdMsg").html("아이디: 5~20자의 영문 소문자, 숫자사용 가능합니다.")
				$("#userIdMsg").css("display","block");
				userId.parent().css("border","1px solid red");
				return false;
			}
		//아이디 중복 체크 함수
		$.ajax({
			type : "GET",           // 타입 (get, post, put 등등)
			url : "/check_user_id",           // 요청할 서버url
			dataType : "text",       // 데이터 타입 (html, xml, json, text 등등)
			data : {
				'userId' : id
			},
			success : function(result) { // 결과 성공 콜백함수
				if(result!=0){
					console.log("result: " + result);
					$("#userIdMsg").html("아이디: 사용할 수 없는 아이디입니다.");
					userId.parent().css("border","1px solid red");
					$("#userIdMsg").css("display","block");
					return false;
				}
			}
		});

		userId.parent().css("border","none");
		$("#userIdMsg").css("display","none");
		return true;
	}
}

//사용자 비밀번호 조건 확인
function checkUserPwd(){
	var userPwd=$("input[name=userPwd]");
	if(userPwd.val()==""){
		userPwd.parent().css("border","1px solid red");
		$("#userPwdMsg").css("display","block");
		return false;
	}else{
		//비밀번호 정규식 체크
		var regex=/^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@#$%^&+=!_])[A-Za-z\d@#$%^&+=!_]{8,16}$/
			if(!(regex).test(userPwd.val())){
				$("#userPwdMsg").html("비밀번호: 8~16자의 최소 1개 이상의 영문 대/소문자, 숫자, 특수문자(@#$%^&+=!_)를 포함해야 합니다.")
				$("#userPwdMsg").css("display","block");
				userPwd.parent().css("border","1px solid red");
				return false;
			}
		userPwd.parent().css("border","none");
		$("#userPwdMsg").css("display","none");
		return true;
	}
}

//사용자 비밀번호 확인란 조건 확인
function checkUserPwdConfirm(){
	if($("input[name=userPwdConfirm]").val()!=$("input[name=userPwd]").val()){
		$("input[name=userPwdConfirm]").parent().css("border","1px solid red");
		$("#userPwdConfirmMsg").css("display","block");
		return false;
	}else{
		$("input[name=userPwdConfirm]").parent().css("border","none");
		$("#userPwdConfirmMsg").css("display","none");
		return true;
	}
}

//사용자 전화번호 조건 확인
function checkUserTelNo(){
	var userTelNo=$("input[name=userTelNo]");
	if(userTelNo.val()==""){
		userTelNo.parent().css("border","1px solid red");
		$("#userTelNoMsg").css("display","block");
		return false;;
	}else{
		//전화번호 정규식 체크
		var regex=/^\d{10,11}$/
			if(!(regex).test(userTelNo.val())){
				$("#userTelNoMsg").html("휴대전화번호: 휴대전화번호가 정확한지 확인해주세요.")
				$("#userTelNoMsg").css("display","block");
				userTelNo.parent().css("border","1px solid red");
				return false;
			}
		userTelNo.parent().css("border","none");
		$("#userTelNoMsg").css("display","none");
		return true;
	}
}

//사용자 이름 조건 확인
function checkUserNm(){
	var userNm=$("input[name=userNm]");
	if(userNm.val()==""){
		userNm.parent().css("border","1px solid red");
		$("#userNmMsg").css("display","block");
		return false;
	}else{
		userNm.parent().css("border","none");
		$("#userNmMsg").css("display","none");
		return true;
	}
}

//사용자 이메일 조건 체크
function checkUserEmail(){
	var userEmail=$("input[name=userEmail]");
	if(userEmail.val()==""){
		userEmail.parent().css("border","1px solid red");
		$("#userEmailMsg").css("display","block");
		return false;
	}else{
		//이메일 정규식 체크
		var regex=/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/
			if(!(regex).test(userEmail.val())){
				$("#userEmailMsg").html("이메일: 이메일이 정확한지 확인해주세요.")
				$("#userEmailMsg").css("display","block");
				userEmail.parent().css("border","1px solid red");
				return false;
			}
		userEmail.parent().css("border","none");
		$("#userEmailMsg").css("display","none");
		return true;
	}
}

//사용자 주소 조건 체크
function checkUserAddr(){
	var userAddr=$("input[name=userAddr]");
	if(userAddr.val()==""){
		userAddr.parent().css("border","1px solid red");
		$("#userAddrMsg").css("display","block");
		return false;
	}else{
		userAddr.parent().css("border","none");
		$("#userAddrMsg").css("display","none");
		return true;
	}
}

//사용자 점포 주소 조건 확인
function checkStoreAddr(){
	var storeAddr=$("input[name=storeAddr]");
	var storeNm=$("input[name=storeNm]");
	if(storeAddr.val()==""){
		storeAddr.parent().css("border","1px solid red");
		storeNm.parent().css("border","1px solid red");
		$("#storeAddMsg").css("display","block");
		return false;
	}else{
		storeAddr.parent().css("border","none");
		storeNm.parent().css("border","none");
		$("#storeAddMsg").css("display","none");
		return true;
	}
}

//사용자 장비코드 조건 확인
function checkEqpmnCd(){
	if($("select[name=eqpmnCd] option:selected").val()==""){
		$("select[name=eqpmnCd]").parent().css("border","1px solid red");
		$("#mechaAddMsg").css("display","block");
		return false;
	}else{
		$("select[name=eqpmnCd]").parent().css("border","none");
		$("#mechaAddMsg").css("display","none");
		return true;
	}
}
function checklocationCd(){
	if($("select[name=location] option:selected").val()==""){
		$("select[name=location]").parent().css("border","1px solid red");
		$("#locationMsg").css("display","block");
		return false;
	}else{
		$("select[name=location]").parent().css("border","none");
		$("#locationMsg").css("display","none");
	}
	if($("select[name=locationCd] option:selected").val()==""){
		$("select[name=locationCd]").parent().css("border","1px solid red");
		$("#locationCdMsg").css("display","block");
		return false;
	}else{
		$("select[name=locationCd]").parent().css("border","none");
		$("#locationCdMsg").css("display","none");
	}
	return true;
}
//사진 등록했는지 확인
function checkUploadImg(){
	if($("input[name=imgFile]").val()==""){
		$("#insert-img-box").css("border","1px solid red");
		$("#imgFileMsg").css("display","block")
		return false;
	}else{
		$("#insert-img-box").css("border","none");
		$("#imgFileMsg").css("display","none")
		return true;
	}
}

//대분류 지역 조건 변경 시 소분류 지역값들 변경 함수
function changeLosctionDtlCd(data){
	var locOption=$("select[name=locationCd]");
	var content='<option value="">지역 상세 선택</option>';

	for(var i=0;i<data.length;i++){
		content +='<option value="'+data[i].grpCdDtlId+'" >'+data[i].grpCdDtlNm+'</option>'
	}
	locOption.html(content);
}

//대분류 지역 선택한 값에 맞춰서 소분류 지역 값 조회
function changeLocationCd(){
	var locCd = $("select[name=location] option:selected").val();
	$.ajax({
		type : "GET",           // 타입 (get, post, put 등등)
		url : "/search-location-code",           // 요청할 서버url
		dataType : "json",       // 데이터 타입 (html, xml, json, text 등등)
		data : {
			'locCd' : locCd,
		},
		success : function(data) { // 결과 성공 콜백함수
			changeLosctionDtlCd(data);
		}
	});
}

//사진 확장자 체크-> 이미지 사진만 올리게 
function imgTypeCheck(fileName){
	var imgFile=fileName.files[0];
	//파일 확장자 추출	
	var fileLen = imgFile.name.length;
	var lastDot = imgFile.name.lastIndexOf('.');
	var fileType = imgFile.name.substring(lastDot, fileLen).toLowerCase();

	//확장자 비교
	if(fileType == ".jpeg" || fileType == ".jpg" || fileType == ".png"){
		$("#imgFileMsg").css("display","none")
		var reader = new FileReader();
		reader.onload = (e) => {
			var image = new Image();
			image.src=e.target.result;
			$(".photo>img").attr("src",e.target.result);
			$(".photo>img").css("display","block");
			$(".photo>p").css("display","none");
		};
		$(".insert-img").css("border","none");
		reader.readAsDataURL(imgFile);
	}else{
		$("#imgFileMsg").html("사용자 사진 : jpeg, jpg, png 확장자를 가진 파일만 사용하실 수 있습니다.")
		$(".insert-img").css("border","1px solid red");
		$("#imgFileMsg").css("display","block")
		$(".photo>img").css("display","none");
		$(".photo>p").css("display","block");
		$("input[name=imgFile]").val("")
	}
}
function selectStore(){
	var checked=$("input[name=select-store]:checked");
	var storeId;
	var storeAddr;
	var storeNm;

	// 체크된 체크박스 값을 가져온다
	checked.each(function(i) {
		// checkbox.parent() : checkbox의 부모는 <td>이다.
		// checkbox.parent().parent() : <td>의 부모이므로 <tr>이다.
		var tr = checked.parent().parent().eq(i);
		var td = tr.children();
		//선택한 행의 점포 아이디, 점포 주소, 점포 이름 가져오기
		storeId=td.eq(0).children().eq(1).val();
		storeNm=td.eq(1).text();
		storeAddr=td.eq(2).text()
	});
	$("input[name=storeId]").val(storeId);
	$("input[name=storeAddr]").val(storeAddr);
	$("input[name=storeNm]").val(storeNm);
	$(".search-store").css("display","none");
}

window.onload = function(){
	$(".paging>li").eq(0).children().css("font-weight","bold");

	//카카오 주소 API
	$("#address_kakao").click(function(){
		new daum.Postcode({
			oncomplete: function(data) { //선택시 입력값 세팅
				document.getElementById("address_kakao").value = data.address; // 주소 넣기
				document.querySelector("input[name=userAddressDetail]").focus(); //상세입력 포커싱
			}
		}).open();
	})

	//아이디 입력창 포커스 아웃됐을 때 아이디 조건이나 중복 아이디인 경우 알림 
	$("input[name=userId]").focusout(function(){
		checkUserId();
	})
	// 비밀번호 조건에서 벗어난 겅우
	$("input[name=userPwd]").focusout(function(){
		checkUserPwd()
	})
	// 비밀번호와 비밀번호 확인에 있는 단어가 일치하는지 확인
	$("input[name=userPwdConfirm]").focusout(function(){
		checkUserPwdConfirm();
	})
	// 전화번호 조건에 벗어난 경우
	$("input[name=userTelNo]").focusout(function(){
		checkUserTelNo();
	})
	// 이름 조건에 벗어난 경우
	$("input[name=userNm]").focusout(function(){
		checkUserNm()
	})

	// 이메일 조건에 벗어난 경우
	$("input[name=userEmail]").focusout(function(){
		checkUserEmail();
	})

	//점주 회원가입시 주소 누르면 새로운 모달 생성
	$("#store-addr").click(function(){
		$(".search-store").css("display","block");
	})

	// 모달창 밖에 영역 클릭시 삭제
	window.addEventListener("click", function(e) {
		if ($(e.target).hasClass('search-store')) {
			$(".search-store").css("display","none");
		}
	});

	//점주의 점포등록위해 주소 클릭
	$("#select-store").click(function(){
		var checked=$("input[name=select-store]:checked");
		var storeId;
		var storeAddr;
		var storeNm;

		// 체크된 체크박스 값을 가져온다
		checked.each(function(i) {
			// checkbox.parent() : checkbox의 부모는 <td>이다.
			// checkbox.parent().parent() : <td>의 부모이므로 <tr>이다.
			var tr = checked.parent().parent().eq(i);
			var td = tr.children();
			//선택한 행의 점포 아이디, 점포 주소, 점포 이름 가져오기
			storeId=td.eq(0).children().eq(1).val();
			storeNm=td.eq(1).text();
			storeAddr=td.eq(2).text()
		});
		$("input[name=storeId]").val(storeId);
		$("input[name=storeAddr]").val(storeAddr);
		$("input[name=storeNm]").val(storeNm);
		$(".search-store").css("display","none");
	})

	//점포 검색한에서 엔터키 입력
	$(".search-input").keyup(function(){
		if(window.event.keyCode==13){
			var keyword=$(".search-input").val();
			$.ajax({
				type : "GET",           // 타입 (get, post, put 등등)
				url : "/search-store-list",           // 요청할 서버url
				dataType : "json",       // 데이터 타입 (html, xml, json, text 등등)
				data : {
					'keyword' : keyword,
					'page':1
				},
				success : function(data) { // 결과 성공 콜백함수
					insertTableContent(data);
					insertPageContent(data);					
				}
			});
		}
	})

	//회원가입 클릭시 유효성 검사
	$("#signup").click(function(){
		var userTypeCd=$("input[name=userTypeCd]").val();
		console.log(userTypeCd)
		if(!checkUserId())return;
		if(!checkUserPwd())return;
		if(!checkUserPwdConfirm())return;

		if(!checkUserNm())return;
		if(!checkUserTelNo())return;
		if(!checkUserEmail())return;
		if(!checkUserAddr())return;

		if(userTypeCd=="02"){
			if(!checkStoreAddr())return;
		}else{
			if(!checkUploadImg())return;
		}

		if(userTypeCd=="03"){
			if(!checkEqpmnCd())return;
			if(!checklocationCd())return;
		}
		$("#registerForm").submit();
	})
}
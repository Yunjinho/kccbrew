/*페이지 클릭 시 폼으로 현재페이지 전송*/
function goPage(arg){
	console.log("goPage()함수 실행!");
	var fm = document.srhForm;
	fm.currentPage.value = arg;
	performSearch();
	return true;
}

/*페이지 클릭에 따른 업데이트*/
function updatePageButtons(currentPage, totalPage, sharePage) {
	// 페이지 표시
	document.getElementById("currentPage").textContent = currentPage;
	document.getElementById("totalPage").textContent = totalPage;

	var numberList = document.getElementById("number-list");
	var pageButtonsHTML = '';

	// 페이지 버튼들을 생성
	for (var page = sharePage * 10 + 1; page <= (sharePage + 1) * 10 && page <= totalPage; page++) {
		pageButtonsHTML += '<a href="" onclick="goPage(' + page + '); return false;" class="pageNow pagination page-btn ' + (currentPage == page ? 'selected' : '') + '">' + page + '</a>';
	}

	// 페이지 버튼들을 업데이트
	numberList.innerHTML = pageButtonsHTML;

	document.querySelector('.pageFirst').href = "javascript:void(0);";
	document.querySelector('.pageFirst img').alt = "처음";
	document.querySelector('.pageFirst').onclick = function() {
		goPage(1);
		return false;
	};

	document.querySelector('.pagePrev').href = "javascript:void(0);";
	document.querySelector('.pagePrev img').alt = "이전";
	document.querySelector('.pagePrev').onclick = function() {
		if (currentPage === 1) {
		} else {
			goPage(currentPage - 1);
		}
	};

	document.querySelector('.pageNext').href = "javascript:void(0);";
	document.querySelector('.pageNext img').alt = "다음";
	document.querySelector('.pageNext').onclick = function() {
		if (currentPage === totalPage) {
		} else {
			goPage(currentPage + 1);
		}
	};

	document.querySelector('.pageLast').href = "javascript:void(0);";
	document.querySelector('.pageLast img').alt = "마지막";
	document.querySelector('.pageLast').onclick = function() {
		goPage(totalPage);
		return false;
	};

	var pageButtons = document.querySelectorAll('.page-btn a');
	pageButtons.forEach(function(pageButton, index) {
		var page = currentPage + index;
		pageButton.href = "javascript:void(0);";
		pageButton.textContent = page;
		pageButton.className = "pageNow pagination page-btn" + (currentPage == page ? ' selected' : '');
		pageButton.onclick = function() {
			goPage(page);
			return false;
		};
	});
}
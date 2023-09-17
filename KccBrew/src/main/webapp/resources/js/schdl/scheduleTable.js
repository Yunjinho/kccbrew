// AJAX 요청을 보내는 함수
        function performSearch() {
        	console.log("performSearch()함수 실행!");
            // 폼 데이터 가져오기
            var formData = $("form[name='srhForm']").serialize();

            // AJAX POST 요청 보내기
            $.ajax({
                type: "POST",
                url: "/schedule", // 데이터를 처리할 URL을 여기에 지정
                data: formData,
                success: function (response) {
                    // 성공적인 응답 처리
                    console.log(response);
                },
                error: function (error) {
                    // 에러 처리
                    console.error("AJAX 요청 실패:", error);
                }
            });
        }
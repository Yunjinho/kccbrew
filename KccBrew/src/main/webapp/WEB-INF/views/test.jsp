<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
	.sticky_wrap {
	  list-style: none;
	  height: 400px;
	  border: 1px solid #ccc;
	  padding: 10px;
	}
	
	.sticky_wrap > div {
	  padding: 10px;
	  margin-bottom: 3px;
	  background: #eee;
	  border-radius: 5px;
	}
	
	div.sticky {
	  background: #ffdcf9;
	  color: red;
	  font-weight: bold;
	  position: sticky;
	  top: 5px;
	  box-shadow: 1px 1px 1px rgba(0,0,0,0.3);
	  border: 1px solid red;
	}
	
	.other_block {
	  background: #eee;
	  border: 1px solid #ccc;
	  margin-top: 5px;
	  height: 700px;
	  padding: 10px;
	}
</style>
<body>
	<form action="/s3" method="post" enctype="multipart/form-data">
		<input type="file" name="img">
		<input type="submit" value="test">
	</form>
	<div style="width: 300px; height: 300px">
		<img src="https://kccbrewbucket.s3.ap-northeast-2.amazonaws.com/register/3c3d0d9f-5a0a-4f03-9326-42ad11941ca6_%EC%BB%A4%ED%94%BC%EB%A8%B8%EC%8B%A0+(2).jpg">
	</div>
</body>
</html>
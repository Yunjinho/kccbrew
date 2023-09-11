<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>로그인</title>
</head>
<body>
<h1>로그인</h1>
<form action="./login"	method="post">
	<p>사용자명 <input type="text" name="username" placeholder="username" >
	<p>비밀번호 <input type="password" name="password" 	placeholder="password" >
	<p><button type="submit">로그인</button>				
	<input type="hidden" name="${_csrf.parameterName}" 	value="${_csrf.token}" />
</form>	
</body>
</html>
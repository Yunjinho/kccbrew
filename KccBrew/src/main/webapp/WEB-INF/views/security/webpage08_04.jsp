<%@ page contentType="text/html; charset=utf-8"%>
  <%@ taglib prefix="sec"  uri="http://www.springframework.org/security/tags"%> 
<html>
<head>
<title>Security</title>
</head>
<body>  
<h2>스프링 시큐리티 예</h2>   

<sec:authorize access="isAuthenticated()">
    <h5><sec:authentication property="principal.username"/>님, 반갑습니다.</h5>
    <form action="./logout" method="POST">
    <button type="submit">LOGOUT</button>
        <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
    </form>
</sec:authorize>

</body>
</html>



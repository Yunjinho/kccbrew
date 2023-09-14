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
	<div class="sticky_wrap">
	  <div>Not Sticky1</div>
	  <div>Not Sticky2</div>
	  <div class="sticky">Sticky!!</div>
	  <div>Not Sticky3</div>
	  <div>Not Sticky4</div>
	  <div>Not Sticky5</div>
	</div>
	
	<div class="other_block">
	  <h4>Other block</h4>
	  <p>
	    text<br />
	    text<br />
	    text<br />
	    text<br />
	    text<br />
	    text<br />
	    text<br />
	    text<br />
	    text<br />
	  </p>
	</div>
</body>
</html>
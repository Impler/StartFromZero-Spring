<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>RequestBody &amp; Response Body</title>
</head>
<body>
<h2>@RequestBody &amp; @ResponseBody</h2>
<ul>
	<li>
		<p>@RequestBody</p>
		<p>@RequestBody标识在requestMapping的方法的参数上，该参数将会被赋予HTTP请求体的内容(get请求没有请求体)。
			<a href="javascript:document.getElementById('mainForm').submit()">测试</a>
		</p>		
		
	</li>
	<li>
		<p>@ResponseBody</p>
		<p>@ResponseBody标识在requestMapping方法上，表示该方法的返回值将被直接放入response中，而不是作为逻辑view。常用于ajax响应。 <a href="../user/responseBody">测试</a></p>
	</li>
</ul>

<form id="mainForm" action="../user/requestBody" method="post">
	<input type="hidden" name="p1" value="p1-value">
	<input type="hidden" name="p2" value="p2-value">
</form>
</body>
</html>
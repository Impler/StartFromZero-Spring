<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Model And View</title>
</head>
<body>
<h2>Spring MVC ModelAndView</h2>
<p>Spring MVC根据入参执行相应的逻辑，产生模型数据，导向特定的试图中。Spring MVC提供了多种途径输出模型数据。</p>
<ol>
	<li><a href="../user/login4">ModelAndView</a>：处理方法返回值类型为ModelAndView</li>
	<li><a href="../user/login5">@ModelAttribute</a>：标注了该注解的入参会被放到数据模型中</li>
	<li><a href="../user/login6">Map及Model</a>：入参为Model、ModelMap或Map时，处理方法返回时，Map中的数据会被添加到模型中</li>
	<li>@SessionAttribute：将模型中的某个属性暂存到Session中</li>
</ol>
</body>
</html>
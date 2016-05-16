<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>@RequestMapping</title>
</head>
<body>
<h1>@RequestMapping</h1>
<ul>
	<li>任何一个标注了@Controller的POJO都可以成为Spring MVC中的控制器。</li>
	<li>使用@RequestMapping映射请求。控制器的类定义或方法处都可以标注@RequestMapping，类定义处的@RequestMapping提供初步的请求映射信息，方法处的@RequestMapping提供进一步的细分映射信息。</li>
	<li>将请求映射到控制器处理方法的工作可以通过请求URL、请求参数、请求头、请求方法4种方式来完成。</li>
</ul>
<h2>1 通过请求URL进行映射</h2>
<ul>
	<li>@RequestMapping使用value值指定请求的URL（当只包含value属性时，可以直接写属性值，而不用写value=属性值）</li>
 	<li>@RequestMapping在类定义处指定的URL相对于Web应用的部署路径</li>
 	<li>@RequestMapping在方法定义处指定的URL则相对于类定义指定的URL</li>
 	<li>如果类定义处未标注@RequestMapping，仅在方法处标注，则其指定的URL相对于Web应用的部署路径</li>
 	<li>例如：
 		<ul>
 			<li>URL映射：<a href="../user/createUser">@RequestMapping("/createUser")：/user/createUser</a></li>
 			<li>支持Ant风格的URL：<a href="../user/createUser/createAdmin">@RequestMapping("/createUser/*")：/user/createUser/createAdmin</a></li>
 			<li>支持{xxx}占位符搭配<b>@PathVariable</b>：<a href="../user/showUser/admin">@RequestMapping("/showUser/{role}")：/user/showUser/admin</a></li>
 			<li>支持{varName:regex}形式的正则表达式URL（匹配）：<a href="../user/showUserDetail/2">@RequestMapping("/showUserDetail/{userId:\\d}")：/user/showUserDetail/2</a></li>
 			<li>支持{varName:regex}形式的正则表达式URL（不匹配）：<a href="../user/showUserDetail/abc">@RequestMapping("/showUserDetail/{userId:\\d}")：/user/showUserDetail/abc</a></li>
 			<li>支持矩阵形式的URL：<a href="../user/showUserDetail/2;u=abc;p=123">@RequestMapping("/showUserDetail/{userId}")：/user/showUserDetail/2;u=abc;p=123</a></li>
 		</ul>
 	</li>
 </ul>
<h2>2 通过请求参数、请求方法或请求头进行映射</h2>
<ul>
	<li>@RequestMapping value/method/params/headers属性：<a href="../user/deleteUser?userID=2">@RequestMapping(value="/delete", method=RequestMethod.GET, params="userID", headers="content-type=text/html")</a></li>
	<li>@RequestParam：<a href="../user/login1?username=jack&password=123456">@RequestMapping("/login") @RequestParam("username") @RequestParam("password")</a></li>
	<li>@CookieValue, @RequestHeader：<a href="../user/requestInfo">@RequestMapping("/requestInfo") @CookieValue("JSESSIONID") @RequestHeader("Accept-Language")</a></li>
	<li>按属性名称匹配的方式为java bean赋值<a href="../user/login2?username=tom&password=654321">@RequestMapping("/login2")：/user/login2?username=tom&amp;password=654321</a></li>
	<li>将Http对象传递给处理方法<a href="../user/login3?username=kitty&password=65432111">@RequestMapping("/login3")：/user/login3?username=kitty&amp;password=65432111</a></li>
	<li>@ModelAttribute标识方法入参<a href="../user/login4?username=kitty&password=65432111">@RequestMapping("/login4")：login4(@ModelAttribute("hello")User user)</a></li>
	<li>@ModelAttribute标识方法<a href="../user/login5">@RequestMapping("/login5")</a></li>
	<li><a href="../user/requestBody?username=kitty&password=65432111">@RequestBody</a></li>
	<li><a href="../user/responseBody">@ResponseBody</a></li>
	<li><a href=""></a>Spring MVC还支持处理方法其他类型的入参，包括IO对象、java.util.Locale等</li>
</ul>
</body>
</html>
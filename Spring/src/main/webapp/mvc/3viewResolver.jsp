<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>视图处理</title>
</head>
<body>
	<h2>视图处理</h2>
	<p>Spring提供视图解析机制，不拘泥与具体的视图技术，在浏览器中渲染数据。</p>
	
	<p>视图处理的两个重要接口</p>
	<ol>
		<li>
			ViewResolver：在视图名称与实际的视图之间建立对应关系
			<p>ViewResolver的几个常见实现</p>
			<ul>
				<li>AbstractCachingViewResolver：抽象实现，提供视图缓存功能</li>
				<li>XmlViewResolver</li>
				<li>ResourceBundleViewResolver</li>
				<li>UrlBasedViewResolver：实现URL与逻辑视图间的简单对应关系</li>
				<li>InternalResourceViewResolver：扩展自UrlBasedViewResolver</li>
				<li>VelocityViewResolver / FreeMarkerViewResolver：</li>
				<li>ContentNegotiatingViewResolver</li>
			</ul>
			<p>例如：返回名为test的逻辑视图，视图解析器将会把请求转发到/WEB-INF/jsp/test.jsp</p>
			<pre>
				&lt;bean id="viewResolver"
			        class="org.springframework.web.servlet.view.UrlBasedViewResolver"&gt;
				    &lt;property name="viewClass" value="org.springframework.web.servlet.view.JstlView"/&gt;
				    &lt;property name="prefix" value="/WEB-INF/jsp/"/&gt;
				    &lt;property name="suffix" value=".jsp"/&gt;
				&lt;/bean&gt;
			</pre>
			<p>Spring 容器中可以配置多个视图解析器，提供order属性为排序使用，order值越大，匹配越靠后</p>
		</li>
		<li>View：用于渲染内容，显式数据</li>
	</ol>
</body>
</html>
package com.study.spring.mvc.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.study.spring.mvc.domain.User;
/**
 * 任何一个标注了@Controller的POJO都可以成为Spring MVC中的控制器
 * 使用@RequestMapping映射请求
 * 控制器的类定义或方法处都可以标注@RequestMapping，类定义处的@RequestMapping提供初步的请求映射信息，方法处的@RequestMapping提供进一步的细分映射信息
 * 将请求映射到控制器处理方法的工作可以通过请求URL、请求参数、请求头、请求方法4种方式来完成
 * @author Impler
 * @date 2016-03-18
 */

@Controller
@RequestMapping("/user")
public class UserController {

	/*
	 * 1 通过请求URL进行映射
	 * 	@RequestMapping使用value值指定请求的URL（当只包含value属性时，可以直接写属性值，而不用写value=属性值）
	 * 	@RequestMapping在类定义处指定的URL相对于Web应用的部署路径
	 * 	@RequestMapping在方法定义处指定的URL则相对于类定义指定的URL
	 * 	如果类定义处未标注@RequestMapping，仅在方法处标注，则其指定的URL相对于Web应用的部署路径
	 */
	//匹配URL WebRoot/user/createUser
	@RequestMapping("/createUser")
	public ModelAndView createUser(){
		String msg = "UserController-->createUser";
		return commonMV(msg);
	}
	
	/*
	 * @RequestMapping不但支持标准的URL，还支持Ant风格（即?、*和**）和带{xxx}占位符的URL
	 * 例如：
	 * /user/* /createUser匹配/user/aaa/creatUser、/user/bbb/createUser等URL
	 * /user/** /createUser匹配/user/createUser、/user/aaa/bbb/createUser等URL
	 * /user/createUser??匹配/user/createUseraa、/user/createUserab等URL
	 * /user/{userId}匹配/user/123、/user/456等URL
	 */
	//匹配URL WebRoot/user/createUser/createAdmin
	@RequestMapping("/createUser/*")
	public ModelAndView createAdmin(){
		String msg = "UserController-->createUser-->createAdmin";
		return commonMV(msg);
	}
	
	//URL中的{xxx}占位符可以通过@PathVariable("xxx")绑定到操作方法的入参中，最好在@PathVariable中显式指定绑定的参数名
	@RequestMapping("/showUser/{role}")
	public ModelAndView showUserInfo(@PathVariable("role") String role){
		String msg = "show user role：" + role + " information";
		return commonMV(msg);
	}
	//支持正则表达式，格式：{varName:regex}
	@RequestMapping("/showUserDetail/{userId:\\d}")
	public ModelAndView acceptRegex(@PathVariable String userId){
		String msg = "user id: " + userId;
		return commonMV(msg);
	}
	//支持矩阵形式的URL   必须设置参数<mvc:annotation-driven enable-matrix-variables="true"/>
	@RequestMapping("/showUserDetail/{userId}")
	public ModelAndView acceptMatrix(@PathVariable int userId, @MatrixVariable("u") String username, @MatrixVariable("p") String password){
		User u = new User();
		u.setId(userId);
		u.setUsername(username);
		u.setPassword(password);
		return commonMV(u);
	}
	/*
	 * 2 通过请求参数、请求方法或请求头进行映射
	 * params和headers分别通过请求参数及报文头属性进行映射，他们支持简单的表达式：
	 * 	"param1" 表示请求必须包含名为param1的请求参数
	 *  "!param1" 表示请求不能包含名为param1的请求参数
	 *  "param1!=value1" 表示请求包含名为param1的请求参数，但其值不能为value1
	 *  {"param1=value1","param2"} 表示请求中必须包含名为param1和param2的请求参数，且param1参数的值必须为value1
	 *  
	 * TODO headers测试不成功
	 */
	//@RequestMapping(value="/deleteUser", method=RequestMethod.GET, params="userID", headers="content-type=text/html")
	@RequestMapping(value="/deleteUser", method=RequestMethod.GET, params="userID")
	public ModelAndView deleteUser(@RequestParam("userID") String userId){
		String msg = "UserController-->deleteUser id:" + userId;
		return commonMV(msg);
	}
	
	//通过请求参数映射映射
	@RequestMapping("/login1")
	public ModelAndView login1(@RequestParam("username") String username,
				@RequestParam("password") String password){
		String msg = "user login. usernmae:" + username + ",password:" + password;
		return commonMV(msg);
	}
	
	//通过请求头，cookie映射
	@RequestMapping("/requestInfo")
	public ModelAndView requestInfo(@CookieValue("JSESSIONID") String sessionId, 
				@RequestHeader("Accept-Language") String lan){
		String msg = "jsessionid: " + sessionId + ", language：" + lan;
		return commonMV(msg);
	}
	
	//请求参数按名称匹配的方式绑定到javabean中
	@RequestMapping("/login2")
	public ModelAndView login2(User user){
		String msg = "login user: " + user;
		return commonMV(msg);
	}
	//Servlet API对象作为入参
	@RequestMapping("/login3")
	public ModelAndView login3(HttpServletRequest request){
		String msg = "request username: " + request.getParameter("username") 
				+ ", request password: " + request.getParameter("password");
		return commonMV(msg);
	}
	
	//处理模型数据通过@ModelAttribute，Spring将被@ModelAttribute标识的入参自动添加到模型中
	@RequestMapping("/login4")
	public String login4(@ModelAttribute("hello")User user){
		System.out.println("callof login4");
		user.setUsername("zhangsan");
		return "hello";
	}
	
	//SpringMVC在调用任何一个请求处理方法前都会调用被@ModelAttribute标识的方法，将返回值已“hello”为key添加到model中
	@ModelAttribute("hello")
	public User getBaseUser(){
		System.out.println("callof getBaseUser");
		User u = new User();
		u.setUsername("lisi");
		u.setPassword("QWEasd123");
		return u;
	}
	@RequestMapping("/login5")
	public String login5(Map<String, Object> map){
		//获取隐含模型中的对象
		User u = (User) map.get("hello");
		System.out.println("login5:" + u);
		u.setUsername("wanger");
		return "hello";
	}
	
	/**
	 * 无法接收GET请求，因为GET请求没有请求体
	 * @param body
	 * @return
	 */
	@RequestMapping("/requestBody")
	public ModelAndView getRequestBody(@RequestBody String body){
		return commonMV("request body:" + body);
	}
	
	//@RequestBody意味着返回值将会直接被写入到响应体中，适用于ajax响应，不会被当做View处理
	@RequestMapping("/responseBody")
	@ResponseBody
	public String getResponseBody(){
		return "Response Body: Hello World";
	}
	
	//TODO Using HttpEntity
	//TODO 
	/**
	 * 通用返回 ModelAndView
	 * @param msg
	 * @return
	 */
	public ModelAndView commonMV(Object msg){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("hello");
		mv.addObject("hello", msg);
		return mv;
	}
}

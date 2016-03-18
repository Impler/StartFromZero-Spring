package com.study.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public void createUser(){
		System.out.println("UserController-->createUser");
	}
}

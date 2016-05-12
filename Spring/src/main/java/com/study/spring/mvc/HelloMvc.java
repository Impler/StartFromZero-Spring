package com.study.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 使用@Controller注解标识的class，将被作为控制器类
 * @author Impler
 *
 */
@Controller
public class HelloMvc {
	
	/**
	 * 使用@RequestMapping 来映射URL
	 * @return
	 */
	@RequestMapping("/mvc/hello")
	public ModelAndView hello(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("hello");
		mv.addObject("hello", "Hello Spring MVC");
		return mv;
	}
}

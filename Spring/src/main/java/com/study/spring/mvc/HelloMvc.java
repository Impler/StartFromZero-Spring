package com.study.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloMvc {
	
	@RequestMapping("/mvc/hello")
	public ModelAndView hello(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("hello");
		mv.addObject("hello", "Hello Spring MVC");
		return mv;
	}
}

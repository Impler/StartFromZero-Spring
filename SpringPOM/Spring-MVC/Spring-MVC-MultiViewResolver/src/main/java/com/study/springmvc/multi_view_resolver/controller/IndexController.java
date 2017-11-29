package com.study.springmvc.multi_view_resolver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/hello")
    public String toJsp(){
        return "hello";
    }
    
    @RequestMapping("/hi")
    public String toFreeMarker(){
        return "hi";
    }
}

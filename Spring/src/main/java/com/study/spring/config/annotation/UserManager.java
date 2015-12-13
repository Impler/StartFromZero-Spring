package com.study.spring.config.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.study.spring.base.domain.User;
/**
 * controller
 * @author Impler
 * @date 2015年11月23日
 */

@Controller
public class UserManager {

	@Autowired
	private IUserService userService;
	
	public void register(User u){
		System.out.println("-ready to register user:");
		userService.register(u);
		System.out.println("-end");
	}
}

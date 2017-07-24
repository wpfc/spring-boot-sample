package cn.edu.ntu.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.ntu.entity.User;

@RestController
public class MainController {

	@Value("${my.uuid}")
	private String UUID;
	
	@RequestMapping("/main")
	public Object sayHello(){
		User user = new User();
		user.setUserId(1L);
		user.setUserName("hello");
		user.setAge(23);
		return user;
	}
	
}

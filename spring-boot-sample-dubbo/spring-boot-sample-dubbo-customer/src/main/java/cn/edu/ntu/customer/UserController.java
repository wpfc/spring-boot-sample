package cn.edu.ntu.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.ntu.dubbo.api.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/reformSomeone")
	public void reformSomeone(){
		userService.reformUserContent("helloworld");
	}
}

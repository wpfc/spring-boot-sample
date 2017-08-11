package cn.edu.ntu.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.ntu.dubbo.api.UserService;
import cn.edu.ntu.model.User;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/reformSomeone")
	@ResponseBody
	public Object reformSomeone(){
		User currentUser = new User();
		currentUser.setUserId(1);
		currentUser.setUserName("wpfc");
		userService.reformUserContent(currentUser, "helloworld");
		return currentUser;
	}
}

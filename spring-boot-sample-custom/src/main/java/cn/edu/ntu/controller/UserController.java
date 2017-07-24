package cn.edu.ntu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.ntu.entity.User;
import cn.edu.ntu.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/getUserInfoById")
	public String getUserInfoById(Model model, Long userId){
		User user = userService.getUserById(userId);
		model.addAttribute("userinfo", user);
		return "hello";
	}
	
}

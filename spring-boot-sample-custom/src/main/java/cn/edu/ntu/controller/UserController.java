package cn.edu.ntu.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import cn.edu.ntu.entity.User;
import cn.edu.ntu.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/getUserInfoById")
	public String getUserInfoById(Model model){
		Subject subject = SecurityUtils.getSubject();
		User userInfo = (User) subject.getPrincipal();
		User user = userService.getUserById(userInfo.getUserId());
		model.addAttribute("userinfo", user);
		return "hello";
	}
	
}

package cn.edu.ntu.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.ntu.entity.User;
import cn.edu.ntu.utils.RandomCode;


@Controller
public class LoginController {

	@GetMapping("/login")
	public String login(){
		return "login";
	}
	
	@PostMapping("login")
	public Map<String, Object> login(User user, String randomCode){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "200");
		return result;
	}
	
	@RequestMapping(value = "/random")
	public void random(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
		response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expire", 0);
		RandomCode randomCode = new RandomCode();
		try {
			randomCode.getRandcode(request, response);//输出图片方法
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package cn.edu.ntu.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.ntu.entity.Result;
import cn.edu.ntu.entity.User;
import cn.edu.ntu.utils.RandomCode;


@Controller
public class LoginController {

	@GetMapping("/login")
	public String login(){
		return "login";
	}
	
	@PostMapping("/login")
	@ResponseBody
	public Result login(User user){
		Result result = new Result();
		try{
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
			subject.login(token);
			result.setCode("200");
		}catch(Exception ex){
			result.setCode("999");
			result.setMsg(ex.getMessage());
			
		}
		return result;
	}
	
	@RequestMapping(value = "/random")
	public void random(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("image/jpeg");// ������Ӧ����,������������������ΪͼƬ
		response.setHeader("Pragma", "No-cache");// ������Ӧͷ��Ϣ�������������Ҫ���������
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expire", 0);
		RandomCode randomCode = new RandomCode();
		try {
			randomCode.getRandcode(request, response);// ���ͼƬ����
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

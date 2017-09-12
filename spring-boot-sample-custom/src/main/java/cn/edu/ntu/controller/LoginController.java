package cn.edu.ntu.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.edu.ntu.entity.User;
import cn.edu.ntu.utils.Cons;
import cn.edu.ntu.utils.HttpResult;
import cn.edu.ntu.utils.RandomCode;


@Controller
public class LoginController extends BaseController {

	@GetMapping("/login")
	public String login(){
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<HttpResult> login1(String randomCode, String userName, String password) throws Exception {
		Session session = SecurityUtils.getSubject().getSession();
		logger.info("sessionId : {}", session.getId());
		String random = session.getAttribute(RandomCode.RANDOMCODEKEY) != null ? session
				.getAttribute(RandomCode.RANDOMCODEKEY).toString() : null;
		if(!random.equals(randomCode)){
			ok(HttpResult.err().msg("验证码不正确！").build());
		}
		UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
		Subject subject = SecurityUtils.getSubject();
		subject.login(token);
		User user = (User) subject.getPrincipal();
		// setting permissions.
		session.setAttribute(Cons.SESSION_INFO, user);
		return ok(HttpResult.ok().build());
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
			randomCode.getRandcode(request, response);// 输出图片方法
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

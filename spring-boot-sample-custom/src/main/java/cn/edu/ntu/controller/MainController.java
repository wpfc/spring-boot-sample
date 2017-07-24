package cn.edu.ntu.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	@Value("${my.uuid}")
	private String UUID;
	
	@RequestMapping("/main")
	public String sayHello(){
		return "say " + UUID + " to the world!";
	}
	
}

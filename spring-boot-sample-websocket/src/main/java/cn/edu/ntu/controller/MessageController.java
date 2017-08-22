package cn.edu.ntu.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.ntu.entity.Message;

@Controller
public class MessageController {

	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
	
	@Autowired  
    private SimpMessageSendingOperations simpMessageSendingOperations;
	
	@MessageMapping("/wpfc")
	public void connect(){
		logger.info("connect successfully ... ");
	}
	
	/**
	 * 广播模式（一对多）
	 * 当客户端向服务器端发送STOMP请求时，通过@MessageMapping注解来映射地址('/app/**')
	 * 服务器端通过"/topic/greeting"给客户端推送消息
	 * 可以通过@SendTo("/topic/**") 自定义推送地址 
	 * @return
	 */
	@MessageMapping("/greeting")
	@SendTo("/topic/sendTo")
	public Message sendTo(){
		return new Message("/topic/greeting", null , null);
	}
	
	/**
	 * 当客户端订阅了该地址，便直接返回订阅的信息
	 */
	@SubscribeMapping("/marco")
	public Message subscrible(){
		return new Message("/marco", null, null);
	}
	
	@MessageMapping("/message")
	@SendToUser("message")
	public Message message(){
		return new Message("message to user", null, null);
	}
	
	@RequestMapping("/send")
	public Message sendMessage(){
		simpMessageSendingOperations.convertAndSendToUser("userInfo", "/message", new Message("create new Message to user", null, null));
		return new Message("send message to user", null, null);
	}
}

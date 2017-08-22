package cn.edu.ntu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		//通过在"/wpfc"暴露STOMP webSocket/SockJS端点
		//后台需要对应的有一个@MessageMapping("/wpfc"),让客户端与服务器连接起来
		registry.addEndpoint("/wpfc").withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		//stomp客户端请求需要以/app/** 发起
		registry.setApplicationDestinationPrefixes("/app");
		//stomp客户端需要订阅（监听在这个路径）
		registry.enableSimpleBroker("/topic", "/user");
		//服务器端一对一发送信息需要以"/user/{userInfo}/**"开头
		registry.setUserDestinationPrefix("/user");
	}
}

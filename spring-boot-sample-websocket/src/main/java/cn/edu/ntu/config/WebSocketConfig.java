package cn.edu.ntu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import cn.edu.ntu.intercepotr.HandshakeInterceptor;
import cn.edu.ntu.websocket.MyHandler;


@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

		registry.addHandler(myHandler(), "/websocket").addInterceptors(new HandshakeInterceptor()).setAllowedOrigins("*");
	}

	@Bean
	public static WebSocketHandler myHandler() {
		return new MyHandler();
	}

}

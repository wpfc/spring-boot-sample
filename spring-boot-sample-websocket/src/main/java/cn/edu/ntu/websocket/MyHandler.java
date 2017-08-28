package cn.edu.ntu.websocket;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * 
 * @author wpfc
 *
 *
 * 调试地址：http://www.blue-zero.com/WebSocket/#
 *
 */

@Component
public class MyHandler extends TextWebSocketHandler {

	private static Logger logger = LoggerFactory.getLogger(MyHandler.class);

	private static Map<String, WebSocketSession> WEBSOCKET_SESSION_MAP = new HashMap<String, WebSocketSession>();
	
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		//WEBSOCKET_SESSION_MAP.put(session.getId(), session);
		//session.sendMessage(new TextMessage(session.getId()+",你是第" + (WEBSOCKET_SESSION_MAP.size()) + "位访客"));
	}
	
	@Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		WEBSOCKET_SESSION_MAP.put(session.getId(), session);
        super.afterConnectionEstablished(session);
    }
}

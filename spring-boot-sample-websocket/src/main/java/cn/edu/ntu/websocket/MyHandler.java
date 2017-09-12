package cn.edu.ntu.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import cn.edu.ntu.entity.RequestMessage;
import cn.edu.ntu.utils.JsonUtils;

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
	
	private static List<String> USERINFO_LIST = new ArrayList<String>();
	
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		RequestMessage reqMsg = JsonUtils.parseString2Object(message.getPayload(), RequestMessage.class);
		//session.sendMessage(new TextMessage(message.getPayload()));
		
	}
	
	@Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        session.sendMessage(new TextMessage("<b>服务端主动向你推送：</b> <span style='font-size:20px;'>点击进入<a style='font-weight:900;font-size:16px;' href='http://www.blue-zero.com/chat/' target='_blank'>【聊天室】[围观]</a>，使用websocket开发，支持一对一私聊和一对多公聊</span>"));
        WEBSOCKET_SESSION_MAP.put(session.getId(), session);
        USERINFO_LIST.add(session.getId());
        super.afterConnectionEstablished(session);
    }
	
	private void broadcast(List<String> userList, CharSequence message){
		if(userList != null && userList.size() > 0){
			for(String item : userList){
				WebSocketSession session =  WEBSOCKET_SESSION_MAP.get(item);
				if(session !=null && session.isOpen() && !StringUtils.isEmpty(message)){
					try {
						session.sendMessage(new TextMessage(message));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	private void sendToUser(String someone, CharSequence message){
		WebSocketSession session =  WEBSOCKET_SESSION_MAP.get(someone);
		if(session !=null && session.isOpen() && !StringUtils.isEmpty(message)){
			try {
				session.sendMessage(new TextMessage(message));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args){
		String[] original = {"a", "b", "c"};
		int newLength = 10;
		String[] copy = new String[newLength];
		System.arraycopy(original, 0, copy, 0,
                Math.min(original.length, newLength));
		System.out.println(original);
		System.out.println(copy);
	}
	
}

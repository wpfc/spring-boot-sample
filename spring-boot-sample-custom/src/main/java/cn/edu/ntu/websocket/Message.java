package cn.edu.ntu.websocket;

import org.springframework.web.socket.WebSocketMessage;

public class Message implements WebSocketMessage {

	@Override
	public Object getPayload() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPayloadLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isLast() {
		// TODO Auto-generated method stub
		return false;
	}

}

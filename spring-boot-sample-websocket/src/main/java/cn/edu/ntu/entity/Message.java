package cn.edu.ntu.entity;

public class Message {

	/**
	 * 目的地地址
	 */
	private String destination;
	
	/**
	 * 内容
	 */
	private Object content;
	
	/**
	 * 用户信息
	 */
	private String userInfo;

	
	public Message(String destination, Object content, String userInfo) {
		super();
		this.destination = destination;
		this.content = content;
		this.userInfo = userInfo;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}
	
}

package cn.edu.ntu.entity;

import java.util.Date;

public class TextInfo {

	private String from;
	
	private Date time;
	
	private Object data;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "{from:" + from + ", time:" + time + ", data:" + data + "}";
	}
}

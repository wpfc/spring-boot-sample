package cn.edu.ntu.utils;

/**
 *
 * @author luoyh(Roy) - May 24, 2017
 */
public class HttpResult {
	
	private int code;
	private String msg;
	private Object data;

	/**
	 * The builder code default {@link Cons#ERR}
	 * @return
	 * 
	 * @author luoyh(Roy) - May 24, 2017
	 */
	public static Builder builder() {
		return builder(Cons.ERR);
	}
	
	public static Builder builder(int code) {
		return new Builder(code);
	}
	
	public static Builder ok() {
		return builder(Cons.OK);
	}
	
	public static Builder err() {
		return builder(Cons.ERR);
	}
	
	public static class Builder {
		private int code;
		private String msg;
		private Object data;
		
		public Builder(int code) {
			this.code = code;
		}
		
		public Builder code(int code) {
			this.code = code;
			return this;
		}
		
		public Builder msg(String msg) {
			this.msg = msg;
			return this;
		}
		
		public Builder data(Object data) {
			this.data = data;
			return this;
		}
		
		public HttpResult build() {
			HttpResult r = new HttpResult();
			r.setCode(code);
			r.setMsg(msg);
			r.setData(data);
			return r;
		}
		
	}
	
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
	@Override
	public String toString() {
		return JsonUtils.objectToJson(this);
	}

}

package cn.edu.ntu.entity;

/**
 *  消息对象
 *  
 *  格式： {rname: "公共场所", rpsw: "", id: "7c1ba827", name: "野茫茫的鼓手", act: "sign", msg: ""}
 *  
 */
public class RequestMessage {

	private String rname;
	
	private String rpsw;
	
	private String id;
	
	private String name;
	
	private String oldName;
	
	private String act;
	
	private String msg;

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public String getRpsw() {
		return rpsw;
	}

	public void setRpsw(String rpsw) {
		this.rpsw = rpsw;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "{rname:" + rname + ", rpsw:" + rpsw + ", id:" + id + ", name:" + name + ", oldName:" + oldName
				+ ", act:" + act + ", msg:" + msg + "}";
	}

	
}

package cn.edu.ntu.tag;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class DateToString extends TagSupport {

	private static final long serialVersionUID = 1L;

	private static final String pattern = "yyyy-MM-dd HH:mm:ss";
	
	private String value;
	
	@Override
	public int doStartTag() throws JspException {
		try {
			String result = "";
			long source = new SimpleDateFormat(pattern).parse(value).getTime();
			long now = System.currentTimeMillis();
			long diff = (now - source)/1000;
			if(diff/60 < 60){
				//一小时以内
				result = diff/60 + "分钟前";
			}else if(diff/(60*60) < 24){
				//一天以内
				result = diff/(60*60) + "小时前";
			}else{
				result = diff/(60*60*24) + "天前";
			}
			HttpServletResponse response = (HttpServletResponse) this.pageContext.getResponse();
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.doStartTag();
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}

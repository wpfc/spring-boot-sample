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
				//һСʱ����
				result = diff/60 + "����ǰ";
			}else if(diff/(60*60) < 24){
				//һ������
				result = diff/(60*60) + "Сʱǰ";
			}else{
				result = diff/(60*60*24) + "��ǰ";
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

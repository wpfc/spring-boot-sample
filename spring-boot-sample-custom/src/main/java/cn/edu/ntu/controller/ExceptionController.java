package cn.edu.ntu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.ntu.utils.HttpResult;
import cn.edu.ntu.utils.JsonUtils;
import cn.edu.ntu.utils.MyException;

/**
 * ������@ControllerAdviceӦ�÷�Χ�������������쳣���Ӧ�÷�Χ
 * @ControllerAdvice�ķ�Χ�У�
 * ��basePackages��Ӧ����xx��
 * ��basePackageClasses��Ӧ����xx��
 * ��assignableTypes��Ӧ���ڼ���@Controller����
 * ��annotations��Ӧ���ڴ���xxע�������߷���
 * 
 * @author wpfc
 *
 */
@ControllerAdvice
public class ExceptionController {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class); 
	
	// ȫ���쳣������JSON
//	@ExceptionHandler(Exception.class)
//	@ResponseBody
//	public Map<String, Object> exception(HttpServletRequest request, Exception e){
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("url", request.getRequestURI());
//		map.put("message", e.getMessage());
//		return map;
//	}
	
	/*public ModelAndView resolveException(HttpServletRequest request,
            HttpServletResponse response, Object object, Exception exception) {
        // �ж��Ƿ�ajax����
        if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request
                .getHeader("X-Requested-With") != null && request.getHeader(
                "X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
            // �������ajax��JSP��ʽ����
            // Ϊ��ȫ�����ֻ��ҵ���쳣���Ƕ�ǰ�˿ɼ����������ͳһ��Ϊϵͳ�쳣
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("success", false);
            if (exception instanceof BusinessException) {
                map.put("errorMsg", exception.getMessage());
            } else {
                map.put("errorMsg", "ϵͳ�쳣��");
            }
            //������Ҫ�ֶ����쳣��ӡ����������û������log��ʵ����������Ӧ�ô�ӡ��log����
            exception.printStackTrace();
            //���ڷ�ajax�������Ƕ�ͳһ��ת��error.jspҳ��
            return new ModelAndView("/error", map);
        } else {
            // �����ajax����JSON��ʽ����
            try {
                response.setContentType("application/json;charset=UTF-8");
                PrintWriter writer = response.getWriter();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("success", false);
                // Ϊ��ȫ�����ֻ��ҵ���쳣���Ƕ�ǰ�˿ɼ�������ͳһ��Ϊϵͳ�쳣
                if (exception instanceof BusinessException) {
                    map.put("errorMsg", exception.getMessage());
                } else {
                    map.put("errorMsg", "ϵͳ�쳣��");
                }
                writer.write(JSONUtils.toJSONString(map));
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }*/
	
	@ExceptionHandler(value = Exception.class)
	public Object globalExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e){
		if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request
                .getHeader("X-Requested-With") != null && request.getHeader(
                "X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
			request.setAttribute("exception", e);
			request.setAttribute("url", request.getRequestURI());
			handlerException(e);
			return "exception";
		}else{
			try {
				response.setContentType("UTF-8");
				PrintWriter out = response.getWriter();
				out.write(HttpResult.err().msg(e.getMessage()).build().toString());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	@ExceptionHandler(value = MyException.class)
	public String myExceptionHandler(HttpServletRequest request, Exception e){
		try{
			request.setAttribute("exception", e);
			request.setAttribute("url", request.getRequestURI());
			handlerException(e);
		}catch(Exception ex){
			
		}
		return "exception";
	}
	
	private void handlerException(Exception e){
		logger.info("--------------------- exception start ----------------------");
		StackTraceElement[] stacks = e.getStackTrace();
		StackTraceElement item = stacks[0];
		logger.info("����������           {}", item.getClassName());
		logger.info("���󷽷�����       {}", item.getMethodName());
		logger.info("�����кţ�           {}", item.getLineNumber());
		logger.info("����ԭ��           {}", e.getMessage());
		logger.info("--------------------- exception  end  ----------------------");
	}
	
}

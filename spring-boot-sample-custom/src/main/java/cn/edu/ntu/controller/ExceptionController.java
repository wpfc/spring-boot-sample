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
 * 设置了@ControllerAdvice应用范围，即就设置了异常类的应用范围
 * @ControllerAdvice的范围有：
 * ①basePackages：应用在xx包
 * ②basePackageClasses：应用在xx类
 * ③assignableTypes：应用在加了@Controller的类
 * ④annotations：应用在带有xx注解的类或者方法
 * 
 * @author wpfc
 *
 */
@ControllerAdvice
public class ExceptionController {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class); 
	
	// 全局异常处理返回JSON
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
        // 判断是否ajax请求
        if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request
                .getHeader("X-Requested-With") != null && request.getHeader(
                "X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
            // 如果不是ajax，JSP格式返回
            // 为安全起见，只有业务异常我们对前端可见，否则否则统一归为系统异常
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("success", false);
            if (exception instanceof BusinessException) {
                map.put("errorMsg", exception.getMessage());
            } else {
                map.put("errorMsg", "系统异常！");
            }
            //这里需要手动将异常打印出来，由于没有配置log，实际生产环境应该打印到log里面
            exception.printStackTrace();
            //对于非ajax请求，我们都统一跳转到error.jsp页面
            return new ModelAndView("/error", map);
        } else {
            // 如果是ajax请求，JSON格式返回
            try {
                response.setContentType("application/json;charset=UTF-8");
                PrintWriter writer = response.getWriter();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("success", false);
                // 为安全起见，只有业务异常我们对前端可见，否则统一归为系统异常
                if (exception instanceof BusinessException) {
                    map.put("errorMsg", exception.getMessage());
                } else {
                    map.put("errorMsg", "系统异常！");
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
		logger.info("请求类名：           {}", item.getClassName());
		logger.info("请求方法名：       {}", item.getMethodName());
		logger.info("方法行号：           {}", item.getLineNumber());
		logger.info("错误原因：           {}", e.getMessage());
		logger.info("--------------------- exception  end  ----------------------");
	}
	
}

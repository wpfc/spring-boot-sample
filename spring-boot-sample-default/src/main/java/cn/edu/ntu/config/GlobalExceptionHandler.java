package cn.edu.ntu.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public ModelAndView dealWithGlobalException(HttpServletRequest request, Exception ex){
		ModelAndView mv = new ModelAndView();
		mv.addObject("exception", ex);
        mv.addObject("url", request.getRequestURL());
		mv.setViewName("404");
		return mv;
	}
}

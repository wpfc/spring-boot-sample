package cn.edu.ntu.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

@ControllerAdvice  
public class JsonpConfig extends AbstractJsonpResponseBodyAdvice {  
    
	public JsonpConfig() {  
        //参数包含callback的时候 使用jsonp的反馈形式  
//        super("callback");
		super("callback","jsonp");
    }  
} 
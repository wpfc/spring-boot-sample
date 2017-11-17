package cn.edu.ntu.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.validation.support.BindingAwareModelMap;

import cn.edu.ntu.annotation.Log;

/**
 * 
 * @author wpfc
 * 
 * pointcut()和around()方法中必须接收Log的参数   
 * 否则会导致：error Type referred to is not an annotation type: Log
 * 
 */
@Aspect
@Component
public class LogAspect {

	private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
	
	@Pointcut("execution(* cn.edu.ntu.controller.*.*(..)) && @annotation(log)")
	public void pointcut(Log log){}
	
	
	@Around("pointcut(log)")
    public Object around(ProceedingJoinPoint pjp, Log log) throws Throwable {
        StopWatch sw = new StopWatch(getClass().getSimpleName());
        try {
            sw.start(pjp.getSignature().getName());
            StringBuffer sb = new StringBuffer("");
            Object[] objs = pjp.getArgs();
            for(Object obj : objs){
            	if(!(obj instanceof BindingAwareModelMap)){
            		sb.append(" "+obj.toString()+"("+obj.getClass().getSimpleName()+")  ");
            	}
            }
            logger.info("==>     Method: " + pjp.getSignature().getName());
            logger.info("==> Parameters:" + sb.toString());
            String signature = pjp.getSignature().toString().split(" ")[0];
            logger.info("<==     Result: " + signature);
            return pjp.proceed();
            
        } finally {
            sw.stop();
            System.out.println(sw.prettyPrint());
        }
    }
}
	

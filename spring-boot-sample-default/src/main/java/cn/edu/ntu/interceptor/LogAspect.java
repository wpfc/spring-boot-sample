package cn.edu.ntu.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.util.StopWatch;

/**
 * 
 * @author wpfc
 * 
 * 不能添加@Component注解   会导致：error Type referred to is not an annotation type: Log
 * 
 */
@Aspect
//@Component
public class LogAspect {

	@Pointcut("execution(* cn.edu.ntu.controller.*.*(..)) && @annotation(Log)")
	public void pointcut(){
		
	}
	
	@Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch sw = new StopWatch(getClass().getSimpleName());
        try {
            sw.start(pjp.getSignature().getName());
            return pjp.proceed();
        } finally {
            sw.stop();
            System.out.println(sw.prettyPrint());
        }
    }

	
}

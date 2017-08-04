package cn.edu.ntu.task;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;

import cn.edu.ntu.annotation.TaskHandler;

@Component
public class TaskHandlerRegister extends ApplicationObjectSupport {

	private static Map<String, AbstractTaskHandler> TASK_HANDLER_MAP = new HashMap<String, AbstractTaskHandler>();
	
	private static final Logger logger = LoggerFactory.getLogger(TaskHandlerRegister.class);
	
	@Override
	protected void initApplicationContext(ApplicationContext context) throws BeansException {
		super.initApplicationContext(context);
		Map<String, Object> taskBeanMap = context.getBeansWithAnnotation(TaskHandler.class);
		if(!taskBeanMap.isEmpty()){
			/*for(Map.Entry<String, Object> entry : taskBeanMap.entrySet()){
				Object bean = entry.getValue();
				Class<?> clazz = bean.getClass();
				if(bean instanceof AbstractTaskHandler && clazz.isAnnotationPresent(TaskHandler.class)){
					TaskHandler handler = clazz.getAnnotation(TaskHandler.class);
					String taskType = handler.taskType();
					if(TASK_HANDLER_MAP.keySet().contains(taskType)){
						throw new RuntimeException("TASK_HANDLER_MAP has contain the taskType : " + taskType);
					}
					TASK_HANDLER_MAP.put(handler.taskType(), (AbstractTaskHandler) bean);
					logger.info("Task Handler Register. taskType={},beanName={}", handler.taskType(), clazz.getSimpleName());
				}
			}*/
			taskBeanMap.keySet().forEach(beanName ->{
				Object handler = taskBeanMap.get(beanName);
				Class<?> clazz = handler.getClass();
				if(handler instanceof AbstractTaskHandler && clazz.isAnnotationPresent(TaskHandler.class)){
					String taskType = clazz.getAnnotation(TaskHandler.class).taskType();
					if(taskBeanMap.keySet().contains(taskType)){
						throw new RuntimeException("TASK_HANDLER_MAP has contain the taskType : " + taskType);
					}
					TASK_HANDLER_MAP.put(taskType, (AbstractTaskHandler) handler);
					logger.info("Task Handler Register. taskType={},beanName={}", taskType, clazz.getSimpleName());
				}
			});
		}
	}
	
	public static AbstractTaskHandler getTaskHandler(String handlerName){
		return TASK_HANDLER_MAP.get(handlerName);
	}
	
}

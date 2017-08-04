package cn.edu.ntu.task;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.ntu.entity.User;
import cn.edu.ntu.service.UserService;

public class TaskExecutor implements Job {

	private static final String TASK_TYPE = "taskType";

	private static final Logger logger = LoggerFactory.getLogger(TaskExecutor.class);
	
	/*@Override
    public TaskResult execute(Task task){
        String taskType=task.getTaskType();
        if (TaskHandlerRegister.getTaskHandler(taskType) == null) {
            throw new RuntimeException("can't find taskHandler,taskType=" + taskType);
        }
        AbstractTaskHandler abstractHandler = TaskHandlerRegister.getTaskHandler(taskType);
        return abstractHandler.execute(task);
    }*/

	@Autowired
	private UserService userService;
	
	@Override
	public void execute(JobExecutionContext arg0) {
		System.out.println("helloworld");
		User user = userService.getUserById(1L);
		System.out.println("user:"+user.getUserName());
	}
	
	/**
	 * 一个Job可以有多个Trigger，但多个Job不能对应同一个Trigger
	 */
	public static void main(String[] args){
		try {
			//创建一个调度器
			SchedulerFactory sf = new StdSchedulerFactory();
			Scheduler scheduler = sf.getScheduler();
			scheduler.start();
			
			//创建一个任务   task实现jobDetail
			JobDetail job = JobBuilder.newJob(TaskExecutor.class)
		             .withIdentity("myJob")
		             //一个任务绑定多个触发器必须设置.storeDurably() 
		             .storeDurably()
		             .build();
			
			//创建一个触发器
	        Trigger trigger = TriggerBuilder
	    			.newTrigger()
	    			.withIdentity("myTrigger")
	    			.withSchedule(
    					CronScheduleBuilder.cronSchedule("0/20 * * * * ?"))
	    			.build();
	        Trigger trigger1 = TriggerBuilder
	    			.newTrigger()
	    			.withIdentity("myTrigger1")
	    			.forJob(job)
	    			.withSchedule(
    					CronScheduleBuilder.cronSchedule("0/25 * * * * ?"))
	    			.build();
	        
			scheduler.scheduleJob(job, trigger);
			scheduler.scheduleJob(trigger1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}

package cn.edu.ntu.utils;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobUtils {

	@Autowired
	private Scheduler scheduler;
	
	public synchronized void addJob(Job job, String cronExpression){
		try {
			JobDetail jobDetail = JobBuilder.newJob(job.getClass())
					                        .withIdentity(job.getClass().getSimpleName())
					                        .build();
			Trigger trigger = TriggerBuilder.newTrigger()
											.withIdentity(job.getClass().getSimpleName() + "Trigger")
											.withSchedule(
												CronScheduleBuilder.cronSchedule(cronExpression))
											.startNow()
											.build();
			scheduler.scheduleJob(jobDetail, trigger);
			scheduler.start();  
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
}

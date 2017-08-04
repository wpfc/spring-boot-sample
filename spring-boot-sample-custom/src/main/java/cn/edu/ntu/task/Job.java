package cn.edu.ntu.task;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import cn.edu.ntu.entity.Task;

public class Job implements org.quartz.Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("111111111111");
	}
	
	public void execute(Task task) throws JobExecutionException {
		System.out.println("22222222222");
	}

}

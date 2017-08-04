package cn.edu.ntu.config;

import java.io.IOException;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import cn.edu.ntu.task.JobFactory;

@Configuration
public class QuartzConfig {

	@Autowired
	private JobFactory jobFactory;
	
	/**
	 * 注册scheduler调度器
	 */
	@Bean
	public SchedulerFactoryBean createSchedulerFactoryBean(){
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		//配置自定义的jobFactory，使job实例可以注入spring容器中管理的对象
		//http://blog.csdn.net/u012572955/article/details/51656270
		//http://www.cnblogs.com/daxin/p/3608320.html
		schedulerFactoryBean.setJobFactory(jobFactory);
		return schedulerFactoryBean;
	}
	
	/**
	 * 创建scheduler实例
	 */
	@Bean  
    public Scheduler scheduler(SchedulerFactoryBean schedulerFactoryBean) throws IOException, SchedulerException {  
        Scheduler scheduler = schedulerFactoryBean.getScheduler();  
        scheduler.start();  
        return scheduler;  
    } 
}

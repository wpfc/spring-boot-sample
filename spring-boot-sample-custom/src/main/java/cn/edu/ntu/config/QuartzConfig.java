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
	 * ע��scheduler������
	 */
	@Bean
	public SchedulerFactoryBean createSchedulerFactoryBean(){
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		//�����Զ����jobFactory��ʹjobʵ������ע��spring�����й���Ķ���
		//http://blog.csdn.net/u012572955/article/details/51656270
		//http://www.cnblogs.com/daxin/p/3608320.html
		schedulerFactoryBean.setJobFactory(jobFactory);
		return schedulerFactoryBean;
	}
	
	/**
	 * ����schedulerʵ��
	 */
	@Bean  
    public Scheduler scheduler(SchedulerFactoryBean schedulerFactoryBean) throws IOException, SchedulerException {  
        Scheduler scheduler = schedulerFactoryBean.getScheduler();  
        scheduler.start();  
        return scheduler;  
    } 
}

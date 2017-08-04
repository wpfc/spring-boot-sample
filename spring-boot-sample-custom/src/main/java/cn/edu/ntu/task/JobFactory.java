package cn.edu.ntu.task;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;


/**
 * 配置job交给spring管理
 * https://my.oschina.net/wangnian/blog/758054
 */
@Component
public class JobFactory extends AdaptableJobFactory{

	//这个对象Spring会帮我们自动注入进来,也属于Spring技术范畴.
	@Autowired
    private AutowireCapableBeanFactory capableBeanFactory;
	
	@Override
	protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
		Object jobInstance = super.createJobInstance(bundle);
		//将job的实例交给spring管理
		capableBeanFactory.autowireBean(jobInstance);
		return jobInstance;
	}
	
}

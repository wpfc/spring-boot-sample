package cn.edu.ntu.task;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;


/**
 * ����job����spring����
 * https://my.oschina.net/wangnian/blog/758054
 */
@Component
public class JobFactory extends AdaptableJobFactory{

	//�������Spring��������Զ�ע�����,Ҳ����Spring��������.
	@Autowired
    private AutowireCapableBeanFactory capableBeanFactory;
	
	@Override
	protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
		Object jobInstance = super.createJobInstance(bundle);
		//��job��ʵ������spring����
		capableBeanFactory.autowireBean(jobInstance);
		return jobInstance;
	}
	
}

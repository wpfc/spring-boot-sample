package cn.edu.ntu.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class BeanUtils implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(BeanUtils.applicationContext == null) {
        	BeanUtils.applicationContext = applicationContext;
        }
        System.out.println("---------------------------------------------------------------------");

        System.out.println("---------------------------------------------------------------------");

        System.out.println("---------------me.shijunjie.util.SpringUtil------------------------------------------------------");

        System.out.println("========ApplicationContext配置成功,在普通类可以通过调用SpringUtils.getAppContext()获取applicationContext对象,applicationContext="+BeanUtils.applicationContext+"========");

        System.out.println("---------------------------------------------------------------------");
    }

	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName){
		return (T) applicationContext.getBean(beanName);
	}
}

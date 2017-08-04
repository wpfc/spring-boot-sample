package cn.edu.ntu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;

import cn.edu.ntu.task.TaskExecutor;
import cn.edu.ntu.utils.BeanUtils;
import cn.edu.ntu.utils.JobUtils;

@SpringBootApplication
//�Զ������ã�ɨ��mapper�Ľӿ��Լ�ʵ�ֵ�xml�ļ����ڵİ�
@MapperScan("cn.edu.ntu.mapper")
public class Application implements CommandLineRunner{
	
	public static void main(String[] args){
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		JobUtils obj = BeanUtils.getBean("jobUtils");
		obj.addJob(new TaskExecutor(), "0/20 * * * * ?");
		
		test();
	}
	
	@Scheduled(fixedRate=10000)
	public void test(){
		System.out.println("����������");
	}
}

package cn.edu.ntu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//�Զ������ã�ɨ��mapper�Ľӿ��Լ�ʵ�ֵ�xml�ļ����ڵİ�
@MapperScan("cn.edu.ntu.mapper")
public class Application {
	
	public static void main(String[] args){
		SpringApplication.run(Application.class, args);
	}
	
}

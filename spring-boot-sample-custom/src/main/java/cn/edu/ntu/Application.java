package cn.edu.ntu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//自定义配置：扫描mapper的接口以及实现的xml文件所在的包
@MapperScan("cn.edu.ntu.mapper")
public class Application {
	
	public static void main(String[] args){
		SpringApplication.run(Application.class, args);
	}
	
}

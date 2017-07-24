package cn.edu.ntu.mapper;

import org.apache.ibatis.annotations.Mapper;

import cn.edu.ntu.entity.User;

//官方配置通过@Mapper注解进行扫描
//自定义可以通过MapperScan("basePackageName")进行扫描
@Mapper
public interface UserMapper {

	User getUserById(Long userId);
	
}

package cn.edu.ntu.mapper;

import org.apache.ibatis.annotations.Mapper;

import cn.edu.ntu.entity.User;

//�ٷ�����ͨ��@Mapperע�����ɨ��
//�Զ������ͨ��MapperScan("basePackageName")����ɨ��
@Mapper
public interface UserMapper {

	User getUserById(Long userId);
	
}

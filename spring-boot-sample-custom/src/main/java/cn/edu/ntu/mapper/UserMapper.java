package cn.edu.ntu.mapper;

import cn.edu.ntu.entity.User;

public interface UserMapper {

	User getUserById(Long userId);
	
	void insertUser(User user);
	
	void updateUserById(User user);
}

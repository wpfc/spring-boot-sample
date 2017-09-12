package cn.edu.ntu.service;

import cn.edu.ntu.entity.User;

public interface UserService {

	User getUserById(Long userId);
	
	User getUserByName(String userName);
	
	void testTransactional();
}

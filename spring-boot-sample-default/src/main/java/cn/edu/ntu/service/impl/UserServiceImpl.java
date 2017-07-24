package cn.edu.ntu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.ntu.entity.User;
import cn.edu.ntu.mapper.UserMapper;
import cn.edu.ntu.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User getUserById(Long userId) {
		return userMapper.getUserById(userId);
	}

}

package cn.edu.ntu.dubbo.api;

import cn.edu.ntu.model.User;

public interface UserService {

	void reformUserContent(User user, String content);
	
}

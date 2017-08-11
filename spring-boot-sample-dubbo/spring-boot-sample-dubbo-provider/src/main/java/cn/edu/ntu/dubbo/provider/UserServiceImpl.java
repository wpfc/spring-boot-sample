package cn.edu.ntu.dubbo.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.edu.ntu.dubbo.api.UserService;


@Service("userService")
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public void reformUserContent(String content) {
		logger.info("say " + content + " to you");
	}

}

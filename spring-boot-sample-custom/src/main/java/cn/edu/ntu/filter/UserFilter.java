package cn.edu.ntu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(UserFilter.class);
	
	private static final String USER_INFO = "userInfo";
	
	private static final String DEFAULT_USER_INFO = "wpfc";
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//将用户信息放到日志中
		MDC.put(USER_INFO, DEFAULT_USER_INFO);
		
		chain.doFilter(request, response);
		
		//清除用户信息
		clearUserInfo();
	}

	private void clearUserInfo() {
		MDC.remove(USER_INFO);
	}

	@Override
	public void destroy() {

	}

}

package cn.edu.ntu.interceptor;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Intercepts({
    @Signature(method = "query", type = Executor.class, args = {
           MappedStatement.class, Object.class, RowBounds.class,
           ResultHandler.class })})
public class MybatisInterceptor implements Interceptor {

	private static final Logger logger = LoggerFactory.getLogger(MybatisInterceptor.class);
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		logger.info(invocation.getMethod().getName());
		Object[] args = invocation.getArgs();
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
	}

}

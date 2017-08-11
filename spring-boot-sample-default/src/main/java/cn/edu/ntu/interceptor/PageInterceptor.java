package cn.edu.ntu.interceptor;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Intercepts({
    @Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class, Integer.class}) })
public class PageInterceptor implements Interceptor {

	private static final Logger logger = LoggerFactory.getLogger(MybatisInterceptor.class);
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
		MetaObject metaStatementHandler = SystemMetaObject.forObject(handler);  
		MappedStatement mappedStatement=(MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
		String queryName = mappedStatement.getId();
		BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");  
		// 分页参数作为参数对象parameterObject的一个属性  
		String sql = boundSql.getSql();
		logger.info(boundSql.getParameterObject().getClass().getName());
		if(queryName.endsWith("ByPage")){
			//TODO 处理sql
		}
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

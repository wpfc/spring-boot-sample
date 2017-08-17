package cn.edu.ntu.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.edu.ntu.shiro.MyRealm;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;

@Configuration
public class ShiroConfig {

	/**
	 * 来源 ： http://www.jianshu.com/p/672abf94a857?winzoom=1
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     *
     * Filter Chain定义说明 1、一个URL可以配置多个Filter，使用逗号分隔 2、当设置多个过滤器时，全部验证通过，才视为通过
     * 3、部分过滤器可指定参数，如perms，roles
     *
     */
	//@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		
		// 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        // 未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/404");
		
		//拦截器 {anon=anon, authc=authc, authcBasic=authcBasic, logout=logout, 
        //noSessionCreation=noSessionCreation, perms=perms, port=port, rest=rest, 
        //roles=roles, ssl=ssl, user=user}
        Map<String, String> filterChainDefinitionMap = new HashMap<String, String>();
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/random", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        
        filterChainDefinitionMap.put("/getUserInfoById", "perms[admin:edit]");
        
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        
		return shiroFilterFactoryBean;
	}
	
	/**
	 * 创建安全管理器
	 * @param realm
	 * @return
	 */
	@Bean
	public SecurityManager customSecurityManager(Realm realm){
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(realm);
		return securityManager;
	}
	
	/*@Bean
	public Realm customRealm(MyRealm myRealm){
		Realm realm = new MyRealm();
		return  realm;
	}*/
	
}

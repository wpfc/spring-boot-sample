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
	 * ��Դ �� http://www.jianshu.com/p/672abf94a857?winzoom=1
     * ShiroFilterFactoryBean ����������Դ�ļ����⡣
     * ע�⣺����һ��ShiroFilterFactoryBean�����ǻ򱨴�ģ���Ϊ��
     * ��ʼ��ShiroFilterFactoryBean��ʱ����Ҫע�룺SecurityManager
     *
     * Filter Chain����˵�� 1��һ��URL�������ö��Filter��ʹ�ö��ŷָ� 2�������ö��������ʱ��ȫ����֤ͨ��������Ϊͨ��
     * 3�����ֹ�������ָ����������perms��roles
     *
     */
	//@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		
		// �������� SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // ���������Ĭ�ϻ��Զ�Ѱ��Web���̸�Ŀ¼�µ�"/login.jsp"ҳ��
        shiroFilterFactoryBean.setLoginUrl("/login");
        // ��¼�ɹ���Ҫ��ת������
        shiroFilterFactoryBean.setSuccessUrl("/index");
        // δ��Ȩ����;
        shiroFilterFactoryBean.setUnauthorizedUrl("/404");
		
		//������ {anon=anon, authc=authc, authcBasic=authcBasic, logout=logout, 
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
	 * ������ȫ������
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

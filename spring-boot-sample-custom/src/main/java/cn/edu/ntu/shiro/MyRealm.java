package cn.edu.ntu.shiro;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import cn.edu.ntu.entity.User;

@Component
public class MyRealm extends AuthorizingRealm  {

	/**
	 * 每一次URL请求对应一个线程
	 */
	
	/**
	 * 权限设置，若无权限则可直接返回null
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		SimpleAuthorizationInfo authorizationInfo = null;
    	//根据身份信息，获取权限信息  
    	//连接数据库。。。  
    	//模拟从数据库获取到数据  
    	List<String> permissionList=new ArrayList<String>();  
    	permissionList.add("admin:edit");//用户的创建权限  
    	permissionList.add("items:add");//商品添加权限  
    	// and so on...  
    	//查到权限数据，返回授权信息（把上面查询到的数据填充）  
    	authorizationInfo = new SimpleAuthorizationInfo();  
    	//将上面查询到的授权信息进行填充  
    	authorizationInfo.addStringPermissions(permissionList);  
        return authorizationInfo; 
	}

	/**
	 * 登录身份验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken userInfo = (UsernamePasswordToken) token;
		String username = (String) token.getPrincipal();
		if(!"admin".equals(username)){
			throw new AccountException("帐号或密码不正确！");
		}
		User user = new User(1L, "admin");
		System.out.println(getName());
		//将用户的信息放到Subject中
		return new SimpleAuthenticationInfo(user, userInfo.getPassword(), getName());
	}

}

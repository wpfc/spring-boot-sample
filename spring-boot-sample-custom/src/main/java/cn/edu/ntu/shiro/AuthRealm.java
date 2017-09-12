package cn.edu.ntu.shiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.ntu.entity.User;
import cn.edu.ntu.service.UserService;

public class AuthRealm extends AuthorizingRealm {

	/**
	 * 每一次URL请求对应一个线程
	 */
	
	@Autowired
	private UserService userService;
	
	//授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		// permissions
		Set<String> permissions = new HashSet<String>();
		permissions.add("user:add,edit");
		permissions.add("role:*");
		info.setStringPermissions(permissions);
		
		// role
		Set<String> roleSet = new HashSet<String>();
		roleSet.add("admin");
		info.setRoles(roleSet);
		
		return info;
	}

	//认证.登录
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken userInfo = (UsernamePasswordToken) token;
		String userName = (String) userInfo.getPrincipal();
		User user = userService.getUserByName(userName);
		if(user == null){
			throw new UnknownAccountException("用户不存在！");
		}
		return new SimpleAuthenticationInfo(user, userInfo.getCredentials(), getName());
	}

}

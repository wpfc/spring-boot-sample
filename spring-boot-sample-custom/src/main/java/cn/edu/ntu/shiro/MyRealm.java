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
	 * ÿһ��URL�����Ӧһ���߳�
	 */
	
	/**
	 * Ȩ�����ã�����Ȩ�����ֱ�ӷ���null
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		SimpleAuthorizationInfo authorizationInfo = null;
    	//���������Ϣ����ȡȨ����Ϣ  
    	//�������ݿ⡣����  
    	//ģ������ݿ��ȡ������  
    	List<String> permissionList=new ArrayList<String>();  
    	permissionList.add("admin:edit");//�û��Ĵ���Ȩ��  
    	permissionList.add("items:add");//��Ʒ���Ȩ��  
    	// and so on...  
    	//�鵽Ȩ�����ݣ�������Ȩ��Ϣ���������ѯ����������䣩  
    	authorizationInfo = new SimpleAuthorizationInfo();  
    	//�������ѯ������Ȩ��Ϣ�������  
    	authorizationInfo.addStringPermissions(permissionList);  
        return authorizationInfo; 
	}

	/**
	 * ��¼�����֤
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken userInfo = (UsernamePasswordToken) token;
		String username = (String) token.getPrincipal();
		if(!"admin".equals(username)){
			throw new AccountException("�ʺŻ����벻��ȷ��");
		}
		User user = new User(1L, "admin");
		System.out.println(getName());
		//���û�����Ϣ�ŵ�Subject��
		return new SimpleAuthenticationInfo(user, userInfo.getPassword(), getName());
	}

}

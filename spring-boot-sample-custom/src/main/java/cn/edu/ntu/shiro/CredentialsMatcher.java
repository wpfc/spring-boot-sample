package cn.edu.ntu.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

public class CredentialsMatcher extends SimpleCredentialsMatcher {

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		//return super.doCredentialsMatch(token, info);
		UsernamePasswordToken utoken=(UsernamePasswordToken) token;
        //获得用户输入的密�?:(可以采用加盐(salt)的方式去�?�?)
        String inPassword = new String(utoken.getPassword());
        //获得数据库中的密�?
        String dbPassword = new String(toBytes(info.getCredentials()));
        //进行密码的比�? 
        return this.equals(inPassword, dbPassword);
	}
	
}

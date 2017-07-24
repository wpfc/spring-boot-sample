package cn.edu.ntu.config;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import com.alibaba.druid.support.http.StatViewServlet;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/druid/*", 
	initParams={
        @WebInitParam(name="allow",value="192.168.16.110,127.0.0.1"),// IP������ (û�����û���Ϊ�գ����������з���)
        @WebInitParam(name="deny",value="192.168.16.111"),// IP������ (���ڹ�ͬʱ��deny������allow)
        @WebInitParam(name="loginUsername",value="shanhy"),// �û���
        @WebInitParam(name="loginPassword",value="shanhypwd"),// ����
        @WebInitParam(name="resetEnable",value="false")// ����HTMLҳ���ϵġ�Reset All������
})
public class DruidStatViewServlet extends StatViewServlet {

}

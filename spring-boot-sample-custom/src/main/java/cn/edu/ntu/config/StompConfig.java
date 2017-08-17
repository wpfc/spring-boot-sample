package cn.edu.ntu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * STOMP
 * @author wpfc
 *
 */
@Configuration
@EnableWebSocketMessageBroker
public class StompConfig extends AbstractWebSocketMessageBrokerConfigurer {

	/**
     * ��"/hello"·��ע��ΪSTOMP�˵㣬���·���뷢�ͺͽ�����Ϣ��Ŀ��·��������ͬ������һ���˵㣬�ͻ����ڶ��Ļ򷢲���Ϣ��Ŀ�ĵ�ַǰ��Ҫ���Ӹö˵㣬
     * ���û���������url="/applicationName/hello"��STOMP server�������ӡ�֮����ת��������url��
     * PS���˵�����á����ͻ����ڶ��Ļ򷢲���Ϣ��Ŀ�ĵ�ַǰ��Ҫ���Ӹö˵㡣
     * @param stompEndpointRegistry
     */
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        //����ҳ�Ͽ���ͨ��"/applicationName/hello"���ͷ�������WebSocket����
        stompEndpointRegistry.addEndpoint("/hello").setAllowedOrigins("*").withSockJS();
    }

    /**
     * ������һ���򵥵���Ϣ������������أ�Ĭ������»��Զ�����һ���򵥵��ڴ���Ϣ��������������"/topic"Ϊǰ׺����Ϣ����������configureMessageBroker()������
     * ��Ϣ�����ᴦ��ǰ׺Ϊ"/topic"��"/queue"����Ϣ��
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
         //Ӧ�ó�����/appΪǰ׺������Ŀ�ĵ���/topic��/queueΪǰ׺
        registry.enableSimpleBroker("/topic", "/user");
        registry.setApplicationDestinationPrefixes("/app");
        registry.setUserDestinationPrefix("/user");
    }
	
}

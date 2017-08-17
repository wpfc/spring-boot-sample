package cn.edu.ntu.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.ntu.entity.Greeting;

@RestController  
public class GreetingController {  
  
	/**
	 * ���е����Ͷ�Ҫͨ��simpMessageSendingOperations
	 * convertAndSend   Ⱥ��
	 * convertAndSendToUser   ����
	 */
    @Autowired  
    private SimpMessageSendingOperations simpMessageSendingOperations;  
  
    /** 
     * 
     * ��ʾ����˿��Խ��տͻ���ͨ�����⡰/app/hello�����͹�������Ϣ��stompClient����
     * �ͻ�����Ҫ������"/topic/hello"�ϼ��������շ���˷��ص���Ϣ 
     * @param topic 
     * @param headers 
     * @return 
     */  
    @MessageMapping("/hello") //"/hello"ΪWebSocketConfig����registerStompEndpoints()�������õ�  
    @SendTo("/topic/greetings")  
    public Greeting greeting(@Header("atytopic") String topic, @Headers Map<String, Object> headers) {  
        System.out.println("connected successfully....");  
        System.out.println(topic);  
        System.out.println(headers);  
        return new Greeting("hello : " + topic);
    }  
  
    /** 
     * �����õ���@SendToUser������Ƿ��͸���һ�ͻ��˵ı�־�������У� 
     * �ͻ��˽���һ��һ��Ϣ������Ӧ���ǡ�/user/�� + �û�Id + ��/message�� ,������û�id������һ����ͨ���ַ�����ֻҪÿ���û��˶�ʹ���Լ���id���ҷ����֪��ÿ���û���id���С� 
     * @return 
     */  
    @MessageMapping("/message")  
    @SendToUser("/message")  
    public Greeting handleSubscribe() {  
        System.out.println("this is the @SubscribeMapping('/marco')");  
        return new Greeting("I am a msg from SubscribeMapping('/macro').");  
    }  
  
    /** 
     * ���Զ�ָ���û�������Ϣ���� 
     * @return 
     */  
    @RequestMapping(path = "/send", method = RequestMethod.GET)  
    public Greeting send() {  
        simpMessageSendingOperations.convertAndSendToUser("1", "/message", new Greeting("I am a msg from SubscribeMapping('/macro1')."));  
        return new Greeting("I am a msg from SubscribeMapping('/macro').");  
    }  
  
} 

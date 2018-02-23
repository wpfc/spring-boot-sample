package cn.edu.ntu.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.junit.Test;

public class ActiveMQTest {

	@Test
	public void testProducerOfQueue(){
		try {
			//创建一个工厂类
			ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://120.77.60.6:61616");
			//创建一个连接
			Connection connection = factory.createConnection();
			connection.start();
			//创建一次会话   参数1：是否开启分布式事务（true的话参数2则无效）
			Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
			//创建一个目的地  Destination
			Queue queue = session.createQueue("firstQueue");
			//创建消息生产者
			MessageProducer producer = session.createProducer(queue);
			//设置消息存活时间（ms）
			producer.setTimeToLive(30000);
			
			//创建一个发送的信息
			TextMessage message = new ActiveMQTextMessage();
			message.setText("hello avtivemq30s");
			//发送消息
			producer.send(message);
			
			//关闭资源
			producer.close();
			session.close();
			connection.close();
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCustomOfQueue(){
		try {
			//创建一个工厂类
			ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://120.77.60.6:61616");
			//创建一个连接
			Connection connection = factory.createConnection();
			connection.start();
			//创建一次会话   参数1：是否开启分布式事务（true的话参数2则无效）
			Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
			//创建一个目的地  Destination
			Destination queue = session.createQueue("firstQueue");
			//创建一个消费者
			MessageConsumer consumer = session.createConsumer(queue);
			while (true) {
                TextMessage textMessage = (TextMessage) consumer.receive(1000);
                if(textMessage != null){
                    System.out.println("收到的消息:" + textMessage.getText());
                }else {
                    break;
                }
            }
			
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
}

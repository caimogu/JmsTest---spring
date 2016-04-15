package test;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;

import pojo.Customer;

public class ConsumerTest {

	@Test
	public void subTest1() throws Exception{
		ConnectionFactory factory = new ActiveMQConnectionFactory(
				"failover://tcp://127.0.0.1:61616");

		Connection con = factory.createConnection();
		con.start();
		Session sen = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Topic topic=sen.createTopic("johnTopic");
		MessageConsumer consumer=sen.createConsumer(topic);
		TextMessage msg=(TextMessage)consumer.receive();
		System.out.println(msg.getText());
		con.stop();

		con.close();
	}
	
	@Test
	public void synTest() throws Exception {
		ConnectionFactory factory = new ActiveMQConnectionFactory(
				"failover://tcp://127.0.0.1:61616");

		Connection con = factory.createConnection();
		con.start();

		Session sen = con.createSession(false, Session.CLIENT_ACKNOWLEDGE);
		Queue queue = sen.createQueue("john01");
		// Queue queue=new ActiveMQQueue("john01");

		MessageConsumer consumer = sen.createConsumer(queue);
		TextMessage msg1 = (TextMessage) consumer.receive();
		System.out.println(msg1.getText());
		TextMessage msg2 = (TextMessage) consumer.receive();
		System.out.println(msg2.getText());
		msg2.acknowledge();
		
		con.stop();

		con.close();
	}
	
	@Test
	public void synTest1() throws Exception {
		ConnectionFactory factory = new ActiveMQConnectionFactory(
				"failover://tcp://127.0.0.1:61616");

		Connection con = factory.createConnection();
		con.start();

		Session sen = con.createSession(false, Session.CLIENT_ACKNOWLEDGE);
		Queue queue = sen.createQueue("john01");
		// Queue queue=new ActiveMQQueue("john01");

		MessageConsumer consumer = sen.createConsumer(queue);
		ObjectMessage msg1 = (ObjectMessage) consumer.receive();
		Customer c=(Customer)msg1.getObject();
		System.out.println(c.getCname());
		
		msg1.acknowledge();
		
		con.stop();

		con.close();
	}
	@Test
	public void asynTest() throws Exception {
		ConnectionFactory factory = new ActiveMQConnectionFactory(
				"failover://tcp://127.0.0.1:61616");

		Connection con = factory.createConnection();
		con.start();

		
		
		
		Session sen = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue = sen.createQueue("john01");
		// Queue queue=new ActiveMQQueue("john01");

		MessageConsumer consumer = sen.createConsumer(queue);
		consumer.setMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(Message arg0) {
				TextMessage msg=(TextMessage)arg0;
				try {
					System.out.println(msg.getText());
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		System.out.println("main is running.......");
		Thread.sleep(15000);
		

	}

	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ActiveMQConnectionFactory(
				"failover://tcp://127.0.0.1:61616");

		Connection con = factory.createConnection();
		con.start();
		
		
		Session sen = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue = sen.createQueue("john01");
		// Queue queue=new ActiveMQQueue("john01");

		MessageConsumer consumer = sen.createConsumer(queue);
		consumer.setMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(Message arg0) {
				TextMessage msg=(TextMessage)arg0;
				try {
					System.out.println(msg.getText());
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		System.out.println("main is running.......");
	}

}

package test;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
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

public class ProducerTest {

	@Test
	public void topicProducerTest() throws Exception{
		ConnectionFactory factory = new ActiveMQConnectionFactory(
				"failover://tcp://127.0.0.1:61616");

		Connection con = factory.createConnection();
		con.start();
		Session sen = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Topic topic=sen.createTopic("johnTopic");
		MessageProducer producer = sen.createProducer(topic);
		TextMessage msg=sen.createTextMessage("is topic");
		producer.send(msg);
		con.stop();

		con.close();
	}

	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ActiveMQConnectionFactory(
				"failover://tcp://127.0.0.1:61616");

		Connection con = factory.createConnection();
		con.start();

		Session sen = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue = sen.createQueue("john01");
		// Queue queue=new ActiveMQQueue("john01");

		MessageProducer producer = sen.createProducer(queue);
		for (int i = 0; i < 10; i++) {

			Customer c = new Customer();
			c.setAge(i);
			c.setCname("john" + i);
			ObjectMessage msg = sen.createObjectMessage(c);
			producer.send(msg);
		}
		con.stop();

		con.close();

	}

}

package test;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Ct {

	public static void main(String[] args) throws Exception{
		ConnectionFactory factory = new ActiveMQConnectionFactory(
				"failover://tcp://127.0.0.1:61616");

		Connection con = factory.createConnection();
		con.start();

		Session sen = con.createSession(true, Session.AUTO_ACKNOWLEDGE);
		Queue queue = sen.createQueue("john01");
		// Queue queue=new ActiveMQQueue("john01");

		MessageConsumer consumer = sen.createConsumer(queue);
		TextMessage msg = (TextMessage) consumer.receive();
		System.out.println(msg.getText()+".....ct");
		
		
		sen.commit();
		con.stop();

		con.close();

	}

}

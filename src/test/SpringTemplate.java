package test;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class SpringTemplate {
	
	

	@Test
	public void recive() throws Exception{
		// 
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		JmsTemplate template = (JmsTemplate) context.getBean("jmsTemplate");
		Queue que = (Queue) context.getBean("queueDestination");
		TextMessage msg=(TextMessage)template.receive(que);
		System.out.println(msg.getText());
	}

	public static void main(String[] args) {
		// 
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		JmsTemplate template = (JmsTemplate) context.getBean("jmsTemplate");
		Queue que = (Queue) context.getBean("queueDestination");
		template.send(que, new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage msg = (TextMessage) session
						.createTextMessage("John.....");
				return msg;
			}
		});
		template.send(que, new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage msg = (TextMessage) session
						.createTextMessage("John.....");
				return msg;
			}
		});
		template.send(que, new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage msg = (TextMessage) session
						.createTextMessage("John.....");
				return msg;
			}
		});

	}

}

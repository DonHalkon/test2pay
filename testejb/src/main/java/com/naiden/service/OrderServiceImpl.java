package com.naiden.service;

import com.naiden.model.EOrder;
import com.naiden.repository.EOrderRepository;
import org.jboss.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.List;


@Stateless
public class OrderServiceImpl implements OrderService {

	private final static Logger log = Logger.getLogger(OrderServiceImpl.class.getName());

	@Inject
	private EOrderRepository orderRepository;

	@Resource(mappedName = "java:/ConnectionFactory")
	private ConnectionFactory factory;

	@Resource(mappedName = "java:/jms/txrequest")
	private Queue requestQueue;

	@Resource(mappedName = "java:/jms/txresponse")
	private Queue responseQueue;

	@Override
	public void addOrder(EOrder order) throws JMSException {
		try (Connection con = factory.createConnection()) {
			Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer producer = session.createProducer(requestQueue);
			ObjectMessage message = session.createObjectMessage(order);
			message.setJMSReplyTo(responseQueue);
			producer.send(message);
			log.info("Message '" + message.getJMSMessageID() + "' sent!");
			MessageConsumer replyConsumer = session.createConsumer(responseQueue, "fromMessageID = " + "'" + message.getJMSMessageID() + "'");

			con.start();
			// ToDo: inject timeout value
			Message msg = replyConsumer.receive(5000L);
			if (msg != null) {
				log.info("Message '" + message.getJMSMessageID() + "' received!");
				if (msg instanceof TextMessage) {
					TextMessage replyMessage = (TextMessage) msg;
					if (message.getJMSMessageID().equals(replyMessage.getText())) {
						log.info("MESSAGE VERIFICATION PASSED!");
					} else {
						log.info("WRONG replyMessageID: " + message.getJMSMessageID() + " and " + replyMessage.getText());
					}
				} else {
					log.info("MESSAGE VERIFICATION FAILED! IT IS NOT A TEXT MESSAGE: " + message);
				}
			} else {
				log.info("MESSAGE RECEIVING TIMED OUT!");
			}
		}
	}

	@Override
	public List<EOrder> findAll() {
		return orderRepository.findAll();
	}


}

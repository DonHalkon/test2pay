package com.naiden.messaging;

import com.naiden.model.EOrder;
import com.naiden.repository.EOrderRepository;
import org.jboss.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;


@MessageDriven(
		activationConfig = {
				@ActivationConfigProperty(propertyName = "destination", propertyValue = "jms/txrequest"),
				@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
				@ActivationConfigProperty(propertyName = "maxSession", propertyValue = "1")})
public class Processor implements MessageListener {

	private final static Logger log = Logger.getLogger(Processor.class.getName());

	@Inject
	private EOrderRepository orderRepository;

	@Resource(mappedName = "java:/ConnectionFactory")
	private ConnectionFactory factory;

	@Resource
	private MessageDrivenContext mdctx;

	@Override
	public void onMessage(Message message) {

		try (Connection connection = factory.createConnection()) {
			log.info("Message '" + message.getJMSMessageID() + "' received!");
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			connection.start();

			if (message instanceof ObjectMessage && message.isBodyAssignableTo(EOrder.class)) {
				ObjectMessage objectMessage = (ObjectMessage) message;
				EOrder eOrder = (EOrder) objectMessage.getObject();
				orderRepository.create(eOrder);  // ToDo: handle exception
				log.info(eOrder + " saved!");

				Destination replyDestination = message.getJMSReplyTo();
				TextMessage replyMessage = session.createTextMessage();
				replyMessage.setText(message.getJMSMessageID());
				replyMessage.setJMSCorrelationID(message.getJMSMessageID());
				replyMessage.setStringProperty("fromMessageID", message.getJMSMessageID());
				replyMessage.setJMSDestination(message.getJMSReplyTo());
				MessageProducer replyProducer = session.createProducer(replyDestination);

				replyProducer.send(replyMessage);
		        log.info("Message '" + replyMessage.getJMSMessageID() + "' sent!");
			} else {
		        log.info("Message '" + message.getJMSMessageID() + "' has wrong type!");
				mdctx.setRollbackOnly();
			}
		} catch (JMSException e) {
			mdctx.setRollbackOnly();
		}
	}
}

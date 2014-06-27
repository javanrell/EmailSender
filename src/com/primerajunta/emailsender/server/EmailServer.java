package com.primerajunta.emailsender.server;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;

import com.primerajunta.emailsender.configuration.SMTPConfiguration;

public class EmailServer {
	
	private Session session;

	public Session getSession() {
		return session;
	}

	public EmailServer() {
		session = Session.getDefaultInstance(SMTPConfiguration.getSMTPConfiguration());
		session.setDebug(true);
	}
	
	public void sendEmail(Message message) {
		try {
			Transport.send(message,message.getAllRecipients());
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}

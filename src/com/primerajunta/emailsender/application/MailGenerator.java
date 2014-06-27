package com.primerajunta.emailsender.application;

import java.util.HashMap;
import java.util.Map;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.primerajunta.emailsender.server.EmailServer;

public class MailGenerator {

	public static void main(String[] args) {
		Map<String, String> users = new HashMap<String, String>();
		users.put("javanrell@gmail.com", "Juan");
		users.put("freddyenrich@hotmail.com", "Freddy");
		EmailServer emailServer = new EmailServer();
		for (String email : users.keySet()) {
			MimeMessage message = new MimeMessage(emailServer.getSession());
			try {
				message.setFrom(new InternetAddress("juan@vanrell.com"));
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));	
				message.setSubject("Test");
				message.setText("First message on JavaMail");
			} catch (AddressException e) {
				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			emailServer.sendEmail(message);
		}			
	}

}

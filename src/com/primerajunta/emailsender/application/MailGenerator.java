package com.primerajunta.emailsender.application;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.primerajunta.emailsender.server.EmailServer;
import com.primerajunta.emailsender.tracking.GoogleAnalyticsTrackingGenerator;

public class MailGenerator {

	private GoogleAnalyticsTrackingGenerator trackingGenerator;
	
	public MailGenerator() {
		trackingGenerator = new GoogleAnalyticsTrackingGenerator("UA-52089709-1");
	}
	
	public Message createMessage(User user, String campaignName, EmailServer emailServer) {
		MimeMessage message = new MimeMessage(emailServer.getSession());
		try {
			message.setFrom(new InternetAddress("juan@vanrell.com"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));	
			message.setSubject("Test");
			message.setText(getBody(user, campaignName), "ISO-8859-1",	"html");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return message;		
	}
	
	private String getBody(User user, String campaignName) {
		StringBuilder body = new StringBuilder();
		body.append("<html>");
		body.append("<header>");
		body.append("</header>");
		body.append("<body>");
		body.append("First message on JavaMail");
		body.append("<img src=\"" + trackingGenerator.getOpenEmailUrl(user, campaignName) + "\"/>");
		body.append("</body>");
		body.append("</html>");
		return body.toString();
	}

}

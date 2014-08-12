package com.primerajunta.emailsender.application;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.primerajunta.emailsender.model.User;
import com.primerajunta.emailsender.server.EmailServer;
import com.primerajunta.emailsender.tracking.GoogleAnalyticsTrackingGenerator;

@Component
public class MailGenerator {
	
	@Autowired
	@Qualifier("domain")
	private String domain; 

	private GoogleAnalyticsTrackingGenerator trackingGenerator;
	
	public MailGenerator() {
		trackingGenerator = new GoogleAnalyticsTrackingGenerator("UA-52089709-1");
	}
	
	public Message createMessage(User user, String campaignName, EmailServer emailServer) {
		MimeMessage message = new MimeMessage(emailServer.getSession());
		try {
			message.addHeader("List-Unsubscribe", "<mailto:unsubscribe@" + domain + ">, <http://www." + domain + "/user/unsubscribe/?sid=" + user.getId() + ">");
			message.setFrom(new InternetAddress("contacto@" + domain));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));	
			message.setSubject("Test");
			message.setText(getBody(user, campaignName), "ISO-8859-1",	"html");
			System.out.println("Domain: " + domain); 
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

	public void setDomain(String domain) {
		this.domain = domain;
	}

}

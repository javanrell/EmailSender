package com.primerajunta.emailsender.application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.primerajunta.emailsender.model.User;
import com.primerajunta.emailsender.server.EmailServer;

public class ApplicationLauncher {
	
	private static final Logger logger = LoggerFactory.getLogger(ApplicationLauncher.class);

	public static void main(String[] args) throws FileNotFoundException, IOException {
		ApplicationContext context = 
		          new AnnotationConfigApplicationContext("com.primerajunta.emailsender");
		
		List<User> users = new ArrayList<User>();
		users.add(new User("Juan", "contactolavidanatural@gmail.com"));
		//users.add(new User("Freddy", "freddyenrich@hotmail.com"));
		
		
		//String domain = (String) p.get("domain");
		EmailServer emailServer = new EmailServer();
		MailGenerator generator = new MailGenerator();
		//generator.setDomain(domain);
		for (User user : users) {
			Message message = generator.createMessage(user, "First campaing", emailServer);
			logger.debug("Email to: " + user.getEmail());
			//emailServer.sendEmail(message);
		}			
	}
	
}

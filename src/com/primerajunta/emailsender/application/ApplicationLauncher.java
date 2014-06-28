package com.primerajunta.emailsender.application;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Message;

import com.primerajunta.emailsender.server.EmailServer;

public class ApplicationLauncher {

	public static void main(String[] args) {
		List<User> users = new ArrayList<User>();
		users.add(new User("Juan", "javanrell@gmail.com", 1));
		//users.add(new User("Freddy", "freddyenrich@hotmail.com", 2));
		EmailServer emailServer = new EmailServer();
		MailGenerator generator = new MailGenerator();
		for (User user : users) {
			Message message = generator.createMessage(user, "First campaing", emailServer);
			emailServer.sendEmail(message);
		}			
	}
	
}

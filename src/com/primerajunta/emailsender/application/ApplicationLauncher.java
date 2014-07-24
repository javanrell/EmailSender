package com.primerajunta.emailsender.application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;

import com.primerajunta.emailsender.server.EmailServer;

public class ApplicationLauncher {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		List<User> users = new ArrayList<User>();
		users.add(new User("Juan", "contactolavidanatural@gmail.com", 1));
		//users.add(new User("Freddy", "freddyenrich@hotmail.com", 2));
		
		Properties p = new Properties();
		p.load(new FileInputStream("./src/config/emailSender.properties"));
		String domain = (String) p.get("domain");
		EmailServer emailServer = new EmailServer();
		MailGenerator generator = new MailGenerator();
		generator.setDomain(domain);
		for (User user : users) {
			Message message = generator.createMessage(user, "First campaing", emailServer);
			emailServer.sendEmail(message);
		}			
	}
	
}

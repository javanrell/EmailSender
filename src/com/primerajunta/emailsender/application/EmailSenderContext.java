package com.primerajunta.emailsender.application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class EmailSenderContext {

	private Properties properties;
	
	@Bean(name="domain") 
	public String getDomain() {
		return (String) properties.get("domain");
	}
	
	public EmailSenderContext() throws FileNotFoundException, IOException{
		properties = new Properties();
		properties.load(new FileInputStream("./src/config/emailSender.properties"));
		
	}
	
	public EmailSenderContext(Properties properties) {
		this.properties = properties;
	}

}

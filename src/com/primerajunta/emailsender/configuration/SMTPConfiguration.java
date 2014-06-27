package com.primerajunta.emailsender.configuration;

import java.security.GeneralSecurityException;
import java.util.Properties;

import com.sun.mail.util.MailSSLSocketFactory;

public class SMTPConfiguration {

	public static Properties getSMTPConfiguration() {
		Properties properties = new Properties();
		// Nombre del host de correo, es smtp.gmail.com
		properties.setProperty("mail.smtp.host", "localhost");
		// TLS si está disponible
		properties.setProperty("mail.smtp.starttls.enable", "true");
		// Puerto postfix
		properties.setProperty("mail.smtp.port","25");

		// Certificado
		MailSSLSocketFactory socketFactory;
		try {
			socketFactory = new MailSSLSocketFactory();
			socketFactory.setTrustAllHosts(true);
			properties.put("mail.smtp.ssl.socketFactory", socketFactory);
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
		
		return properties;
	}
}

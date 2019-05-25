package nl.hu.sie.bep.friendspammer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EmailSender {


	private EmailSender() {
		throw new IllegalStateException("Utility class");
	}

	public static void sendEmail(String subject, String to, String messageBody, boolean asHtml) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.mailtrap.io");
		props.put("mail.smtp.port", "2525");
		props.put("mail.smtp.auth", "true");

		InputStream input = null;
		try {
			input = new FileInputStream("src/config/config.properties");
		} catch (FileNotFoundException e) {
			Logger logger = LoggerFactory.getLogger(MongoSaver.class);
			logger.info("Error", e);
		}
		try {
			props.load(input);
		} catch (IOException e) {
			Logger logger = LoggerFactory.getLogger(MongoSaver.class);
			logger.info("Error", e);
		}
		final String username = props.getProperty("username");
		final String password = props.getProperty("password");

		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				  });
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("spammer@spammer.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(to));
			message.setSubject(subject);
			
			if (asHtml) {
					message.setContent(messageBody, "text/html; charset=utf-8");
			} else {
				message.setText(messageBody);	
			}
			Transport.send(message);

			MongoSaver.saveEmail(to, "spammer@spamer.com", subject, messageBody, asHtml);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public static void sendEmail(String subject, String[] toList, String messageBody, boolean asHtml) {

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.mailtrap.io");
		props.put("mail.smtp.port", "2525");
		props.put("mail.smtp.auth", "true");
		
		final String username = "YOUR MAIL USERNAME";
		final String psw = "YOUR MAIL PASSWORD";

		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, psw);
					}
				  });
		try {

			for (int index = 0; index < toList.length; index++) {
			
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("spammer@spammer.com"));
				message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(toList[index]));
				message.setSubject(subject);
				
				if (asHtml) {
						message.setContent(messageBody, "text/html; charset=utf-8");
				} else {
					message.setText(messageBody);	
				}
				Transport.send(message);

				Logger logger = LoggerFactory.getLogger(MongoSaver.class);
				logger.info("EmailSender: Done");
			}

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
}

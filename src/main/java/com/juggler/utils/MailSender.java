package com.juggler.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {

	Properties mailServerProperties;
	Session getMailSession;
	MimeMessage generateMailMessage;

	public void sendMail(String toEmailId,String message) {
		try {
			generateAndSendEmail(toEmailId,message);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void generateAndSendEmail(String toEmailId,String message) throws AddressException, MessagingException {

		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
	/*	mailServerProperties.setProperty("mail.smtp.user", "tannudayan@gmail.com");
		mailServerProperties.setProperty("mail.smtp.password", "tn22cu2345")*/;

		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmailId));
		generateMailMessage.setSubject("Activation Mail From Juggler");
	
		generateMailMessage.setContent(message, "text/html");

		Transport transport = getMailSession.getTransport("smtp");

		// Enter your correct gmail UserID and Password
		// if you have 2FA enabled then provide App Specific Password
		transport.connect("smtp.gmail.com", "tannudayan@gmail.com", "tn22cu2345");
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		transport.close();
	}
}

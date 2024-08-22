package com.scube.mycognitiv.service;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EmailServiceImpl implements EmailService {
	
	@Override
	public void sendMail(String toAddress,String message) throws MessagingException {
	
		Message msg=sendMailer();		
		InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.setSentDate(new Date());
		msg.setText(message);
		Transport.send(msg);

	}
	
	
	private static Message sendMailer() throws MessagingException {
		Message msg=null;
		 int port=587;		 
		 String host="email-smtp.us-east-1.amazonaws.com";
		 String user="AKIA4CYVYLF4KZT3534F";
		 String pass="BF1LbYtRjrFTo8Z0XkZBcI/jfO3PIsb+KF11RrVjCQU/";
		 String fromAddress="support@mycognitiv.com";			
		 String subject="MyCognitiv.com: Skill Assessment Invitation";
		
		try {
			Properties properties=new Properties();
			// sets SMTP server properties
			properties.put("mail.smtp.host", host);
			properties.put("mail.smtp.port", port);
			properties.put("mail.smtp.auth", "true");
			properties.setProperty("mail.transport.protocol", "smtp");
			properties.put("mail.smtp.starttls.enable", "false");
			properties.setProperty("mail.debug", "false");
			properties.setProperty("mail.user",user);
			properties.setProperty("mail.password",pass);
			Authenticator auth = new Authenticator() {
	            public PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(user, pass);
	            }
	        };
			//Getting the Session Instance
			Session session=Session.getInstance(properties,auth);
			//Initializing Message Object
			 msg=new MimeMessage(session);
			//Setting Message Content
			msg.setFrom(new InternetAddress(fromAddress));			
			msg.setSubject(subject);

		}catch (Exception e) {
			
			//System.out.println(e);
		}
		return msg;
	}

}

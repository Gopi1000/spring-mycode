package com.scube.mycognitiv.service;

import javax.mail.MessagingException;

public interface EmailService {

	public void sendMail(String toAddress, String message) throws MessagingException;

}

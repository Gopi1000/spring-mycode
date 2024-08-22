package com.scube.techboot.utils;

import javax.mail.MessagingException;

import com.scube.techboot.model.EmailModel;

public interface SendEmailService {
	
	public String sendEmail(String toEmail,String subjects,String message,EmailModel model)throws MessagingException;

}

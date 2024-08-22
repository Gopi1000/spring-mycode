package com.scube.techboot.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.scube.techboot.model.EmailModel;


@Service("emailManager")
public class SendEmailServiceImpl implements SendEmailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private VelocityEngine velocityEngine;
	
	private static final Logger LOGGER = Logger.
			       getLogger(SendEmailServiceImpl.class);
	
	Map<String, Object> modelValue = null;

	@Override
	public String sendEmail(final String toEmail, final String subjects,
			   final String messageContent,final EmailModel model) throws MessagingException{
		// TODO Auto-generated method stub
		LOGGER.info("Confirmation email sent with out attachment:" + toEmail);
		
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage,
						true);			
				
				message.setTo(toEmail);
				message.setFrom(TechbootResourceBundle.getValue("From.emailAddress"));
				message.setSubject(subjects);
				message.setSentDate(new Date());
				
				if(null!=model.getEmailCC()){
					message.setCc(model.getEmailCC());
				}
				
				try{
					String text= getTemplate(messageContent,model);
					message.setText(text, true);
				}catch(Exception ie)
				{
					LOGGER.info("=======================reading image failed=====================================",ie.fillInStackTrace());				
				}
			}
		};
		try{
			mailSender.send(preparator);
		}catch (Exception e) {
			e.printStackTrace();
			return "failed";
		}
		return "success";
	}

    private String getTemplate(String bodyContent, EmailModel model) {
	// TODO Auto-generated method stub
    	String templateContent = null;
        modelValue = new HashMap<String, Object>();
        if(EmailCodes.COURSE_REGISTRATION.endsWith(bodyContent)){
        	
        	modelValue.put("firstName", model.getFirstname());
        	modelValue.put("email", model.getEmail());
        	modelValue.put("mobileNo", model.getPhoneno());
        	modelValue.put("courseName", model.getCoursetitle());
        	modelValue.put("professional", model.getProfessional());
        	templateContent = VelocityEngineUtils.mergeTemplateIntoString(
        			velocityEngine, "velocity/courseRegisters.vm", "UTF-8", modelValue);
        }
	  return templateContent;
    }
}

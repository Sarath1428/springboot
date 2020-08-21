package com.sarath.flightReservation.util;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

	@Value("${com.sarath.flightReservation.iteninary.email.body}")
	private  String EMAIL_TEXT ;
	@Value("${com.sarath.flightReservation.iteninary.email.subject}")
	private  String EMAIL_SUBJECT;
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtil.class);
	@Autowired
	private JavaMailSender sender;
	
	public void sendItinerary(String toAddress,String filepath) {
		LOGGER.info("inside sendItinerary");
		MimeMessage message = sender.createMimeMessage();
		
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(message,true);
			messageHelper.setTo(toAddress);
			messageHelper.setSubject(EMAIL_SUBJECT);
			messageHelper.setText(EMAIL_TEXT);
			messageHelper.addAttachment("Itinerary", new File(filepath));
			sender.send(message);
			
		} catch (MessagingException e) {
			LOGGER.error("Exception inside sendItinerary" + e);
		}
		
		
	}
}

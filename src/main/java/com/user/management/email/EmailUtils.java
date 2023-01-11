package com.user.management.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {

	@Autowired
	private JavaMailSender javaMailSender;

	public boolean sendMail(String to, String subject, String body) {
		boolean isSend = false;

		

		
		try 
		{
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
			
			helper.setTo(to);
			helper.setText(body, true);
			helper.setSubject(subject);
			
			javaMailSender.send(mimeMessage);
			isSend = true;

		} catch (MessagingException e) {

			e.printStackTrace();
		}

		return isSend;
	}
}

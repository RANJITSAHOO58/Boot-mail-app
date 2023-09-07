package com.nt.service;



import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
@Service("ImpMassageService")
public class ImpMassage implements IMassage {
	@Autowired
    private JavaMailSender sender;
	@Value("${spring.mail.username}")
	private String fromEmail;
	@Override
	public String sendmassage(String[] msg, String[] toEmails) throws Exception{
		//prepare massage
		String massage=Arrays.toString(msg)+"sending this mail ranjit";
		//send mail
		String status=sendMail(massage,toEmails);
		return massage+"-->"+status;
	}
	private String sendMail(String massage,String[] toEmails)throws Exception{
		//empty email massage
		MimeMessage message=sender.createMimeMessage();
		//create helper class object
		MimeMessageHelper helper= new MimeMessageHelper(message,true);
		helper.setFrom(fromEmail);
		helper.setCc(toEmails);
		helper.setSubject("open to know it");
		helper.setSentDate(new Date());
		helper.setText(massage);
		//add attachment to the email masage 
		//place logo.jpg file in src/main/resource folder
		helper.addAttachment("logo.jpg",new ClassPathResource("logo.jpg"));
		//send mail
		sender.send(message);
		return "mail sent";
	}

}

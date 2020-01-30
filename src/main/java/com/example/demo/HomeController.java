package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping; 

@Controller
public class HomeController {

	@Autowired
	MailCfg mailcfg;
	
	@RequestMapping("/")
	public String load() {
		
		return "index.jsp";
	}
		
	@RequestMapping("send")
	public String send(UserVO userVO) {					
		
		// create mail sender
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(this.mailcfg.getHost());
		mailSender.setPort(this.mailcfg.getPort());
		mailSender.setUsername(this.mailcfg.getUsername());
		mailSender.setPassword(this.mailcfg.getPassword());
		
		//create an mail instance 
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom(mailcfg.getUsername());		
		mailMessage.setTo(userVO.getEmail()); // pass array of string to send multiple recipient 				
		mailMessage.setSubject("testing "+userVO.getName());
		mailMessage.setText("hello, "+userVO.getName()+" "+userVO.getMessage());
		
		mailSender.send(mailMessage);
		
		return "redirect:/";
	}
	
}
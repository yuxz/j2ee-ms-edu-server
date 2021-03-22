package com.yxz.base.email.service;

import java.util.Map;

import javax.mail.MessagingException;

public interface MailService {

	  void sendSimpleTextMail(String to, String subject, String content);
	  
	  void sendHtmlMail(String to, String subject, String content) throws MessagingException;
	  
	  void sendAttachmentMail(String to, String subject, String content, String... fileArr) throws MessagingException;
	  
	  void sendImgMail(String to, String subject, String content, Map<String, String> imgMap)  throws MessagingException;
	  
	  void sendTemplateMail(String to, String subject, Map<String, Object> paramMap, String template)     throws MessagingException;
}

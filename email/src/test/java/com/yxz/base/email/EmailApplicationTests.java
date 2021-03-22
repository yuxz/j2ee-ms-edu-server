package com.yxz.base.email;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;

import com.yxz.base.email.service.MailService;

@SpringBootTest
class EmailApplicationTests {

	@Test
	void contextLoads() {
	}
	
	
	@Autowired
    private MailService mailService;
    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void sendSimpleTextMailTest() {
        String to = "yxz20180810@gmail.com ";
        String subject = "Springboot 发送简单文本邮件";
        String content = "<p>第一封 Springboot 简单文本邮件</p>";
        mailService.sendSimpleTextMail(to, subject, content);
    }
    
    
    @Test
    public void sendHtmlMailTest() throws MessagingException {
        String to ="yxz20180810@gmail.com ";
        String subject = "Springboot 发送 HTML 邮件";
        String content = "<h2>Hi~</h2><p>第一封 Springboot HTML 邮件</p>";
        mailService.sendHtmlMail(to, subject, content);
    }

    
    
    @Test
    public void sendAttachmentTest() throws MessagingException {
        String to = "yxz20180810@gmail.com ";
        String subject = "Springboot 发送 HTML 附件邮件";
        String content = "<h2>Hi~</h2><p>第一封 Springboot HTML 附件邮件</p>";
        String filePath = "pom.xml";
        mailService.sendAttachmentMail(to, subject, content, filePath, filePath);
    }

    
    @Test
    public void sendImgTest() throws MessagingException {
        String to = "yxz20180810@gmail.com ";
        String subject = "Springboot 发送 HTML 图片邮件";
        String content =
            "<h2>Hi~</h2><p>第一封 Springboot HTML 图片邮件</p><br/><img src=\"cid:img01\" /><img src=\"cid:img02\" />";
        String imgPath = "apple.png";
        Map<String, String> imgMap = new HashMap<>();
        imgMap.put("img01", imgPath);
        imgMap.put("img02", imgPath);
        mailService.sendImgMail(to, subject, content, imgMap);
    }
    
    
    @Test
    public void sendTemplateMailTest() throws MessagingException {
        String to ="yxz20180810@gmail.com ";
        String subject = "Springboot 发送 模版邮件";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("username", "Darcy");
        mailService.sendTemplateMail(to, subject, paramMap, "RegisterSuccess");
    }




}

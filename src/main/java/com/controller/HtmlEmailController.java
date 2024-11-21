package com.controller;



import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HtmlEmailController {
 
    @Autowired
    public JavaMailSender emailSender;
 
    //Hòm thư mới
    @PostMapping("/sendnewemail")
    public String sendHtmlEmail(HttpSession session,@RequestParam("email")String email) throws MessagingException {
 
        MimeMessage message = emailSender.createMimeMessage();
 
        boolean multipart = true;
         
        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");
       
        String htmlMsg = "<meta charset=\"UTF-8\">"
        		+"<h3>Chào mừng bạn đến với Ngọc Mai </h3>"
                +"<div>Xin chào bạn chúc bạn có một ngày vui vẻ, chúng tôi rất mong chờ bạn sẽ ghé qua cửa hàng<a href=\"http://localhost:8080/\"></a></div>";
        
        message.setContent(htmlMsg, "text/html;charset=utf-8");
        
        helper.setTo(email);
        helper.setSubject("Chào mừng đến với thế giới Xe đạp!");
         
     
        this.emailSender.send(message);
       
        return "redirect:/";
    }
    //Xác nhận tài khoản
    @PostMapping("/sendvalidateemail")
    public String sendvalidateEmail(HttpSession session,@RequestParam("email")String email) throws MessagingException {
 
        MimeMessage message = emailSender.createMimeMessage();
 
        boolean multipart = true;
         
        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");
       
        String htmlMsg = "<meta charset=\"UTF-8\">"
        		+"<h3>Chào mừng bạn đến với xe đạp Ngọc Mai </h3>"
                +"<div>Xin chào bạn chúc bạn có một ngày vui vẻ, chúng tôi rất mong chờ bạn sẽ ghé qua cửa hàng<a href=\"http://localhost:8080/\"></a></div>";
        
        message.setContent(htmlMsg, "text/html;charset=utf-8");
        
        helper.setTo(email);
        helper.setSubject("Chào mừng đến với thế giới giày Sneaker!");
         
     
        this.emailSender.send(message);
       
        return "redirect:/logout";
    }
 
}
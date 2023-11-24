package com.example.webtoeic.controller;


import com.example.webtoeic.model.EmailRequest;
import com.example.webtoeic.response.ResponseMessage;
import com.example.webtoeic.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;

@RestController
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;
    @PostMapping("/send-email")
    public Object sendEmail(@RequestBody EmailRequest emailRequest) {
        emailService.sendEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getBody());
        return new ResponseMessage("Email sent successfully!", "success", null);
    }

    @PostMapping("/send-html-email")
    public String sendHtmlEmail(@RequestBody EmailRequest emailRequest) {
        Context context = new Context();
        context.setVariable("message", emailRequest.getTo());
        context.setVariable("otpCode", emailService.generateOTP(emailRequest.getTo()));
        emailService.sendEmailWithHtmlTemplate(emailRequest.getTo(), emailRequest.getSubject(), "email-template", context);
        return "HTML email sent successfully!";
    }
}

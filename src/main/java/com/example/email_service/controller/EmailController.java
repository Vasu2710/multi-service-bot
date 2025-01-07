package com.example.email_service.controller;

import com.example.email_service.model.EmailRequest;
import com.example.email_service.model.EmailScheduleRequest;
import com.example.email_service.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public String sendEmail(@RequestBody EmailRequest emailRequest) {
        try {
            emailService.sendEmail(
                    emailRequest.getRecipients(),
                    emailRequest.getSubject(),
                    emailRequest.getBody()
            );
            return "Email sent successfully!";
        } catch (MessagingException e) {
            return "Failed to send email: " + e.getMessage();
        }
    }

    @PostMapping("/schedule")
    public String scheduleEmail(@RequestBody EmailScheduleRequest scheduleRequest) {
        emailService.scheduleEmail(
                scheduleRequest.getRecipients(),
                scheduleRequest.getSubject(),
                scheduleRequest.getBody(),
                scheduleRequest.getTimesPerDay(),
                scheduleRequest.getIntervalInMinutes()
        );
        return "Email scheduled successfully!";
    }
}

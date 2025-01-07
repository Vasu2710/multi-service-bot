package com.example.email_service.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail( List<String> recipients, String subject, String body) throws MessagingException {
        // Create a new MimeMessage
        MimeMessage message = javaMailSender.createMimeMessage();

        // Use MimeMessageHelper to add all necessary parts to the email
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

      //  helper.setFrom(from);  // Set the sender email address
        helper.setSubject(subject);  // Set the subject of the email
        helper.setText(body, true);  // Set the body content (HTML)

        // Add recipients
        for (String recipient : recipients) {
            helper.addTo(recipient);
        }

        // Send the email
        javaMailSender.send(message);
    }
}

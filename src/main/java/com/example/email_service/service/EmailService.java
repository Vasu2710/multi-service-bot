package com.example.email_service.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    public void scheduleEmail(List<String> recipients, String subject, String body, int timesPerDay, int intervalInMinutes) {
        // Create a scheduler for handling scheduling
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.initialize();

        // Calculate interval for each email
        long intervalMillis = Duration.ofMinutes(intervalInMinutes).toMillis();

        // Schedule emails with proper delay between them
        for (int i = 0; i < timesPerDay; i++) {
            // Calculate the delay before each email (staggered by interval)
            long delayMillis = intervalMillis * i;

            // Create the email task
            Runnable emailTask = () -> {
                try {
                    sendEmail(recipients, subject, body);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            };

            // Schedule the email task at the calculated delay
            scheduler.schedule(emailTask, new Date(System.currentTimeMillis() + delayMillis));
        }
    }
}

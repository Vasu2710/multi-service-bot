package com.example.email_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("vasubansal2002@gmail.com");
        mailSender.setPassword("vdgu sfry ayqa ysib");  // Use App Password if 2FA is enabled

        // Set properties for SMTP
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");  // STARTTLS should be enabled
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.connectiontimeout", "5000");  // Optional: Increase connection timeout
        props.put("mail.smtp.timeout", "5000");  // Optional: Increase timeout for operations

        return mailSender;
    }
}

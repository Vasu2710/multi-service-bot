package com.example.email_service.model;

import java.util.List;

public class EmailScheduleRequest {
    private List<String> recipients;
    private String subject;
    private String body;
    private int timesPerDay;        // Number of times to send the email per day
    private int intervalInMinutes;  // Interval between emails in minutes

    // Getters and Setters
    public List<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getTimesPerDay() {
        return timesPerDay;
    }

    public void setTimesPerDay(int timesPerDay) {
        this.timesPerDay = timesPerDay;
    }

    public int getIntervalInMinutes() {
        return intervalInMinutes;
    }

    public void setIntervalInMinutes(int intervalInMinutes) {
        this.intervalInMinutes = intervalInMinutes;
    }
}

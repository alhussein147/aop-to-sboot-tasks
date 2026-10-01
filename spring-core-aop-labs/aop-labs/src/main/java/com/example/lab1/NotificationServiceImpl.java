package com.example.lab1;

class NotificationServiceImpl implements NotificationService {
    public void sendEmail(String to, String message) {
        System.out.println("{Using impl} sending Email to " + to + ": " + message);
    }

    public void sendSms(String to, String message) {
        System.out.println("{Using impl} sending SMS to " + to + ": " + message);
    }
}
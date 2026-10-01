package com.example.lab1;

interface NotificationService {
    void sendEmail(String to, String message);

    void sendSms(String to, String message);
}
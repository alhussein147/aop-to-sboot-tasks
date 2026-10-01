package com.example.lab1;

import java.lang.reflect.Proxy;

public class main {
    public static void main(String[] args) {

        NotificationService real = new NotificationServiceImpl();

        NotificationService proxy = (NotificationService) Proxy.newProxyInstance(
                NotificationService.class.getClassLoader(),
                new Class<?>[]{NotificationService.class},
                new LoggingHandler(real));

        proxy.sendEmail("a@x.com", "Hello");
        proxy.sendSms("+201000000000", "Hi");

    }
}
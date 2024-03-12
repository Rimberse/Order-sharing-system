package fr.efrei.ordersharingsystem.infrastructure.services.handlers;

import fr.efrei.ordersharingsystem.entity.User;
import fr.efrei.ordersharingsystem.infrastructure.services.NotificationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationHandler implements NotificationService {
    public void sendNotification(List<User> recipients, String subject, String body) {
        System.out.println(
                "Sending notification \n" +
                "Recipients: " + recipients.stream().map(User::getEmail).reduce((a, b) -> a + ", " + b).orElse("") + "\n" +
                "Subject: " + subject + "\n" +
                "Body: " + body);
    }
}
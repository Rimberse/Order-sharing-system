package fr.efrei.ordersharingsystem.infrastructure.notifications.handlers;

import fr.efrei.ordersharingsystem.infrastructure.notifications.MailingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailingHandler implements MailingService {
    @Override
    public void sendEmail(List<String> recipients, String subject, String body) {
        System.out.println(
                "Sending email \n" +
                "Recipients: " + recipients.stream().reduce((s1, s2) -> s1 + ", " + s2).orElse("") + "\n" +
                "Subject: " + subject + "\n" +
                "Body: " + body);
    }
}

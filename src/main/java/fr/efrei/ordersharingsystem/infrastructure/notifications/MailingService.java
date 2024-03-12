package fr.efrei.ordersharingsystem.infrastructure.notifications;

import java.util.List;

public interface MailingService {
    void sendEmail(List<String> recipients, String subject, String body);
}

package fr.efrei.ordersharingsystem.infrastructure.interfaces;

import fr.efrei.ordersharingsystem.domain.User;

import java.util.List;

public interface NotificationService {
    void sendNotification(List<User> recipients, String subject, String body);
}

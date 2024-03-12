package fr.efrei.ordersharingsystem.infrastructure.services;

import fr.efrei.ordersharingsystem.entity.User;

import java.util.List;

public interface NotificationService {
    void sendNotification(List<User> recipients, String subject, String body);
}

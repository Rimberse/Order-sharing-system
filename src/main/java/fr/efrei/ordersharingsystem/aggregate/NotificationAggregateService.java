package fr.efrei.ordersharingsystem.aggregate;

import fr.efrei.ordersharingsystem.commands.notifications.SendNotificationCommand;

public interface NotificationAggregateService {
    void handle(SendNotificationCommand command);
}

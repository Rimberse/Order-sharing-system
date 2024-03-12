package fr.efrei.ordersharingsystem.aggregate.handlers;

import fr.efrei.ordersharingsystem.aggregate.NotificationAggregateService;
import fr.efrei.ordersharingsystem.aggregate.ProductAggregateService;
import fr.efrei.ordersharingsystem.commands.notifications.SendMailCommand;
import fr.efrei.ordersharingsystem.commands.notifications.SendNotificationCommand;
import fr.efrei.ordersharingsystem.commands.products.CreateProductCommand;
import fr.efrei.ordersharingsystem.commands.products.DeleteProductCommand;
import fr.efrei.ordersharingsystem.commands.products.ModifyProductCommand;
import fr.efrei.ordersharingsystem.domain.Notifications;
import fr.efrei.ordersharingsystem.domain.Product;
import fr.efrei.ordersharingsystem.domain.User;
import fr.efrei.ordersharingsystem.exceptions.ItemNotFoundException;
import fr.efrei.ordersharingsystem.repositories.NotificationRepository;
import fr.efrei.ordersharingsystem.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class NotificationAggregateHandler implements NotificationAggregateService {

    @Autowired
    private final NotificationRepository notificationRepository;

    public void handle(SendNotificationCommand command) {
        var users = command.users();
        for (User user: users
             ) {
            var notifications = new Notifications();
            notifications.setUser(user);
            notifications.setMessage(command.message());
            notificationRepository.save(notifications);
        }
    }

    public void handle(SendMailCommand command) {
        var notifications = new Notifications();
        notifications.setUser(command.user());
        if(Objects.equals(command.message(), "")) {
            notifications.setMessage("Thank you for your patronage, here's the due payment : " + command.invoice());
        }
        System.out.println(notifications.getMessage());
        notificationRepository.save(notifications);
    }


}

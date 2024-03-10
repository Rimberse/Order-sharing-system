package fr.efrei.ordersharingsystem.aggregate.handlers;

import fr.efrei.ordersharingsystem.aggregate.OrderAggregateService;
import fr.efrei.ordersharingsystem.commands.orders.CreateOrderCommand;
import fr.efrei.ordersharingsystem.commands.orders.ModifyOrderCommand;
import fr.efrei.ordersharingsystem.domain.Order;
import fr.efrei.ordersharingsystem.domain.Status;
import fr.efrei.ordersharingsystem.exceptions.ItemNotFoundException;
import fr.efrei.ordersharingsystem.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class OrderAggregateHandler implements OrderAggregateService {

    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final AlleyRepository alleyRepository;

    public long handle(CreateOrderCommand command) {
        var order = new Order();
        var alley = alleyRepository.getAlleyByPark_IdAndNumber(command.parkId(), command.alleyNumber());
        if (alley == null) {
            throw new ItemNotFoundException("Park and Alley", command.parkId() + " and " + command.alleyNumber());
        }
        var client = userRepository.findById(command.userId()).orElse(null);
        if (client == null) {
            throw new ItemNotFoundException("User", command.userId());
        }
        order.setPark(alley.getPark());
        order.setAlleyNumber(command.alleyNumber());
        order.setUser(client);
        order.setStatus(Status.PENDING);
        return orderRepository.save(order).getId();
    }

    public void handle(ModifyOrderCommand command) {
        var order = orderRepository.findById(command.id()).orElse(null);
        if (order == null) {
            throw new ItemNotFoundException("Order", command.id());
        }
        if (!Objects.equals(order.getUser().getId(), command.userId())) {
            throw new IllegalArgumentException("User is not modifiable. Order: " + order + ". Command: " + command + ".");
        }
        if (!Objects.equals(order.getPark().getId(), command.parkId())) {
            throw new IllegalArgumentException("Park is not modifiable. Order: " + order + ". Command: " + command + ".");
        }
        if (!Objects.equals(order.getAlleyNumber(), command.alleyNumber())) {
            throw new IllegalArgumentException("Alley number is not modifiable. Order: " + order + ". Command: " + command + ".");
        }
        order.setStatus(command.status());
        orderRepository.save(order);
    }
}

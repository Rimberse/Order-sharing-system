package fr.efrei.ordersharingsystem.aggregate.handlers;

import fr.efrei.ordersharingsystem.aggregate.OrderAggregateService;
import fr.efrei.ordersharingsystem.commands.orders.AddOrderCommand;
import fr.efrei.ordersharingsystem.commands.orders.ModifyOrderCommand;
import fr.efrei.ordersharingsystem.domain.Order;
import fr.efrei.ordersharingsystem.domain.OrderItem;
import fr.efrei.ordersharingsystem.domain.Status;
import fr.efrei.ordersharingsystem.exceptions.ItemNotFoundException;
import fr.efrei.ordersharingsystem.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class OrderAggregateHandler implements OrderAggregateService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final AlleyRepository alleyRepository;

    @Autowired
    public OrderAggregateHandler(
            OrderRepository orderRepository,
            UserRepository userRepository,
            AlleyRepository alleyRepository,
            OrderItemRepository orderItemRepository,
            ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.alleyRepository = alleyRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
    }

    public long handle(AddOrderCommand command) {
        var order = orderRepository.findAllByParkIdAndAlleyNumberAndUserIdAndStatus(
                command.parkId(),
                command.alleyNumber(),
                command.userId(),
                Status.PENDING)
                .stream().findFirst().orElse(null);
        if (order == null) {
            order = new Order();
            var alley = alleyRepository.getAlleyByPark_IdAndNumber(command.parkId(), command.alleyNumber());
            if (alley == null) {
                throw new ItemNotFoundException("Park and Alley", command.parkId() + " and " + command.alleyNumber());
            }
            var client = userRepository.findById(command.userId()).orElse(null);
            if (client == null) {
                throw new ItemNotFoundException("User", command.userId());
            }
            order.setParkId(alley.getPark().getId());
            order.setAlleyNumber(command.alleyNumber());
            order.setUserId(client.getId());
            order.setStatus(Status.PENDING);
            order = orderRepository.save(order);
        }
        var orderItem = orderItemRepository.findAllByOrderIdAndProduct_Id(order.getId(), command.productId()).stream().findFirst().orElse(null);
        if (orderItem != null) {
            throw new IllegalArgumentException("Product already exists in the order. Order: " + order + ". Command: " + command + ".");
        }
        var product = productRepository.findById(command.productId()).orElse(null);
        if (product == null) {
            throw new ItemNotFoundException("Product", command.productId());
        }
        if (!Objects.equals(product.getParkId(), command.parkId())) {
            throw new IllegalArgumentException("Product does not belong to the park. Order: " + order + ". Command: " + command + ".");
        }
        orderItem = new OrderItem();
        orderItem.setOrderId(order.getId());
        orderItem.setProduct(product);
        orderItem.setQuantity(command.quantity());
        orderItemRepository.save(orderItem);
        return order.getId();
    }

    public void handle(ModifyOrderCommand command) {
        var order = orderRepository.findById(command.id()).orElse(null);
        if (order == null) {
            throw new ItemNotFoundException("Order", command.id());
        }
        if (!Objects.equals(order.getUserId(), command.userId())) {
            throw new IllegalArgumentException("User is not modifiable. Order: " + order + ". Command: " + command + ".");
        }
        if (!Objects.equals(order.getParkId(), command.parkId())) {
            throw new IllegalArgumentException("Park is not modifiable. Order: " + order + ". Command: " + command + ".");
        }
        if (!Objects.equals(order.getAlleyNumber(), command.alleyNumber())) {
            throw new IllegalArgumentException("Alley number is not modifiable. Order: " + order + ". Command: " + command + ".");
        }
        order.setStatus(command.status());
        orderRepository.save(order);
    }
}

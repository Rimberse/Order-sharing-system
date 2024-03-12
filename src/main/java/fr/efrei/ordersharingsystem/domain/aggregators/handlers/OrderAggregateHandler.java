package fr.efrei.ordersharingsystem.domain.aggregators.handlers;

import fr.efrei.ordersharingsystem.domain.aggregators.OrderAggregateService;
import fr.efrei.ordersharingsystem.commands.orders.*;
import fr.efrei.ordersharingsystem.entity.Order;
import fr.efrei.ordersharingsystem.entity.OrderItem;
import fr.efrei.ordersharingsystem.entity.Status;
import fr.efrei.ordersharingsystem.domain.commands.orders.DeleteOrderCommand;
import fr.efrei.ordersharingsystem.domain.commands.orders.DeleteOrderItemCommand;
import fr.efrei.ordersharingsystem.domain.commands.orders.SetOrderItemCommand;
import fr.efrei.ordersharingsystem.exceptions.ItemNotFoundException;
import fr.efrei.ordersharingsystem.infrastructure.repositories.OrderItemRepository;
import fr.efrei.ordersharingsystem.infrastructure.repositories.OrderRepository;
import fr.efrei.ordersharingsystem.infrastructure.repositories.ProductRepository;
import fr.efrei.ordersharingsystem.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class OrderAggregateHandler implements OrderAggregateService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    @Autowired
    public OrderAggregateHandler(
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository,
            ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
    }
    private Order createNewOrder(SetOrderItemCommand command) {
        var order = new Order();
        order.setParkId(command.parkId());
        order.setAlleyNumber(command.alleyNumber());
        order.setStatus(Status.PENDING);
        order = orderRepository.save(order);
        return order;
    }

    private OrderItem createNewOrderItem(SetOrderItemCommand command, Order order) {
        var orderItem = new OrderItem();
        orderItem.setOrderId(order.getId());
        var product = productRepository.findById(command.productId())
                .orElseThrow(() -> new ItemNotFoundException("Product", command.productId()));
        var productNotInPark = !Objects.equals(product.getParkId(), command.parkId());
        if (productNotInPark) {
            throw new IllegalArgumentException("Product does not belong to the park. Order: " + order + ". Command: " + command + ".");
        }
        orderItem.setOrderId(order.getId());
        orderItem.setProduct(product);
        orderItem.setQuantity(command.quantity());
        return orderItemRepository.save(orderItem);
    }

    public void handle(SetOrderItemCommand command) {
        var order = orderRepository.findAllByParkIdAndAlleyNumberAndStatus(
                        command.parkId(),
                        command.alleyNumber(),
                        Status.PENDING
                )
                .stream().findFirst()
                .orElse(createNewOrder(command));
        var orderItem = orderItemRepository
                .findByOrderIdAndUserIdAndProduct_Id(order.getId(), command.userId(), command.productId())
                .orElse(createNewOrderItem(command, order));
        orderItemRepository.save(orderItem);
    }

    public void handle(DeleteOrderCommand command) {
        var order = orderRepository.findById(command.id()).orElse(null);
        if (order == null) {
            return;
        }
        var orderNotInAlley =
                !Objects.equals(order.getParkId(), command.parkId()) ||
                        !Objects.equals(order.getAlleyNumber(), command.alleyNumber());
        if (orderNotInAlley) {
            return;
        }
        orderRepository.deleteById(command.id());
    }
    public void handle(DeleteOrderItemCommand command) {
        orderItemRepository.deleteById(command.id());
    }
}

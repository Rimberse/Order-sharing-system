package fr.efrei.ordersharingsystem.projections.handlers;

import fr.efrei.ordersharingsystem.domain.Order;
import fr.efrei.ordersharingsystem.projections.OrderProjectionService;
import fr.efrei.ordersharingsystem.queries.orders.GetOrderByOrderIdQuery;
import fr.efrei.ordersharingsystem.queries.orders.GetOrdersByAlleyQuery;
import fr.efrei.ordersharingsystem.repositories.OrderItemRepository;
import fr.efrei.ordersharingsystem.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class OrderProjectionHandler implements OrderProjectionService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderProjectionHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> handle(GetOrdersByAlleyQuery query) {
        return orderRepository.findAllByParkIdAndAlleyNumberAndStatus(
                query.parkId(),
                query.alleyNumber(),
                query.status());
    }

    public Order handle(GetOrderByOrderIdQuery query) {
        var order = orderRepository.findById(query.orderId()).orElse(null);
        if (order == null) {
            throw new IllegalArgumentException("Order not found: " + query.orderId());
        }
        if (!Objects.equals(order.getParkId(), query.parkId())) {
            throw new IllegalArgumentException("Order not found in park: " + query.parkId());
        }
        if (!Objects.equals(order.getAlleyNumber(), query.alleyNumber())) {
            throw new IllegalArgumentException("Order not found in alley: " + query.alleyNumber());
        }
        return order;
    }
}

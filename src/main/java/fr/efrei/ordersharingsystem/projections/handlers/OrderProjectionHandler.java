package fr.efrei.ordersharingsystem.projections.handlers;

import fr.efrei.ordersharingsystem.domain.Order;
import fr.efrei.ordersharingsystem.domain.Status;
import fr.efrei.ordersharingsystem.projections.OrderProjectionService;
import fr.efrei.ordersharingsystem.queries.orders.GetOrderByAlleyQuery;
import fr.efrei.ordersharingsystem.queries.orders.GetOrderByOrderIdQuery;
import fr.efrei.ordersharingsystem.queries.orders.GetRemainingAmountByOrderIdQuery;
import fr.efrei.ordersharingsystem.queries.orders.GetTotalDueAmountByOrderIdQuery;
import fr.efrei.ordersharingsystem.repositories.OrderRepository;
import fr.efrei.ordersharingsystem.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class OrderProjectionHandler implements OrderProjectionService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderProjectionHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order handle(GetOrderByAlleyQuery query) {
        var orderResults = orderRepository.findAllByParkIdAndAlleyNumberAndStatus(
                query.parkId(),
                query.alleyNumber(),
                Status.PENDING);
        return orderResults.stream().findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + query.parkId() + " and " + query.alleyNumber()));
    }

    public Order handle(GetOrderByOrderIdQuery query) {
        var order = orderRepository.findById(query.orderId())
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + query.orderId()));
        var orderNotInAlley =
                !Objects.equals(order.getParkId(), query.parkId()) ||
                !Objects.equals(order.getAlleyNumber(), query.alleyNumber());
        if (orderNotInAlley) {
            throw new IllegalArgumentException("Order not found in alley: Park " + query.parkId() + " and alley " + query.alleyNumber());
        }
        return order;
    }

    public Integer handle(GetRemainingAmountByOrderIdQuery query) {
        var orderQuery = new GetOrderByOrderIdQuery(query.parkId(), query.alleyNumber(), query.orderId());
        var order = handle(orderQuery);
        return Utils.calculateTotalDueAmount(order) - Utils.calculateTotalPaymentAmount(order);
    }

    public Integer handle(GetTotalDueAmountByOrderIdQuery query) {
        var orderQuery = new GetOrderByOrderIdQuery(query.parkId(), query.alleyNumber(), query.orderId());
        var order = handle(orderQuery);
        return Utils.calculateTotalDueAmount(order);
    }
}

package fr.efrei.ordersharingsystem.projections.handlers;

import fr.efrei.ordersharingsystem.domain.Order;
import fr.efrei.ordersharingsystem.projections.OrderProjectionService;
import fr.efrei.ordersharingsystem.queries.orders.GetOrderByOrderIdQuery;
import fr.efrei.ordersharingsystem.queries.sessions.GetSessionByAlleyQuery;
import fr.efrei.ordersharingsystem.repositories.OrderRepository;
import fr.efrei.ordersharingsystem.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class OrderProjectionHandler implements OrderProjectionService {

    private final SessionRepository sessionRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderProjectionHandler(OrderRepository orderRepository, SessionRepository sessionRepository) {
        this.orderRepository = orderRepository;
        this.sessionRepository = sessionRepository;
    }

    public List<Order> handle(GetSessionByAlleyQuery query) {
        var sessions = sessionRepository.findAllByParkIdAndAlleyNumberAndStatus(
                query.parkId(),
                query.alleyNumber(),
                query.status());
        return sessions.stream()
                .map(session -> session.getOrders().stream().toList())
                .flatMap(List::stream)
                .toList();
    }

    public Order handle(GetOrderByOrderIdQuery query) {
        var order = orderRepository.findById(query.orderId()).orElse(null);
        var orderNotFound = order == null;
        if (orderNotFound) {
            throw new IllegalArgumentException("Order not found: " + query.orderId());
        }
        var session = sessionRepository.findById(order.getSessionId()).orElse(null);
        var sessionNotFound = session == null;
        if (sessionNotFound) {
            throw new IllegalArgumentException("Session not found: " + order.getSessionId());
        }
        var orderNotInAlley =
                !Objects.equals(session.getParkId(), query.parkId()) ||
                !Objects.equals(session.getAlleyNumber(), query.alleyNumber());
        if (orderNotInAlley) {
            throw new IllegalArgumentException("Order not found in the specified alley: " + query.orderId());
        }
        return order;
    }
}

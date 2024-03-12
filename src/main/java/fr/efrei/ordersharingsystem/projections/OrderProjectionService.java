package fr.efrei.ordersharingsystem.projections;

import fr.efrei.ordersharingsystem.domain.Order;
import fr.efrei.ordersharingsystem.queries.orders.GetOrderByOrderIdQuery;
import fr.efrei.ordersharingsystem.queries.sessions.GetSessionByAlleyQuery;

import java.util.List;

public interface OrderProjectionService {
    List<Order> handle(GetSessionByAlleyQuery query);
    Order handle(GetOrderByOrderIdQuery query);
}

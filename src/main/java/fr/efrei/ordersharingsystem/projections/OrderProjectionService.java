package fr.efrei.ordersharingsystem.projections;

import fr.efrei.ordersharingsystem.domain.Order;
import fr.efrei.ordersharingsystem.queries.orders.GetOrderByOrderIdQuery;
import fr.efrei.ordersharingsystem.queries.orders.GetOrdersByAlleyQuery;

import java.util.List;

public interface OrderProjectionService {
    List<Order> handle(GetOrdersByAlleyQuery query);
    Order handle(GetOrderByOrderIdQuery query);
}

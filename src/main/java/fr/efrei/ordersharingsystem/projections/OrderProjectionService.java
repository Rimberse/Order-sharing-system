package fr.efrei.ordersharingsystem.projections;

import fr.efrei.ordersharingsystem.domain.Order;
import fr.efrei.ordersharingsystem.queries.GetOrderByAlleyQuery;

import java.util.List;

public interface OrderProjectionService {
    List<Order> handle(GetOrderByAlleyQuery query);
}

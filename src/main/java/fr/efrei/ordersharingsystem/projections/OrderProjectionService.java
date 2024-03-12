package fr.efrei.ordersharingsystem.projections;

import fr.efrei.ordersharingsystem.domain.Order;
import fr.efrei.ordersharingsystem.queries.orders.GetOrderByOrderIdQuery;
import fr.efrei.ordersharingsystem.queries.orders.GetOrderByAlleyQuery;
import fr.efrei.ordersharingsystem.queries.orders.GetRemainingAmountByOrderIdQuery;
import fr.efrei.ordersharingsystem.queries.orders.GetTotalDueAmountByOrderIdQuery;

import java.util.List;

public interface OrderProjectionService {
    Order handle(GetOrderByAlleyQuery query);
    Order handle(GetOrderByOrderIdQuery query);
    Integer handle(GetRemainingAmountByOrderIdQuery query);
    Integer handle(GetTotalDueAmountByOrderIdQuery query);
}

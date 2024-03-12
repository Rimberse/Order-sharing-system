package fr.efrei.ordersharingsystem.dataaccess.projections;

import fr.efrei.ordersharingsystem.dataaccess.queries.orders.GetOrderByAlleyQuery;
import fr.efrei.ordersharingsystem.dataaccess.queries.orders.GetOrderByOrderIdQuery;
import fr.efrei.ordersharingsystem.dataaccess.queries.orders.GetRemainingAmountByOrderIdQuery;
import fr.efrei.ordersharingsystem.dataaccess.queries.orders.GetTotalDueAmountByOrderIdQuery;
import fr.efrei.ordersharingsystem.entity.Order;

public interface OrderProjectionService {
    Order handle(GetOrderByAlleyQuery query);
    Order handle(GetOrderByOrderIdQuery query);
    Integer handle(GetRemainingAmountByOrderIdQuery query);
    Integer handle(GetTotalDueAmountByOrderIdQuery query);
}

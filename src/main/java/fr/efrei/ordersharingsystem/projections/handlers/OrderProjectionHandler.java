package fr.efrei.ordersharingsystem.projections.handlers;

import fr.efrei.ordersharingsystem.domain.Order;
import fr.efrei.ordersharingsystem.projections.OrderProjectionService;
import fr.efrei.ordersharingsystem.queries.orders.GetOrderByAlleyQuery;
import fr.efrei.ordersharingsystem.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderProjectionHandler implements OrderProjectionService {
    @Autowired
    private final OrderRepository orderRepository;

    public List<Order> handle(GetOrderByAlleyQuery query) {
        return orderRepository.findAllByParkIdAndAlleyNumberAndStatus(
                query.parkId(),
                query.alleyNumber(),
                query.status());
    }
}

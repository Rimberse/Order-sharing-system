package fr.efrei.ordersharingsystem.controller;

import fr.efrei.ordersharingsystem.aggregate.OrderAggregateService;
import fr.efrei.ordersharingsystem.commands.orders.AddOrderCommand;
import fr.efrei.ordersharingsystem.domain.Order;
import fr.efrei.ordersharingsystem.domain.OrderItem;
import fr.efrei.ordersharingsystem.domain.Status;
import fr.efrei.ordersharingsystem.projections.OrderProjectionService;
import fr.efrei.ordersharingsystem.queries.orders.GetOrderByOrderIdQuery;
import fr.efrei.ordersharingsystem.queries.orders.GetOrdersByAlleyQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/parks/{parkId}/alleys/{alleyNumber}")
public class OrderController {
    private final OrderProjectionService orderProjectionService;
    private final OrderAggregateService orderAggregateService;

    @Autowired
    public OrderController(OrderProjectionService orderProjectionService, OrderAggregateService orderAggregateService) {
        this.orderProjectionService = orderProjectionService;
        this.orderAggregateService = orderAggregateService;
    }


    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrders(
            @PathVariable Long parkId,
            @PathVariable Integer alleyNumber,
            @RequestParam(defaultValue = "PENDING") Status status) {
        GetOrdersByAlleyQuery query = new GetOrdersByAlleyQuery(parkId, alleyNumber, status);
        return ResponseEntity.ok(orderProjectionService.handle(query));
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<Order> getOrder(
            @PathVariable Long parkId,
            @PathVariable Integer alleyNumber,
            @PathVariable Long orderId) {
        GetOrderByOrderIdQuery query = new GetOrderByOrderIdQuery(parkId, alleyNumber, orderId);
        return ResponseEntity.ok(orderProjectionService.handle(query));
    }

    @PostMapping("/users/{userId}")
    public ResponseEntity<Order> addOrder(
            @PathVariable Long parkId,
            @PathVariable Integer alleyNumber,
            @PathVariable Long userId,
            @RequestBody AddOrderCommand product) {
        var command = new AddOrderCommand(userId, parkId, alleyNumber, product.productId(), product.quantity());
        var resultId = orderAggregateService.handle(command);
        URI location = URI.create("/api/v1/parks/" + parkId + "/alleys/" + alleyNumber + "/orders/" + resultId);
        return ResponseEntity.created(location).build();
    }

}

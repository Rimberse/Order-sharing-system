package fr.efrei.ordersharingsystem.controller;

import fr.efrei.ordersharingsystem.aggregate.OrderAggregateService;
import fr.efrei.ordersharingsystem.commands.orders.CreateOrderCommand;
import fr.efrei.ordersharingsystem.domain.Order;
import fr.efrei.ordersharingsystem.domain.Status;
import fr.efrei.ordersharingsystem.projections.OrderProjectionService;
import fr.efrei.ordersharingsystem.queries.orders.GetOrderByAlleyQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/parks/{parkId}/alleys/{alleyNumber}/orders")
@RequiredArgsConstructor
public class OrderController {
    @Autowired
    private final OrderProjectionService orderProjectionService;

    @Autowired
    private final OrderAggregateService orderAggregateService;
    @GetMapping()
    public ResponseEntity<List<Order>> getOrders(
            @PathVariable Long parkId,
            @PathVariable Integer alleyNumber,
            @RequestParam(defaultValue = "PENDING") Status status) {
        GetOrderByAlleyQuery query = new GetOrderByAlleyQuery(parkId, alleyNumber, status);
        return ResponseEntity.ok(orderProjectionService.handle(query));
    }

    @PostMapping("/users/{userId}")
    public ResponseEntity<Order> createOrder(
            @PathVariable Long parkId,
            @PathVariable Integer alleyNumber,
            @PathVariable Long userId) {
        var command = new CreateOrderCommand(userId, parkId, alleyNumber);
        var resultId = orderAggregateService.handle(command);
        URI location = URI.create("/api/v1/parks/" + parkId + "/alleys/" + alleyNumber + "/orders/" + resultId);
        return ResponseEntity.created(location).build();
    }

}

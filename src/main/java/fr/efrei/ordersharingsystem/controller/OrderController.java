package fr.efrei.ordersharingsystem.controller;

import fr.efrei.ordersharingsystem.aggregate.OrderAggregateService;
import fr.efrei.ordersharingsystem.aggregate.SessionAggregateService;
import fr.efrei.ordersharingsystem.commands.orders.*;
import fr.efrei.ordersharingsystem.commands.sessions.DeleteSessionCommand;
import fr.efrei.ordersharingsystem.domain.Order;
import fr.efrei.ordersharingsystem.domain.Session;
import fr.efrei.ordersharingsystem.domain.Status;
import fr.efrei.ordersharingsystem.projections.OrderProjectionService;
import fr.efrei.ordersharingsystem.projections.SessionProjectionService;
import fr.efrei.ordersharingsystem.queries.orders.GetOrderByOrderIdQuery;
import fr.efrei.ordersharingsystem.queries.sessions.GetSessionByAlleyQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/parks/{parkId}/alleys/{alleyNumber}")
public class OrderController {
    private final OrderProjectionService orderProjectionService;
    private final OrderAggregateService orderAggregateService;
    private final SessionProjectionService sessionProjectionService;
    private final SessionAggregateService sessionAggregateService;

    @Autowired
    public OrderController(
            OrderProjectionService orderProjectionService,
            OrderAggregateService orderAggregateService,
            SessionAggregateService sessionAggregateService,
            SessionProjectionService sessionProjectionService
    ) {
        this.orderProjectionService = orderProjectionService;
        this.orderAggregateService = orderAggregateService;
        this.sessionAggregateService = sessionAggregateService;
        this.sessionProjectionService = sessionProjectionService;
    }


    @GetMapping("/orders")
    public ResponseEntity<Session> getSessionOrders(
            @PathVariable Long parkId,
            @PathVariable Integer alleyNumber,
            @RequestParam(defaultValue = "PENDING") Status status) {
        GetSessionByAlleyQuery query = new GetSessionByAlleyQuery(parkId, alleyNumber, status);
        return ResponseEntity.ok(sessionProjectionService.handle(query));
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
    public ResponseEntity<String> addOrder(
            @PathVariable Long parkId,
            @PathVariable Integer alleyNumber,
            @PathVariable Long userId,
            @RequestBody AddOrderCommand product) {
        var command = new AddOrderCommand(userId, parkId, alleyNumber, product.productId(), product.quantity());
        var resultId = orderAggregateService.handle(command);
        URI location = URI.create("/api/v1/parks/" + parkId + "/alleys/" + alleyNumber + "/orders/" + resultId);
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/orders/{orderId}/users/{userId}/items/{itemId}")
    public ResponseEntity<String> modifyOrderItem(
            @PathVariable Long parkId,
            @PathVariable Integer alleyNumber,
            @PathVariable Long userId,
            @PathVariable Long orderId,
            @PathVariable Long itemId,
            @RequestBody ModifyOrderItemCommand orderItem) {
        var command = new ModifyOrderItemCommand(itemId, userId, parkId, alleyNumber, orderId, orderItem.productId(), orderItem.quantity());
        orderAggregateService.handle(command);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/sessions/{sessionId}")
    public ResponseEntity<String> deleteSession(
            @PathVariable Long parkId,
            @PathVariable Integer alleyNumber,
            @PathVariable Long sessionId) {
        sessionAggregateService.handle(new DeleteSessionCommand(sessionId, parkId, alleyNumber));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/orders/{orderId}/users/{userId}/items/{itemId}")
    public ResponseEntity<String> deleteOrderItem(
            @PathVariable Long parkId,
            @PathVariable Integer alleyNumber,
            @PathVariable Long userId,
            @PathVariable Long orderId,
            @PathVariable Long itemId) {
        orderAggregateService.handle(new DeleteOrderItemCommand(itemId));
        return ResponseEntity.noContent().build();
    }
}

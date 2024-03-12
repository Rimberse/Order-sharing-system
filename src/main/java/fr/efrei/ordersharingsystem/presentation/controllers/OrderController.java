package fr.efrei.ordersharingsystem.presentation.controllers;

import fr.efrei.ordersharingsystem.domain.aggregators.OrderAggregateService;
import fr.efrei.ordersharingsystem.domain.commands.orders.DeleteOrderCommand;
import fr.efrei.ordersharingsystem.entity.Order;
import fr.efrei.ordersharingsystem.domain.commands.orders.DeleteOrderItemCommand;
import fr.efrei.ordersharingsystem.domain.commands.orders.SetOrderItemCommand;
import fr.efrei.ordersharingsystem.dataaccess.projections.OrderProjectionService;
import fr.efrei.ordersharingsystem.dataaccess.queries.orders.GetOrderByOrderIdQuery;
import fr.efrei.ordersharingsystem.dataaccess.queries.orders.GetOrderByAlleyQuery;
import fr.efrei.ordersharingsystem.dataaccess.queries.orders.GetRemainingAmountByOrderIdQuery;
import fr.efrei.ordersharingsystem.dataaccess.queries.orders.GetTotalDueAmountByOrderIdQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/parks/{parkId}/alleys/{alleyNumber}")
public class OrderController {
    private final OrderProjectionService orderProjectionService;
    private final OrderAggregateService orderAggregateService;

    @Autowired
    public OrderController(
            OrderProjectionService orderProjectionService,
            OrderAggregateService orderAggregateService
    ) {
        this.orderProjectionService = orderProjectionService;
        this.orderAggregateService = orderAggregateService;
    }


    @GetMapping("/currentSession")
    public ResponseEntity<Order> getSessionOrder(
            @PathVariable Long parkId,
            @PathVariable Integer alleyNumber) {
        GetOrderByAlleyQuery query = new GetOrderByAlleyQuery(parkId, alleyNumber);
        return ResponseEntity.ok(orderProjectionService.handle(query));
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<Order> getOrder(
            @PathVariable Long parkId,
            @PathVariable Integer alleyNumber,
            @PathVariable Long orderId) {
        var query = new GetOrderByOrderIdQuery(parkId, alleyNumber, orderId);
        return ResponseEntity.ok(orderProjectionService.handle(query));
    }

    @GetMapping("/orders/{orderId}/totalDueAmount")
    public ResponseEntity<Integer> getTotalDueAmount(
            @PathVariable Long parkId,
            @PathVariable Integer alleyNumber,
            @PathVariable Long orderId) {
        var query = new GetTotalDueAmountByOrderIdQuery(parkId, alleyNumber, orderId);
        return ResponseEntity.ok(orderProjectionService.handle(query));
    }

    @GetMapping("/orders/{orderId}/remainingAmount")
    public ResponseEntity<Integer> getRemainingAmount(
            @PathVariable Long parkId,
            @PathVariable Integer alleyNumber,
            @PathVariable Long orderId) {
        var query = new GetRemainingAmountByOrderIdQuery(parkId, alleyNumber, orderId);
        return ResponseEntity.ok(orderProjectionService.handle(query));
    }

    @PutMapping("/users/{userId}/items/{itemId}")
    public ResponseEntity<String> modifyOrderItem(
            @PathVariable Long parkId,
            @PathVariable Integer alleyNumber,
            @PathVariable Long userId,
            @PathVariable Long itemId,
            @RequestBody SetOrderItemCommand product) {
        var command = new SetOrderItemCommand(userId, parkId, alleyNumber, itemId, product.quantity());
        orderAggregateService.handle(command);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity<String> deleteOrder(
            @PathVariable Long parkId,
            @PathVariable Integer alleyNumber,
            @PathVariable Long orderId) {
        orderAggregateService.handle(new DeleteOrderCommand(orderId, parkId, alleyNumber));
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

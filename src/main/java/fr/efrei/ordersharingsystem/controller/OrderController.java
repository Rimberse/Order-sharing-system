package fr.efrei.ordersharingsystem.controller;

import fr.efrei.ordersharingsystem.domain.Order;
import fr.efrei.ordersharingsystem.domain.Status;
import fr.efrei.ordersharingsystem.projections.OrderProjectionService;
import fr.efrei.ordersharingsystem.queries.orders.GetOrderByAlleyQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parks/{parkId}/alleys/{alleyNumber}/orders")
@RequiredArgsConstructor
public class OrderController {
    @Autowired
    private final OrderProjectionService orderProjectionService;

    @GetMapping()
    public ResponseEntity<List<Order>> getOrders(
            @PathVariable Long parkId,
            @PathVariable Integer alleyNumber,
            @RequestParam(defaultValue = "PENDING") Status status) {
        GetOrderByAlleyQuery query = new GetOrderByAlleyQuery(parkId, alleyNumber, status);
        return ResponseEntity.ok(orderProjectionService.handle(query));
    }

}

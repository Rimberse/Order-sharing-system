package fr.efrei.ordersharingsystem.controller;

import fr.efrei.ordersharingsystem.domain.Payment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {

    @GetMapping("/{id}")
    public ResponseEntity<List<Payment>> getPayment(@PathVariable Integer id) { //checks payment from id
        return ResponseEntity.ok(new ArrayList<>());
    }

    @PostMapping("/{id}/{amount}")
    public ResponseEntity<List<Payment>> createPayment(@PathVariable Integer id, @PathVariable Integer amount) {//creates a payment
        return ResponseEntity.ok(new ArrayList<>());
    }
}

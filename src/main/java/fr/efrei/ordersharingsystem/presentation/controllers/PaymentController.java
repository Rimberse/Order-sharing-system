package fr.efrei.ordersharingsystem.presentation.controllers;

import fr.efrei.ordersharingsystem.domain.aggregators.PaymentAggregateService;
import fr.efrei.ordersharingsystem.domain.commands.payments.CreatePaymentCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders/{orderId}")
public class PaymentController {

    private final PaymentAggregateService paymentAggregateService;

    @Autowired
    public PaymentController(PaymentAggregateService paymentAggregateService) {
        this.paymentAggregateService = paymentAggregateService;
    }
    @PostMapping("/payments")
    public ResponseEntity<String> makePayment(
            @PathVariable Long orderId,
            @RequestBody CreatePaymentCommand payment) {
        System.out.println(payment.userId() + " " + orderId + " " + payment.amount() + " " + payment.paymentAccount());
        var command = new CreatePaymentCommand(payment.userId(), orderId, payment.amount(), payment.paymentAccount());
        paymentAggregateService.handle(command);
        return ResponseEntity.noContent().build();
    }
}

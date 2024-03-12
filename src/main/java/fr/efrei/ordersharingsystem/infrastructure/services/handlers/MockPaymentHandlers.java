package fr.efrei.ordersharingsystem.infrastructure.services.handlers;

import fr.efrei.ordersharingsystem.infrastructure.services.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class MockPaymentHandlers implements PaymentService {
    public Boolean pay(String paymentAccount, Integer amount) {
        System.out.println("Payment of " + amount + " from account " + paymentAccount + " has been made");
        return true;
    }
}

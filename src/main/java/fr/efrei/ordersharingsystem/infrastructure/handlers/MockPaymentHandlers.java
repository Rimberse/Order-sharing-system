package fr.efrei.ordersharingsystem.infrastructure.handlers;

import fr.efrei.ordersharingsystem.infrastructure.interfaces.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class MockPaymentHandlers implements PaymentService {
    public Boolean pay(String paymentAccount, Integer amount) {
        System.out.println("Payment of " + amount + " from account " + paymentAccount + " has been made");
        return true;
    }
}

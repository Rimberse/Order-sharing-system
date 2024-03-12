package fr.efrei.ordersharingsystem.infrastructure.interfaces;

public interface PaymentService {
    Boolean pay(String paymentAccount, Integer amount);
}

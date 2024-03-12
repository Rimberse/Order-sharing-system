package fr.efrei.ordersharingsystem.infrastructure.services;

public interface PaymentService {
    Boolean pay(String paymentAccount, Integer amount);
}

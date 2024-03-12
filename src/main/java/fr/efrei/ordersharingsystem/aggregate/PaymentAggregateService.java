package fr.efrei.ordersharingsystem.aggregate;

import fr.efrei.ordersharingsystem.commands.payments.CreatePaymentCommand;
import fr.efrei.ordersharingsystem.domain.Payment;

public interface PaymentAggregateService {
    void handle(CreatePaymentCommand command);
}

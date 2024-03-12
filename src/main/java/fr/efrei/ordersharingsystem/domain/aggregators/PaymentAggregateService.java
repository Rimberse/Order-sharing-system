package fr.efrei.ordersharingsystem.domain.aggregators;

import fr.efrei.ordersharingsystem.domain.commands.payments.CreatePaymentCommand;

public interface PaymentAggregateService {
    void handle(CreatePaymentCommand command);
}

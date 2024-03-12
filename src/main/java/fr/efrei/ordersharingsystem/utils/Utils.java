package fr.efrei.ordersharingsystem.utils;

import fr.efrei.ordersharingsystem.domain.Order;
import fr.efrei.ordersharingsystem.domain.Payment;
import fr.efrei.ordersharingsystem.domain.Status;

public class Utils {
    public static Integer calculateTotalDueAmount(Order order) {
        return order.getItems()
                .stream()
                .map(orderItem -> orderItem.getQuantity() * orderItem.getProduct().getPrice())
                .mapToInt(Integer::intValue).sum();
    }

    public static Integer calculateTotalPaymentAmount(Order order) {
        return order.getPayments()
                .stream()
                .filter(payment -> payment.getStatus() == Status.COMPLETED)
                .map(Payment::getAmount)
                .mapToInt(Integer::intValue).sum();
    }
}

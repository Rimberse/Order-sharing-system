package fr.efrei.ordersharingsystem.exceptions;

public class InvalidPaymentException extends RuntimeException {
    public InvalidPaymentException() {
        super();
    }

    public InvalidPaymentException(String aggregateType, long paymentId) {
        super(aggregateType + " for order id: " + paymentId + " cannot be processed due to invalid amount");
    }

    public InvalidPaymentException(String aggregateType, String paymentId) {
        super(aggregateType + " for order id: " + paymentId + " cannot be processed due to invalid amount");
    }
}

package fr.efrei.ordersharingsystem.exceptions;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException() {
        super();
    }

    public ItemNotFoundException(String aggregateType, long itemId) {
        super(aggregateType + " not found id: " + itemId);
    }
}
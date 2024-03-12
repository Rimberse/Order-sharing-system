package fr.efrei.ordersharingsystem.entity;

public enum Status {
    PENDING("PENDING"),
    IN_PROGRESS("IN_PROGRESS"),
    FAILED("FAILED"),
    COMPLETED("COMPLETED"),
    CANCELLED("CANCELLED");

    private final String label;

    Status(String label) {
        this.label = label;
    }
}

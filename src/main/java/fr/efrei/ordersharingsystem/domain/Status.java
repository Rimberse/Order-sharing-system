package fr.efrei.ordersharingsystem.domain;

public enum Status {
    PENDING("Pending"),
    IN_PROGRESS("In progress"),
    FAILED("Failed"),
    COMPLETED("Completed"),
    CANCELLED("Cancelled");

    private final String label;

    Status(String label) {
        this.label = label;
    }
}

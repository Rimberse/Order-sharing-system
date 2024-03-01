package fr.efrei.ordersharingsystem.domain;

public enum Role {
    AGENT("agent"),
    CUSTOMER("customer");

    private final String label;

     Role(String label) {
        this.label = label;
    }
}

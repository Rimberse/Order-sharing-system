package fr.efrei.ordersharingsystem.entity;

public enum Role {
    AGENT("AGENT"),
    CUSTOMER("CUSTOMER");

    private final String label;

     Role(String label) {
        this.label = label;
    }
}

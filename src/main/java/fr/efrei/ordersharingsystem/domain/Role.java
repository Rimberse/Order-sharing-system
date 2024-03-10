package fr.efrei.ordersharingsystem.domain;

public enum Role {
    AGENT("AGENT"),
    CUSTOMER("CUSTOMER");

    private final String label;

     Role(String label) {
        this.label = label;
    }
}

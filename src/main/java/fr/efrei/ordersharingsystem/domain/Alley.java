package fr.efrei.ordersharingsystem.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Alley {
    private int id;
    private String qrCode;

    public Alley(int id, String qrCode) {
        this.id = id;
        this.qrCode = qrCode;
    }
}

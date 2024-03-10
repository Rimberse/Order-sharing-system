package fr.efrei.ordersharingsystem.domain.idclass;

import fr.efrei.ordersharingsystem.domain.BowlingPark;
import lombok.Data;

import java.io.Serializable;

@Data
public class AlleyId implements Serializable {
    private Integer number;
    private BowlingPark park;
}

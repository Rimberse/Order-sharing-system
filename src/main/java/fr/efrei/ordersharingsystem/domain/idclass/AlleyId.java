package fr.efrei.ordersharingsystem.domain.idclass;

import fr.efrei.ordersharingsystem.domain.BowlingPark;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class AlleyId implements Serializable {
    private Integer number;
    private Long park;
}

package fr.efrei.ordersharingsystem.entity.idclass;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class AlleyId implements Serializable {
    private Integer number;
    private Long park;
}

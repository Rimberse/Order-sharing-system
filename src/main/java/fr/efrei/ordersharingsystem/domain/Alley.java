package fr.efrei.ordersharingsystem.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Alleys")
@Getter
@Setter
public class Alley {
    @Id
    private int number;

    @Id
    @ManyToOne
    @JoinColumn(name = "parkId", referencedColumnName = "id")
    private BowlingPark park;
}

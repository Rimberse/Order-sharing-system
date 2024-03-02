package fr.efrei.ordersharingsystem.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "BowlingParks")
@Getter
@Setter
public class BowlingPark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "location", nullable = false)
    private String location;
}

package fr.efrei.ordersharingsystem.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Alleys")
@Getter
@Setter
@Convert(attributeName = "alleys", converter = Alley.class)
public class Alley {

    private String parkId;
    @Id
    private Long number;
}

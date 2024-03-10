package fr.efrei.ordersharingsystem.domain;

import fr.efrei.ordersharingsystem.domain.idclass.AlleyId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "alleys")
@Getter
@Setter
@NoArgsConstructor
@IdClass(AlleyId.class)
public class Alley {
    @Id
    private Integer number;

    @Id
    @ManyToOne
    @JoinColumn(name = "park_id", referencedColumnName = "id")
    private BowlingPark park;
}


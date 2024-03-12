package fr.efrei.ordersharingsystem.entity;

import fr.efrei.ordersharingsystem.entity.idclass.AlleyId;
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


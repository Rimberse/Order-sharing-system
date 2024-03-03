package fr.efrei.ordersharingsystem.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Orders")
@Getter
@Setter
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parkId", referencedColumnName = "id")
    private BowlingPark park;

    @Column(name = "alleyNumber", nullable = false)
    private int alleyNumber;

    @Column(name = "status", length = 20, nullable = false)
    private Status status = Status.PENDING;
}

package fr.efrei.ordersharingsystem.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;

@Entity
@Table(name = "Orders")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productId", referencedColumnName = "id")
    private Product product;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parkId", referencedColumnName = "id")
    private BowlingPark park;

    @Column(name = "alleyNumber", length = 100, nullable = false)
    @Check(constraints = "alleyNumber >= 1 AND alleyNumber <= 20")
    private int alleyNumber;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "status", length = 20, nullable = false)
    private Status status = Status.PENDING;
}

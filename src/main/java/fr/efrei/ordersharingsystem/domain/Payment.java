package fr.efrei.ordersharingsystem.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Payments")
@Getter
@Setter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderId", referencedColumnName = "id")
    private Order order;

    @Column(name = "amount", precision = 10, scale = 2, nullable = false)
    private double amount;

    @Column(name = "status", length = 20, nullable = false)
    private Status status = Status.PENDING;
}

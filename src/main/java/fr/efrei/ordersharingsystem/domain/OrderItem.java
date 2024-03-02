package fr.efrei.ordersharingsystem.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "OrderItems")
@Getter
@Setter
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderId", referencedColumnName = "id")
    private Order order;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productId", referencedColumnName = "id")
    private Product product;

    @Column(name = "quantity", nullable = false)
    private int quantity;
}

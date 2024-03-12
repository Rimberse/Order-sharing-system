package fr.efrei.ordersharingsystem.domain;

import fr.efrei.ordersharingsystem.domain.idclass.OrderItemId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
@IdClass(OrderItemId.class)
public class OrderItem {
    @Id
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Id
    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}

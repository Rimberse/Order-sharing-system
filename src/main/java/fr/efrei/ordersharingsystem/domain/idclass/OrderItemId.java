package fr.efrei.ordersharingsystem.domain.idclass;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class OrderItemId implements Serializable {
    private Long userId;
    private Long orderId;
    private Long product;
}

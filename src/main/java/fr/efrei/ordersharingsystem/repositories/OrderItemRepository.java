package fr.efrei.ordersharingsystem.repositories;

import fr.efrei.ordersharingsystem.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findAll();
    List<OrderItem> findAllByOrderIdAndProduct_Id(Long orderId, Long productId);
    OrderItem save(OrderItem orderItem);
    void delete(OrderItem orderItem);

}

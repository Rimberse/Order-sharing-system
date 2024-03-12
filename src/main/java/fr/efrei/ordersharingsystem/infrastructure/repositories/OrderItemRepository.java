package fr.efrei.ordersharingsystem.infrastructure.repositories;

import fr.efrei.ordersharingsystem.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findAll();
    Optional<OrderItem> findByOrderIdAndUserIdAndProduct_Id(Long orderId, Long userId, Long productId);
    OrderItem save(OrderItem orderItem);
    void delete(OrderItem orderItem);
}
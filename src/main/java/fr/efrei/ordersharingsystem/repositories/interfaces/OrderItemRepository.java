package fr.efrei.ordersharingsystem.repositories.interfaces;

import fr.efrei.ordersharingsystem.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findAll();
    List<OrderItem> findAllByOrderId(Long orderId);
    List<OrderItem> findAllByProductId(Long productId);
    List<OrderItem> findAllByOrderIdAndProductId(Long orderId, Long productId);
    List<OrderItem> findAllByOrderIdAndQuantity(Long orderId, int quantity);
    List<OrderItem> findAllByOrderIdAndProductIdAndQuantity(Long orderId, Long productId, int quantity);
    List<OrderItem> findAllByQuantityLessThan(int quantity);
    List<OrderItem> findAllByQuantityLessThanEqual(int quantity);
    List<OrderItem> findAllByQuantityGreaterThan(int quantity);
    List<OrderItem> findAllByQuantityGreaterThanEqual(int quantity);
    List<OrderItem> findAllByQuantityBetween(int start, int end);
    Optional<OrderItem> findByQuantity(int quantity);
    List<OrderItem> save(Iterable<OrderItem> orderItems);
    OrderItem save(OrderItem orderItem);
    void delete(OrderItem orderItem);
    boolean existsBy(Long id);
    boolean existsByOrderId(Long orderId);
    boolean existsByProductId(Long productId);
    boolean existsByOrderIdAndProductId(Long orderId, Long productId);
    boolean existsByQuantityBetween(int start, int end);
    boolean existsByOrderIdAndQuantity(Long orderId, int quantity);
    boolean existsByOrderIdAndProductIdAndQuantity(Long orderId, Long productId, int quantity);
}

package fr.efrei.ordersharingsystem.repositories;

import fr.efrei.ordersharingsystem.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findAll();
    OrderItem save(OrderItem orderItem);
    void delete(OrderItem orderItem);

}

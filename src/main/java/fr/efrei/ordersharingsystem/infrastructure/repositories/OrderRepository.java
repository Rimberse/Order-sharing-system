package fr.efrei.ordersharingsystem.infrastructure.repositories;

import fr.efrei.ordersharingsystem.entity.Order;
import fr.efrei.ordersharingsystem.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAll();
    List<Order> findAllByParkIdAndAlleyNumberAndStatus(Long parkId, Integer alleyNumber, Status status);
    Order save(Order order);
    void delete(Order order);

}

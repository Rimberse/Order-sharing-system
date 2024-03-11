package fr.efrei.ordersharingsystem.repositories;

import fr.efrei.ordersharingsystem.domain.Order;
import fr.efrei.ordersharingsystem.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAll();
    List<Order> findAllByParkIdAndAlleyNumberAndStatus(Long parkId, Integer alleyNumber, Status status);
    List<Order> findAllByParkIdAndAlleyNumberAndUserIdAndStatus(Long park_id, Integer alleyNumber, Long user_id, Status status);
    Order save(Order order);
    void delete(Order order);

}

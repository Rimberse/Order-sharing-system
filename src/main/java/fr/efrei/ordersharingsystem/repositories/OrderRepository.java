package fr.efrei.ordersharingsystem.repositories;

import fr.efrei.ordersharingsystem.domain.Order;
import fr.efrei.ordersharingsystem.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAll();
    List<Order> findAllByUserId(Long userId);
    List<Order> findAllByParkId(Long parkId);
    List<Order> findAllByUserIdAndParkId(Long userId, Long parkId);
    List<Order> findAllByAlleyNumber(int alleyNumber);
    List<Order> findAllByAlleyNumberAndUserId(int alleyNumber, Long userId);
    List<Order> findAllByAlleyNumberAndParkId(int alleyNumber, Long parkId);
    List<Order> findAllByUserIdAndParkIdAndAlleyNumber(Long userId, Long parkId, int alleyNumber);
    List<Order> findAllByStatus(Status status);
    List<Order> findAllByStatusAndUserId(Status status, Long userId);
    List<Order> findAllByStatusAndParkId(Status status, Long parkId);
    List<Order> findAllByStatusAndUserIdAndParkId(Status status, Long userId, Long parkId);
    List<Order> findAllByStatusAndAlleyNumber(Status status, int alleyNumber);
    List<Order> findAllByStatusAndUserIdAndAlleyNumber(Status status, Long userId, int alleyNumber);
    List<Order> findAllByStatusAndUserIdAndParkIdAndAlleyNumber(Status status, Long userId, Long parkId, int alleyNumber);
    List<Order> save(Iterable<Order> orders);
    List<Order> findAllByParkIdAndAlleyNumberAndStatus(Long parkId, Integer alleyNumber, Status status);
    Order save(Order order);
    void delete(Order order);
    boolean existsBy(Long id);
    boolean existsByUserId(Long userId);
    boolean existsByParkId(Long parkId);
    boolean existsByUserIdAndParkId(Long userId, Long parkId);
    boolean existsByAlleyNumber(int alleyNumber);
    boolean existsByUserIdAndAlleyNumber(Long userId, int alleyNumber);
    boolean existsByUserIdAndParkIdAndAlleyNumber(Long userId, Long parkId, int alleyNumber);
}

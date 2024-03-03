package fr.efrei.ordersharingsystem.repositories.interfaces;

import fr.efrei.ordersharingsystem.domain.Payment;
import fr.efrei.ordersharingsystem.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findAll();
    List<Payment> findAllByUserId(Long userId);
    List<Payment> findAllByOrderId(Long orderId);
    List<Payment> findAllByUserIdAndOrderId(Long userId, Long orderId);
    List<Payment> findAllByUserIdAndAmount(Long userId, int amount);
    List<Payment> findAllByUserIdAndOrderIdAndAmount(Long userId, Long orderId, int amount);
    List<Payment> findAllByAmountLessThan(int amount);
    List<Payment> findAllByAmountLessThanEqual(int amount);
    List<Payment> findAllByAmountGreaterThan(int amount);
    List<Payment> findAllByAmountGreaterThanEqual(int amount);
    List<Payment> findAllByAmountBetween(int start, int end);
    List<Payment> findAllByStatus(Status status);
    List<Payment> findAllByUserIdAndStatus(Long userId, Status status);
    List<Payment> findAllByOrderIdAndStatus(Long orderId, Status status);
    List<Payment> findAllByUserIdAndOrderIdAndStatus(Long userId, Long orderId, Status status);
    List<Payment> findAllByAmountAndStatus(int amount, Status status);
    List<Payment> findAllByUserIdAndOrderIdAndAmountAndStatus(Long userId, Long orderId, int amount, Status status);
    Optional<Payment> findByAmount(int amount);
    List<Payment> save(Iterable<Payment> payments);
    Payment save(Payment payment);
    void delete(Payment payment);
    boolean existsBy(Long id);
    boolean existsByUserId(Long userId);
    boolean existsByOrderId(Long orderId);
    boolean existsByUserIdAndOrderId(Long userId, Long orderId);
    boolean existsByAmountBetween(int start, int end);
    boolean existsByUserIdAndAmount(Long userId, int amount);
    boolean existsByUserIdAndOrderIdAndAmount(Long userId, Long orderId, int amount);
}

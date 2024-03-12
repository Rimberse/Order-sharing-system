package fr.efrei.ordersharingsystem.infrastructure.repositories;

import fr.efrei.ordersharingsystem.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findAll();
    Payment save(Payment payment);
    void delete(Payment payment);

}

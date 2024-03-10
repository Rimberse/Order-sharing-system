package fr.efrei.ordersharingsystem.repositories;

import fr.efrei.ordersharingsystem.domain.Payment;
import fr.efrei.ordersharingsystem.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findAll();
    Payment save(Payment payment);
    void delete(Payment payment);

}

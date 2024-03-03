package fr.efrei.ordersharingsystem.repositories.interfaces;

import fr.efrei.ordersharingsystem.domain.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notifications, Long> {
    List<Notifications> findAll();
    List<Notifications> findAllByUserId(Long userId);
    List<Notifications> findAllByMessage(String message);
    List<Notifications> findAllByUserIdAndMessageContainingIgnoreCase(Long userId, String message);
    List<Notifications> findAllByCreatedAt(Timestamp createdAt);
    List<Notifications> findAllByUserIdAndCreatedAtBetween(Long userId, Timestamp start, Timestamp end);
    List<Notifications> findAllByUserIdAndMessageContainingIgnoreCaseAndCreatedAtBetween(Long userId, String message, Timestamp start, Timestamp end);
    List<Notifications> findAllByCreatedAtBefore(Timestamp createdAt);
    List<Notifications> findAllByCreatedAtAfter(Timestamp createdAt);
    List<Notifications> findAllByCreatedAtBetween(Timestamp start, Timestamp end);
    Optional<Notifications> findByMessageIgnoreCase(String message);
    Optional<Notifications> findByCreatedAt(Timestamp createdAt);
    List<Notifications> save(Iterable<Notifications> notifications);
    Notifications save(Notifications notification);
    void delete(Notifications notification);
    boolean existsBy(Long id);
    boolean existsByUserId(Long userId);
    boolean existsByMessageContainingIgnoreCase(String message);
    boolean existsByCreatedAtBefore(Timestamp createdAt);
    boolean existsByCreatedAtAfter(Timestamp createdAt);
    boolean existsByCreatedAtBetween(Timestamp start, Timestamp end);
    boolean existsByUserIdAndCreatedAtBetween(Long userId, Timestamp start, Timestamp end);
    boolean existsByUserIdAndMessageContainingIgnoreCaseAndCreatedAtBetween(Long userId, String message, Timestamp start, Timestamp end);
}

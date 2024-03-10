package fr.efrei.ordersharingsystem.repositories;

import fr.efrei.ordersharingsystem.domain.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notifications, Long> {
    List<Notifications> findAll();
    Notifications save(Notifications notification);
    void delete(Notifications notification);

}

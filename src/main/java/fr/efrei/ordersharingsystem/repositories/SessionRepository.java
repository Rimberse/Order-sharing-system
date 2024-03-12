package fr.efrei.ordersharingsystem.repositories;

import fr.efrei.ordersharingsystem.domain.Session;
import fr.efrei.ordersharingsystem.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findAll();
    List<Session> findAllByParkIdAndAlleyNumberAndStatus(Long parkId, Integer alleyNumber, Status status);
    Session save(Session session);
    void delete(Session session);
}

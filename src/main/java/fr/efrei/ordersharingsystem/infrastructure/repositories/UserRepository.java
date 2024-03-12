package fr.efrei.ordersharingsystem.infrastructure.repositories;

import fr.efrei.ordersharingsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    @Query(
        "SELECT u FROM User u WHERE u.id IN (" +
            "SELECT oi.userId FROM OrderItem oi WHERE oi.orderId = ?1" +
        ")"
    )
    List<User> findAllUsersInOrderId(Long orderId);
    User save(User user);
    void delete(User user);
}

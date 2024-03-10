package fr.efrei.ordersharingsystem.repositories;

import fr.efrei.ordersharingsystem.domain.Role;
import fr.efrei.ordersharingsystem.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    User save(User user);
    void delete(User user);
}

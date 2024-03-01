package fr.efrei.ordersharingsystem.repositories.interfaces;

import fr.efrei.ordersharingsystem.domain.Role;
import fr.efrei.ordersharingsystem.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    List<User> findAllBy(Role role);
    Optional<User> findByFirstName(String firstName);
    Optional<User> findByLastName(String lastName);
    Optional<User> findByFullName(String fullName);
    Optional<User> findByName(String name);
    Optional<User> findByEmail(String email);
    boolean matchesHashedPassword(String hashValue);
    Optional<User> findByPhoneNumber(String phoneNumber);
    List<User> save(Iterable<User> users);
    User save(User user);
    void delete(User user);
    boolean exists(Long id);
}

package fr.efrei.ordersharingsystem.services;

import fr.efrei.ordersharingsystem.domain.Role;
import fr.efrei.ordersharingsystem.domain.User;
import fr.efrei.ordersharingsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findAllByRole(Role role) {
        return userRepository.findAllByRole(role);
    }

    public List<User> findAllByBowlingPark(Long assignedBowlingParkId) {
        return userRepository.findAllByAssignedBowlingParkId(assignedBowlingParkId);
    }

    public User findById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return returnIfPresentOrElseThrow(user, "No user has been found with the given id: " + id);
    }

    public User findByFirstName(String firstName) {
        User user = userRepository.findByFirstName(firstName).orElse(null);
        return returnIfPresentOrElseThrow(user, "No user has been found with the given first name: " + firstName);
    }

    public User findByLastName(String lastName) {
        User user = userRepository.findByLastName(lastName).orElse(null);
        return returnIfPresentOrElseThrow(user, "No user has been found with the given last name: " + lastName);
    }

    public User findByFirstNameAndLastName(String firstName, String lastName) {
        User user = userRepository.findByFirstNameAndLastName(firstName, lastName).orElse(null);
        return returnIfPresentOrElseThrow(user, "No user has been found with the given full name: " + (firstName + " " + lastName));
    }

    public User findByName(String name) {
        User user = userRepository.findByName(name).orElse(null);
        return returnIfPresentOrElseThrow(user, "No user has been found with the given name: " + name);
    }

    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        return returnIfPresentOrElseThrow(user, "No user has been found with the given email: " + email);
    }

    public User findByPhoneNumber(String phoneNumber) {
        User user = userRepository.findByPhoneNumber(phoneNumber).orElse(null);
        return returnIfPresentOrElseThrow(user, "No user has been found with the given phone number: " + phoneNumber);
    }

    private User returnIfPresentOrElseThrow(User user, String message) {
        if (user != null) {
            return user;
        } else {
            // todo: CustomException
            throw new IllegalArgumentException(message);
        }
    }

    public List<User> save(Iterable<User> users) {
        return userRepository.save(users);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public boolean existsBy(Long id) {
        return userRepository.existsBy(id);
    }

    public boolean matchesPassword(String hashedPassword) {
        return userRepository.existsByPassword(hashedPassword);
    }

    public boolean matchesUserPassword(Long id, String hashedPassword) {
        return userRepository.existsByIdAndPassword(id, hashedPassword);
    }
}

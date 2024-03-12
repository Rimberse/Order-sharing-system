package fr.efrei.ordersharingsystem.dataaccess.projections.handlers;

import fr.efrei.ordersharingsystem.dataaccess.projections.UserProjectionService;
import fr.efrei.ordersharingsystem.dataaccess.queries.users.GetUserByRole;
import fr.efrei.ordersharingsystem.entity.User;
import fr.efrei.ordersharingsystem.exceptions.ItemNotFoundException;
import fr.efrei.ordersharingsystem.dataaccess.queries.users.GetUserByAssignedBowlingParkIdQuery;
import fr.efrei.ordersharingsystem.dataaccess.queries.users.GetUserByCredentialsQuery;
import fr.efrei.ordersharingsystem.dataaccess.queries.users.GetUserByIdQuery;
import fr.efrei.ordersharingsystem.infrastructure.repositories.BowlingParkRepository;
import fr.efrei.ordersharingsystem.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserProjectionHandler implements UserProjectionService {
    private final UserRepository userRepository;
    private final BowlingParkRepository bowlingParkRepository;

    @Autowired
    public UserProjectionHandler(UserRepository userRepository, BowlingParkRepository bowlingParkRepository) {
        this.userRepository = userRepository;
        this.bowlingParkRepository = bowlingParkRepository;
    }

    public List<User> handle() {
        return userRepository.findAll();
    }

    public List<User> handle(GetUserByRole query) {
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getRole() == query.role())
                .collect(Collectors.toList());
    }

    public List<User> handle(GetUserByAssignedBowlingParkIdQuery query) {
        return userRepository.findAll()
                .stream()
                .filter(
                        user -> user.getAssignedBowlingPark() == bowlingParkRepository.findById(query.assignedBowlingParkId())
                                .orElseThrow(() -> new ItemNotFoundException("BowlingPark", query.assignedBowlingParkId()))
                )
                .collect(Collectors.toList());
    }

    public User handle(GetUserByIdQuery query) {
        return userRepository.findById(query.userId()).orElse(null);
    }

    public User handle(GetUserByCredentialsQuery query) {
        return userRepository.findAll()
                .stream()
                .filter(
                        user -> (query.name() != null)
                        ? user.getName().equals(query.name())
                        : user.getEmail().equals(query.email())
                        && user.getPassword().equals(query.password())
                )
                .findAny().orElse(null);
    }
}

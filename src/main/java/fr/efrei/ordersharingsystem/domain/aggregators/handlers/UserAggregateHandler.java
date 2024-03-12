package fr.efrei.ordersharingsystem.domain.aggregators.handlers;

import fr.efrei.ordersharingsystem.domain.aggregators.UserAggregateService;
import fr.efrei.ordersharingsystem.domain.commands.users.CreateUserCommand;
import fr.efrei.ordersharingsystem.domain.commands.users.DeleteUserCommand;
import fr.efrei.ordersharingsystem.domain.commands.users.ModifyUserCommand;
import fr.efrei.ordersharingsystem.entity.BowlingPark;
import fr.efrei.ordersharingsystem.entity.User;
import fr.efrei.ordersharingsystem.exceptions.ItemNotFoundException;
import fr.efrei.ordersharingsystem.infrastructure.repositories.BowlingParkRepository;
import fr.efrei.ordersharingsystem.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAggregateHandler implements UserAggregateService {
    UserRepository userRepository;
    BowlingParkRepository bowlingParkRepository;

    @Autowired
    public UserAggregateHandler(UserRepository userRepository, BowlingParkRepository bowlingParkRepository) {
        this.userRepository = userRepository;
        this.bowlingParkRepository = bowlingParkRepository;
    }

    public User handle(CreateUserCommand command) {
        User user = new User();
        user.setFirstName(command.firstName());
        user.setLastName(command.lastName());
        user.setName(command.name());
        user.setEmail(command.email());
        user.setPassword(command.password());
        user.setPhoneNumber(command.phoneNumber());
        user.setRole(command.role());

        BowlingPark bowlingPark = bowlingParkRepository.findById(command.assignedBowlingParkId()).orElse(null);

        if (bowlingPark == null) {
            throw new ItemNotFoundException("Bowling park", command.assignedBowlingParkId());
        }

        user.setAssignedBowlingPark(bowlingPark);
        return userRepository.save(user);
    }

    public void handle(ModifyUserCommand command) {
        User user = userRepository.findById(command.id()).orElse(null);

        if (user == null) {
            throw new ItemNotFoundException("User", command.id());
        }

        user.setName(command.name());
        user.setEmail(command.email());
        user.setPassword(command.password());
        user.setPhoneNumber(command.phoneNumber());

        BowlingPark bowlingPark = bowlingParkRepository.findById(command.assignedBowlingParkId()).orElse(null);

        if (bowlingPark == null) {
            throw new ItemNotFoundException("Bowling park", command.assignedBowlingParkId());
        }

        user.setAssignedBowlingPark(bowlingPark);
        userRepository.save(user);
    }

    public void handle(DeleteUserCommand command) {
        userRepository.deleteById(command.id());
    }
}

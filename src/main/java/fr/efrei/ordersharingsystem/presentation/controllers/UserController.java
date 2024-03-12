package fr.efrei.ordersharingsystem.presentation.controllers;

import fr.efrei.ordersharingsystem.dataaccess.queries.users.GetUserByAssignedBowlingParkIdQuery;
import fr.efrei.ordersharingsystem.dataaccess.queries.users.GetUserByIdQuery;
import fr.efrei.ordersharingsystem.dataaccess.queries.users.GetUserByRole;
import fr.efrei.ordersharingsystem.domain.aggregators.UserAggregateService;
import fr.efrei.ordersharingsystem.domain.commands.users.CreateUserCommand;
import fr.efrei.ordersharingsystem.domain.commands.users.DeleteUserCommand;
import fr.efrei.ordersharingsystem.domain.commands.users.ModifyUserCommand;
import fr.efrei.ordersharingsystem.entity.Role;
import fr.efrei.ordersharingsystem.entity.User;
import fr.efrei.ordersharingsystem.dataaccess.projections.UserProjectionService;
import fr.efrei.ordersharingsystem.dataaccess.queries.users.GetUserByCredentialsQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private UserAggregateService userAggregateService;
    private UserProjectionService userProjectionService;

    @Autowired
    public UserController(UserAggregateService userAggregateService, UserProjectionService userProjectionService) {
        this.userAggregateService = userAggregateService;
        this.userProjectionService = userProjectionService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userProjectionService.handle());
    }

    @GetMapping("/role")
    public ResponseEntity<List<User>> getUserByRole(@RequestParam("role") String role) {
        Role roleType = (role.equals(Role.CUSTOMER)) ? Role.CUSTOMER : Role.AGENT;
        GetUserByRole query = new GetUserByRole(roleType);
        return ResponseEntity.ok(userProjectionService.handle(query));
    }

    @GetMapping("/club")
    public ResponseEntity<List<User>> getUserByBowlingPark(@RequestParam("role") Optional<String> role, @RequestParam("assignedBowlingParkId") Long assignedBowlingParkId) {
        String roleValue = role.orElse(null);
        Role roleType = (roleValue != null && role.equals(Role.CUSTOMER)) ? Role.CUSTOMER : (role.isPresent()) ? Role.AGENT : null;
        GetUserByAssignedBowlingParkIdQuery query = new GetUserByAssignedBowlingParkIdQuery(roleType, assignedBowlingParkId);
        return ResponseEntity.ok(userProjectionService.handle(query));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        GetUserByIdQuery query = new GetUserByIdQuery(id);
        return ResponseEntity.ok(userProjectionService.handle(query));
    }

    @GetMapping("/credentials")
    public ResponseEntity<User> getUserByCredentials(@RequestParam("name") Optional<String> name, @RequestParam("email") Optional<String> email,
                                                     @RequestParam("password") String password) {
        GetUserByCredentialsQuery query = new GetUserByCredentialsQuery(name.orElse(null), email.orElse(null), password);
        return ResponseEntity.ok(userProjectionService.handle(query));
    }

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody CreateUserCommand command) {
        User user = userAggregateService.handle(command);
        URI location = URI.create("/api/v1/users/" + user.getId());
        return ResponseEntity.created(location).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modifyUser(@PathVariable Long id, @RequestBody ModifyUserCommand command) {
        if (id != command.id()) {
            return ResponseEntity.badRequest().body("The id in the path and in the body must be the same");
        }

        userAggregateService.handle(command);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        DeleteUserCommand command = new DeleteUserCommand(id);
        userAggregateService.handle(command);
        return ResponseEntity.noContent().build();
    }
}

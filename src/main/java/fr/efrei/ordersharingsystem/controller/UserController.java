package fr.efrei.ordersharingsystem.controller;

import fr.efrei.ordersharingsystem.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @PostMapping("/create")
    public ResponseEntity<List<User>> createUser() { //create user with info from request body
        return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping("/update")
    public ResponseEntity<List<User>> updateUser() { //update user info
        return ResponseEntity.ok(new ArrayList<>());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<List<User>> deleteUser(@PathVariable Integer id) { //delete user
        return ResponseEntity.ok(new ArrayList<>());
    }

}

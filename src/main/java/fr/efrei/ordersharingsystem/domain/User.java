package fr.efrei.ordersharingsystem.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName", length = 50)
    private String firstName;

    @Column(name = "lastName", length = 50)
    private String lastName;

    @Column(name = "name", length = 50, unique = true)
    private String name;

    @Column(name = "email", length = 100, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phoneNumber", length = 20)
    private String phoneNumber;

    @Column(name = "role", length = 10, nullable = false)
    private Role role;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "BowlingParks", referencedColumnName = "id")
    BowlingPark assignedBowlingParkId;
}

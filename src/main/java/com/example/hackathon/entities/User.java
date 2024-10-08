package com.example.hackathon.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    @NotBlank(message = "User must have a first name.")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "User must have a last name.")
    private String lastName;

    @Column
    @Min(value = 0, message = "Age must be between 0 and 100.")
    @Max(value = 99, message = "Age must be between 0 and 100.")
    private byte age;

    @Column
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "User must have a valid email.")
    private String email;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<ActivityEnrollment> activities;

}

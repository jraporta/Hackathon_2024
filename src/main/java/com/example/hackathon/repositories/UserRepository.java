package com.example.hackathon.repositories;

import com.example.hackathon.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

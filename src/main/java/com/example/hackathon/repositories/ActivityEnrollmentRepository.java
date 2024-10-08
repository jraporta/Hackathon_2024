package com.example.hackathon.repositories;

import com.example.hackathon.entities.ActivityEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityEnrollmentRepository extends JpaRepository<ActivityEnrollment, Long> {
}

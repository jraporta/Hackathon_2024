package com.example.hackathon.services;

import com.example.hackathon.entities.Activity;
import com.example.hackathon.entities.ActivityEnrollment;
import com.example.hackathon.entities.User;
import com.example.hackathon.exceptions.IdNotFoundException;
import com.example.hackathon.repositories.ActivityEnrollmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ActivityEnrollmentService {

  ActivityService activityService;
  UserService userService;

  ActivityEnrollmentRepository activityEnrollmentRepository;

  public ActivityEnrollmentService(ActivityService activityService, UserService userService,
                                   ActivityEnrollmentRepository activityEnrollmentRepo){
    this.activityService = activityService;
    this.userService = userService;
    this.activityEnrollmentRepository = activityEnrollmentRepo;
  }

  public ResponseEntity<String> enrollInActivity(long idActivity, long idUser) throws IdNotFoundException {
    Activity activity = activityService.findById(idActivity);
    User user = userService.findById(idUser);
    List<User> users = activity.getUsers().stream().map(ActivityEnrollment::getUser).toList();
    if (users.contains(user)){
      log.info("Failed to enroll, user id={} is already enrolled in activity id={}.", user.getId(), activity.getId());
      return ResponseEntity.status(HttpStatus.CONFLICT).body("User already enrolled in activity.");
    }else if (users.size() >= activity.getCapacity()){
      log.info("User id={} failed to enroll, activity id={} is full.", user.getId(), activity.getId());
      return ResponseEntity.status(HttpStatus.CONFLICT).body("The activity is full.");
    }else {
      activityEnrollmentRepository.save(new ActivityEnrollment(activity, user));
      log.info("User id={} enrolled in activity id={}.", user.getId(), activity.getId());
      return ResponseEntity.status(HttpStatus.OK).body("Enrolled in activity.");
    }
  }
}

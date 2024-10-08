package com.example.hackathon.controllers;

import com.example.hackathon.exceptions.IdNotFoundException;
import com.example.hackathon.entities.Activity;
import com.example.hackathon.entities.User;
import com.example.hackathon.services.ActivityEnrollmentService;
import com.example.hackathon.services.ActivityService;
import com.example.hackathon.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appActivitats")
public class Controller {

    private final UserService userService;
    private final ActivityService activityService;
    private final ActivityEnrollmentService activityEnrollmentService;

    public Controller(UserService userService,
                      ActivityService activityService,
                      ActivityEnrollmentService activityEnrollmentService){
        this.userService = userService;
        this.activityService = activityService;
        this.activityEnrollmentService = activityEnrollmentService;
    }

    @PostMapping("/user")
    public ResponseEntity<String> postUser(@Valid @RequestBody User user){
        return userService.saveUser(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @Valid @RequestBody User user)
            throws IdNotFoundException {
        return userService.updateUser(id, user);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id) throws IdNotFoundException {
        return userService.getUser(id);
    }

    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable Long id) throws IdNotFoundException {
        return userService.deleteUser(id);
    }



    @PostMapping("/activity")
    public ResponseEntity<String> postActivity(@Valid @RequestBody Activity activity){
        return activityService.saveActivity(activity);
    }

    @GetMapping("/activities/{id}")
    public ResponseEntity<Activity> getActivity(@PathVariable long id) throws IdNotFoundException {
        return activityService.getActivity(id);
    }

    @GetMapping("/activities")
    public ResponseEntity<List<Activity>> exportActivities(){
        return activityService.exportActivities();
    }

    @PostMapping("/activities")
    public ResponseEntity<String> importActivities(@Valid @RequestBody List<@Valid Activity> activities){
        return activityService.importActivities(activities);
    }

    @PostMapping("/activities/{idActivity}/users/{idUser}")
    public ResponseEntity<String> enrollInActivity(@PathVariable long idActivity, @PathVariable long idUser)
            throws IdNotFoundException {
        return activityEnrollmentService.enrollInActivity(idActivity, idUser);
    }
}

package com.example.hackathon.services;

import com.example.hackathon.entities.User;
import com.example.hackathon.exceptions.IdNotFoundException;
import com.example.hackathon.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Slf4j
public class UserService {

  private final UserRepository userRepo;

  public UserService(UserRepository userRepo){
    this.userRepo = userRepo;
  }

  public ResponseEntity<String> saveUser(User user) {
    user.setId(0);
    User newUser = userRepo.save(user);
    log.info("Saved user");
    return ResponseEntity.ok("User saved with ID: " + newUser.getId());
  }

  public ResponseEntity<String> updateUser(Long id, User user) throws IdNotFoundException {
    User updatedUser = this.findById(id);
    updatedUser.setFirstName(user.getFirstName());
    updatedUser.setLastName(user.getLastName());
    updatedUser.setAge(user.getAge());
    updatedUser.setEmail(user.getEmail());
    userRepo.save(updatedUser);
    log.info("Updated user");
    return ResponseEntity.status(HttpStatus.OK).body("User edited.");
  }

  public ResponseEntity<User> getUser(long id) throws IdNotFoundException {
    try {
      return ResponseEntity.status(HttpStatus.OK).body(userRepo.findById(id).get());
    }catch (NoSuchElementException ex){
      throw new IdNotFoundException("User not found.");
    }
  }

  public String deleteUser(Long id) throws IdNotFoundException {
    User user = this.findById(id);
    userRepo.delete(user);
    log.info("user with id={} deleted", user.getId());
    return "User deleted";
  }

  public User findById(Long id) throws IdNotFoundException {
    try {
      return userRepo.findById(id).get();
    }catch (NoSuchElementException ex){
      throw new IdNotFoundException("User not found.");
    }
  }
}

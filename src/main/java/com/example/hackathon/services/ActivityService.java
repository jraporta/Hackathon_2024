package com.example.hackathon.services;

import com.example.hackathon.entities.Activity;
import com.example.hackathon.exceptions.IdNotFoundException;
import com.example.hackathon.repositories.ActivityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class ActivityService {

  private final ActivityRepository activityRepo;

  public ActivityService(ActivityRepository activityRepo){
    this.activityRepo = activityRepo;
  }

  public ResponseEntity<String> saveActivity(Activity activity) {
    activity.setId(0);
    Activity savedActivity = activityRepo.save(activity);
    log.info("Saved activity");
    return ResponseEntity.status(HttpStatus.OK).body(
            String.format("Activity saved with ID: %d.", savedActivity.getId()));
  }

  public ResponseEntity<Activity> getActivity(long id) throws IdNotFoundException {
    return ResponseEntity.status(HttpStatus.OK).body(this.findById(id));
  }

  public ResponseEntity<List<Activity>> exportActivities() {
    return ResponseEntity.status(HttpStatus.OK).body(activityRepo.findAll());
  }

  public ResponseEntity<String> importActivities(List<Activity> activities) {
    activities.forEach(a -> a.setId(0));
    return ResponseEntity.status(HttpStatus.OK).body(String.format("Imported %d activities.",
            activityRepo.saveAll(activities).size()));
  }

  public Activity findById(Long id) throws IdNotFoundException {
    try {
      return activityRepo.findById(id).get();
    }catch (NoSuchElementException ex){
      throw new IdNotFoundException("Activity not found.");
    }
  }

}

package com.example.hackathon.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Getter
@Setter
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotBlank(message = "Activity must have a name.")
    private String name;

    @Column(name = "scheduled_date_time")
    @NotNull(message = "Activity must have a scheduledDateTime field.")
    private LocalDateTime scheduledDateTime;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    @NotNull(message = "Activity must have a capacity.")
    private Integer capacity;

    @OneToMany(mappedBy = "activity")
    @JsonIgnore
    List<ActivityEnrollment> users;

}

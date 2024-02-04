package com.gil.school.api.model;

import com.gil.school.api.model.Validators.Validators;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "school_id")
    private long school_id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "School location is required")
    @Validators.ValidLocation(message = "Invalid School Location")
    private String location_school;

    @NotBlank(message = "Website URL is required")
    @Validators.ValidWebsiteURL(message = "Invalid website URL")
    private String website;

    @NotBlank(message = "Description is required")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "school_course",
            joinColumns = @JoinColumn(name = "school_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    @JsonIgnore
    private Set<Course> courses = new HashSet<>();


}

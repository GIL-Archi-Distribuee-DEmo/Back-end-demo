package com.gil.school.api.model;

import com.gil.school.api.model.Enumeration.CourseLanguage;
import com.gil.school.api.model.Enumeration.CourseType;
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
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long course_id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Price is required")
    private Double price;

    @NotBlank(message = "Capacities is required")
    private Integer capacities;

    @NotBlank(message = "Course Language is required")
    @Enumerated(EnumType.STRING)
    private CourseLanguage course_language;

    @NotBlank(message = "Course Type is required")
    @Enumerated(EnumType.STRING)
    private CourseType course_type;

    @ManyToMany(mappedBy = "courses")
    @JsonIgnore
    private Set<School> schools = new HashSet<>();


}

package com.gil.school.api.repository;



import com.gil.school.api.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Long> {
    List<Course> findByName(String name);
}

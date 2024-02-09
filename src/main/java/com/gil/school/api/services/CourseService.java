package com.gil.school.api.services;

import com.gil.school.api.model.Course;
import com.gil.school.api.model.School;
import com.gil.school.api.repository.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> getAllCourse() {
        List<Course> courseList = new ArrayList<>();
        courseRepository.findAll().forEach(courseList::add);
        return courseList;
    }

            public Optional<Course> getACourse(Long idCourse) {
                return courseRepository.findById(idCourse);
            }

    public Course updateCourse(Course updatedCourse) {
        Long CourseId = updatedCourse.getCourse_id();
        if (CourseId != null && courseRepository.existsById(CourseId)) {
            return courseRepository.save(updatedCourse);
        } else {
            throw new EntityNotFoundException("Course not found");
        }
    }

    public Course updatPartialCourse(Long id, Course updatedCourse) {
        return courseRepository.findById(id)
                .map(existingCourse -> {
                    if (updatedCourse.getName() != null) existingCourse.setName(updatedCourse.getName());
                    if (updatedCourse.getCapacities() != null)
                        existingCourse.setCapacities(updatedCourse.getCapacities());
                    if (updatedCourse.getPrice() != null) existingCourse.setPrice(updatedCourse.getPrice());
                    if (updatedCourse.getDescription() != null)
                        existingCourse.setDescription(updatedCourse.getDescription());
                    if (updatedCourse.getSchools() != null)
                        existingCourse.setSchools(updatedCourse.getSchools());
                    return courseRepository.save(existingCourse);
                }).orElseThrow(() -> new EntityNotFoundException("Course not found"));
    }

    public List<Course> findCourseByName(String name) {
        return courseRepository.findByName(name);
    }


    public void deleteCourse(Long id) {
        Optional<Course> CourseOptional = courseRepository.findById(id);

        if (CourseOptional.isPresent()) {
            Course course = CourseOptional.get();
            courseRepository.delete(course);
        } else {
            throw new EntityNotFoundException("Course with ID " + id + " not found");
        }
    }

    public Set<School> findSchoolByCourse(Long courseId) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);

        return courseOptional.map(Course::getSchools).orElse(Collections.emptySet());
    }
}

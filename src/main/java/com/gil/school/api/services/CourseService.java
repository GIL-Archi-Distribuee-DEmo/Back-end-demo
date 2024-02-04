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

            public Optional<Course> getACourse(Long idFormation) {
                return courseRepository.findById(idFormation);
            }

    public Course updateCourse(Course updatedCourse) {
        Long formationId = updatedCourse.getCourse_id();
        if (formationId != null && courseRepository.existsById(formationId)) {
            return courseRepository.save(updatedCourse);
        } else {
            throw new EntityNotFoundException("Formation not found");
        }
    }

    public Course updatPartialCourse(Long id, Course updatedCourse) {
        return courseRepository.findById(id)
                .map(existingFormation -> {
                    if (updatedCourse.getName() != null) existingFormation.setName(updatedCourse.getName());
                    if (updatedCourse.getCapacities() != null)
                        existingFormation.setCapacities(updatedCourse.getCapacities());
                    if (updatedCourse.getPrice() != null) existingFormation.setPrice(updatedCourse.getPrice());
                    if (updatedCourse.getDescription() != null)
                        existingFormation.setDescription(updatedCourse.getDescription());
                    if (updatedCourse.getSchools() != null)
                        existingFormation.setSchools(updatedCourse.getSchools());
                    return courseRepository.save(existingFormation);
                }).orElseThrow(() -> new EntityNotFoundException("Formation not found"));
    }

    public List<Course> findFormationByName(String name) {
        return courseRepository.findByName(name);
    }


    public void deleteFormation(Long id) {
        Optional<Course> formationOptional = courseRepository.findById(id);

        if (formationOptional.isPresent()) {
            Course course = formationOptional.get();
            courseRepository.delete(course);
        } else {
            throw new EntityNotFoundException("Formation with ID " + id + " not found");
        }
    }

    public Set<School> findSchoolByCourse(Long courseId) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);

        return courseOptional.map(Course::getSchools).orElse(Collections.emptySet());
    }
}

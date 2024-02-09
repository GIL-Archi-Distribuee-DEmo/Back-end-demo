package com.gil.school.api.controller.rest;


import com.gil.school.api.model.Course;
import com.gil.school.api.model.School;
import com.gil.school.api.services.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;



import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/course")
public class CourseController {

    @Autowired
    private CourseService courseService;


    @GetMapping("")
    public List<Course> getAllCourse() {
        return courseService.getAllCourse();
    }

    @PostMapping("")
    public ResponseEntity<Object> createCourse(@RequestBody @Valid Course course, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        Course savedCourse = courseService.saveCourse(course);
        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCourse(@PathVariable Long id, @RequestBody Course updateCourse) {
        // Set the ID of the updated Course to match the path variable
        updateCourse.setCourse_id(id);

        try {
            Course updated = courseService.updateCourse(updateCourse);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}")
    public Optional<Course> getCourse(@PathVariable Long id) {
        return courseService.getACourse(id);
    }

    @GetMapping("/name")
    public List<Course> getCourseByName(
            @RequestParam("name") String name
    ) {
        return courseService.findCourseByName(name);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> partialUpdateCourse(
            @PathVariable Long id,
            @RequestBody Course updatedCourse
    ) {
        try {
            Course updated = courseService.updatPartialCourse(id, updatedCourse);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        try {
            courseService.deleteCourse(id);
            return ResponseEntity.ok("Course with ID " + id + " deleted successfully.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request.");
        }
    }

    @GetMapping("/{courseId}/schools")
    @Operation(summary = "Get All Schools that  give a Course ", description = "Returns  the school ..")
    public ResponseEntity<Set<School>> getSchoolsByCourseId(@PathVariable Long courseIdId) {
        Set<School> schools = courseService.findSchoolByCourse(courseIdId);

        if (schools.isEmpty()) {
            return ResponseEntity.noContent().build(); // Return 204 No Content if no  found
        } else {
            return ResponseEntity.ok(schools); // Return 200 OK with Courses
        }
    }
}

package com.gil.school.api.controller;


import com.gil.school.api.model.Course;
import com.gil.school.api.model.School;
import com.gil.school.api.services.SchoolService;
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
@RequestMapping("/api/v1/school")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;


    @GetMapping("")
    @Operation(summary = "Get All School present in the dataBase", description = "Returns All School Present without the associate formation .")
    public List<School> getAllScool(){
        return schoolService.getAllSchool() ;
    }

    @PostMapping("")
    @Operation(summary = "Add A new School in dataBase can be perform by the Admin only", description = "He need to fill all the required fill and make sure to give a real position of the school to locate it .")
    public ResponseEntity<Object> createSchool(@RequestBody @Valid School school, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        School savedSchool = schoolService.saveSchool(school);
        return new ResponseEntity<>(savedSchool, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Update a School here(The Admin) he need to re-write all the fields ", description = "Returns  the updated schools")
    public ResponseEntity<Object> updateSchool(@PathVariable Long id, @RequestBody School updatedSchool) {
        // Set the ID of the updated school to match the path variable
        updatedSchool.setSchool_id(id);

        try {
            School updated = schoolService.updateSchool(updatedSchool);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PatchMapping("/{id}")
    @Operation(summary = " Partial Update of a School here(The Admin) he need to re-write all the fields ", description = "Returns  the updated schools")
    public ResponseEntity<Object> partialUpdateSchool(
            @PathVariable Long id,
            @RequestBody School updatedSchool
    ) {
        try {
            School updated = schoolService.updatPartialeSchool(id, updatedSchool);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping(value = "/{id}")
    @Operation(summary = "Get A particular school by it's Id ", description = "Returns  the school ..")
    public Optional<School> getSChool(@PathVariable Long id) {
        return schoolService.getASchool(id);
    }

    @GetMapping("/area")
    @Operation(summary = "Search School by it position ..", description = "Here the purpose is to move througth a map and see all school presents")
    public List<School> getSchoolsAtLocation(
            @RequestParam("location") String location
    ) {
        return schoolService.findSchoolsByLocation(location);
    }

    @GetMapping("/name")
    @Operation(summary = "Get A particular school by it's Name ", description = "Returns  the school ..")
    public List<School> getSchoolsByName(
            @RequestParam("name") String name
    ) {
        return schoolService.findSchoolByName(name);
    }
    @GetMapping("/{schoolId}/courses")
    @Operation(summary = "Get All Courses  inside a school ", description = "Returns  the school ..")
    public ResponseEntity<Set<Course>> getCoursesBySchoolId(@PathVariable Long schoolId) {
        Set<Course> courses = schoolService.findFormationsBySchoolId(schoolId);

        if (courses.isEmpty()) {
            return ResponseEntity.noContent().build(); // Return 204 No Content if no formations found
        } else {
            return ResponseEntity.ok(courses); // Return 200 OK with formations
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSchool(@PathVariable Long id) {
        try {
            schoolService.deleteSchool(id);
            return ResponseEntity.ok("School with ID " + id + " deleted successfully.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request.");
        }
    }
}

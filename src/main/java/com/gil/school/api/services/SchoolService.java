package com.gil.school.api.services;
import com.gil.school.api.model.Course;
import com.gil.school.api.model.School;
import com.gil.school.api.repository.SchoolRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class SchoolService {
    @Autowired
    private SchoolRepository schoolRepository ;

    public School saveSchool(School school){
         return schoolRepository.save(school);
    }

    public List<School> getAllSchool(){
       List<School> schoolList =new ArrayList<>() ;
       schoolRepository.findAll().forEach(schoolList::add);
       return schoolList ;
    }

    public Optional<School> getASchool(Long idSchool){
        return  schoolRepository.findById(idSchool);
    }

    public School updateSchool(School updatedSchool) {
        Long schoolId = updatedSchool.getSchool_id();
        if (schoolId != null && schoolRepository.existsById(schoolId)) {
            return schoolRepository.save(updatedSchool);
        } else {
            throw new EntityNotFoundException("School not found");
        }
    }

    public School updatPartialeSchool(Long id, School updatedSchool) {
        return schoolRepository.findById(id)
                .map(existingSchool -> {
                    if (updatedSchool.getName() != null) existingSchool.setName(updatedSchool.getName());
                    if (updatedSchool.getLocation_school() != null) existingSchool.setLocation_school(updatedSchool.getLocation_school());
                    if (updatedSchool.getWebsite() != null) existingSchool.setWebsite(updatedSchool.getWebsite());
                    if (updatedSchool.getDescription() != null) existingSchool.setDescription(updatedSchool.getDescription());
                    return schoolRepository.save(existingSchool);
                })
                .orElseThrow(() -> new EntityNotFoundException("School not found"));
    }

    public List<School> findSchoolsByLocation(String location) {
        String[] coordinates = location.split(",");
        double latitude = Double.parseDouble(coordinates[0]);
        double longitude = Double.parseDouble(coordinates[1]);

        return schoolRepository.findSchoolsByLocation(latitude, longitude);
    }

    public List<School> findSchoolByName(String name) {
        return schoolRepository.findByName(name);
    }

    public void deleteSchool(Long id) {
        Optional<School> schoolOptional = schoolRepository.findById(id);

        if (schoolOptional.isPresent()) {
            School school = schoolOptional.get();
            schoolRepository.delete(school);
        } else {
            throw new EntityNotFoundException("School with ID " + id + " not found");
        }
    }
    public Set<Course> findCoursesBySchoolId(Long schoolId) {
        Optional<School> schoolOptional = schoolRepository.findById(schoolId);

        return schoolOptional.map(School::getCourses).orElse(Collections.emptySet());
    }
}

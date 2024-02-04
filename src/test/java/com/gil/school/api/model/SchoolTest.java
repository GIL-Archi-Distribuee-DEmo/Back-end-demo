package com.gil.school.api.model;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class SchoolTest {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();
    @Test
    public void testValidationModelSchoolSucces(){
        // Create a School object with a valid website URL
        School school1 = new School();
        school1.setName("Example School");
        school1.setLocation_school("40.7128,-74.0060");
        school1.setWebsite("https://www.example.com");
        school1.setDescription("A sample school");
        // Create a ValidatorFactory and Validator instance
        Set<ConstraintViolation<School>> violations1 = validator.validate(school1);
        assertTrue(violations1.isEmpty());

    }

    @Test
    public void testValidationSchoolError(){
        // Create a School object with an invalid website URL
        School school2 = new School();
        school2.setName("Invalid School");
        school2.setLocation_school("Town");
        school2.setWebsite("invalid-url"); // Invalid URL
        school2.setDescription("Another school");
        Set<ConstraintViolation<School>> violations2 = validator.validate(school2);
        assertFalse(violations2.isEmpty());
        for (ConstraintViolation<School> violation : violations2) {
            System.out.println(violation.getMessage());
        }

    }
}

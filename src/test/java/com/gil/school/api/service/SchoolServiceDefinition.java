package com.gil.school.api.service;


import com.gil.school.api.model.School;
import com.gil.school.api.services.SchoolService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SchoolServiceDefinition {

    private List<School> schools;
    private School updatedSchool;
    private int deletedSchoolId;

    private SchoolService schoolService = mock(SchoolService.class);

    @Given("the following schools exist:")
    public void the_following_schools_exist(DataTable dataTable) {
        List<Map<String, String>> schoolsData = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> schoolData : schoolsData) {
            School school = new School();
            school.setName(schoolData.get("name"));
            school.setLocation_school(schoolData.get("location_school"));
            school.setWebsite(schoolData.get("website"));
            school.setDescription(schoolData.get("description"));

            when(schoolService.saveSchool(any(School.class))).thenReturn(school);
        }
    }

    @When("I request the school with name {string}")
    public void i_request_the_school_with_name(String name) {
        schools = new ArrayList<>();
        School schoolA = new School();
        schoolA.setName("School A");
        schoolA.setLocation_school("40.7128,-74.0060");
        schoolA.setWebsite("http://www.schoolA.com");
        schoolA.setDescription("School A Description");
        schools.add(schoolA);
        when(schoolService.findSchoolByName(name)).thenReturn(schools);
    }

    @When("I request schools near location {string}")
    public void i_request_schools_near_location(String location) {
        schools = new ArrayList<>();
        School schoolA = new School();
        schoolA.setName("School A");
        schoolA.setLocation_school("40.7128,-74.0060");
        schoolA.setWebsite("http://www.schoolA.com");
        schoolA.setDescription("School A Description");
        schools.add(schoolA);
        when(schoolService.findSchoolsByLocation(location)).thenReturn(schools);
    }

    @Then("the response should contain the following JSON:")
    public void the_response_should_contain_the_following_JSON(DataTable dataTable) {
        List<Map<String, String>> expectedSchoolData = dataTable.asMaps(String.class, String.class);
        System.out.println(schools);
        for (Map<String, String> data : expectedSchoolData) {
            System.out.println("the added school is " + schools);
            assertTrue(schools.stream()
                    .anyMatch(s -> s.getName().equals(data.get("name")) &&
                            s.getLocation_school().equals(data.get("location_school")) &&
                            s.getWebsite().equals(data.get("website")) &&
                            s.getDescription().equals(data.get("description"))));
        }
    }


    @When("I add a new school with the following details:")
    public void i_add_a_new_school_with_the_following_details(DataTable dataTable) {
        List<Map<String, String>> newSchoolDataList = dataTable.asMaps(String.class, String.class);
        Map<String, String> newSchoolData = newSchoolDataList.get(0);
        updatedSchool = new School();
        updatedSchool.setName(newSchoolData.get("name"));
        updatedSchool.setLocation_school(newSchoolData.get("location_school"));
        updatedSchool.setWebsite(newSchoolData.get("website"));
        updatedSchool.setDescription(newSchoolData.get("description"));
        schools.add(updatedSchool);
        when(schoolService.saveSchool(any(School.class))).thenReturn(updatedSchool);
    }

    @When("I update the school with ID {int} with the following details:")
    public void i_update_the_school_with_ID_with_the_following_details(int schoolId, DataTable dataTable) {
        List<Map<String, String>> updatedSchoolDataList = dataTable.asMaps(String.class, String.class);
        Map<String, String> updatedSchoolData = updatedSchoolDataList.get(0);

        updatedSchool = new School();
        updatedSchool.setName(updatedSchoolData.get("name"));
        updatedSchool.setLocation_school(updatedSchoolData.get("location_school"));
        updatedSchool.setWebsite(updatedSchoolData.get("website"));
        updatedSchool.setDescription(updatedSchoolData.get("description"));

        when(schoolService.updateSchool(updatedSchool)).thenReturn(updatedSchool);
    }
}

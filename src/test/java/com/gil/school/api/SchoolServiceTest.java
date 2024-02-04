package com.gil.school.api;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = { "classpath:features/schoolService.feature" })
public class SchoolServiceTest {
}

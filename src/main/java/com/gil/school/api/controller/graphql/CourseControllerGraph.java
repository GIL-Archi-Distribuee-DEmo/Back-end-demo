package com.gil.school.api.controller.graphql;

import com.gil.school.api.model.Course;
import com.gil.school.api.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class CourseControllerGraph {

    @Autowired
    private CourseService courseService;
    @QueryMapping
    public Iterable<Course> courses(){
        return this.courseService.getAllCourse();
    }

    @QueryMapping
    public Optional<Course> courseById(@Argument Long course_id) {
        return this.courseService.getACourse(course_id);
    }

    @QueryMapping
    public List<Course>courseByName(@Argument String name){
        return this.courseService.findFormationByName(name);
    }
}

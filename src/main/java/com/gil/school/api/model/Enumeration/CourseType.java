package com.gil.school.api.model.Enumeration;


import lombok.Getter;
import lombok.Setter;


public enum CourseType {
    BACHELOR_DEGREE("Bachelor Degree"),
    MASTER_DEGREE("Master Degree"),
    PHD_DOCTORATE("PhD Doctorate"),
    CROSS_FACULTY_GRADUATE_RESEARCH("Cross-faculty graduate and research school"),
    LANGUAGE_COURSES("Language Courses"),
    SHORT_COURSES("Short Courses");
    private  String displayName;

    CourseType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }


}


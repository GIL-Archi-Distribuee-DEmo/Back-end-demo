package com.gil.school.api.model.Enumeration;



public enum CourseLanguage {
    FRENCH("French"),
    ENGLISH("English"),
    ENGLISH_AND_FRENCH("Courses in English and French");

    private   String displayName ;

    CourseLanguage(String displayName) {
        this.displayName = displayName;
    }

    public  String getDisplayName() {
        return displayName;
    }

    public static CourseLanguage fromDisplayName(String displayName) {
        for (CourseLanguage language : values()) {
            if (language.displayName.equalsIgnoreCase(displayName)) {
                return language;
            }
        }
        throw new IllegalArgumentException("No enum constant with displayName: " + displayName);
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName; // Provide a string representation based on display name
    }

}

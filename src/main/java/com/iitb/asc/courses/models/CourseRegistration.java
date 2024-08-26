package com.iitb.asc.courses.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CourseRegistration {
    @JsonProperty("code")
    private String courseCode;

    @JsonProperty("title")
    private String courseTitle;

    @JsonProperty("description")
    private String courseDescription;

    public CourseRegistration() {
    }

    public CourseRegistration(String courseCode, String courseTitle, String courseDescription) {
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.courseDescription = courseDescription;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }
}

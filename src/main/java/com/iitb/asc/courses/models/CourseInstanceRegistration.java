package com.iitb.asc.courses.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CourseInstanceRegistration {
    @JsonProperty("course_id")
    private Long courseId;

    @JsonProperty("year")
    private int year;

    @JsonProperty("semester")
    private int semester;

    public CourseInstanceRegistration() {
    }

    public CourseInstanceRegistration(Long courseId, int year, int semester) {
        this.courseId = courseId;
        this.year = year;
        this.semester = semester;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
}

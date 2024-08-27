package com.iitb.asc.courses.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long id;

    @Column(name = "course_code")
    private String courseCode;

    @Column(name = "course_title")
    private String courseTitle;

    @Column(name = "course_description")
    private String courseDescription;

    public Course() {
    }

    public Course(Long id, String courseCode, String courseTitle, String courseDescription) {
        this.id = id;
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.courseDescription = courseDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

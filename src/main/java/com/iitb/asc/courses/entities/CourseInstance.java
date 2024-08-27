package com.iitb.asc.courses.entities;


import javax.persistence.*;

@Entity
@Table(name = "course_instance")
public class CourseInstance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long courseInstanceId;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "year")
    private int year;

    @Column(name = "semester")
    private int semester;

    public CourseInstance() {
    }

    public CourseInstance(Long courseInstanceId, Course course, int year, int semester) {
        this.courseInstanceId = courseInstanceId;
        this.course = course;
        this.year = year;
        this.semester = semester;
    }

    public Long getCourseInstanceId() {
        return courseInstanceId;
    }

    public void setCourseInstanceId(Long courseInstanceId) {
        this.courseInstanceId = courseInstanceId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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

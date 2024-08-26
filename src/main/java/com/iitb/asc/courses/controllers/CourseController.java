package com.iitb.asc.courses.controllers;

import com.iitb.asc.courses.entities.Course;
import com.iitb.asc.courses.models.ResponseData;
import com.iitb.asc.courses.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseData createCourse(@RequestBody Course course){
        return null;
    }

    @GetMapping
    public ResponseData getAllCourses(){
        return null;
    }

    @GetMapping("/{id}")
    public ResponseData getCourseById(@PathVariable Long id){
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseData deleteCourseById(@PathVariable Long id){
        return null;
    }
}

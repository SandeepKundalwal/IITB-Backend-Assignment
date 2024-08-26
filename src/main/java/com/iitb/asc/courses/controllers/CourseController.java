package com.iitb.asc.courses.controllers;

import com.iitb.asc.courses.entities.Course;
import com.iitb.asc.courses.models.CourseRegistration;
import com.iitb.asc.courses.models.ResponseData;
import com.iitb.asc.courses.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseData createCourse(@RequestBody CourseRegistration courseRegistration){
        return courseService.save(courseRegistration);
    }

    @GetMapping
    public ResponseData getAllCourses(){
        return courseService.findAllCourses();
    }

    @GetMapping("/{id}")
    public ResponseData getCourseById(@PathVariable Long id){
        return courseService.findCourseById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseData deleteCourseById(@PathVariable Long id){
        return courseService.deleteCourseById(id);
    }
}

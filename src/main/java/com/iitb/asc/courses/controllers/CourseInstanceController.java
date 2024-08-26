package com.iitb.asc.courses.controllers;

import com.iitb.asc.courses.entities.CourseInstance;
import com.iitb.asc.courses.models.ResponseData;
import com.iitb.asc.courses.services.CourseInstanceService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/instances")
public class CourseInstanceController {

    private final CourseInstanceService courseInstanceService;

    public CourseInstanceController(CourseInstanceService courseInstanceService) {
        this.courseInstanceService = courseInstanceService;
    }

    @PostMapping
    public ResponseData createInstance(@RequestBody CourseInstance courseInstance){
        return null;
    }

    @GetMapping("/{year}/{semester}")
    public ResponseData getCourseInstanceByYearAndSemester(
            @PathVariable int year,
            @PathVariable int semester
    ){
        return null;
    }

    @GetMapping("/{year}/{semester}/{id}")
    public ResponseData getCourseInstanceByYearAndSemesterAndId(
            @PathVariable int year,
            @PathVariable int semester,
            @PathVariable Long id
    ){
        return null;
    }

    @DeleteMapping("/{year}/{semester}/{id}")
    public ResponseData deleteCourseInstance(
            @PathVariable int year,
            @PathVariable int semester,
            @PathVariable Long id
    ){
        return null;
    }
}

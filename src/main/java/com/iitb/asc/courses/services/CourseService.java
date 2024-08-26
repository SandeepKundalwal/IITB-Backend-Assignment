package com.iitb.asc.courses.services;

import com.iitb.asc.courses.models.CourseRegistration;
import com.iitb.asc.courses.models.ResponseData;

public interface CourseService {
    ResponseData save(CourseRegistration courseRegistration);

    ResponseData findAllCourses();

    ResponseData findCourseById(Long id);

    ResponseData deleteCourseById(Long id);
}

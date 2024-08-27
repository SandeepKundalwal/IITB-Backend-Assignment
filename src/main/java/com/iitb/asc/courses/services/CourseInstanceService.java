package com.iitb.asc.courses.services;

import com.iitb.asc.courses.models.CourseInstanceRegistration;
import com.iitb.asc.courses.models.ResponseData;

public interface CourseInstanceService{

    ResponseData save(CourseInstanceRegistration courseInstanceRegistration);

    ResponseData getCourseInstanceByYearAndSemester(int year, int semester);

    ResponseData getCourseInstanceByIdAndYearAndSemester(Long id, int year, int semester);

    ResponseData deleteCourseInstanceByCourseIdAndYearAndSemester(Long id, int year, int semester);

    ResponseData deleteCourseInstanceById(Long id);
}

package com.iitb.asc.courses.services.impl;

import com.iitb.asc.courses.entities.Course;
import com.iitb.asc.courses.exception.CustomApplicationException;
import com.iitb.asc.courses.models.CourseRegistration;
import com.iitb.asc.courses.models.ResponseData;
import com.iitb.asc.courses.respositories.CourseRepository;
import com.iitb.asc.courses.services.CourseService;
import com.iitb.asc.courses.utils.ResponseCode;
import com.iitb.asc.courses.utils.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.aspectj.util.LangUtil.isEmpty;

//import static org.apache.commons.lang3.StringUtils.isEmpty;

@Service
public class CourseServiceImpl implements CourseService {

    ResponseData responseData;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public ResponseData save(CourseRegistration courseRegistration) {
        String courseCode = courseRegistration.getCourseCode();
        String courseTitle = courseRegistration.getCourseTitle();
        String courseDescription = courseRegistration.getCourseDescription();

        if(isEmpty(courseCode)){
            throw new CustomApplicationException(HttpStatus.BAD_REQUEST, "course code cannot be empty");
        }

        if(isEmpty(courseTitle)){
            throw new CustomApplicationException(HttpStatus.BAD_REQUEST, "course title cannot be empty");
        }

        if(isEmpty(courseDescription)){
            throw new CustomApplicationException(HttpStatus.BAD_REQUEST, "course description cannot be empty");
        }

        Map<String, Object> retData = new LinkedHashMap<>();
        Course course = null;
        try{
            course = courseRepository.findCourseByCourseCode(courseCode);
        } catch (DataAccessException e){
            throw new CustomApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SERVER_INTERNAL_SERVER_ERROR.toString());
        }

        if(course != null){
            throw new CustomApplicationException(HttpStatus.BAD_REQUEST, "course code already exists");
        } else {
            course = new Course();
            course.setCourseCode(courseCode);
            course.setCourseTitle(courseTitle);
            course.setCourseDescription(courseDescription);

            try {
                course = courseRepository.save(course);
            } catch (DataAccessException e){
                throw new CustomApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SERVER_INTERNAL_SERVER_ERROR.toString());
            }

            retData.put("course", course);
            responseData = new ResponseData(Response.Status.CREATED.getStatusCode(), ResponseCode.SUCCESS.getCode(), retData);
        }

        return responseData;
    }

    @Override
    public ResponseData findAllCourses() {
        List<Course> allCourse = null;
        Map<String, Object> retData = new LinkedHashMap<>();
        try {
            allCourse = courseRepository.findAll();

            if(isEmpty(allCourse)){
                throw new CustomApplicationException(HttpStatus.NOT_FOUND, "No courses created.");
            }

            retData.put("courses", allCourse);
            responseData = new ResponseData(ResponseCode.SUCCESS.getCode(), Response.Status.OK.getStatusCode(), retData);
        } catch (DataAccessException e){
            throw new CustomApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SERVER_INTERNAL_SERVER_ERROR.toString());
        }

        return responseData;
    }

    @Override
    public ResponseData findCourseById(Long id) {
        Optional<Course> course = null;
        Map<String, Object> retData = new LinkedHashMap<>();

        try{
            course = courseRepository.findById(id);

            if(course.isEmpty()){
                throw new CustomApplicationException(HttpStatus.NOT_FOUND, "course not found.");
            }

            retData.put("course", course);
            responseData = new ResponseData(ResponseCode.SUCCESS.getCode(), Response.Status.OK.getStatusCode(), retData);
        } catch (DataAccessException e){
            throw new CustomApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SERVER_INTERNAL_SERVER_ERROR.toString());
        }

        return responseData;
    }

    @Override
    public ResponseData deleteCourseById(Long id) {
        Optional<Course> course = null;

        try{
            course = courseRepository.findById(id);
        } catch (DataAccessException e){
            throw new CustomApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SERVER_INTERNAL_SERVER_ERROR.toString());
        }

        if(course.isPresent()){
            try {
                courseRepository.deleteById(id);

                responseData = new ResponseData(ResponseCode.SUCCESS.getCode(), Response.Status.OK.getStatusCode(), "Deleted course with id: " + id + " successfully.");
            } catch (DataAccessException e){
                throw new CustomApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SERVER_INTERNAL_SERVER_ERROR.toString());
            }
        } else {
            throw new CustomApplicationException(HttpStatus.NOT_FOUND, "course not found.");
        }

        return responseData;
    }
}

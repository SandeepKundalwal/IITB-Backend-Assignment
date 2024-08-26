package com.iitb.asc.courses.services.impl;

import com.iitb.asc.courses.entities.Course;
import com.iitb.asc.courses.entities.CourseInstance;
import com.iitb.asc.courses.exception.CustomApplicationException;
import com.iitb.asc.courses.models.CourseInstanceRegistration;
import com.iitb.asc.courses.models.ResponseData;
import com.iitb.asc.courses.respositories.CourseInstanceRepository;
import com.iitb.asc.courses.respositories.CourseRepository;
import com.iitb.asc.courses.services.CourseInstanceService;
import com.iitb.asc.courses.utils.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.apache.commons.lang3.ObjectUtils.isEmpty;

@Service
public class CourseInstanceServiceImpl implements CourseInstanceService {
    ResponseData responseData;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseInstanceRepository courseInstanceRepository;

    @Override
    public ResponseData save(CourseInstanceRegistration courseInstanceRegistration) {
        Long courseId = courseInstanceRegistration.getCourseId();
        int year = courseInstanceRegistration.getYear();
        int semester = courseInstanceRegistration.getSemester();

        if(isEmpty(courseId)){
            throw new CustomApplicationException(HttpStatus.BAD_REQUEST, "course id cannot be empty");
        }

        if(isEmpty(year)){
            throw new CustomApplicationException(HttpStatus.BAD_REQUEST, "year cannot be empty");
        }

        if(isEmpty(semester)){
            throw new CustomApplicationException(HttpStatus.BAD_REQUEST, "semester cannot be empty");
        }

        Map<String, Object> retData = new LinkedHashMap<>();
        Optional<Course> course = null;
        CourseInstance courseInstance = null;

        try {
            course = courseRepository.findById(courseId);
        }  catch (DataAccessException e){
            throw new CustomApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SERVER_INTERNAL_SERVER_ERROR.toString());
        }

        try {
            if(course.isEmpty()){
                throw new CustomApplicationException(HttpStatus.BAD_REQUEST, "course id not found.");
            } else {
                courseInstance = courseInstanceRepository.findCourseInstanceByCourseIdAndYearAndSemester(courseId, year, semester);

                if(courseInstance != null){
                    throw new CustomApplicationException(HttpStatus.BAD_REQUEST, "course instance already exists");
                } else {
                    courseInstance = new CourseInstance();
                    courseInstance.setYear(year);

                    Course courseDTO = new Course(course.get().getId(),
                            course.get().getCourseCode(),
                            course.get().getCourseTitle(),
                            course.get().getCourseDescription()
                    );

                    courseInstance.setCourse(courseDTO);
                    courseInstance.setSemester(semester);

                    courseInstanceRepository.save(courseInstance);

                    retData.put("course_instance", courseInstance);
                    responseData = new ResponseData(Response.Status.CREATED.getStatusCode(), ResponseCode.SUCCESS.getCode(), retData);
                }
            }
        } catch (DataAccessException e){
            throw new CustomApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SERVER_INTERNAL_SERVER_ERROR.toString());
        }

        return responseData;
    }

    @Override
    public ResponseData getCourseInstanceByYearAndSemester(int year, int semester) {
        Map<String, Object> retData = new LinkedHashMap<>();

        try {
            List<CourseInstance> courseInstances = courseInstanceRepository.findCourseInstanceByYearAndSemester(year, semester);

            if(isEmpty(courseInstances)){
                throw new CustomApplicationException(HttpStatus.NOT_FOUND, "No courses instances for " + year + " and semester " + semester);
            }

            retData.put("course-instances", courseInstances);
            responseData = new ResponseData(ResponseCode.SUCCESS.getCode(), Response.Status.OK.getStatusCode(), retData);
        } catch (DataAccessException e){
            throw new CustomApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SERVER_INTERNAL_SERVER_ERROR.toString());
        }

        return responseData;
    }

    @Override
    public ResponseData getCourseInstanceByIdAndYearAndSemester(Long id, int year, int semester) {
        if(isEmpty(id)){
            throw new CustomApplicationException(HttpStatus.BAD_REQUEST, "course id cannot be empty");
        }

        if(isEmpty(year)){
            throw new CustomApplicationException(HttpStatus.BAD_REQUEST, "year cannot be empty");
        }

        if(isEmpty(semester)){
            throw new CustomApplicationException(HttpStatus.BAD_REQUEST, "semester cannot be empty");
        }

        Map<String, Object> retData = new LinkedHashMap<>();
        CourseInstance courseInstance = null;

        try {
            courseInstance = courseInstanceRepository.findCourseInstanceByCourseIdAndYearAndSemester(id, year, semester);

            if(courseInstance == null){
                responseData = new ResponseData(Response.Status.CREATED.getStatusCode(), ResponseCode.SUCCESS.getCode(), "No data found.");
            } else {
                courseInstanceRepository.save(courseInstance);

                retData.put("course_instance", courseInstance);
                responseData = new ResponseData(Response.Status.CREATED.getStatusCode(), ResponseCode.SUCCESS.getCode(), retData);
            }

        } catch (DataAccessException e){
            throw new CustomApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SERVER_INTERNAL_SERVER_ERROR.toString());
        }

        return responseData;
    }

    @Override
    public ResponseData deleteCourseInstanceByCourseIdAndYearAndSemester(Long id, int year, int semester) {
        if(isEmpty(id)){
            throw new CustomApplicationException(HttpStatus.BAD_REQUEST, "course id cannot be empty");
        }

        if(isEmpty(year)){
            throw new CustomApplicationException(HttpStatus.BAD_REQUEST, "year cannot be empty");
        }

        if(isEmpty(semester)){
            throw new CustomApplicationException(HttpStatus.BAD_REQUEST, "semester cannot be empty");
        }

        CourseInstance courseInstance = null;

        try {
            courseInstance = courseInstanceRepository.findCourseInstanceByCourseIdAndYearAndSemester(id, year, semester);

            if(courseInstance == null){
                responseData = new ResponseData(Response.Status.CREATED.getStatusCode(), ResponseCode.SUCCESS.getCode(), "No data found.");
            } else {
                courseInstanceRepository.deleteCourseInstanceByCourseIdAndYearAndSemester(id, year, semester);

                responseData = new ResponseData(Response.Status.CREATED.getStatusCode(), ResponseCode.SUCCESS.getCode(), "successfully deleted course instance");
            }

        } catch (DataAccessException e){
            throw new CustomApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SERVER_INTERNAL_SERVER_ERROR.toString());
        }

        return responseData;
    }
}

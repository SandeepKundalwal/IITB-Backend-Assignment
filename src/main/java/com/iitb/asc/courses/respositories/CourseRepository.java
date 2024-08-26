package com.iitb.asc.courses.respositories;

import com.iitb.asc.courses.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findCourseByCourseCode(String courseCode);
}

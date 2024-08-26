package com.iitb.asc.courses.respositories;

import com.iitb.asc.courses.entities.CourseInstance;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Queue;

@Repository
public interface CourseInstanceRepository extends JpaRepository<CourseInstance, Long> {
    CourseInstance findCourseInstanceByCourseIdAndYearAndSemester(Long courseId, int year, int semester);

    List<CourseInstance> findCourseInstanceByYearAndSemester(int year, int semester);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM course_instance ci WHERE ci.course_id=:course_id AND ci.year=:year AND ci.semester=:semester", nativeQuery = true)
    void deleteCourseInstanceByCourseIdAndYearAndSemester(@Param("course_id") Long id, @Param("year") int year, @Param("semester") int semester);
}

package com.iitb.asc.courses.respositories;

import com.iitb.asc.courses.entities.CourseInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseInstanceRepository extends JpaRepository<CourseInstance, Long> {
}

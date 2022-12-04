package com.emeritus.assignment.repository;

import com.emeritus.assignment.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    @Query(value = "SELECT up.NAME FROM USER_PROFILE up LEFT JOIN STUDENT_COURSE sa on up.id = sa.student_id WHERE sa.course_id=?1",nativeQuery = true)
    List<String> findAllStudentsForCourse(Integer courseId);

    @Query("select c from Course c where c.createdBy = ?1")
    List<Course> findAllInstructorCourses(String createdBy);

    @Query(value = "SELECT * from courses cr LEFT JOIN STUDENT_COURSE sc on cr.id = sc.course_id WHERE sc.student_id=?1",nativeQuery = true)
    List<Course> findAllEnrolledCourses(String studentId);
}

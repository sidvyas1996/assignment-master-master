package com.emeritus.assignment.repository;

import com.emeritus.assignment.entity.Assignment;
import com.emeritus.assignment.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment,Integer> {
    @Query(value = "SELECT asn.* FROM ASSIGNMENT asn LEFT JOIN STUDENT_ASSIGNMENT sa on asn.id=sa.assignment_id WHERE sa.student_id=?1 ",nativeQuery = true)
    List<Assignment> getStudentAssignment(Integer studentId);

    @Query(value = "SELECT USER_PROFILE.NAME FROM USER_PROFILE up LEFT JOIN STUDENT_ASSIGNMENT sa on up.id = sa.assignment_id WHERE sa.assignment_id=?1;",nativeQuery = true)
    List<UserProfile> getAssignmentForStudents(String studentId);
    @Query(value = "SELECT asn.id FROM  ASSIGNMENT asn where id in (select ASSIGNMENT_ID from STUDENT_ASSIGNMENT where student_ID = ?1)",nativeQuery = true)
    List<Integer> getAssignmentIdsForStudent(Integer studentId);
}

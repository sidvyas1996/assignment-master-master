package com.emeritus.assignment.repository;

import com.emeritus.assignment.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserProfileRepository extends JpaRepository<UserProfile,Integer> {
    @Query("select u from UserProfile u where u.profile ='STUDENT'")
    List<UserProfile> findAllStudents();

    @Query("select u from UserProfile u where u.profile = 'STUDENT' or u.profile='INSTRUCTOR'")
    List<UserProfile> findAllNonAdminUsers();
}

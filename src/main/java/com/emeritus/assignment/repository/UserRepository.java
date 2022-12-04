package com.emeritus.assignment.repository;

import com.emeritus.assignment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {


    Optional<User> findByUsername(String username);

    @Transactional
    @Modifying
    @Query("delete from User u where u.username = ?1")
    void deleteByUser(String username);

    @Query(value = "SELECT * FROM USERS LEFT JOIN USERS_ROLES ur on users.id = ur.user_id WHERE ur.role_id in (Select id from ROLES where name = 'STUDENT');",nativeQuery = true)
    List<User> findAllStudents();
}

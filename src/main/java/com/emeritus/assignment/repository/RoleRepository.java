package com.emeritus.assignment.repository;

import com.emeritus.assignment.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
}

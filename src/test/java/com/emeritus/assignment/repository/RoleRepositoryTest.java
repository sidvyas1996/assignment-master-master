package com.emeritus.assignment.repository;

import com.emeritus.assignment.entity.Role;
import com.emeritus.assignment.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Rollback(value = false)
class RoleRepositoryTest {
    @Autowired
    private RoleRepository repo;

    @Test
    @Commit
    public void testCreateRoles() {
        Role admin = new Role("ADMIN");


       repo.save(admin);
        System.out.println(repo.findAll());
        //long count = repo.count();
        //assertEquals(1, count);
    }


}
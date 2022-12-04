package com.emeritus.assignment.repository;

import com.emeritus.assignment.entity.Role;
import com.emeritus.assignment.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Rollback(value = false)
class UserRepositoryTest {
    @Autowired
    private UserRepository repo;


    @Test
    public void testCreateUsers() {
        User user = new User("sid","pass");


        repo.save(user);

        long count = repo.count();
        assertEquals(1, count);
    }

    @Test
    public void testAssignRoleToUser() {
        Integer userId = 1;
        Integer roleId = 1;
        User user = repo.findById(userId).get();
        user.addRole(new Role(roleId));

        User updatedUser = repo.save(user);
        //assertTrue(updatedUser.getRoles().hasSize(1));
        assertEquals(1,updatedUser.getRoles().size());

    }

   /* @Test
    public void testAssignRoleToUser2() {
        Integer userId = 2;
        User user = repo.findById(userId).get();
        user.addRole(new Role(1));
        user.addRole(new Role(2));


        User updatedUser = repo.save(user);
        assertEquals (2,updatedUser.getRoles().size());

    }*/

}
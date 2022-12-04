package com.emeritus.assignment.controller;

import com.emeritus.assignment.dto.UserDTO;
import com.emeritus.assignment.dto.UserProfileDTO;
import com.emeritus.assignment.service.CourseService;
import com.emeritus.assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/admin")
/*
*       -Create/Update/Delete/List Users (Instructor, Student)
		- List all courses
		- List all Students
		- List students enrolled in each course
* */
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    CourseService courseService;

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO){
            userService.createUser(userDTO);
        return ResponseEntity.ok("User created successfully!");
    }

    @PutMapping("/user")
    public ResponseEntity<?> updateUser(@RequestBody UserProfileDTO userProfileDTO){
        userService.updateUser(userProfileDTO);
        return ResponseEntity.ok("User updated successfully!");
    }

    @DeleteMapping("/user")
    public ResponseEntity<?> deleteUser(@RequestParam(value = "username")String username){
            userService.deleteUser(username);
        return ResponseEntity.ok("user deleted successfully!");
    }
    @GetMapping("/user")
    public ResponseEntity<?> getUser(){

        return ResponseEntity.ok(userService.getAllNonAdminUsers());
    }


    @GetMapping("/courses")
    public ResponseEntity<?> viewAllCourses()
    {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/students")
    public ResponseEntity<?> viewAllStudents()
    {
        return ResponseEntity.ok(userService.getAllStudents());
    }
}

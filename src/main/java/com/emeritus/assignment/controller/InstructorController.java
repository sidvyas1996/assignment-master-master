package com.emeritus.assignment.controller;

import com.emeritus.assignment.dto.AssignmentDTO;
import com.emeritus.assignment.dto.CourseDTO;
import com.emeritus.assignment.entity.Course;
import com.emeritus.assignment.service.AssignmentService;
import com.emeritus.assignment.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.Assignment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasAuthority('INSTRUCTOR')")
@RequestMapping("/instructor")
/*
*       - Instructor
		- Create Courses
		- Create Assignment for each course
		- List all courses
		- Access/Modify courses created by themselves
		- List the students enrolled in each course
		- Must not be able to access courses created by other instuctors
* */
public class InstructorController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private AssignmentService assignmentService;

    @PostMapping("/course")
    public ResponseEntity<?> createCourses(@RequestBody CourseDTO courseDTO,Authentication authentication){
        courseDTO.setCreatedBy(authentication.getName());
        courseService.createCourse(courseDTO);
        return ResponseEntity.ok("course created successfully!");
    }

    @PostMapping("/assignments")
    public ResponseEntity<?> createAssignments(@RequestBody AssignmentDTO assignmentDTO                                               ){

        return ResponseEntity.ok(assignmentService.createAssignment(assignmentDTO));
    }

    @GetMapping("/courses")
    public ResponseEntity<?> viewAllCourses(Authentication authentication)
    {
        //find all list of courses
        return ResponseEntity.ok(courseService.getAllCourses(authentication.getName()));
    }

    @PutMapping("/courses")
    public ResponseEntity<?> modifyCourses(CourseDTO courseDTO){
        courseService.updateCourse(courseDTO);
        return ResponseEntity.ok("course created successfully!");
    }

    @GetMapping("/students-in-course")
    public ResponseEntity<?> viewAllStudentsInCourse(@RequestParam("courseId")Integer courseId)
    {
        //List of all student in the given course
        return ResponseEntity.ok(courseService.getStudentsForCourse(courseId));
    }
}

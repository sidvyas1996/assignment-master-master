package com.emeritus.assignment.controller;

import com.emeritus.assignment.dto.AssignmentDTO;
import com.emeritus.assignment.service.AssignmentService;
import com.emeritus.assignment.service.CourseService;
import com.emeritus.assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasAuthority('STUDENT')  ")
@RequestMapping("/student")
/*- Student
		- Can see all the courses
		- Can enroll in more than one course
		- Can access only enrolled courses and assignments
		- Can submit/update assignments
		*/
public class StudentController {

     @Autowired
     private CourseService courseService;

     @Autowired
     private AssignmentService assignmentService;

     @Autowired
     private UserService userService;

    @GetMapping("/courses")
    public ResponseEntity<?> viewAllCourses()
    {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @PostMapping("/enroll")
    public ResponseEntity<?> enrollInCourse(@RequestParam(name = "courseId")Integer courseId,
                                            @RequestParam(name = "studentId") Integer studentId)
    {
        //enroll in courses
        return ResponseEntity.ok(userService.enrollStudentInCourse(courseId,studentId));
    }

    @GetMapping("/enrolledCourses")
    public ResponseEntity<?> viewMyEnrolledCourses(@RequestParam(name = "studentId")String studentId)
    {
        //find all list of courses
        return ResponseEntity.ok(courseService.getEnrolledCourses(studentId));
    }

    @PostMapping("/assignment")
    public ResponseEntity<?> submitAssignment(@RequestBody AssignmentDTO assignmentDTO,
                                              @RequestParam(name = "studentId")Integer studentId) throws Exception {
        return ResponseEntity.ok(assignmentService.submitAssignment(studentId,assignmentDTO));
    }

    @PutMapping("/assignment")
    public ResponseEntity<?> updateAssignment(@RequestBody AssignmentDTO assignmentDTO,
                                              @RequestParam(name = "studentId")Integer studentId) throws Exception {
        //updateAssignment
        return ResponseEntity.ok(assignmentService.updateAssignment(studentId,assignmentDTO));
    }
    @GetMapping("/student-assignments")
    public ResponseEntity<?> getStudentAssignment(@RequestParam("studentId") Integer studentId) throws Exception {
        //find all list of all student assignments
        return ResponseEntity.ok(assignmentService.getStudentAssignment(studentId));
    }
}

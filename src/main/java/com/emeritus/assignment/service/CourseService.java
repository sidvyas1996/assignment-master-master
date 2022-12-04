package com.emeritus.assignment.service;

import com.emeritus.assignment.dto.CourseDTO;
import com.emeritus.assignment.entity.Course;
import com.emeritus.assignment.repository.CourseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;


    @Autowired
    ModelMapper modelMapper;
    public void createCourse(CourseDTO courseDTO)
    {
        Course course= this.modelMapper.map(courseDTO,Course.class);
        courseRepository.save(course);
    }

    public List<CourseDTO> getAllCourses(String createdBy) {
        List<Course> courses= courseRepository.findAllInstructorCourses(createdBy);
        List<CourseDTO> courseDTOs = courses.stream().map(course ->this.modelMapper.map(course,CourseDTO.class)).collect(Collectors.toList());
        return courseDTOs;
    }
    public List<CourseDTO> getAllCourses() {
        List<Course> courses= courseRepository.findAll();
        List<CourseDTO> courseDTOs = courses.stream().map(course ->this.modelMapper.map(course,CourseDTO.class)).collect(Collectors.toList());
        return courseDTOs;
    }

    public List<CourseDTO> getEnrolledCourses(String studentId) {

        List<Course> courses = courseRepository.findAllEnrolledCourses(studentId);
        return null;
    }


    public String updateCourse(CourseDTO courseDTO) {

        Course course = this.modelMapper.map(courseDTO,Course.class);
           courseRepository.save(course);
        return "Course modified successfully!";
    }

    public List<String> getStudentsForCourse(Integer courseId) {

        List<String> students = courseRepository.findAllStudentsForCourse(courseId);

        return students;
    }
}

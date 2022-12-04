package com.emeritus.assignment.service;

import com.emeritus.assignment.dto.AssignmentDTO;
import com.emeritus.assignment.entity.Assignment;
import com.emeritus.assignment.entity.UserProfile;
import com.emeritus.assignment.repository.AssignmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssignmentService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AssignmentRepository assignmentRepository;
    public String   createAssignment (AssignmentDTO assignmentDTO) {
        Assignment assignment = this.modelMapper.map(assignmentDTO,Assignment.class);
        assignmentDTO.getStudents().forEach(student->assignment.addStudent(new UserProfile(student,"STUDENT")));
        assignmentRepository.save(assignment);

        return "Assignment created succesfully";
    }

    public String submitAssignment(Integer studentId, AssignmentDTO assignmentDTO) throws Exception {

        //check if the list assignment ids returned by the student id key is matching assignment id given by the user
        List<Integer> assignmentIds =assignmentRepository.getAssignmentIdsForStudent(studentId);
        if(!assignmentIds.stream().anyMatch(id -> assignmentDTO.getId().equals(id)))
        {
            throw new RuntimeException("Student is not assigned for the given homework");
        }
        if(assignmentDTO.getAnswer()==null) throw new Exception("Answer cannot be blank");

        Assignment assignment = this.modelMapper.map(assignmentDTO,Assignment.class);
        assignment.addStudent(new UserProfile(studentId,"STUDENT"));
        assignmentRepository.save(assignment);
        return "Assignment submitted succesfully!";
    }

    public String updateAssignment(Integer studentId, AssignmentDTO assignmentDTO) throws Exception {

        List<Integer> assignmentIds =assignmentRepository.getAssignmentIdsForStudent(studentId);
        if(!assignmentIds.stream().anyMatch(id -> assignmentDTO.getId().equals(id)))
        {
            throw new RuntimeException("Student is not assigned for the given homework");
        }

        if(assignmentDTO.getAnswer()==null) throw new Exception("Answer cannot be blank");

        Assignment assignment = this.modelMapper.map(assignmentDTO,Assignment.class);

        assignmentRepository.save(assignment);
        return "Assignment updated succesfully!";
    }

    public List<AssignmentDTO> getStudentAssignment(Integer studentId) {
       List<Assignment> assignments= assignmentRepository.getStudentAssignment(studentId);
        System.out.println(assignments);
        List<AssignmentDTO> assignmentDTOS = new ArrayList<>();
        assignments.stream()
                .map(assignment -> assignmentDTOS.add(new AssignmentDTO(assignment.getId(),assignment.getQuestion(),assignment.getAnswer(),assignment.getIsSubmitted()))).collect(Collectors.toList());

        return assignmentDTOS;
    }


}

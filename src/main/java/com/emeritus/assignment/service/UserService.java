package com.emeritus.assignment.service;

import com.emeritus.assignment.dto.UserDTO;
import com.emeritus.assignment.dto.UserProfileDTO;
import com.emeritus.assignment.entity.Course;
import com.emeritus.assignment.entity.Role;
import com.emeritus.assignment.entity.User;
import com.emeritus.assignment.entity.UserProfile;
import com.emeritus.assignment.repository.UserProfileRepository;
import com.emeritus.assignment.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

@Autowired
private ModelMapper modelMapper;

@Autowired
private UserRepository userRepository;

@Autowired
private UserProfileRepository userProfileRepository;

@Autowired
private PasswordEncoder passwordEncoder;
    public void createUser(UserDTO userDTO)
    {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setUserProfile(userDTO.getUserProfile());
        userDTO.getRoles().forEach(role -> user.addRole(new Role(role)));
        System.out.println(user.toString());
        userRepository.save(user);


    }

    public void deleteUser(String username)
    {

        userRepository.deleteByUser(username);
    }

    public List<UserProfileDTO> getAllStudents() {
        List<UserProfile> userProfiles = userProfileRepository.findAllStudents();
        List<UserProfileDTO> userProfileDTOs =  userProfiles.stream()
                .map(userProfile -> this.modelMapper.map(userProfile,UserProfileDTO.class)).collect(Collectors.toList());

        System.out.println(userProfileDTOs);
        return userProfileDTOs;
    }

    public void updateUser(UserProfileDTO userProfileDTO) {

        UserProfile userProfile = this.modelMapper.map(userProfileDTO,UserProfile.class);
        userProfileRepository.save(userProfile);
    }

    public List<UserProfileDTO> getAllNonAdminUsers() {

        List<UserProfile> userProfiles = userProfileRepository.findAllNonAdminUsers();
        List<UserProfileDTO> userProfileDTOs =  userProfiles.stream()
                .map(userProfile -> this.modelMapper.map(userProfile,UserProfileDTO.class)).collect(Collectors.toList());
        System.out.println(userProfileDTOs);
        return userProfileDTOs;
    }
    public String enrollStudentInCourse(Integer courseId, Integer studentId){

        Optional<UserProfile> userProfile = userProfileRepository.findById(studentId);
        userProfile.get().addCourse(new Course(courseId));
        userProfileRepository.save(userProfile.get());
        return "Student registered to course!";
    }

}

package com.emeritus.assignment.dto;

import com.emeritus.assignment.entity.Role;
import com.emeritus.assignment.entity.UserProfile;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    private String username;
    private String password;
    private List<String> roles = new ArrayList<>();
    private UserProfile userProfile;

    public UserDTO(String username) {
        this.username = username;
    }
}

package com.emeritus.assignment.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AssignmentDTO {
    private Integer id;
    private String question;
    private String answer;
    private Boolean isSubmitted;
    private List<Integer> students;

    public AssignmentDTO(Integer id, String question, String answer, Boolean isSubmitted) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.isSubmitted = isSubmitted;
    }
}

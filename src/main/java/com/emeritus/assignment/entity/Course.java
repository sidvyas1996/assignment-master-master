package com.emeritus.assignment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "COURSES")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id @GeneratedValue
    private Integer id;
    private String name;
    private Integer credits;
    private String createdBy;

      public Course(Integer id) {
        this.id = id;
    }
}

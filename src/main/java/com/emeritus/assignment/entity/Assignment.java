package com.emeritus.assignment.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity()
@Table(name = "ASSIGNMENT")
@Data
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String question;
    private String answer;
    private Boolean isSubmitted;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinTable(name = "student_assignment",
            joinColumns = @JoinColumn(nullable = false, updatable = false,name = "assignment_id"),
            inverseJoinColumns = @JoinColumn( nullable = false, updatable = false,name = "student_id")
    )
    private List<UserProfile> students=new ArrayList<>();

    @Column(name = "course_id")
    private Integer courseId;
    public void addStudent(UserProfile userProfile){
        this.students.add(userProfile);
    }
}

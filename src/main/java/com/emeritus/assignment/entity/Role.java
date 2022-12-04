package com.emeritus.assignment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ROLES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id @GeneratedValue
    private Integer id;
    @Column(nullable = false,unique = true)
    private String name;


    public Role(String role) {
        this.name =role;
    }

    public Role(Integer roleId) {
        this.id=roleId;
    }

}

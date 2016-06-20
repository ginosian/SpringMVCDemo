package com.springmvc.demo.dto;

import javax.persistence.*;

/**
 * Created by Martha on 6/14/2016.
 */
@Entity
@Table(name = "role")
public class RoleDTO {

    private Long id;
    private String role;

    public RoleDTO() {
    }

    public RoleDTO(String role) {
        this.role = role;
    }

    public void set(String role) {
        this.role = role;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "role", nullable = false, length = 10)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

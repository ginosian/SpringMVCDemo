package com.springmvc.demo.dto;

import javax.persistence.*;

/**
 * Created by Martha on 6/14/2016.
 */
@Entity
@Table(name = "role")
public class RoleDTO {
    @Id
    @GeneratedValue
    private Long id;

    private String role;

    public RoleDTO() {
    }

    public void set(String role){
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}

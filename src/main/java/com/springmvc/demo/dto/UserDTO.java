package com.springmvc.demo.dto;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Martha on 6/14/2016.
 */
@Entity
@Table(name = "user")
public class UserDTO {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    @Type(type = "text")
    private String password;
    private String name;
    private boolean enabled;
    @ManyToMany (fetch = FetchType.EAGER)// Both JPA and Hibernate default fetch type for OneToMany is Lazy
    private Set<RoleDTO> userRoles = new HashSet<>();

    public UserDTO() {
    }

    public UserDTO(String username, String password, String name, boolean enabled, Set<RoleDTO> userRoles) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.enabled = enabled;
        this.userRoles = userRoles;
    }

    public UserDTO(boolean enabled, String username, String password) {
        this.enabled = enabled;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void set(String username, String password, String name, boolean enabled, Set<RoleDTO> userRoles) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.enabled = enabled;
        this.userRoles = userRoles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<RoleDTO> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<RoleDTO> userRoles) {
        this.userRoles = userRoles;
    }
}

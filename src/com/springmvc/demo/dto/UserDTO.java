package com.springmvc.demo.dto;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Martha on 6/14/2016.
 */
@Entity
@Table(name = "user")
public class UserDTO {

    private Long id;
    private String username;
    private String password;
    private String name;
    private boolean enabled;
    private Set<RoleDTO> userRoles = new HashSet<RoleDTO>();

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

    @Id
    @GeneratedValue
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


    @Column(name = "username", nullable = false, length = 25)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", nullable = false, length = 35)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "user_first_name", length = 15)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "enabled", nullable = false)
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @ManyToMany (fetch = FetchType.EAGER)// Both JPA and Hibernate default fetch type for OneToMany is Lazy
//    @Column(insertable = false, updatable = false)
    public Set<RoleDTO> getUserRoles() {
        return userRoles;
    }

//    @ManyToMany (fetch = FetchType.LAZY)
//    @JoinTable(name = "user_role",
//            joinColumns = { @JoinColumn(name = "user_id") },
//            inverseJoinColumns = { @JoinColumn(name = "role_id")})// Both JPA and Hibernate default fetch type for OneToMany is Lazy
//    @ManyToOne(targetEntity = RoleDTO.class)
//    @JoinColumn(name = "id", updatable = false, insertable = false)
//    public Set<RoleDTO> getUserRoles() {
//        return userRoles;
//    }

    public void setUserRoles(Set<RoleDTO> userRoles) {
        this.userRoles = userRoles;
    }


}

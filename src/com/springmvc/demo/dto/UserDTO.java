package com.springmvc.demo.dto;

import javax.persistence.*;

/**
 * Created by Martha on 6/14/2016.
 */
@Entity
@Table(name = "user")
public class UserDTO {
    @Id @GeneratedValue
    private Long id;
    private String login;
    private String password;
    private String name;
    @OneToOne
    private RoleDTO roleDTO;

    public UserDTO() {
    }

    public void set(String login, String password, String name, RoleDTO roleDTO) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.roleDTO = roleDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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


    public RoleDTO getRoleDTO() {
        return roleDTO;
    }

    public void setRoleDTO(RoleDTO roleDTO) {
        this.roleDTO = roleDTO;
    }
}

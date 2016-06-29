package com.springmvc.demo.services;

import com.springmvc.demo.dto.RoleDTO;
import com.springmvc.demo.dto.UserDTO;

import java.util.Collection;

/**
 * Created by Martha on 6/14/2016.
 */
public interface UserManager {
    UserDTO getUserById(String id);
    UserDTO addUser(String name, String username, String password, boolean enabled, String role);
    UserDTO getUserByUsername(String login);
    UserDTO getUserByName(String name);
    Collection<RoleDTO> allRoles();
    RoleDTO getRoleByName(String roleName);
    Collection<UserDTO> allUsersByRole(RoleDTO roleDTO);
    void addRole(RoleDTO roleDTO);
    void initDefaults();

}

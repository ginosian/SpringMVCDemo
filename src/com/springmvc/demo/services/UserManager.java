package com.springmvc.demo.services;

import com.springmvc.demo.dto.RoleDTO;
import com.springmvc.demo.dto.UserDTO;

import java.util.Collection;

/**
 * Created by Martha on 6/14/2016.
 */
public interface UserManager {
    UserDTO getUserById(Long id);
    void addUser(UserDTO userDTO);
    UserDTO getUserByLogin(String login);
    Collection<RoleDTO> getRoles();
    Collection<UserDTO> allUsersByRole(Integer role);
    void addRole(RoleDTO roleDTO);
}

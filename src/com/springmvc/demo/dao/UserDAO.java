package com.springmvc.demo.dao;

import com.springmvc.demo.dto.RoleDTO;
import com.springmvc.demo.dto.UserDTO;

import java.util.Collection;

/**
 * Created by Martha on 6/14/2016.
 */
public interface UserDAO {
    UserDTO getUserById(Long id);
    void addUser(UserDTO userDTO);
    UserDTO getUserByUsername(String login);
    Collection<UserDTO> getAllUsersByRole(RoleDTO roleDTO);

}
package com.springmvc.demo.dao;

import com.springmvc.demo.dto.RoleDTO;
import com.springmvc.demo.dto.UserDTO;

import java.util.Collection;

/**
 * Created by Martha on 6/14/2016.
 */
public interface UserDAO {
    UserDTO getUserById(Long id);
    UserDTO addUser(UserDTO userDTO);
    UserDTO getUserByUsername(String login);
    UserDTO getUserByName(String name);
    Collection<UserDTO> getAllUsersByRole(RoleDTO roleDTO);
}

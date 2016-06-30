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
    Collection<RoleDTO> allRoles();
    RoleDTO getRoleByName(String roleName);
    Collection<UserDTO> allUsersByRole(String role);
    /**
     * <p>Takes from config.properties default role types and default administrator authorization data.</p>
     * <p>Checks in DB if "admin" and "user" roles doesn't exist creates one for each and saves in DB.</p>
     * <p>Checks in DB if at least one administrator user  doesn't exist creates one with default authorization data
     * and saves in DB.</p>
     */
    void initDefaults();

}

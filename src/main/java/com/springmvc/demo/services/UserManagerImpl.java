package com.springmvc.demo.services;

import com.springmvc.demo.dao.RoleDAO;
import com.springmvc.demo.dao.UserDAO;
import com.springmvc.demo.dto.RoleDTO;
import com.springmvc.demo.dto.UserDTO;
import com.springmvc.demo.exceptions.EmptyRequiredValueException;
import com.springmvc.demo.exceptions.NoSuchRoleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Martha on 6/14/2016.
 */
@Service
@Transactional
public class UserManagerImpl implements UserManager {

    @Autowired
    UserDAO userDAO;

    @Autowired
    RoleDAO roleDAO;

    @Autowired
    Environment environment;

    private static boolean defaultAreSet = false;

    @Override
    public void initDefaults() {
        // This flag avoids multiple redundant db queries.
        if(defaultAreSet) return;

        userDAO.createRememberMeTable();

        //Get all parameters from .property file
        String admin_username = environment.getProperty("admin_username");
        String admin_pass = environment.getProperty("admin_pass");
        String admin_name = environment.getProperty("admin_name");
        String role_admin = environment.getProperty("role_admin");
        String role_user = environment.getProperty("role_user");

        // Create "admin" and "user" roles if doesn't exist.
        RoleDTO adminRole = new RoleDTO(role_admin);
        RoleDTO userRole = new RoleDTO(role_user);
        RoleDTO adminRoleDTO = roleDAO.getRole(adminRole);
        RoleDTO userRoleDTO = roleDAO.getRole(userRole);

        if (adminRoleDTO == null){
            adminRoleDTO = new RoleDTO(role_admin);
            roleDAO.addRole(adminRoleDTO);
        }

        if (userRoleDTO == null){
            userRoleDTO = new RoleDTO(role_user);
            roleDAO.addRole(userRoleDTO);
        }

        // Create administrator user if doesn't exist
        UserDTO admin = getUserByUsername(admin_username);
        if(admin == null){
            HashSet<RoleDTO> roles = new HashSet<>();
            roles.add(adminRoleDTO);
            admin = new UserDTO(admin_username, admin_pass, admin_name, true, roles);
            userDAO.addUser(admin);
        }
        defaultAreSet = true;
    }

    @Override
    public UserDTO getUserById(String id) {
        if (id == null || id.isEmpty()) throw new EmptyRequiredValueException();
        return userDAO.getUserById(Long.parseLong(id));
    }

    @Override
    public UserDTO addUser(String name, String username, String password, boolean enabled, String role) {
        UserDTO user = userDAO.getUserByUsername(username);
        if(user != null){
            return null;
        }
        UserDTO userDTO = new UserDTO();
        Set<RoleDTO> roles = new HashSet<>();
        RoleDTO localRole = getRoleByName(role);
        if(localRole == null) throw new NoSuchRoleException();
        if(username == null || username.isEmpty() || password == null || password.isEmpty()
                || name == null || name.isEmpty()) throw new EmptyRequiredValueException();
        roles.add(localRole);
        userDTO.set(username, password, name,
                true, roles);
        return userDAO.addUser(userDTO);
    }

    @Override
    public UserDTO getUserByUsername(String login) {
        if(login == null || login.isEmpty()) throw new EmptyRequiredValueException();
        return userDAO.getUserByUsername(login);
    }

    @Override
    public Collection<RoleDTO> allRoles() {
        return roleDAO.allRoles();
    }

    @Override
    public RoleDTO getRoleByName(String roleName) {
        if(roleName == null || roleName.isEmpty()) throw new NoSuchRoleException();
        RoleDTO role = new RoleDTO(roleName);
        return roleDAO.getRole(role);
    }

    @Override
    public Collection<UserDTO> allUsersByRole(String role) {
        if(role == null || role.isEmpty()) throw new EmptyRequiredValueException();
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.set(role);
        ArrayList<UserDTO> users = (ArrayList<UserDTO>)userDAO.getAllUsersByRole(roleDTO);
        if (users == null) users = new ArrayList<>();
        return users;
    }
}

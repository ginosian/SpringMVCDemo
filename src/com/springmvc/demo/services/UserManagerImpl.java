package com.springmvc.demo.services;

import com.springmvc.demo.dao.RoleDAO;
import com.springmvc.demo.dao.UserDAO;
import com.springmvc.demo.dto.RoleDTO;
import com.springmvc.demo.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

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

    @Override
    public UserDTO getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    @Override
    public void addUser(UserDTO userDTO) {
        userDAO.addUser(userDTO);
    }

    @Override
    public UserDTO getUserByLogin(String login) {
        return null;
    }

    @Override
    public Collection<RoleDTO> getRoles() {
        return null;
    }

    @Override
    public Collection<UserDTO> allUsersByRole(Integer role) {
        return null;
    }

    @Override
    public void addRole(RoleDTO roleDTO) {
        roleDAO.addRole(roleDTO);
    }
}

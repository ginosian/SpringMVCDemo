package com.springmvc.demo.dao;

import com.springmvc.demo.dto.RoleDTO;

import java.util.Collection;

/**
 * Created by Martha on 6/14/2016.
 */
public interface RoleDAO {
    Collection<RoleDTO> allRoles();
    void addRole(RoleDTO roleDTO);
}

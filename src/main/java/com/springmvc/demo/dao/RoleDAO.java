package com.springmvc.demo.dao;

import com.springmvc.demo.dto.RoleDTO;

import java.util.Collection;

/**
 * Created by Martha on 6/14/2016.
 */
public interface RoleDAO {
    Collection<RoleDTO> allRoles();
    RoleDTO addRole(RoleDTO roleDTO);
    RoleDTO getRole(RoleDTO roleDTO);
}

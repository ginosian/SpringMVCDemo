package com.springmvc.demo.services;

import com.springmvc.demo.dao.UserDAO;
import com.springmvc.demo.dto.RoleDTO;
import com.springmvc.demo.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Martha on 6/20/2016.
 */
@Service("customUsrService")
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Transactional(readOnly=true)
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDTO user = userDAO.getUserByUsername(s);
        List<GrantedAuthority> rolesList = buildUserRoleList(user.getUserRoles());
        return buidUserForAuthentication(user, rolesList);
    }

    private List<GrantedAuthority> buildUserRoleList(Set<RoleDTO> userRoles){
        Set<GrantedAuthority> tempUserRoles = new HashSet<>();
        for (RoleDTO userRole : userRoles){
            tempUserRoles.add(new SimpleGrantedAuthority("ROLE_" + userRole.getRole())); // Make sure always to put "ROLE_"
        }
        return new ArrayList<>(tempUserRoles);
    }

    private User buidUserForAuthentication(UserDTO user, List<GrantedAuthority> userRolesList){
        return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, userRolesList);
    }
}

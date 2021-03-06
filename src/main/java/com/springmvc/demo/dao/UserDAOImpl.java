package com.springmvc.demo.dao;

import com.springmvc.demo.dto.RoleDTO;
import com.springmvc.demo.dto.UserDTO;
import com.springmvc.demo.exceptions.NoSuchUserException;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * Created by Martha on 6/14/2016.
 */
@Repository
@Transactional
public class UserDAOImpl implements UserDAO {
   @Autowired
   SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void createRememberMeTable() {
        String remTableQuery = "CREATE TABLE IF NOT EXISTS persistent_logins ( " +
                "username VARCHAR(64) NOT NULL, " +
                "series VARCHAR(64) NOT NULL, " +
                "token VARCHAR(64) NOT NULL, " +
                "last_used TIMESTAMP NOT NULL, " +
                "PRIMARY KEY (series))";
        Session session = getSession();
        try {
            session.createSQLQuery(remTableQuery).executeUpdate();
        } catch (HibernateException e){
            e.printStackTrace();
        }
    }

    @Override
    public UserDTO getUserById(Long id) {
        Session session = getSession();
        try{
            Query query = session.createQuery("from UserDTO user where user.id = :id");
            query.setParameter("id", id);
            List<UserDTO> userDTOList = query.list();
            if (userDTOList.size() == 0)throw new NoSuchUserException();
            return userDTOList.get(0);
        }catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        Session session = getSession();
        try{
            session.save(userDTO);
            return userDTO;
        }catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        Session session = getSession();
        try{
            Query query = session.createQuery("from UserDTO user where user.username = :username");
            query.setParameter("username", username);
            List<UserDTO> userDTOList = query.list();
            if (userDTOList.size() == 0) return null;
            return userDTOList.get(0);
        }catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<UserDTO> getAllUsersByRole(RoleDTO roleDTO) {
        String role = roleDTO.getRole();
        Session session = getSession();
        try{
            Query query = session.createQuery("select user from UserDTO user join user.userRoles user_role where user_role.role = :role");
            query.setParameter("role", role);
            List<UserDTO> userDTOList = query.list();
            if (userDTOList.size() == 0) return null;
            return userDTOList;
        }catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }
}

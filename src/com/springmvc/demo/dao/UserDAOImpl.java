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

    private Session openSession() {
        return sessionFactory.openSession();
    }

    @Override
    public UserDTO getUserById(Long id) {
        Session session = openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("from UserDTO user where user.id = :id");
            query.setParameter("id", id);
            List<UserDTO> userDTOList = query.list();
//            Hibernate.initialize(userDTOList);              // If fetch type changes to LAZY this should be uncommented
            transaction.commit();
            if (userDTOList.size() == 0)throw new NoSuchUserException();
            return userDTOList.get(0);
        }catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        Session session = openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.save(userDTO);
            transaction.commit();
            return userDTO;
        }catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        Session session = openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            Query query = openSession().createQuery("from UserDTO user where user.username = :username");
            query.setParameter("username", username);
            List<UserDTO> userDTOList = query.list();
//            Hibernate.initialize(userDTOList);          // If fetch type changes to LAZY this should be uncommented
            transaction.commit();
            if (userDTOList.size() == 0) return null;
            return userDTOList.get(0);
        }catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public Collection<UserDTO> getAllUsersByRole(RoleDTO roleDTO) {
        String role = roleDTO.getRole();
        Session session = openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            Query query = openSession().createQuery("select user from UserDTO user join user.userRoles user_role where user_role.role = :role");
            query.setParameter("role", role);
            List<UserDTO> userDTOList = query.list();
//            Hibernate.initialize(userDTOList);               // If fetch type changes to LAZY this should be uncommented
            transaction.commit();
            if (userDTOList.size() == 0) return null;
            return userDTOList;
        }catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }
}

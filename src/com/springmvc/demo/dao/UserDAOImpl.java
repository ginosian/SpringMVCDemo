package com.springmvc.demo.dao;

import com.springmvc.demo.dto.RoleDTO;
import com.springmvc.demo.dto.UserDTO;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by Martha on 6/14/2016.
 */
@Repository
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
            List<UserDTO> userDTOList = query.list(); // TODO check if return type match , ask Lyov if why not to close session
            transaction.commit();
            if (userDTOList.size() == 0)return null;
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
    public void addUser(UserDTO userDTO) {
        UserDTO candidate = getUserByUsername(userDTO.getUsername());
        Session session = openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            if (candidate != null) {
                userDTO.setId(candidate.getId());
                session.merge(userDTO);
            } else {
                session.save(userDTO);
            }
            transaction.commit();
        }catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Override
    public UserDTO getUserByUsername(String login) {
        Session session = openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            Query query = openSession().createQuery("from UserDTO user where user.username = :username");
            query.setParameter("username", login); // TODO check if this works fine
            List<UserDTO> userDTOList = query.list(); // TODO check if return type match
            transaction.commit();
            if (userDTOList.size() == 0)return null;
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
                    // from UserDTO user join user.userRoles userrole where userrole.role = :role
            query.setParameter("role", role);
            List<UserDTO> userDTOList = query.list();
            transaction.commit();
            if (userDTOList.size() == 0)return null;
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

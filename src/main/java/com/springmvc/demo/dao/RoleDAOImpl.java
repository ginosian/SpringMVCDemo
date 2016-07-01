package com.springmvc.demo.dao;

import com.springmvc.demo.dto.RoleDTO;
import com.springmvc.demo.exceptions.NoSuchRoleException;
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
public class RoleDAOImpl implements RoleDAO {
    @Autowired
    SessionFactory sessionFactory;

    private Session openSession() {
//        return sessionFactory.openSession();
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Collection<RoleDTO> allRoles() {
        Session session = openSession();
//        Transaction transaction = null;
        try{
//            transaction = session.beginTransaction();
            List<RoleDTO> roleDTOs = session.createQuery("from RoleDTO").list();
//            transaction.commit();
            return roleDTOs;
        }catch (HibernateException e) {
//            if (transaction!=null) transaction.rollback();
            e.printStackTrace();
        }finally {
//            session.close();
        }
        return null;
    }

    @Override
    public RoleDTO addRole(RoleDTO roleDTO) {
        Session session = openSession();
//        Transaction transaction = null;
        try {
//            transaction = session.beginTransaction();
                 session.save(roleDTO);
//            transaction.commit();
            return roleDTO;
        } catch (HibernateException e){
//            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
//            session.close();
        }
        return null;
    }
    @Override
    public RoleDTO getRole(RoleDTO roleDTO) {
        Session session = openSession();
//        Transaction transaction = null;
        try{
//            transaction = session.beginTransaction();
            Query query = openSession().createQuery("from RoleDTO role where role.role = :role");
            String role = roleDTO.getRole();
            query.setParameter("role", role);
            List<RoleDTO> roleDTOs = query.list();
//            transaction.commit();
            if(roleDTOs.size() == 0) return null;
            return roleDTOs.get(0);
        }catch (HibernateException e) {
//            if (transaction!=null) transaction.rollback();
            e.printStackTrace();
        }finally {
//            session.close();
        }
        return null;
    }
}

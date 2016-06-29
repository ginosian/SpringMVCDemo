package com.springmvc.demo.dao;

import com.springmvc.demo.dto.RoleDTO;
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
        return sessionFactory.openSession();
    }

    @Override
    public Collection<RoleDTO> allRoles() {
        Session session = openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            List<RoleDTO> roleDTOs = session.createQuery("from RoleDTO").list();
            transaction.commit();
            return roleDTOs;
        }catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public void addRole(RoleDTO roleDTO) {
        Session session = openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
                 session.save(roleDTO);
            transaction.commit();
        } catch (HibernateException e){
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    @Override
    public RoleDTO getRoleByName(String name) {
        Session session = openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            Query query = openSession().createQuery("from RoleDTO role where role.role = :role");
            query.setParameter("role", name); // TODO check if this works fine
            List<RoleDTO> roleDTOs = query.list();
            transaction.commit();
            if(roleDTOs.size() > 0) return roleDTOs.get(0);
            return null;
        }catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }
}

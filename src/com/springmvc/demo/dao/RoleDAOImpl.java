package com.springmvc.demo.dao;

import com.springmvc.demo.dto.RoleDTO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Martha on 6/14/2016.
 */
@Repository
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
            Iterator iterator = roleDTOs.iterator();
            while(iterator.hasNext()){
                roleDTOs.add((RoleDTO)iterator.next());
            }
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
}

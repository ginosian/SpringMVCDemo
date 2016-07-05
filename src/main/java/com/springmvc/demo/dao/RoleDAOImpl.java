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

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Collection<RoleDTO> allRoles() {
        Session session = getSession();
        try{
            List<RoleDTO> roleDTOs = session.createQuery("from RoleDTO").list();
            return roleDTOs;
        }catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public RoleDTO addRole(RoleDTO roleDTO) {
        Session session = getSession();
        try {
            session.save(roleDTO);
            return roleDTO;
        } catch (HibernateException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public RoleDTO getRole(RoleDTO roleDTO) {
        Session session = getSession();
        try{
            Query query = session.createQuery("from RoleDTO role where role.role = :role");
            String role = roleDTO.getRole();
            query.setParameter("role", role);
            List<RoleDTO> roleDTOs = query.list();
            if(roleDTOs.size() == 0) return null;
            return roleDTOs.get(0);
        }catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }
}

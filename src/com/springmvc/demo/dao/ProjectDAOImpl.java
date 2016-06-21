package com.springmvc.demo.dao;

import com.springmvc.demo.dto.ProjectDTO;
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
public class ProjectDAOImpl implements ProjectDAO {
    @Autowired
    SessionFactory sessionFactory;

    private Session openSession() {
        return sessionFactory.openSession();
    }


    @Override
    public ProjectDTO getProjectById(int id) {
        Session session = openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            Query query = openSession().createQuery("from ProjectDTO project where project.id = :id");
            query.setParameter("id", id);
            List<ProjectDTO> projectDTOList = query.list(); // TODO check if return type match , ask Lyov if why not to close session
            transaction.commit();
            if (projectDTOList.size() == 0)return null;
            return projectDTOList.get(0);
        }catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public void addProject(ProjectDTO projectDTO) {
        ProjectDTO candidate = getProjectByName(projectDTO.getStory());
        Session session = openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            if (candidate != null) {
                projectDTO.setId(candidate.getId());
                session.merge(projectDTO);
            } else {
                session.save(projectDTO);
            }
            transaction.commit();
        }catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Override
    public void modifyProjectStory(String story) {
        Session session = openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            ProjectDTO project = (ProjectDTO)session.get(ProjectDTO.class, story);
            project.setStory(story);
            session.update(project);
            transaction.commit();
        }catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Override
    public void modifyProjectDescription(String projectDTO) {
        Session session = openSession();
        Transaction transaction = null;
        try{
//            transaction = session.beginTransaction();
//            String description = projectDTO.getDescription();
//            Long id = projectDTO.getId();
//            String hql = "UPDATE ProjectDTO set description = :description "  +
//            "WHERE id = :id";
//            Query query = session.createQuery(hql);
//            query.setParameter("description", description);
//            int result = query.executeUpdate();
//
//            ProjectDTO projectDTO = (ProjectDTO)session.get(ProjectDTO.class, description);
        }catch (HibernateException e){
            if(transaction != null) transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Override
    public Collection<ProjectDTO> allProjects() {
        return null;
    }

    @Override
    public ProjectDTO getProjectByName(String name) {
        Session session = openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("from ProjectDTO project where project.name = :name");
            query.setParameter("name", name);
            List<ProjectDTO> project = query.list();
            if(project.size() == 0) return null;
            return project.get(0);
        }catch (HibernateException e){
            if(transaction != null)transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }
}

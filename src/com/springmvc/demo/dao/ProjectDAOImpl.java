package com.springmvc.demo.dao;

import com.springmvc.demo.dto.ProjectDTO;
import com.springmvc.demo.dto.TaskDTO;
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
public class ProjectDAOImpl implements ProjectDAO {
    @Autowired
    SessionFactory sessionFactory;

    private Session openSession() {
        return sessionFactory.openSession();
    }

    @Override
    public ProjectDTO getProjectById(Long id) {
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
    public ProjectDTO getProjectByStory(String story) {
        Session session = openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("from ProjectDTO project where project.story = :story");
            query.setParameter("story", story);
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

    @Override
    public void addProject(ProjectDTO projectDTO) {
        ProjectDTO candidate = getProjectByStory(projectDTO.getStory());
        Session session = openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            if (candidate != null) {
                projectDTO.setId(candidate.getId());
                session.update(projectDTO);
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
    public void modifyProject(Long id, String story, String description) {
        Session session = openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            ProjectDTO project = (ProjectDTO)session.get(ProjectDTO.class, id);
            project.setStory(story);
            project.setDescription(description);
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
    public Collection<ProjectDTO> allProjects() {
        Session session = openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            List<ProjectDTO> projects = session.createQuery("from ProjectDTO").list();
            transaction.commit();
            if(projects.size() == 0)return null;
            return projects;
        }catch (HibernateException e){
            if(transaction != null)transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }
}

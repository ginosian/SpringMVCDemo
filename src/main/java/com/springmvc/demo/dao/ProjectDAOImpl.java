package com.springmvc.demo.dao;

import com.springmvc.demo.dto.ProjectDTO;
import com.springmvc.demo.exceptions.NoSuchProjectException;
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

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public ProjectDTO getProjectById(Long id) {
        Session session = getSession();
        try{
            Query query = session.createQuery("from ProjectDTO project where project.id = :id");
            query.setParameter("id", id);
            List<ProjectDTO> projectDTOList = query.list();
            if (projectDTOList.size() == 0)throw new NoSuchProjectException();
            return projectDTOList.get(0);
        }catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ProjectDTO addProject(ProjectDTO projectDTO) {
        Session session = getSession();
        try{
            session.save(projectDTO);
            return projectDTO;
        }catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ProjectDTO modifyProject(Long id, String story, String description) {
        Session session = getSession();
        try{
            ProjectDTO project = (ProjectDTO)session.get(ProjectDTO.class, id);
            if(project == null) throw new NoSuchProjectException();
            project.setStory(story);
            project.setDescription(description);
            session.update(project);
            return project;
        }catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<ProjectDTO> allProjects() {
        Session session = getSession();
        try{
            List<ProjectDTO> projects = session.createQuery("from ProjectDTO").list();
            if(projects.size() == 0) return null;
            return projects;
        }catch (HibernateException e){
            e.printStackTrace();
        }
        return null;
    }
}

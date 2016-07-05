package com.springmvc.demo.dao;

import com.springmvc.demo.dto.TaskDTO;
import com.springmvc.demo.exceptions.NoSuchTaskException;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by Martha on 6/14/2016.
 */
@Repository
@Transactional
public class TaskDAOImpl implements TaskDAO {

    @Autowired
    SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public TaskDTO getTaskById(Long taskId) {
        Session session = getSession();
        try{
            Query query = session.createQuery("from TaskDTO task where task.id = :id");
            query.setParameter("id", taskId);
            List<TaskDTO> taskDTOLIST = query.list();
            if (taskDTOLIST.size() == 0)throw new NoSuchTaskException();
            return taskDTOLIST.get(0);
        }catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public TaskDTO addTask(TaskDTO taskDTO) {
        Session session = getSession();
        try{
            session.save(taskDTO);
            return taskDTO;
        }catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public TaskDTO modifyTask(TaskDTO taskDTO) {
        Session session = getSession();
        try{
            session.update(taskDTO);
            return taskDTO;
        }catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<TaskDTO> allTasks() {
        Session session = getSession();
        try{
            List<TaskDTO> tasks = session.createQuery("from TaskDTO").list();
            if(tasks.size() == 0) return null;
            return tasks;
        }catch (HibernateException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<TaskDTO> getTasksWithinProjects(Long projectId) {
        Session session = getSession();
        try{
            Query query = session.createQuery("select task from TaskDTO task join task.projectDTO project where project.id = :id");
            query.setParameter("id", projectId);
            List<TaskDTO> taskDTOList = query.list();
            return taskDTOList;
        }catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<TaskDTO> getTasksByUser(Long userId) {
        Session session = getSession();
        try{
            Query query = session.createQuery("select task from TaskDTO task join task.userDTO assignee where assignee.id = :id");
            query.setParameter("id", userId);
            List<TaskDTO> taskDTOList = query.list();
            return taskDTOList;
        }catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }
}

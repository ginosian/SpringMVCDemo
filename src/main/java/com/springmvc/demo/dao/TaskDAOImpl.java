package com.springmvc.demo.dao;

import com.springmvc.demo.dto.ProjectDTO;
import com.springmvc.demo.dto.TaskDTO;
import com.springmvc.demo.dto.UserDTO;
import com.springmvc.demo.exceptions.NoSuchTaskException;
import javassist.bytecode.Descriptor;
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

    private Session openSession() {
//        return sessionFactory.openSession();
        return sessionFactory.getCurrentSession();
    }

    @Override
    public TaskDTO getTaskById(Long taskId) {
        Session session = openSession();
//        Transaction transaction = null;
        try{
//            transaction = session.beginTransaction();
            Query query = openSession().createQuery("from TaskDTO task where task.id = :id");
            query.setParameter("id", taskId);
            List<TaskDTO> taskDTOLIST = query.list();
//            Hibernate.initialize(taskDTOLIST);  // If fetch type changes to LAZY this should be uncommented
//            transaction.commit();
            if (taskDTOLIST.size() == 0)throw new NoSuchTaskException();
            return taskDTOLIST.get(0);
        }catch (HibernateException e) {
//            if (transaction!=null) transaction.rollback();
            e.printStackTrace();
        }finally {
//            session.close();
        }
        return null;
    }

    @Override
    public TaskDTO addTask(TaskDTO taskDTO) {
        Session session = openSession();
//        Transaction transaction = null;
        try{
//            transaction = session.beginTransaction();
            session.save(taskDTO);
//            transaction.commit();
            return taskDTO;
        }catch (HibernateException e) {
//            if (transaction!=null) transaction.rollback();
            e.printStackTrace();
        }finally {
//            session.close();
        }
        return null;
    }

    @Override
    public TaskDTO modifyTask(TaskDTO taskDTO) {
        Session session = openSession();
//        Transaction transaction = null;
        try{
//            transaction = session.beginTransaction();
            session.update(taskDTO);
//            transaction.commit();
            return taskDTO;
        }catch (HibernateException e) {
//            if (transaction!=null) transaction.rollback();
            e.printStackTrace();
        }finally {
//            session.close();
        }
        return null;
    }

    @Override
    public Collection<TaskDTO> allTasks() {
        Session session = openSession();
//        Transaction transaction = null;
        try{
//            transaction = session.beginTransaction();
            List<TaskDTO> tasks = session.createQuery("from TaskDTO").list();
//            Hibernate.initialize(tasks);  // If fetch type changes to LAZY this should be uncommented
//            transaction.commit();
            if(tasks.size() == 0) return null;
            return tasks;
        }catch (HibernateException e){
//            if(transaction != null)transaction.rollback();
            e.printStackTrace();
        }finally {
//            session.close();
        }
        return null;
    }

    @Override
    public Collection<TaskDTO> getTasksWithinProjects(Long projectId) {
        Session session = openSession();
//        Transaction transaction = null;
        try{
//            transaction = session.beginTransaction();
            Query query = openSession().createQuery("select task from TaskDTO task join task.projectDTO project where project.id = :id");
            query.setParameter("id", projectId);
            List<TaskDTO> taskDTOList = query.list();
//            Hibernate.initialize(taskDTOList);  // If fetch type changes to LAZY this should be uncommented
//            transaction.commit();
            return taskDTOList;
        }catch (HibernateException e) {
//            if (transaction!=null) transaction.rollback();
            e.printStackTrace();
        }finally {
//            session.close();
        }
        return null;
    }

    @Override
    public Collection<TaskDTO> getTasksByUser(Long userId) {
        Session session = openSession();
//        Transaction transaction = null;
        try{
//            transaction = session.beginTransaction();
            Query query = session.createQuery("select task from TaskDTO task join task.userDTO assignee where assignee.id = :id");
            query.setParameter("id", userId);
            List<TaskDTO> taskDTOList = query.list();
//            Hibernate.initialize(taskDTOList);  // If fetch type changes to LAZY this should be uncommented
//            transaction.commit();
            return taskDTOList;
        }catch (HibernateException e) {
//            if (transaction!=null) transaction.rollback();
            e.printStackTrace();
        }finally {
//            session.close();
        }
        return null;
    }

}

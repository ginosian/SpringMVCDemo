package com.springmvc.demo.dao;

import com.springmvc.demo.dto.ProjectDTO;
import com.springmvc.demo.dto.TaskDTO;
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
public class TaskDAOImpl implements TaskDAO {

    @Autowired
    SessionFactory sessionFactory;

    private Session openSession() {
        return sessionFactory.openSession();
    }

    @Override
    public TaskDTO getTaskById(int id) {
        Session session = openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            Query query = openSession().createQuery("from TaskDTO task where task.id = :id");
            query.setParameter("id", id);
            List<TaskDTO> taskDTOLIST = query.list(); // TODO check if return type match , ask Lyov if why not to close session
            transaction.commit();
            if (taskDTOLIST.size() == 0)return null;
            return taskDTOLIST.get(0);
        }catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public void addTask(TaskDTO taskDTO) {
        Session session = openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.save(taskDTO);
            transaction.commit();
        }catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Override
    public TaskDTO reassignTask(TaskDTO taskDTO, UserDTO newAssignee) {
        return null;
    }

    @Override
    public TaskDTO markTaskAsComplete() {
        return null;
    }

    @Override
    public Collection<TaskDTO> allTasks(boolean complete) {
        return null;
    }

    @Override
    public Collection<TaskDTO> getTaskByProject(ProjectDTO projectDTO) {
        return null;
    }

    @Override
    public Collection<TaskDTO> getTaskByUser(UserDTO userDTO) {
        return null;
    }
}

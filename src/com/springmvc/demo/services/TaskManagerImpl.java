package com.springmvc.demo.services;

import com.springmvc.demo.dao.TaskDAO;
import com.springmvc.demo.dto.TaskDTO;
import com.springmvc.demo.dto.UserDTO;
import com.springmvc.demo.exceptions.EmptyRequiredValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by Martha on 6/14/2016.
 */
@Service
@Transactional
public class TaskManagerImpl implements TaskManager {

    @Autowired
    TaskDAO taskDAO;

    @Override
    public TaskDTO getTaskById(String id) {
        if (id == null || id.isEmpty()) throw new EmptyRequiredValueException();
        return taskDAO.getTaskById(Long.parseLong(id));
    }

    @Override
    public TaskDTO getTaskByStory(String story) {
        return taskDAO.getTaskByStory(story);
    }

    @Override
    public void addTask(TaskDTO taskDTO) {
        taskDAO.addTask(taskDTO);
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
        ArrayList<TaskDTO> tasks = (ArrayList<TaskDTO>)taskDAO.allTasks(complete);
        if (tasks == null) tasks = new ArrayList<>();
        return tasks;
    }

    @Override
    public Collection<TaskDTO> getTasksWithinProject(String id) {
        if(id == null || id.isEmpty()) throw new EmptyRequiredValueException();
        return taskDAO.getTasksWithinProjects(Long.parseLong(id));
    }

    @Override
    public Collection<TaskDTO> getTaskByUser(UserDTO userDTO) {
        return taskDAO.getTaskByUser(userDTO);
    }

    @Override
    public void modifyTask(TaskDTO taskDTO) {
        taskDAO.modifyTask(taskDTO);
    }

    @Override
    public HashMap<String, ArrayList<TaskDTO>> userTasks(UserDTO userDTO) {
        return taskDAO.userTasks(userDTO);
    }
}

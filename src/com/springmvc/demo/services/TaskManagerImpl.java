package com.springmvc.demo.services;

import com.springmvc.demo.dto.ProjectDTO;
import com.springmvc.demo.dto.TaskDTO;
import com.springmvc.demo.dto.UserDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by Martha on 6/14/2016.
 */
@Service
@Transactional
public class TaskManagerImpl implements TaskManager {

    @Override
    public TaskDTO getTaskById(int id) {
        return null;
    }

    @Override
    public TaskDTO addTask(TaskDTO taskDTO) {
        return null;
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

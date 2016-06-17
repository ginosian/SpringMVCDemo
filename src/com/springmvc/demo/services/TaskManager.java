package com.springmvc.demo.services;

import com.springmvc.demo.dto.ProjectDTO;
import com.springmvc.demo.dto.TaskDTO;
import com.springmvc.demo.dto.UserDTO;

import java.util.Collection;

/**
 * Created by Martha on 6/14/2016.
 */
public interface TaskManager {
    TaskDTO getTaskById(int id);
    TaskDTO addTask(TaskDTO taskDTO);
    TaskDTO reassignTask(TaskDTO taskDTO, UserDTO newAssignee);
    TaskDTO markTaskAsComplete();
    Collection<TaskDTO> allTasks(boolean complete);
    Collection<TaskDTO> getTaskByProject(ProjectDTO projectDTO);
    Collection<TaskDTO> getTaskByUser(UserDTO userDTO);
}


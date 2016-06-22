package com.springmvc.demo.dao;

import com.springmvc.demo.dto.ProjectDTO;
import com.springmvc.demo.dto.TaskDTO;
import com.springmvc.demo.dto.UserDTO;

import java.util.Collection;

/**
 * Created by Martha on 6/14/2016.
 */
public interface TaskDAO {
    TaskDTO getTaskById(long id);
    TaskDTO getTaskByStory(String story);
    void addTask(TaskDTO taskDTO);
    TaskDTO reassignTask(TaskDTO taskDTO, UserDTO newAssignee);
    TaskDTO markTaskAsComplete();
    Collection<TaskDTO> allTasks(boolean complete);
    Collection<TaskDTO> getTasksWithinProjects(ProjectDTO projectDTO);
    Collection<TaskDTO> getTaskByUser(UserDTO userDTO);
    void modifyTask(TaskDTO taskDTO);
}

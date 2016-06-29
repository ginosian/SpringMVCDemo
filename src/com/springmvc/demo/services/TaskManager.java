package com.springmvc.demo.services;

import com.springmvc.demo.dto.TaskDTO;
import com.springmvc.demo.dto.UserDTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by Martha on 6/14/2016.
 */
public interface TaskManager {
    TaskDTO getTaskById(String id);
    TaskDTO getTaskByStory(String story);
    void addTask(TaskDTO taskDTO);
    TaskDTO reassignTask(TaskDTO taskDTO, UserDTO newAssignee);
    TaskDTO markTaskAsComplete();
    Collection<TaskDTO> allTasks(boolean complete);
    Collection<TaskDTO> getTasksWithinProject(String id);
    Collection<TaskDTO> getTaskByUser(UserDTO userDTO);
    void modifyTask(TaskDTO taskDTO);
    HashMap<String, ArrayList<TaskDTO>> userTasks(UserDTO userDTO); // String stands for project story, ArrayList for tasks within project
}


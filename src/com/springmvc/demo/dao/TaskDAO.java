package com.springmvc.demo.dao;

import com.springmvc.demo.dto.TaskDTO;
import com.springmvc.demo.dto.UserDTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by Martha on 6/14/2016.
 */
public interface TaskDAO {
    TaskDTO getTaskById(long id);
    TaskDTO getTaskByStory(String story);
    void addTask(TaskDTO taskDTO);
    void modifyTask(TaskDTO taskDTO);
    TaskDTO reassignTask(TaskDTO taskDTO, UserDTO newAssignee);
    TaskDTO markTaskAsComplete();
    Collection<TaskDTO> allTasks(boolean complete);
    Collection<TaskDTO> getTasksWithinProjects(Long id);
    Collection<TaskDTO> getTaskByUser(UserDTO userDTO);
    HashMap<String, ArrayList<TaskDTO>> userTasks(UserDTO userDTO); // String stands for project story, ArrayList for tasks within project
}

package com.springmvc.demo.services;

import com.springmvc.demo.dto.TaskDTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by Martha on 6/14/2016.
 */
public interface TaskManager {
    TaskDTO getTaskById(String taskId);
    TaskDTO addOrModifyTask(String taskId, String taskStory, String taskDescription, String projectId, String userId);
    Collection<TaskDTO> allTasks();
    Collection<TaskDTO> getTasksWithinProject(String projectId);
    HashMap<String, ArrayList<TaskDTO>> userTasksMap(String userId); // String stands for project story, ArrayList for tasks within project
}


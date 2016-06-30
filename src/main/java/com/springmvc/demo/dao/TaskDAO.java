package com.springmvc.demo.dao;

import com.springmvc.demo.dto.TaskDTO;

import java.util.Collection;

/**
 * Created by Martha on 6/14/2016.
 */
public interface TaskDAO {
    TaskDTO getTaskById(Long taskId);
    TaskDTO addTask(TaskDTO taskDTO);
    TaskDTO modifyTask(TaskDTO taskDTO);
    Collection<TaskDTO> allTasks();
    Collection<TaskDTO> getTasksWithinProjects(Long projectId);
    Collection<TaskDTO> getTasksByUser(Long userId);
}

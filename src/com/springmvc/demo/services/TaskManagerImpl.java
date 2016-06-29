package com.springmvc.demo.services;

import com.springmvc.demo.dao.TaskDAO;
import com.springmvc.demo.dto.ProjectDTO;
import com.springmvc.demo.dto.TaskDTO;
import com.springmvc.demo.dto.UserDTO;
import com.springmvc.demo.exceptions.EmptyRequiredValueException;
import com.springmvc.demo.exceptions.NoSuchProjectException;
import com.springmvc.demo.exceptions.NoSuchTaskException;
import com.springmvc.demo.exceptions.NoSuchUserException;
import com.sun.istack.internal.NotNull;
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

    @Autowired
    ProjectManager projectManager;

    @Autowired
    UserManager userManager;

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
    public TaskDTO addOrModifyTask(String taskId, String taskStory, String taskDescription, String projectId, String userId) {
        if(taskStory == null || taskStory.isEmpty()
                || taskDescription == null || taskDescription.isEmpty()) throw new EmptyRequiredValueException();

        TaskDTO task;
        if (taskId != null && !taskId.isEmpty()) {
             task = modifyTask(taskId, taskStory, taskDescription, userId);
            return task;
        }else {
            if (projectId == null || projectId.isEmpty()
                    || userId == null || userId.isEmpty()) throw new EmptyRequiredValueException();

            task = addTask(taskStory, taskDescription, projectId, userId);
            return task;
        }
    }

    private TaskDTO addTask(@NotNull String taskStory, @NotNull String taskDescription, @NotNull String projectId, @NotNull String userId) {
        ProjectDTO project = projectManager.getProjectById(projectId);
        if (project == null) throw new NoSuchProjectException();

        UserDTO user = userManager.getUserById(userId);
        if (user == null) throw new NoSuchUserException();

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.set(taskStory, taskDescription, project, user);
        taskDAO.addTask(taskDTO);
        return taskDTO;
    }

    private TaskDTO modifyTask(@NotNull String taskId, @NotNull String taskStory, @NotNull String taskDescription, String newAssigneeId) {
        TaskDTO taskDTO = getTaskById(taskId);
        if (taskDTO == null) throw new NoSuchTaskException();

        if (newAssigneeId != null && !newAssigneeId.isEmpty()) {
            UserDTO assignee = userManager.getUserById(newAssigneeId);
            if(assignee == null) throw new NoSuchUserException();
            taskDTO.setUserDTO(assignee);
        }
        taskDTO.set(taskStory, taskDescription);
        taskDAO.modifyTask(taskDTO);
        return taskDTO;
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
    public HashMap<String, ArrayList<TaskDTO>> userTasks(UserDTO userDTO) {
        return taskDAO.userTasks(userDTO);
    }
}

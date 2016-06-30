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

import java.util.*;

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
    public TaskDTO addOrModifyTask(String taskId, String taskStory, String taskDescription, String projectId, String userId) {
        if(taskStory == null || taskStory.isEmpty()
                || taskDescription == null || taskDescription.isEmpty()) throw new EmptyRequiredValueException();
        TaskDTO task;
        if (taskId != null && !taskId.isEmpty()){
                task = modifyTask(taskId, taskStory, taskDescription, userId);
        } else {
            if (projectId == null || projectId.isEmpty()
                        || userId == null || userId.isEmpty()) throw new EmptyRequiredValueException();
            task = addTask(taskStory, taskDescription, projectId, userId);
        }
            return task;
    }

    private TaskDTO addTask(@NotNull String taskStory, @NotNull String taskDescription, @NotNull String projectId, @NotNull String userId) {
        ProjectDTO project = projectManager.getProjectById(projectId);
        UserDTO user = userManager.getUserById(userId);
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.set(taskStory, taskDescription, project, user);
        taskDAO.addTask(taskDTO);
        return taskDTO;
    }

    private TaskDTO modifyTask(@NotNull String taskId, @NotNull String taskStory, @NotNull String taskDescription, String newAssigneeId) {
        TaskDTO taskDTO = getTaskById(taskId);
        if (newAssigneeId != null && !newAssigneeId.isEmpty()) {
            taskDTO.setUserDTO(userManager.getUserById(newAssigneeId));
        }
        taskDTO.set(taskStory, taskDescription);
        taskDAO.modifyTask(taskDTO);
        return taskDTO;
    }

    @Override
    public Collection<TaskDTO> allTasks() {
        return taskDAO.allTasks();
    }

    @Override
    public Collection<TaskDTO> getTasksWithinProject(String projectId) {
        if(projectId == null || projectId.isEmpty()) throw new EmptyRequiredValueException();
        if(projectManager.getProjectById(projectId) == null) throw new NoSuchProjectException();
        return taskDAO.getTasksWithinProjects(Long.parseLong(projectId));
    }

    @Override
    public HashMap<String, ArrayList<TaskDTO>> userTasksMap(String userId) {
        if(userManager.getUserById(userId)== null) throw new NoSuchUserException();
        ArrayList<TaskDTO> allTasks;
        HashMap<String, ArrayList<TaskDTO>> userTasksByProject = new HashMap<>();
        try{
            allTasks = (ArrayList<TaskDTO>)taskDAO.getTasksByUser(Long.parseLong(userId));
        } catch (NoSuchTaskException e){
            e.printStackTrace();
            return userTasksByProject;
        }

        Set<Long> projectsId = new HashSet<>();
        for (TaskDTO task : allTasks){
            projectsId.add(task.getProjectDTO().getId());
        }
        Iterator iterator = projectsId.iterator();
        ArrayList<TaskDTO> tempTaskList = new ArrayList<>();
        while (iterator.hasNext()){
            Long tempId = (Long)iterator.next();
            String projectStory = null;
            for (int i = 0; i < allTasks.size(); i++) {
                ProjectDTO tempProject = allTasks.get(i).getProjectDTO();
                if(tempProject.getId().equals(tempId)){
                    tempTaskList.add(allTasks.get(i));
                    projectStory = tempProject.getStory();
                }
            }
            ArrayList<TaskDTO> taskList = new ArrayList<>();
            taskList.addAll(tempTaskList);
            userTasksByProject.put(projectStory, taskList);
            tempTaskList.clear();
        }
        return userTasksByProject;
    }
}

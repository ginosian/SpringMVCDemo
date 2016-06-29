package com.springmvc.demo.utils;

import com.springmvc.demo.dto.ProjectDTO;
import com.springmvc.demo.dto.RoleDTO;
import com.springmvc.demo.dto.TaskDTO;
import com.springmvc.demo.dto.UserDTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Martha on 6/27/2016.
 */
public class DTOMockUtils {

    public static ArrayList<UserDTO> generateUsersList(int quantity){
        ArrayList<UserDTO> result = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            Set<RoleDTO> roles = new HashSet<>();
            roles.add(new RoleDTO("USER"));
            UserDTO user = new UserDTO((i + "@test.com"), "aa", ("TestUser" + i), true, roles);
            user.setId((long) i + 1);
            result.add(user);
        }
        return result;
    }

    public static ArrayList<TaskDTO> generateTasksList(int quantity){
        ArrayList<TaskDTO> result = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            UserDTO user = generateUser();
            ProjectDTO project = generateProject();
            TaskDTO task = new TaskDTO((i + "TestTaskStory"), ("TestTaskDescription" + i), true, project, user);
            task.setId((long) i + 1);
            result.add(task);
        }
        return result;
    }

    public static TaskDTO generateTask(){
        UserDTO user = generateUser();
        ProjectDTO project = generateProject();
        TaskDTO task = new TaskDTO("TestTaskStory", "TestTaskDescription", true, project, user);
        task.setId(1L);
        return task;
    }

    public static ArrayList<ProjectDTO> generateProjectList(int quantity){
        ArrayList<ProjectDTO> result = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            ProjectDTO project = new ProjectDTO((i + "TestProjectStory"), ("TestProjectDescription" + i));
            project.setId((long) i + 1);
            result.add(project);
        }
        return result;
    }

    public static ArrayList<RoleDTO> generateRoleList(int quantity){
        ArrayList<RoleDTO> result = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            RoleDTO role = new RoleDTO("TEST_ROLE");
            result.add(role);
        }
        return result;
    }

    public static UserDTO generateUser(){
        Set<RoleDTO> roles = new HashSet<>();
        roles.add(new RoleDTO("USER"));
        UserDTO user = new UserDTO("test@test.com", "aa", "test", true, roles);
        user.setId((long)1);
        return user;
    }

    public static ProjectDTO generateProject(){
        ProjectDTO project = new ProjectDTO();
        project.set("Test project", "Test project description");
        project.setId((long)1);
        return project;
    }


}

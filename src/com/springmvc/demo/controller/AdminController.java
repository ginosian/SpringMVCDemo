package com.springmvc.demo.controller;

import com.springmvc.demo.dto.ProjectDTO;
import com.springmvc.demo.dto.RoleDTO;
import com.springmvc.demo.dto.TaskDTO;
import com.springmvc.demo.dto.UserDTO;
import com.springmvc.demo.services.ProjectManager;
import com.springmvc.demo.services.TaskManager;
import com.springmvc.demo.services.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * Created by Martha on 6/20/2016.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    ProjectManager projectManager;

    @Autowired
    TaskManager taskManager;

    @Autowired
    UserManager userManager;


    // region Home
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView adminHome(){
        RoleDTO admin = new RoleDTO();
        RoleDTO user = new RoleDTO();
        admin.set("ADMIN");
        user.set("USER");
        userManager.addRole(admin);
        userManager.addRole(user);

        ModelAndView modelAndView = new ModelAndView();

        List<UserDTO> users = new ArrayList<>();
        List<UserDTO> dbUsersList = (List<UserDTO>)userManager.allUsersByRole(user);
        if(dbUsersList != null){
            users.addAll(dbUsersList);
            modelAndView.addObject("users", users);
        }

        List<ProjectDTO> projects = new ArrayList<>();
        List<ProjectDTO> dbProjectsList = (List<ProjectDTO>)projectManager.allProjects();
        if(dbProjectsList != null){
            projects.addAll(dbProjectsList);
            modelAndView.addObject("projects", projects);
        }

        List<TaskDTO> tasks = new ArrayList<>();
        List<TaskDTO> dbTasksList = (List<TaskDTO>)taskManager.allTasks(true);
        if(dbTasksList != null){
            tasks.addAll(dbTasksList);
            modelAndView.addObject("tasks", tasks);
        }

        modelAndView.setViewName("admin_page");
        return modelAndView;
    }
    // endregion

    // region User
    @RequestMapping(value = "/create_user", method = RequestMethod.GET)
    public ModelAndView createUser(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("roles", userManager.allRoles());

        modelAndView.setViewName("create_user");
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(@ModelAttribute("username")String username,
                           @ModelAttribute("name") String name,
                           @ModelAttribute("password") String password,
                           @ModelAttribute("role") String role){
        UserDTO userDTO = new UserDTO();
        Set<RoleDTO> roles = new HashSet<>();
        roles.add(userManager.getRoleByName(role));
        userDTO.set(username, password, name,
                true, roles);
        userManager.addUser(userDTO);
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("redirect:/admin?success=true");
        return modelAndView;
    }
    // endregion

    // region Project
    @RequestMapping(value = "/create_project", method = RequestMethod.GET)
    public String createProject(){
        return "create_project";
    }

    @RequestMapping(value = "/add_project", method = RequestMethod.POST)
    public ModelAndView addProject(@ModelAttribute("project_story") String projectStory,
                                @ModelAttribute("project_description") String projectDescription){
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.set(projectStory, projectDescription);
        projectManager.addProject(projectDTO);
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("redirect:/admin?success=true");
        return modelAndView;
    }
    // endregion

    // region Task

    @RequestMapping(value = "/create_task", method = RequestMethod.GET)
    public ModelAndView createTask(){
        ModelAndView modelAndView = new ModelAndView();
        RoleDTO user = new RoleDTO();
        user.set("USER");
        modelAndView.addObject("projects", projectManager.allProjects());
        modelAndView.addObject("users", userManager.allUsersByRole(user));

        modelAndView.setViewName("create_task");
        return modelAndView;
    }

    @RequestMapping(value = "/add_task", method = RequestMethod.POST)
    public ModelAndView addTask(@ModelAttribute("task_story") String taskStory,
                                @ModelAttribute("task_description") String taskDescription,
                                @ModelAttribute("users") String assignee,
                                @ModelAttribute("projects") String project){
        TaskDTO taskDTO = new TaskDTO();
        UserDTO userDTO = userManager.getUserByName(assignee);
        ProjectDTO projectDTO = projectManager.getProjectByStory(project);
        taskDTO.set(taskStory, taskDescription, projectDTO, userDTO);
        taskManager.addTask(taskDTO);
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("redirect:/admin?success=true");
        return modelAndView;
    }

    @RequestMapping(value = "/task_detail", method = RequestMethod.GET)
    private ModelAndView taskDetail(@ModelAttribute("story") String taskStory){
        ModelAndView modelAndView = new ModelAndView();

        RoleDTO user = new RoleDTO();
        user.set("USER");

        TaskDTO task = taskManager.getTaskByStory(taskStory);
        String project = task.getProjectDTO().getStory();
        String taskDescription = task.getDescription();
        String assignee = task.getUserDTO().getName();
        String taskId = task.getId().toString();
        Collection<UserDTO> users = userManager.allUsersByRole(user);
        modelAndView.addObject("project", project);
        modelAndView.addObject("assignee", assignee);
        modelAndView.addObject("task_story", taskStory);
        modelAndView.addObject("task_description", taskDescription);
        modelAndView.addObject("users", users);
        modelAndView.addObject("task_id", taskId);
        modelAndView.setViewName("admin_task_detail");

        return modelAndView;
    }

    @RequestMapping(value = "/modify_task", method = RequestMethod.POST)
    public ModelAndView modifyTask(@ModelAttribute("task_story") String taskStory,
                                   @ModelAttribute("user") String newAssignee,
                                   @ModelAttribute("task_description") String task_description,
                                   @ModelAttribute("assignee") String previousAssignee,
                                   @ModelAttribute("task_id") String id){
        TaskDTO taskDTO = taskManager.getTaskById(Long.parseLong(id));
        UserDTO assignee = null;
        if(newAssignee == null || newAssignee.isEmpty()){
            assignee = userManager.getUserByName(previousAssignee);
        } else {assignee = userManager.getUserByName(newAssignee);}
        taskDTO.set(taskStory, task_description, assignee);
        taskManager.modifyTask(taskDTO);
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("redirect:/admin?success=true");
        return modelAndView;
    }


    // endregion

    @RequestMapping("/user_detail")
    public ModelAndView userDetail(){
        return null;
    }


}

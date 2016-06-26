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

import javax.servlet.http.HttpServletRequest;
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
    // region corrected
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
        modelAndView.addObject("user_detail_resource", "user_detail");
        modelAndView.addObject("create_user_resource", "create_user");

        List<ProjectDTO> projects = new ArrayList<>();
        List<ProjectDTO> dbProjectsList = (List<ProjectDTO>)projectManager.allProjects();
        if(dbProjectsList != null){
            projects.addAll(dbProjectsList);
            modelAndView.addObject("projects", projects);
        }
        modelAndView.addObject("project_detail_resource", "project_detail");
        modelAndView.addObject("create_project_resource", "create_project");

        List<TaskDTO> tasks = new ArrayList<>();
        List<TaskDTO> dbTasksList = (List<TaskDTO>)taskManager.allTasks(true);
        if(dbTasksList != null){
            tasks.addAll(dbTasksList);
            modelAndView.addObject("tasks", tasks);
        }
        modelAndView.addObject("task_detail_resource", "task_detail");
        modelAndView.addObject("create_task_resource", "create_task");
        modelAndView.addObject("redirect_modify_task_to", "");

        modelAndView.addObject("home", "admin");
        modelAndView.setViewName("admin_page");
        return modelAndView;
    }
    // endregion


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

    // region Done
    @RequestMapping(value = "/project_detail", method = RequestMethod.GET)
    public ModelAndView projectDetail(@ModelAttribute("projectId") String projectId,
                                      @ModelAttribute("home") String home){
        ModelAndView modelAndView = new ModelAndView();

        ProjectDTO project = projectManager.getProjectById(Long.parseLong(projectId));
        Collection<TaskDTO> tasks = taskManager.getTasksWithinProject(project);

        modelAndView.addObject("project", project);
        modelAndView.addObject("project_tasks", tasks);


        modelAndView.addObject("task_detail_resource", "task_detail");
        modelAndView.addObject("create_task_resource", "create_task");

        modelAndView.addObject("home", home);
        modelAndView.addObject("modify", "modify_project");
        modelAndView.addObject("redirect_modify_to", "");
        modelAndView.addObject("redirect_modify_task_to", "/project_detail");
        modelAndView.setViewName("project_detail");
        return modelAndView;
    }

    @RequestMapping(value = "/create_project", method = RequestMethod.GET)
    public ModelAndView createProject(@ModelAttribute("home") String home){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("home", home);
        modelAndView.addObject("modify", "modify_project");
        modelAndView.addObject("redirect_modify_to", "");

        modelAndView.setViewName("create_project");

        return modelAndView;
    }

    @RequestMapping(value = "/modify_project", method = RequestMethod.POST)
    public ModelAndView modifyProject(@ModelAttribute("project_story") String projectStory,
                                      @ModelAttribute("project_description") String project_description,
                                      @ModelAttribute("projectId") String id,
                                      @ModelAttribute("home") String home,
                                      @ModelAttribute("redirect_modify_to") String redirect_modify_to){
        ModelAndView modelAndView = new ModelAndView();

        if(id == null || id.isEmpty()){
            ProjectDTO newProject = new ProjectDTO();
            newProject.set(projectStory, project_description);
            projectManager.addProject(newProject);
        } else {
            Long projectId = Long.parseLong(id);
            projectManager.modifyProject(projectId, projectStory, project_description);
        }
        modelAndView.setViewName("redirect:/" + home + redirect_modify_to + "?success=true");
        return modelAndView;
    }

    // endregion


    // endregion

    // region Task


    // region Done
    @RequestMapping(value = "/task_detail", method = RequestMethod.GET)
    public ModelAndView taskDetail(@ModelAttribute("taskId") String taskId,
                                   @ModelAttribute("home") String home,
                                   @ModelAttribute("redirect_modify_task_to") String redirect_modify_task_to){

        ModelAndView modelAndView = new ModelAndView();

        RoleDTO user = new RoleDTO();
        user.set("USER");

        TaskDTO task = taskManager.getTaskById(Long.parseLong(taskId));
        Collection<UserDTO> users = userManager.allUsersByRole(user);


        modelAndView.addObject("users", users);
        modelAndView.addObject("task", task);

        if(redirect_modify_task_to == null || redirect_modify_task_to.isEmpty()){
            modelAndView.addObject("redirect_modify_to", "");
        } else {modelAndView.addObject("redirect_modify_to", redirect_modify_task_to);}
        modelAndView.addObject("home", home);
        modelAndView.addObject("modify", "modify_task");
        modelAndView.setViewName("task_detail");
        return modelAndView;
    }

    @RequestMapping(value = "/create_task", method = RequestMethod.GET)
    public ModelAndView createTask(@ModelAttribute("projectId") String projectId,
                                   @ModelAttribute("home") String home,
                                   @ModelAttribute("redirect_modify_task_to") String redirect_modify_task_to){

        ModelAndView modelAndView = new ModelAndView();

        RoleDTO user = new RoleDTO();
        user.set("USER");

        Collection<UserDTO> users = userManager.allUsersByRole(user);
        modelAndView.addObject("users", users);

        if(projectId == null || projectId.isEmpty()){
            Collection<ProjectDTO> projects = projectManager.allProjects();
            modelAndView.addObject("projects", projects);
            modelAndView.setViewName("create_task");

        } else {
            ProjectDTO project = projectManager.getProjectById(Long.parseLong(projectId));
            modelAndView.addObject("project", project);
            modelAndView.setViewName("create_task_from_project");
        }

        if(redirect_modify_task_to == null || redirect_modify_task_to.isEmpty()){
            modelAndView.addObject("redirect_modify_to", "");
        } else {modelAndView.addObject("redirect_modify_to", redirect_modify_task_to);}
        modelAndView.addObject("home", home);
        modelAndView.addObject("modify", "modify_task");

        return modelAndView;
    }

    @RequestMapping(value = "/modify_task", method = RequestMethod.POST)
    public ModelAndView modifyTask(HttpServletRequest request,
                                   Map<String, String[]> values,
                                   @ModelAttribute("task_story") String taskStory,
                                   @ModelAttribute("task_description") String taskDescription,
                                   @ModelAttribute("taskId") String taskId,
                                   @ModelAttribute("projectId") String projectId,
                                   @ModelAttribute("userId") String newAssignee,
                                   @ModelAttribute("redirect_modify_to") String redirect_modify_to,
                                   @ModelAttribute("home") String home){

        UserDTO assignee;
        if(taskId == null || taskId.isEmpty()){
            TaskDTO newTask = new TaskDTO();
            ProjectDTO tasksProject = projectManager.getProjectById(Long.parseLong(projectId));
            assignee = userManager.getUserById(Long.parseLong(newAssignee));
            newTask.set(taskStory, taskDescription, tasksProject, assignee);
            taskManager.addTask(newTask);
        } else{
            TaskDTO taskDTO = taskManager.getTaskById(Long.parseLong(taskId));
            if(newAssignee == null || newAssignee.isEmpty()){
                assignee = userManager.getUserByName(taskDTO.getUserDTO().getName());
            } else {assignee = userManager.getUserById(Long.parseLong(newAssignee));}
            taskDTO.set(taskStory, taskDescription, assignee);
            taskManager.modifyTask(taskDTO);
        }

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("redirect:/" + home + redirect_modify_to + "?success=true&projectId=" + projectId + "&userId=" + assignee.getId() + "&home=" + home);
        return modelAndView;
    }

    // endregion

    // endregion

    // region User
    @RequestMapping("/user_detail")
    public ModelAndView userDetail(@ModelAttribute("userId") String userId,
                                   @ModelAttribute("home") String home){
        ModelAndView modelAndView = new ModelAndView();

        UserDTO user = userManager.getUserById(Long.parseLong(userId));
        HashMap<String, ArrayList<TaskDTO>>  map = taskManager.userTasks(user);

        modelAndView.addObject("user", user);
        modelAndView.addObject("map", map);
        modelAndView.addObject("task_detail_resource", "task_detail");
        modelAndView.addObject("button_label", "SUBMIT");


        modelAndView.addObject("redirect_modify_task_to", "/user_detail");
        modelAndView.addObject("home", home);

        modelAndView.setViewName("user_detail");
        return modelAndView;
    }


    // endregion


}

package com.springmvc.demo.controller;

import com.springmvc.demo.dto.ProjectDTO;
import com.springmvc.demo.dto.RoleDTO;
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView adminHome(){
        RoleDTO admin = new RoleDTO();
        RoleDTO user = new RoleDTO();
        admin.set("ADMIN");
        user.set("USER");
        userManager.addRole(admin);
        userManager.addRole(user);

        List users = new ArrayList<>();
        users.addAll(userManager.allUsersByRole(user));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", users);
        modelAndView.setViewName("admin_page");
        return modelAndView;
    }

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

    @RequestMapping(value = "/create_project", method = RequestMethod.GET)
    public String createProject(){
        return "create_project";
    }

    @RequestMapping(value = "/add_project")
    public String addProject(@ModelAttribute("project_story") String projectStory,
                                @ModelAttribute("project_description") String projectDescription){
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.set(projectStory, projectDescription);
        projectManager.addProject(projectDTO);
        return "admin_page";
    }

    @RequestMapping("/user_detail")
    public ModelAndView userDetail(){
        return null;
    }
}

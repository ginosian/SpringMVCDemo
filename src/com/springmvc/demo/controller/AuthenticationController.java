package com.springmvc.demo.controller;

import com.springmvc.demo.dto.RoleDTO;
import com.springmvc.demo.dto.UserDTO;
import com.springmvc.demo.services.ProjectManager;
import com.springmvc.demo.services.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Martha on 6/14/2016.
 */
@Controller
public class AuthenticationController {

    @Autowired
    UserManager userManager;

    @Autowired
    ProjectManager projectManager;


    @RequestMapping("/")
    public String home() {
        return "login_page";
    }

    @RequestMapping("/tuft")
     public ModelAndView test(@RequestParam("role") String role){
//        ProjectDTO project = new ProjectDTO();
//        project.set("bla", "mla");
//        projectManager.addProject(project);
//        return new ModelAndView("admin_page");

        UserDTO userDTO = new UserDTO();
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.set(role);
        userDTO.set("root", "aa", "Vle", roleDTO);
        userManager.addRole(roleDTO);
        userManager.addUser(userDTO);


        ModelAndView modelAndView = new ModelAndView("project_admin_page");
        return modelAndView;
    }

    @RequestMapping("/muft")
    public ModelAndView testmuft(@RequestParam("id") long id){
        UserDTO user = userManager.getUserById(id);
        ModelAndView modelAndView = new ModelAndView("project_admin_page");
        modelAndView.addObject("id", id);
        modelAndView.addObject("name", user.getName());
        modelAndView.addObject("role", user.getRoleDTO().getRole());
        return modelAndView;
    }

}


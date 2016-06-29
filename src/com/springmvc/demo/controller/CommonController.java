package com.springmvc.demo.controller;

import com.springmvc.demo.dto.RoleDTO;
import com.springmvc.demo.dto.TaskDTO;
import com.springmvc.demo.dto.UserDTO;
import com.springmvc.demo.services.ProjectManager;
import com.springmvc.demo.services.TaskManager;
import com.springmvc.demo.services.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by Martha on 6/24/2016.
 */
@Controller
@RequestMapping("/common")
public class CommonController {
    @Autowired
    ProjectManager projectManager;

    @Autowired
    TaskManager taskManager;

    @Autowired
    UserManager userManager;

    @Autowired
    Environment environment;

    @RequestMapping(value = "")
    public ModelAndView userDetail(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username

        RoleDTO user = new RoleDTO();
        user.set("USER");

        UserDTO userDTO = userManager.getUserByUsername(name);
        String userId = userDTO.getId().toString();
        HashMap<String, ArrayList<TaskDTO>> map = taskManager.userTasks(userDTO);

        modelAndView.addObject("userId", userId);
        modelAndView.addObject("map", map);
        modelAndView.addObject("home", "common");
        modelAndView.addObject("redirect_modify_to", "");

        modelAndView.setViewName("user_detail");
        return modelAndView;
    }

    @RequestMapping(value = "/task_detail", method = RequestMethod.GET)
    public ModelAndView taskDetail(@ModelAttribute("taskId") String taskId,
                                   @ModelAttribute("home") String home){

        ModelAndView modelAndView = new ModelAndView();

        TaskDTO task = taskManager.getTaskById(taskId);
        Collection<UserDTO> users = userManager.allUsersByRole(environment.getProperty("role_user"));


        modelAndView.addObject("users", users);
        modelAndView.addObject("task", task);

        modelAndView.addObject("home", home);
        modelAndView.addObject("modify", "modify_task");
        modelAndView.setViewName("task_detail");
        return modelAndView;
    }
}

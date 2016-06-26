package com.springmvc.demo.controller;

import com.springmvc.demo.dto.RoleDTO;
import com.springmvc.demo.dto.UserDTO;
import com.springmvc.demo.services.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Martha on 6/20/2016.
 */
@Controller
@RequestMapping("")
public class AuthController {

    @Autowired
    UserManager userManager;


    @RequestMapping(value = "")
    public String firstPage(){
        RoleDTO admin = new RoleDTO();
        admin.set("ADMIN");
        userManager.addRole(admin);

        UserDTO user = new UserDTO();
        Set<RoleDTO> roles = new HashSet<>();
        roles.add(admin);
        user.set("a@a.com", "aa", "aaa", true, roles);
        userManager.addUser(user);

       return "redirect:/login";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request){
        new SecurityContextLogoutHandler().logout(request, null, null);
        return "redirect:/login";
    }


//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
//                                @RequestParam(value = "logout", required = false) String logout) {
//        RoleDTO admin = new RoleDTO();
//        RoleDTO user = new RoleDTO();
//        admin.set("ADMIN");
//        user.set("USER");
//        userManager.addRole(admin);
//        userManager.addRole(user);
//        ModelAndView model = new ModelAndView();
//        if (error != null) {
//            model.addObject("error", "Invalid username and password!");
//        }
//        if (logout != null) {
//            model.addObject("msg", "You've been logged out successfully.");
//        }
//        model.setViewName("login");
//        return model;
//    }

}
